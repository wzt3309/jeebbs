package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
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
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");

    private static final String SSE_URL = "http://query.sse.com.cn/marketdata/tradedata/queryMargin.do";
    private static final String SSE_REFERER = "http://www.sse.com.cn/market/othersdata/margin/sum/";
    private static final LocalTime SSE_UPDATE_TIME = LocalTime.parse("23:59:59");

    /**
     * date 那天有数据则radio3!=null
     * date==null 如果时间早于SSE_UPDATE_TIME则查找date前一天数据，如果前一天没数据 radio3==null
     * (如 当天为周一，前一天周日无数据，本日也无数据，则radio3为null，等到周二，更新周一数据 radio3!=null)
     * @param date 时间
     * @return 融资融券领先值
     */
    public Long getRadio3(Date date) {
        if (date == null) {
            date = getUptoDate();
        }else {
            if (!isValidated(date)) {
                throw new IllegalArgumentException(
                        String.format("Illegal date:%s to get radio3", DATE_FMT.format(date)));
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
        params.put("beginDate", DATE_FMT.format(beginDate));
        params.put("endDate", DATE_FMT.format(endDate));
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

    private Date getUptoDate() {
        Date res;
        LocalTime nowTime = LocalTime.now();
        if (nowTime.isBefore(SSE_UPDATE_TIME)) {
            res = getLastDay(new Date());
        }else {
            res = new Date();
        }
        return res;
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
