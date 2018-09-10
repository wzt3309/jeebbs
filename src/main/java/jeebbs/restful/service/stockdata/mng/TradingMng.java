package jeebbs.restful.service.stockdata.mng;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import jeebbs.restful.service.stockdata.model.*;
import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static jeebbs.restful.service.stockdata.model.StockDataConstant.*;
/**
 * Created by ztwang on 2017/7/18 0018.
 */
public final class TradingMng {
    private static final Logger LOG = LoggerFactory.getLogger(TradingMng.class);
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss");
    private static final Map<String, String> TRADE_DAY_TABLE = new HashMap<>();
    private static final String CGW_URL = "http://ddx.gubit.cn/xg/ddxlist.php";//查股网，根据股票代码获取股票信息 yth
    private static final String CGW_REFERER = "http://ddx.gubit.cn/xg/ddx.html";//查股网发送头部，因为不能跨域访问 yth

    static {
        initTradeDayTable();
    }
    private TradingMng() {}

    /**
     * 获得某个股票历史日K线数据
     * @param code 股票代码
     * @param start 起始日期 yyyy-MM-dd
     * @param end 结束日期 yyyy-MM-dd
     * @param pageNum 页码
     * @param pageSize 每页显示记录数
     * @param sort 以StockDaily的哪个属性进行排序
     * @param asc 是否升序排列
     * @return 分页结果
     */
    public static PageInfo<StockDaily> getHistoryData(String code, String start, String end,
                                                      int pageNum, int pageSize, String sort, boolean asc) {
        String symbol = codeToSymbol(code);
        String url = String.format(DAY_PRICE_URL, getPType("http"), getDomains("ifeng"), getKType("D"), symbol);
        String json = HttpUtil.sendGET(url);
        // no data
        if (StringUtils.isBlank(json) || json.length() < 15) return null;
        Map jsonMap = JacksonUtil.json2Map(json);
        List<List<String>> dataList = (List<List<String>>) jsonMap.get("record");
        List<StockDaily> res = new ArrayList<>();

        if (!CollectionUtils.isEmpty(dataList)) {
            if (!StringUtils.isBlank(start)) {
                try {
                    final Date startDate = SDF_DATE.parse(start);
                    dataList = dataList.stream().filter(data -> {
                        try {
                            return startDate.before(SDF_DATE.parse(data.get(0)));
                        } catch (ParseException e) {
                            //ignore
                        }
                        return true;
                    }).collect(Collectors.toList());
                } catch (ParseException e) {
                    //ignore
                }
            }

            if (!StringUtils.isBlank(end)) {
                try {
                    final Date endDate = SDF_DATE.parse(end);
                    dataList = dataList.stream().filter(data -> {
                        try {
                            return endDate.after(SDF_DATE.parse(data.get(0)));
                        } catch (ParseException e) {
                            //ignore
                        }
                        return true;
                    }).collect(Collectors.toList());
                } catch (ParseException e) {
                    //ignore
                }
            }

            for (List<String> data: dataList) {
                StockDailyBuilder stockDailyBuilder = StockDailyBuilder.newBuilder(code);
                Date date = null;
                try {
                    date = SDF_DATE.parse(data.get(0));
                } catch (ParseException e) {
                    //ignore
                }
                try {
                    ArrayList<BigDecimal> resultList=getTradeAndTratio(code,data.get(0));//根据股票代码和日期获取现价和换手率，此处只用到换手率

                    BigDecimal[] values = data.stream().skip(1)
                            .map(n -> new BigDecimal(n.replaceAll(",", "")))
                            .collect(Collectors.toList()).toArray(new BigDecimal[0]);

                    stockDailyBuilder.setDate(java.sql.Date.valueOf(data.get(0)))
                            .setOpen(values[0])
                            .setHigh(values[1])
                            .setClose(values[2])
                            .setLow(values[3])
                            .setVolume(values[4])
                            .setPrice_change(values[5])
                            .setP_change(values[6])
                            .setMa5(values[7])
                            .setMa10(values[8])
                            .setMa20(values[9])
                            .setV_ma5(values[10])
                            .setV_ma10(values[11])
                            .setV_ma20(values[12])
                            .setTurnover(resultList.get(1));//换手率


                    res.add(stockDailyBuilder.build());
                }catch (Exception e) {
                    LOG.error("Parse StockDaily Error Because %s");
                }
            }

        }
        int total = res.size();
        Comparator<StockDaily> comparator = StockUtil.getComparator(sort, StockDaily.class);
        Collections.sort(res, comparator);
        if (!asc) Collections.reverse(res);
        return list2Page(res, total, pageNum, pageSize, sort, asc);
    }

