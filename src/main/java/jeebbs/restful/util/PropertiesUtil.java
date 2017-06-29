package jeebbs.restful.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public final class PropertiesUtil {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
    private static final String DEFAULT_CHARSET = "utf-8";
    private PropertiesUtil() {}

    public static Properties load(String fileName, Class clazz) {
        Properties prop = new Properties();
        try(InputStreamReader in = new InputStreamReader(
                clazz.getResourceAsStream(fileName),DEFAULT_CHARSET)) {
            if (in == null) {
                LOG.error(String.format("Can't find \'%s\'", fileName));
            } else {
                prop.load(in);
            }
        }catch (IOException e) {
            LOG.error(String.format("IOException happended when \'%s\' load", fileName));
        }

        return prop;
    }

    public static Map<String, String> findPropsByKeyPrefix(Properties prop, String keyPrefix) {
        if (prop == null || prop.isEmpty()) return null;
        if (StringUtils.isEmpty(keyPrefix)) return prop2Map(prop);
        Set<String> propNames = prop.stringPropertyNames();
        Map<String, String> res = new HashMap<>();
        for(final String name: propNames) {
            if (name.startsWith(keyPrefix)) {
                res.put(name, prop.getProperty(name));
            }
        }
        return res;
    }

    public static Map<String, String> prop2Map(Properties prop) {
        if (prop == null || prop.isEmpty()) return null;
        Map<String, String> res = new HashMap<>();
        for (final String name: prop.stringPropertyNames()) {
            res.put(name, prop.getProperty(name));
        }
        return res;
    }
}
