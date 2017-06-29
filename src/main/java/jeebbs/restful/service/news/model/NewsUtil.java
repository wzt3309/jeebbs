package jeebbs.restful.service.news.model;
import jeebbs.restful.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public final class NewsUtil {

    public static final String PROP_NEWS_SOURCES = "news.sources";
    public static final String PROP_NEWS_PREFIX = "news.source";
    public static final String PROP_NEWS_NAME = "name";
    public static final String PROP_NEWS_LAYOUT = "layout";
    public static final String PROP_NEWS_URL = "url";
    public static final String PROP_NEWS_SELECTOR = "selector";
    public static final String PROP_NEWS_ABSTRACT_LEN = "abstract.length";

    private static final Logger LOG = LoggerFactory.getLogger(NewsUtil.class);
    private static final String PROPERTIES_FILENAME = "/crawl-news.properties";
    private static final Map<String, Map<String, String>> PROPERTIES = new HashMap<>();

    static {
        Properties prop = PropertiesUtil.load(PROPERTIES_FILENAME, NewsUtil.class);

        if (!prop.isEmpty()) {
            String news_sources_val = prop.getProperty(PROP_NEWS_SOURCES);
            String[] news_sources =
                    (news_sources_val != null ? news_sources_val.split(",") : new String[0]);
            for (final String s: news_sources) {
                String prefix = combineKeys(PROP_NEWS_PREFIX, s);
                Map<String, String> map = PropertiesUtil.findPropsByKeyPrefix(prop, prefix);
                if (map != null && !map.isEmpty()) PROPERTIES.put(s, map);
            }
        }
    }

    public static String get(String source, String key) {
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(key)) return null;
        if (PROPERTIES.containsKey(source)) {
            Map<String, String> map = PROPERTIES.get(source);
            String name = combineKeys(PROP_NEWS_PREFIX, source, key);
            if (map.containsKey(name)) {
                return map.get(name);
            }
        }
        return null;
    }

    public static String combineKeys(String prefix, String... keys) {
        if (StringUtils.isEmpty(prefix)) return null;
        if (prefix.endsWith(".")) prefix = prefix.substring(0, prefix.length() - 1);
        StringBuilder sbd = new StringBuilder(prefix);
        if (keys == null || keys.length == 0) return sbd.toString();
        for (final String key: keys) {
            if (!key.startsWith(".")) sbd.append(".");
            sbd.append(key);
        }
        return sbd.toString();
    }
}
