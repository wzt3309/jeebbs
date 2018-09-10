package jeebbs.restful.service.stockdata.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/7/18 0018.
 */
public class StockDataConstant {
    private final static Logger LOG = LoggerFactory.getLogger(StockDataConstant.class);
    public static List<String> INDEX_LABELS;
    public static Map<String, String> INDEX_LIST;
    public static Map<String, String> INDEX_SYMBOL;
    public static Map<String, String> P_TYPE;
    public static Map<String, String> K_TYPE;
    public static Map<String, String> DOMAINS;
    public static Map<String, String> PAGES;
    public static final String DAY_PRICE_URL = "%sapi.finance.%s/%s/?code=%s&type=last";//某股历史日K线数据
    public static final String SINA_DAY_PRICE_URL = "%s%s/quotes_service/api/%s/Market_Center.getHQNodeData?" +
                                              "num=%s&sort=%s&asc=%d&node=%s&symbol=&_s_r_a=page&page=%s";//当日股票交易数据
    public static final String CODE_SEARCH_URL = "%ssuggest.%s/suggest/default.aspx?name=sData&input=%s&type=";
    public static final String ALL_CAL_FILE = "%s%s/tsdata/calAll.csv";
    public static final String LHB_URL = "%sdata.%s/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1," +
            "sortRule=-1,sortType=,startDate=%s,endDate=%s,gpfw=0,js=vardata_tab_1.html";//龙虎榜链接

    public static String getIndexList(String key) {
        if (CollectionUtils.isEmpty(INDEX_LIST)) {
            String index_list_json = "{'sh': 'sh000001', 'sz': 'sz399001', 'hs300': 'sz399300', " +
                    "'sz50': 'sh000016', 'zxb': 'sz399005', 'cyb': 'sz399006', " +
                    "'zx300': 'sz399008', 'zh500':'sh000905'}";
            INDEX_LIST = init(index_list_json);
        }
        return !CollectionUtils.isEmpty(INDEX_LIST) ? INDEX_LIST.get(key) : null;
    }

    public static String getIndexSymbol(String key) {
        if (CollectionUtils.isEmpty(INDEX_SYMBOL)) {
            URL stock_code_json = StockDataConstant.class.getResource("/stock-code.json");
            INDEX_SYMBOL = init(stock_code_json);
        }

        return !CollectionUtils.isEmpty(INDEX_SYMBOL) ? INDEX_SYMBOL.get(key) : null;
    }

    public static String getPType(String key) {
        if (CollectionUtils.isEmpty(P_TYPE)) {
            String p_type_json = "{'http': 'http://', 'ftp': 'ftp://'}";
            P_TYPE = init(p_type_json);
        }
        return !CollectionUtils.isEmpty(P_TYPE) ? P_TYPE.get(key) : null;
    }

    public static String getKType(String key) {
        if (CollectionUtils.isEmpty(K_TYPE)) {
            String k_type_json = "{'D': 'akdaily', 'W': 'akweekly', 'M': 'akmonthly'}";
            K_TYPE = init(k_type_json);
        }
        return !CollectionUtils.isEmpty(K_TYPE) ? K_TYPE.get(key) : null;
    }


    public static String getDomains(String key) {
        if (CollectionUtils.isEmpty(DOMAINS)) {
            String domains_json = "{'sina': 'sina.com.cn', 'sinahq': 'sinajs.cn', " +
                    "'ifeng': 'ifeng.com', 'sf': 'finance.sina.com.cn', " +
                    "'vsf': 'vip.stock.finance.sina.com.cn', " +
                    "'idx': 'www.csindex.com.cn', '163': 'money.163.com', " +
                    "'em': 'eastmoney.com', 'sseq': 'query.sse.com.cn', " +
                    "'sse': 'www.sse.com.cn', 'szse': 'www.szse.cn', " +
                    "'oss': 'file.tushare.org', 'idxip':'115.29.204.48', " +
                    "'shibor': 'www.shibor.org', 'mbox':'www.cbooo.cn', " +
                    "'tt': 'gtimg.cn', 'gw': 'gw.com.cn'}";
            DOMAINS = init(domains_json);
        }
        return !CollectionUtils.isEmpty(DOMAINS) ? DOMAINS.get(key) : null;
    }

    public static String getPages(String key) {
        if (CollectionUtils.isEmpty(PAGES)) {
            String pages_json = "{'fd': 'index.phtml', 'dl': 'downxls.php', 'jv': 'json_v2.php', " +
                    "'cpt': 'newFLJK.php', 'ids': 'newSinaHy.php', 'lnews':'rollnews_ch_out_interface.php', " +
                    "'ntinfo':'vCB_BulletinGather.php', 'hs300b':'000300cons.xls', " +
                    "'hs300w':'000300closeweight.xls','sz50b':'000016cons.xls', " +
                    "'dp':'all_fpya.php', '163dp':'fpyg.html', " +
                    "'emxsg':'JS.aspx', '163fh':'jjcgph.php', " +
                    "'newstock':'vRPD_NewStockIssue.php', 'zz500b':'000905cons.xls', " +
                    "'zz500wt':'000905closeweight.xls', " +
                    "'t_ticks':'vMS_tradedetail.php', 'dw': 'downLoad.html', " +
                    "'qmd':'queryMargin.do', 'szsefc':'ShowReport.szse', " +
                    "'ssecq':'commonQuery.do', 'sinadd':'cn_bill_download.php', 'ids_sw':'SwHy.php', " +
                    "'idx': 'index.php'}";
            PAGES = init(pages_json);
        }
        return PAGES.get(key);
    }

    private static Map<String, String> init(String json) {
        Map<String, String> map = null;
        ObjectMapper objMapper = new ObjectMapper();
        try {
            map = objMapper.readValue(json.replaceAll("'", "\""),
                    new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            LOG.error("Can't load constant json");
        }
        return map;
    }

    private static Map<String, String> init(URL url) {
        Map<String, String> map = null;
        ObjectMapper objMapper = new ObjectMapper();
        try {
            map = objMapper.readValue(url,
                    new TypeReference<Map<String, String>>() {});
        } catch (IOException e) {
            LOG.error("Can't load url json");
        }
        return map;
    }

    static {
        // init INDEX_LABELS
        INDEX_LABELS = Arrays.asList("sh", "sz", "hs300", "sz50", "cyb", "zxb", "zx300", "zh500");
    }
}