    /**
     * 获取当日股票交易数据
     * @param pageNum 页码
     * @param pageSize 每页显示记录数
     * @param sort 以StockDaily的哪个属性进行排序
     * @param asc 是否升序排列
     * @return 分页结果
     */
    public static PageInfo<StockTrade> getTodayAll(int pageNum, int pageSize, String sort, boolean asc) {
        sort = StringUtils.isBlank(sort) ? "code" : sort;
        int total = getTodayAllNum();
        List<StockTrade> stockTradeList = parseDayPriceJson(pageNum, pageSize, sort, asc);

        return list2Page(stockTradeList, total, pageNum, pageSize, sort, asc);
    }

    /**
     * 获取当日股票总数
     * @return 股票总数
     */
    public static int getTodayAllNum() {
        int pageNum = 41;
        int pageSize = 80;
        int total = (pageNum - 1) * pageSize;
        List<StockTrade> last = parseDayPriceJson(pageNum, pageSize, null, true);
        int lastSize = last != null ? last.size() : 0;
        while (lastSize >= pageSize) {
            total += lastSize;
            last = parseDayPriceJson(++pageNum, pageSize, null, true);
            lastSize = last != null ? last.size() : 0;
        }
        total += lastSize;
        return  total;
    }

    /**
     * 根据股票名称，模糊查找股票代码
     * @param input 股票近似名
     * @return 匹配的所有股票
     * [{code=<股票代码>,label=<股票拼音首字母简写>,name=<股票确切名称>}]
     */
    public static List<Map<String, String>> searchCodeByName(String input) {
        List<Map<String, String>> res = null;
        String url;
        String text;
        int start, end;

        if (StringUtils.isBlank(input)) return null;
        url = String.format(CODE_SEARCH_URL,
                            getPType("http"),
                            getDomains("em"),
                            input);
        text = HttpUtil.sendGET(url);
        if (StringUtils.isBlank(text)) return null;
        start = text.indexOf("\"");
        end = text.lastIndexOf("\"");
        if (start >= 0 && start < end) {
            String content = text.substring(start + 1, end);
            if (StringUtils.isBlank(content)) return null;
            String[] searchArr = content.split(";");
            res = new ArrayList<>();
            for (String record: searchArr) {
                if (!StringUtils.isBlank(record)) {
                    String[] recordArr = record.split(",");
                    if (recordArr.length >= 7) {
                        String label = recordArr[0];
                        String code = recordArr[1];
                        String name = recordArr[4];
                        if (!StringUtils.isAnyBlank(label, code, name)) {
                            Map<String, String> map = new HashMap<>();
                            map.put("code", code);
                            map.put("label", label);
                            map.put("name", name);
                            res.add(map);
                        }
                    }
                }
            }
        }

        return CollectionUtils.isEmpty(res) ? null : res;
    }

    /**
     * 获取股票龙虎榜
     * @param date 日期
     * @return 龙虎榜数据
     */
    public static List<StockTop> getTopList(String date) {
        Calendar now = Calendar.getInstance();
        if (StringUtils.isEmpty(date)) {
            int nowHour = now.get(Calendar.HOUR_OF_DAY);
            if (nowHour < 18) {
                //获取最近一个交易日的日期
                date = lastTradeDay();
            }else {
                date = SDF_DATE.format(now.getTime());
            }
        } else if (isHoliday(date)) {
            return null;
        }
        String url = String.format(LHB_URL, getPType("http"), getDomains("em"), date, date);
        String content = HttpUtil.sendGET(url);
        if (StringUtils.isBlank(content)) return null;
        String json = content.substring(content.indexOf("{"));
        Map<String, Object> jsonMap = JacksonUtil.json2Map(json);
        if (CollectionUtils.isEmpty(jsonMap)) return null;
        List dataList = (List) jsonMap.get("data");
        List<StockTop> res = new ArrayList<>();
        for (Object data: dataList) {
            try {
                Map<String, String> map = (Map<String, String>) data;
                StockTop stockTop = new StockTop();
                stockTop.setCode(map.get("SCode"));
                stockTop.setName(map.get("SName"));
                stockTop.setPchange(new BigDecimal(map.get("Chgradio")));
                stockTop.setAmount(new BigDecimal(map.get("ZeMoney")));
                stockTop.setBuy(new BigDecimal(map.get("Bmoney")));
                stockTop.setSell(new BigDecimal(map.get("Smoney")));
                stockTop.setReason(map.get("Ctypedes"));
                BigDecimal turnover = new BigDecimal(map.get("Turnover"));//总成交额
                stockTop.setBrati(stockTop.getBuy().divide(turnover, 3, BigDecimal.ROUND_DOWN));
                stockTop.setSratio(stockTop.getSell().divide(turnover, 3, BigDecimal.ROUND_DOWN));
                stockTop.setDate(new java.sql.Date(SDF_DATE.parse(date).getTime()));
                //利用查股网根据股票代码获取股票信息 yth
                String strCode=map.get("SCode");//股票代码
                ArrayList<BigDecimal>  resultList=getTradeAndTratio(strCode,"");//查询现价和换手率，最新数据
                stockTop.setTrade(resultList.get(0));//现价
                stockTop.setTurnoverratio(resultList.get(1));//换手率
                //-----
                res.add(stockTop);
            } catch (NumberFormatException e) {
                //ignore
            } catch (ParseException e) {
                //ignore
            } catch (Exception e) {
                //ignore
                String s=e.getMessage();
            }
        }

        return res;
    }

