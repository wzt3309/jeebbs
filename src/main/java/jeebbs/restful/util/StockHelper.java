package jeebbs.restful.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ztwang on 2017/8/19 0019.
 */
public final class StockHelper {
    private static final Logger logger = LoggerFactory.getLogger(StockHelper.class);
    private static final String DDX_URL = "http://ddx.gubit.cn/xg/zhddxlist.php";
    private static final String DDX_REFERER = "http://ddx.gubit.cn/xg/xuangu.html";
    private static final String DDX_DATE_LIST_URL = "http://ddx.gubit.cn/xg/js/zhdatelist.js";

    private StockHelper(){}

    /**
     * 获取最近的交易日期
     * @return 日期
     */
    public static Date getLatestTradeDate() {
        Map<String, Object> data = null;
        Map<String, String> header = new HashMap<>();
        header.put("Referer", DDX_REFERER);
        String json = HttpUtil.sendGET(DDX_URL, null, header);
        if (!StringUtils.isEmpty(json)) {
            data = JacksonUtil.json2Map(json);
        }

        if (!CollectionUtils.isEmpty(data)) {
            String str = (String) data.get("zhupdatetime");
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
            } catch (ParseException e) {
                logger.error("can't parse `zhupdatetime` from ddx");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取最近的交易日期 - days
     * @param days 天数
     * @return 日期
     */
    public static Date LatestTradeDateMinusOfDays(int days) {
        List<Date> list = getDateList(days);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    /**
     * 获取从最近的交易日期前一个交易日开始的n个交易日期
     * @param checkDays 列表大小
     * @return 日期数组
     */
    public static List<Date> getDateList(int checkDays) {
        if (checkDays < 0) {
            throw new IllegalArgumentException("checkDays can't less than 0");
        }
        String response = HttpUtil.sendGET(DDX_DATE_LIST_URL);
        List<Date> dateList = new ArrayList<>();
        if (!StringUtils.isEmpty(response)) {
            int beg = response.indexOf("[");
            int end = response.indexOf(";");
            if (beg <= end) {
                String dateListJson = response.substring(beg, end).replaceAll("'", "\"");
                List<String> list = JacksonUtil.jsonArray2PojoList(dateListJson, String.class);
                if (!CollectionUtils.isEmpty(list)) {
                    Collections.reverse(list);
                    list = list.subList(0, list.size() >= checkDays ? checkDays : list.size());
                    for (String str: list) {
                        try {
                            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                            dateList.add(fmt.parse(str));
                        } catch (ParseException e) {
                            if (logger.isDebugEnabled()) {
                                logger.error(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        return dateList;
    }
}
