package jeebbs.restful.service.stockanalyse.mng;

import com.github.pagehelper.PageInfo;
import jeebbs.restful.service.stockanalyse.model.FundFlow;
import jeebbs.restful.service.stockanalyse.model.StockAnalyse;
import jeebbs.restful.service.stockdata.mng.TradingMng;

import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ztwang on 2017/8/22 0022.
 */
@Service
public class StockAnalyseMng {
    private static final Logger logger = LoggerFactory.getLogger(StockAnalyseMng.class);
    private static final String EAST_URL = "http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx";
    private static final String EAST_REFERER = "http://data.eastmoney.com/stockcomment/";
    //private static final String hy_fund_url = "http://data.eastmoney.com/bkzj/hy.html";//行业资金流
    private static final String hy_fund_url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._BKHY&sty=DCFFPBFM&st=(BalFlowMain)&sr=-1&p=1&ps=999&js=&token=894050c76af8597a853f5b408b759f5d";////行业资金流
    //private static final String gn_fund_url = "http://data.eastmoney.com/bkzj/gn.html";//概念资金流
    private static final String gn_fund_url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._BKGN&sty=DCFFPBFM&st=(BalFlowMain)&sr=-1&p=1&ps=10000&js=&token=894050c76af8597a853f5b408b759f5d";//概念资金流
    private final FundFlowMapper mapper;

    @Autowired//注入
    public StockAnalyseMng(FundFlowMapper mapper) {
        this.mapper = mapper;
    }


    //获取特定时间段的特定类型的资金数据
    public List<FundFlow> fundFlowAnalyse( String fund_type,Date updateDateFrom,Date updateDateTo){

        List<FundFlow> result=mapper.findFundFlowByDate(fund_type,new java.sql.Date(updateDateFrom.getTime()),new java.sql.Date(updateDateTo.getTime()));
        return result;
    }

    //在收市之后定时触发函数，更新数据，只执行一次
    public void updateFundFlow()
    {
        //获取当前日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String dateString=sf.format(c.getTime());//当前日期
        //判断今天是否收市
        boolean isHoliday=TradingMng.isHoliday(dateString);
        if(isHoliday){
            logger.info("今天收市，不会更新数据！！");
        }else {//不收市则更新数据
            try {
                logger.info("开始更新行业资金流数据");
                //分别更新行业和概念两个类型的资金数据
                updateFundFlowByType("行业");
                logger.info("行业资金流数据更新成功");

            } catch (ParseException e) {
                logger.info("行业资金流数据更新失败");
            }

            try{
                logger.info("开始更新概念资金流数据");
                //分别更新行业和概念两个类型的资金数据
                updateFundFlowByType("概念");
                logger.info("概念资金流数据更新成功");

            }catch (Exception e){
                logger.info("概念资金流数据更新失败");
            }
        }
    }


    //根据类型获取资金流数据
    public void updateFundFlowByType(String fund_type) throws ParseException {
        String strContent;
        if(fund_type.equals("行业"))
        {
            strContent= HttpUtil.sendGET(hy_fund_url);//获取行业资金流内容
        }else {
            strContent= HttpUtil.sendGET(gn_fund_url);//获取概念资金流内容
        }

        //处理字符串
        strContent=strContent.replaceAll("\\(\\[\"","").replaceAll("\"\\]\\)","");
        String[] list= strContent.split("\",\"");

        //属性
        String name;
        double flow_today;
        double flow_10;
        double flow_10_avg;
        double flow_20;
        double flow_20_avg;
        double flow_60;
        double flow_60_avg;
        double flow_120;
        double flow_120_avg;

        //获取当前日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String dateString=sf.format(c.getTime());//当前日期
        Date updateDate = sf.parse(dateString);//当前日期，Date形式
        //*日净流入数组
        double day10[];
        double day20[];
        double day60[];
        double day120[];

        //遍历
        for (int i=0;i<list.length;i++) {
            String[] data= list[i].split(",");
            name=data[2];//名称
            flow_today=Double.valueOf(data[3]);//今日主力净流入
            //获取*日净流入和*日平均净流入
            day10=getFundFlowByDays(name,fund_type,10);
            flow_10=day10[0];
            flow_10_avg=day10[1];
            day20=getFundFlowByDays(name,fund_type,20);
            flow_20=day20[0];
            flow_20_avg=day20[1];
            day60=getFundFlowByDays(name,fund_type,60);
            flow_60=day60[0];
            flow_60_avg=day60[1];
            day120=getFundFlowByDays(name,fund_type,120);
            flow_120=day120[0];
            flow_120_avg=day120[1];
            //插入数据库
            FundFlow item=new FundFlow(updateDate,fund_type,i+1,name,flow_today,flow_10,flow_10_avg,flow_20,flow_20_avg,flow_60,flow_60_avg,flow_120,flow_120_avg);
            mapper.insertFund_Flow(item);

        }

    }