    /*
        根据股票代码和日期查询股票的现价和换手率 yth
     */
    private static ArrayList<BigDecimal> getTradeAndTratio(String stockCode,String date) {
        Map<String, String> headers = new HashMap<>();//头部
        headers.put("Referer", CGW_REFERER);

        Map<String, String> params = new HashMap<>();//参数
        params.put("stockid", stockCode);
        params.put("getlsdate", "1");
        //如果日期不为空，则根据日期查询，历史数据
        //日期为空，则查询的是最新数据
        if(!date.equals("")) {
            params.put("lsdate", date);
        }
        String returnJson = HttpUtil.sendGET(CGW_URL, params, headers);//请求返回json
        Map<String, Object> json2Map = JacksonUtil.json2Map(returnJson);
        List result = (List) json2Map.get("data");
        List resultDataList=(List)result.get(0);
        BigDecimal trade=new BigDecimal(resultDataList.get(1).toString());//现价
        BigDecimal turnoverRate=new BigDecimal(resultDataList.get(3).toString()).divide(new BigDecimal(100), 4, BigDecimal.ROUND_DOWN);//换手率

        ArrayList<BigDecimal> resultList=new ArrayList<BigDecimal>();//返回结果

        resultList.add(trade);
        resultList.add(turnoverRate);
        return resultList;
    }

