package jeebbs.restful.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public final class ResponseUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);

    private static final String UNKNOWN_MSG = "Unknown";
    private static final String MESSAGE_FILENAME = "/res-code.properties";
    private static final Map<Integer, String> MESSAGES = new HashMap<>();
    static {
        Properties prop = new Properties();
        try(InputStream in = ResponseUtil.class.getResourceAsStream(MESSAGE_FILENAME)) {
            if (in == null) {
               LOG.error("Can't find \'res-code.properties\'");
            } else {
                prop.load(in);
            }
        } catch (IOException e) {
            LOG.error("IOException happened when load \'res-code.properties\'");
        }

        if (!prop.isEmpty()) {
            for (final String name: prop.stringPropertyNames()) {
                String keyStr = name.substring(name.indexOf(".") + 1);
                if (!StringUtils.isEmpty(keyStr)){
                    MESSAGES.put(Integer.valueOf(keyStr), prop.getProperty(name));
                }
            }
        }
    }

    /**
     * 返回的代码列表
     */
    public static final int DEFAULT_CODE_SUCCESS = 0;
    public static final int DEFAULT_CODE_ERROR = -1;

    private ResponseUtil() {

    }

    public static String getResMsg(int code) {
        String msg = UNKNOWN_MSG;
        if (MESSAGES.containsKey(code)) {
            String val = MESSAGES.get(code);
            if (!StringUtils.isEmpty(val)) {
                msg = val;
            }
        }
        return msg;
    }
}