    //根据过去的天数计算*日净流入和*日平均净流入
    public double[] getFundFlowByDays(String name,String fund_type, int day) throws ParseException {
        double fundFlow=0;//*日净流入
        double fundFlow_avg=0;//*日平均净流入

        //确定结束日期和开始日期
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String dateString_to=sf.format(c.getTime());//当前日期，即为结束日期

        int count=day-1;
        String dateString_from = null;//开始日期
        boolean isHoliday;//是否为节假日
        while(count>0){
            c.add(Calendar.DAY_OF_MONTH, -1);//每次往后一天
            dateString_from=sf.format(c.getTime());
            isHoliday=TradingMng.isHoliday(dateString_from);
            if(isHoliday){
                continue;
            }else{
                count--;
            }
        }

        //转换为日期格式
        Date updateDate_from = sf.parse(dateString_from);
        Date updateDate_to = sf.parse(dateString_to);

        List<FundFlow> resultList= mapper.findFundFlowByDateGap(name,fund_type,new java.sql.Date(updateDate_from.getTime()),new java.sql.Date(updateDate_to.getTime()));
        //计算*日净流入
        for(FundFlow item:resultList){
            fundFlow+=item.getFlow_today();
        }
        fundFlow_avg=fundFlow/day;
        //返回结果,第一个值为*日净流入，第二个值为*日平均净流入
        double[] arr=new double[2];
        arr[0]=fundFlow;
        arr[1]=fundFlow_avg;
        return arr;
    }




    public PageInfo<StockAnalyse> findStockAnalyse(int pageNum, int pageSize, String order, int isDesc) {
        Map<String, Object> data = findData(pageNum, pageSize, resolver(order), -isDesc);
        if (CollectionUtils.isEmpty(data)) return null;
        int total = (Integer) data.get("count");
        List list = (List) data.get("data");
        String date = (String) data.get("date");
        List<StockAnalyse> beanList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Object elem: list) {
                StockAnalyse bean = resolverStockAnalyse((String) elem);
                if (bean != null) {
                    try {
                        bean.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    } catch (ParseException e) {
                        logger.error(e.getMessage());
                    }
                    beanList.add(bean);
                }
            }
        }
        return TradingMng.list2Page(beanList, total, pageNum, pageSize, order, isDesc != 1);
    }

    private StockAnalyse resolverStockAnalyse(String str) {
        if (StringUtils.isBlank(str)) return null;
        StockAnalyse bean = new StockAnalyse();
        String[] strArr = str.split(",");
        if (strArr.length == 10) {
            try {
                bean.setCode(strArr[0]);
                bean.setName(strArr[1]);
                bean.setSuggestion(strArr[3].replace("&sbquo", ""));
                bean.setTrade("-".equals(strArr[4]) ? null : new BigDecimal(strArr[4]));
                bean.setChangepercent("-".equals(strArr[5]) ? null : new BigDecimal(strArr[5].replace("%", "")));
                bean.setTurnoverratio("-".equals(strArr[6]) ? null : new BigDecimal(strArr[6]));
                bean.setPer("-".equals(strArr[7]) ? null : new BigDecimal(strArr[7]));
                bean.setMain("-".equals(strArr[8]) ? null : new BigDecimal(strArr[8]));
                bean.setPartIn("-".equals(strArr[9]) ? null : new BigDecimal(strArr[9]));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return bean;
    }


    private int resolver(String order) {
        int res;
        switch (order) {
            case "code": res = 1;break;
            case "trade": res = 2;break;
            case "changepercent": res = 3;break;
            case "turnoverratio": res = 4;break;
            case "per": res = 5;break;
            case "main": res = 6;break;
            case "partIn": res = 7;break;
            default: res = 1;
        }
        return res;
    }

    public Map<String, Object> findData(int pageNum, int pageSize, int order, int isDesc) {
        assert pageNum > 0
                && pageSize > 0
                && (order >= 1 && order <= 7)
                && (isDesc == 1 || isDesc == -1);
        Map<String, String> params = new HashMap<>();
        params.put("type", "FD");
        params.put("sty", "TSTC");
        params.put("st", String.valueOf(order));
        params.put("sr", String.valueOf(isDesc));
        params.put("p", String.valueOf(pageNum));
        params.put("ps", String.valueOf(pageSize));
        params.put("js", "(x)");

        Map<String, String> header = new HashMap<>();
        header.put("Referer", EAST_REFERER);

        String json = HttpUtil.sendGET(EAST_URL, params, header);
        if (!StringUtils.isEmpty(json)) {
            json = json.replaceFirst("data", "\"data\"");
            json = json.replaceFirst("cdate", "\"cdate\"");
            json = json.replaceFirst(",\\s*date", ",\"date\"");
            json = json.replaceFirst("page", "\"page\"");
            json = json.replaceFirst("pages", "\"pages\"");
            json = json.replaceFirst("pageSize", "\"pageSize\"");
            json = json.replaceFirst("count", "\"count\"");
            json = json.replaceFirst("exTime", "\"exTime\"");
            json = json.replaceFirst("update", "\"update\"");
            return JacksonUtil.json2Map(json);

        }
        return null;
    }
}
