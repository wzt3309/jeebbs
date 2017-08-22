package jeebbs.restful.service.stockanalyse.mng;

import com.github.pagehelper.PageInfo;
import jeebbs.restful.service.stockanalyse.model.StockAnalyse;
import jeebbs.restful.service.stockdata.mng.TradingMng;

import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by ztwang on 2017/8/22 0022.
 */
@Service
public class StockAnalyseMng {
    private static final Logger logger = LoggerFactory.getLogger(StockAnalyseMng.class);
    private static final String EAST_URL = "http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx";
    private static final String EAST_REFERER = "http://data.eastmoney.com/stockcomment/";

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
