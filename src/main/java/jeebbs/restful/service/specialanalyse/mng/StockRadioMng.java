package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@Service
public class StockRadioMng {
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DDX_URL = "http://ddx.gubit.cn/xg/zhddxlist.php";
    private static final String DDX_REFERER = "http://ddx.gubit.cn/xg/xuangu.html";

    /**
     * 股票涨跌比 (上涨股票数 - 下跌股票数) / (上涨股票数 + 下跌股票数)
     *
     * @param date 日期 null就是今天
     * @return 某日radio1
     */
    public Double getRadio1(Date date) {
        Double radio1 = null;
        Map<String, String> params = new HashMap<>();
        if (date != null) {
            params.put("lsdate", DATE_FMT.format(date));
        }
        params.put("t", String.valueOf(Math.random()));
        params.put("orderby", "2");
        params.put("isdesc", "1");
        Map<String, Object> data = getData(params);
        if (!CollectionUtils.isEmpty(data)) {
            double numOfUps = 0;
            double numOfDowns = 0;
            int startPage = 2;  //从第2页开始
            int pages = 1;
            int pageSize = 20;
            int total = (Integer) data.get("total");
            if (total > pageSize) {
                pages = (int) Math.ceil(((double) total) / pageSize);
            }
            Condition up = var -> {
                if (var instanceof Integer) return (Integer) var > 0;
                if (var instanceof Double) return (Double) var > 0;
                return false;
            };
            Condition down = var -> {
                if (var instanceof Integer) return (Integer) var < 0;
                if (var instanceof Double) return (Double) var < 0;
                return false;
            };
            numOfUps += getFromPage(data, up);  //已经计算了第1页股票是涨的股票数
            numOfUps += getFromPages(startPage, startPage, pages, pageSize, params, up);
            params.remove("isdesc");
            params.remove("page");
            numOfDowns += getFromPage(getData(params), down);
            numOfDowns += getFromPages(startPage, startPage, pages, pageSize, params, down);

            radio1 = (numOfUps - numOfDowns) / (numOfUps + numOfDowns);
            if (Double.isInfinite(radio1) || Double.isNaN(radio1)) return null;
        }
        return radio1;
    }

    /**
     * 股票强弱比 (涨停股票数 - 跌停股票数) / (涨停股票数 + 跌停股票数)
     *
     * @param date 日期 null就是今天
     * @return 某日radio2
     */
    public Double getRadio2(Date date) {
        Double radio2 = null;
        Map<String, String> params = new HashMap<>();
        if (date != null) {
            params.put("lsdate", DATE_FMT.format(date));
        }
        params.put("t", String.valueOf(Math.random()));

        double numOfTops = 0, numOfBottoms = 0;
        Map<String, Object> data;

        params.put("zh6", "1");
        data = getData(params);
        if (!CollectionUtils.isEmpty(data)) {
            Object total = data.get("total");
            numOfTops = total != null ? (Integer) total : 0;
        }

        params.remove("zh6");
        params.put("zh7", "1");
        data = getData(params);
        if (!CollectionUtils.isEmpty(data)) {
            Object total = data.get("total");
            numOfBottoms = total != null ? (Integer) total : 0;
        }

        radio2 = (numOfTops - numOfBottoms) / (numOfTops + numOfBottoms);
        if (Double.isInfinite(radio2) || Double.isNaN(radio2)) return null;
        return radio2;
    }

    private int getFromPages(int startPage, int beg, int end, int pageSize,
                             Map<String, String> params, Condition condition) {
        if (beg > end || pageSize <= 0 || params == null) return 0;
        int mid = (beg + end) / 2;
        params.put("page", String.valueOf(mid));
        int num = getFromPage(getData(params), condition);
        if (num == pageSize) {  //可能是mid也可能不是
            params.put("page", String.valueOf(mid + 1));
            int nextNum = getFromPage(getData(params), condition);
            if (nextNum == 0) { //如果下一页全部不满足条件 则该页就是mid
                return (mid - startPage) * pageSize + num;
            }
            return getFromPages(startPage, mid + 1, end, pageSize, params, condition);
        } else if (num == 0) return getFromPages(startPage, beg, mid - 1, pageSize, params, condition);
        else return (mid - startPage) * pageSize + num;     // 0<num<pageSize 肯定是mid
    }

    private int getFromPage(Map<String, Object> data, Condition condition) {
        int num = 0;
        if (!CollectionUtils.isEmpty(data)) {
            List arrays = (List) data.get("data");
            if (!CollectionUtils.isEmpty(arrays)) {
                for (Object array : arrays) {
                    Object var2 = ((List) array).get(2);
                    if (condition.isSatisfied(var2)) {
                        num++;
                    }
                }
            }
        }
        return num;
    }

    private Map<String, Object> getData(Map<String, String> params) {
        Map<String, String> header = new HashMap<>();
        header.put("Referer", DDX_REFERER);
        String json = HttpUtil.sendGET(DDX_URL, params, header);
        if (!StringUtils.isEmpty(json)) {
            return JacksonUtil.json2Map(json);
        }
        return null;
    }

    private interface Condition {
        boolean isSatisfied(Object var);
    }
}
