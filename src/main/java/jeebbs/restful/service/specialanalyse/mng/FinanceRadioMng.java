package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import jeebbs.restful.util.StockHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by ztwang on 2017/8/18 0018.
 */
@Service
public class FinanceRadioMng {
    private static final Logger logger = LoggerFactory.getLogger(FinanceRadioMng.class);

    private static final String SSE_URL = "http://query.sse.com.cn/marketdata/tradedata/queryMargin.do";
    private static final String SSE_REFERER = "http://www.sse.com.cn/market/othersdata/margin/sum/";
    private static final LocalTime SSE_UPDATE_TIME = LocalTime.parse("23:59:59");

    /**
     *
     * @param date 时间
     * @return 融资融券领先值
     */
    public Long getRadio(Date date) {
        if (date == null) {
            date = StockHelper.LatestTradeDateMinusOfDays(1);   //获取最近第二个交易日
        }else {
            if (!isValidated(date)) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                String errMsg = String.format("Illegal date:%s to get radio3", fmt.format(date));
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }

        Long radio3 = null;
        Date lastDay = getLastDay(date);
        Long rzrqToday = getData(date), rzrqLastDay = getData(lastDay);
        if (rzrqToday != null) {
            if (rzrqLastDay == null) {
                List<Long> lastDatas = getData(getDaysBefore(lastDay, 10), lastDay);
                if (!CollectionUtils.isEmpty(lastDatas)) {
                    rzrqLastDay = lastDatas.get(0);
                }else {
                    String errMsg = "can't find rzrqLastDay";
                    logger.error(errMsg);
                    throw new RuntimeException("can't find rzrqLastDay");
                }
            }
            if (rzrqLastDay != null) {
                radio3 = rzrqToday - rzrqLastDay;
            }
        }

        return radio3;
    }

    private Long getData(Date date) {
        List<Long> datas = getData(date, date);
        if (!CollectionUtils.isEmpty(datas)) {
            return datas.get(0);
        }
        return null;
    }

    private List<Long> getData(Date beginDate, Date endDate) {
        List<Long> datas = new ArrayList<>();

        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", SSE_REFERER);
        Map<String, String> params = new HashMap<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        params.put("beginDate", fmt.format(beginDate));
        params.put("endDate", fmt.format(endDate));
        params.put("isdesc", "1");
        params.put("orderby", "2");

        String json = HttpUtil.sendGET(SSE_URL, params, headers);
        Map<String, Object> json2Map = JacksonUtil.json2Map(json);
        if (!CollectionUtils.isEmpty(json2Map)) {
            List result = (List) json2Map.get("result");
            if (!CollectionUtils.isEmpty(result)) {
                for (Object data: result) {
                    if (data instanceof Map) {
                        Object rzrqjyzl = ((Map) data).get("rzrqjyzl");
                        if (rzrqjyzl instanceof Long || rzrqjyzl instanceof Integer) {
                            datas.add((Long) rzrqjyzl);
                        }
                        if (rzrqjyzl instanceof String) {
                            datas.add(Long.valueOf((String) rzrqjyzl));
                        }
                    }
                }
            }
        }
        return datas;
    }

    private boolean isValidated(Date date) {
        if (date == null) return false;
        LocalDateTime ofDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        if (ofDate.toLocalDate().isEqual(now.toLocalDate())) {
            return now.toLocalTime().isAfter(SSE_UPDATE_TIME);
        }
        return ofDate.toLocalDate().isBefore(now.toLocalDate());
    }

    private Date getLastDay(Date today) {
        return getDaysBefore(today, 1);
    }

    private Date getDaysBefore(Date from, long days) {
        if (from == null || days < 0) return null;
        LocalDateTime ofToday = LocalDateTime.ofInstant(from.toInstant(), ZoneId.systemDefault());
        LocalDateTime lastDay = ofToday.minusDays(days);
        return Date.from(lastDay.atZone(ZoneId.systemDefault()).toInstant());
    }
}