    /*
        解析每日股票交易数据json
     */
    private static List<StockTrade> parseDayPriceJson(int pageNum, int pageSize, String sort, boolean asc) {
        String url = String.format(SINA_DAY_PRICE_URL,
                                   getPType("http"),
                                   getDomains("vsf"),
                                   getPages("jv"),
                                   pageSize,
                                   sort,
                                   asc ? 1 : 0,
                                   "hs_a",
                                   pageNum);
        String json = HttpUtil.sendGET(url);
        if (StringUtils.isBlank(json)) return null;
        Pattern p = Pattern.compile("[a-zA-Z]+:");
        Matcher m = p.matcher(json);
        StringBuffer sbf = new StringBuffer();
        while (m.find()) {
            String tmp = m.group();
            tmp = String.format("\"%s\":", tmp.substring(0, tmp.length() - 1));
            m.appendReplacement(sbf, tmp);
        }
        m.appendTail(sbf);
        List<Map<String, Object>> jsonMaps = JacksonUtil.jsonArray2ListMap(sbf.toString());
        List<StockTrade> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(jsonMaps)) {
            for (Map<String, Object> jsonMap: jsonMaps) {
                StockTrade obj = new StockTrade();
                map2Bean(jsonMap, obj);
                res.add(obj);
            }
        }
        return CollectionUtils.isEmpty(res) ? null : res;
    }
    /*
        生成symbol代码标志
     */
    private static String codeToSymbol(String code) {
        String indexSymbol=getIndexSymbol(code);

        if (!StringUtils.isEmpty(indexSymbol)) {
            return indexSymbol;
            //return INDEX_LIST.get(code);
        } else {
            if (code.length() != 6) {
                return "";
            }else {
                if (StringUtils.startsWithAny(code, "5", "6" ,"9")) {
                    return String.format("sh%s", code);
                } else {
                    return String.format("sz%s", code);
                }
            }
        }
    }

    private static void map2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property: properties) {
                String key = property.getName();
                Class clazz = property.getPropertyType();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    if (value instanceof Integer ||
                            value instanceof Long ||
                            value instanceof Float ||
                            value instanceof Double) {
                        value = String.valueOf(value);
                    }
                    if (clazz.equals(BigDecimal.class)) {
                        value = new BigDecimal((String) value);
                    } else if (clazz.equals(java.sql.Date.class)) {
                        value = new java.sql.Date(SDF_TIME.parse((String) value).getTime());
                    }
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (IntrospectionException e) {
            LOG.error("IntrospectionException, because " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOG.error("IllegalAccessException, because " + e.getMessage());
        } catch (InvocationTargetException e) {
            LOG.error("InvocationTargetException, because " + e.getMessage());
        } catch (ParseException e) {
            LOG.error("ParseException, because " + e.getMessage());
        } catch (Exception e) {
            LOG.error("Can't parse map to bean, because " + e.getMessage());
        }
    }

    public static <T> PageInfo<T> list2Page(List<T> list,
                                             int total, int pageNum, int pageSize, String sort, boolean asc) {
        pageNum = pageNum == 0 ? 1 : Math.abs(pageNum);
        pageSize = pageSize == 0 ? 80 : Math.abs(pageSize);
        String orderBy = StringUtils.isBlank(sort) ? null : sort + (asc ? " asc" : " desc");
        Page<T> page = new Page<>(pageNum, pageSize);
        page.setTotal(total);
        page.setOrderBy(orderBy);

        if (!CollectionUtils.isEmpty(list)) {
            int start = page.getStartRow();
            int end = page.getEndRow();
            end = end > total ? total : end;
            if ((end - start) == list.size()) {
                page.addAll(list);
            } else {
                page.addAll(list.subList(start, end));
            }
        }
        return new PageInfo<>(page);
    }

    private static String lastTradeDay() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -1);
        String lastDate = SDF_DATE.format(now.getTime());
        while (isHoliday(lastDate)) {
            now.add(Calendar.DAY_OF_MONTH, -1);
            lastDate = SDF_DATE.format(now.getTime());
        }
        return lastDate;
    }

    private static boolean isHoliday(String date) {
        String[] dateSplit = date.split("-");
        if (dateSplit == null || dateSplit.length != 3) return true;
        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = dateSplit[2];
        if (month != null && month.length() != 2) month = "0" + month;
        if (day != null && day.length() != 2) day = "0" + day;
        date = year + "-" + month + "-" + day;

        List<Map<String, String>> cvs = null;
        if (CollectionUtils.isEmpty(TRADE_DAY_TABLE)) {
            cvs = HttpUtil.downCVS(
                    String.format(ALL_CAL_FILE, P_TYPE.get("http"), DOMAINS.get("oss")),
                    ",");
        }else {
            String lastDate = TRADE_DAY_TABLE.get("lastDate");
            try {
                int lastDateYear = Integer.valueOf(lastDate.split("-")[0]);
                int nowYear = Calendar.getInstance().get(Calendar.YEAR);
                if (nowYear > lastDateYear) {
                    cvs = HttpUtil.downCVS(
                            String.format(ALL_CAL_FILE, P_TYPE.get("http"), DOMAINS.get("oss")),
                            ",");
                }
            } catch (NumberFormatException e) {
                //ignore
            }
        }

        if (!CollectionUtils.isEmpty(cvs)) {
            for (Map<String, String> map: cvs) {
                TRADE_DAY_TABLE.put(map.get("calendarDate"), map.get("isOpen"));
            }
            TRADE_DAY_TABLE.put("lastDate", SDF_DATE.format(new Date()));
        }

        if ("0".equals(TRADE_DAY_TABLE.get(date))) return true;
        return false;
    }

    private static void initTradeDayTable() {
        List<Map<String, String>> cvs = HttpUtil.downCVS(
                String.format(ALL_CAL_FILE, getPType("http"), getDomains("oss")),
                ",");
        if (!CollectionUtils.isEmpty(cvs)) {
            for (Map<String, String> map: cvs) {
                TRADE_DAY_TABLE.put(map.get("calendarDate"), map.get("isOpen"));
            }
            TRADE_DAY_TABLE.put("lastDate", SDF_DATE.format(new Date()));
        }
    }
}
