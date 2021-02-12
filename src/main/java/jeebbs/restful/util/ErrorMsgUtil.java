package jeebbs.restful.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

/**
 * Created by ztwang on 2017/7/10 0010.
 */
public final class ErrorMsgUtil {

    private ErrorMsgUtil(){}

    public static String paramIsNull(String paramName) {
        return String.format("参数: \'%s\'=null", paramName);
    }

    public static String paramIsEmpty(String paramName) {
        return String.format("参数: \'%s\'=\'\'", paramName);
    }

    public static String anyParamsIsNull(String... paramNames) {
        StringBuilder sbd = new StringBuilder("参数集: ");
        if (!ArrayUtils.isEmpty(paramNames)) {
            for (String paramName: paramNames) {
                sbd.append(String.format("\'%s\'=null,", paramName));
            }
        }
        sbd.substring(0, sbd.length() - 1);
        return sbd.toString();
    }

    public static String anyParamsIsEmpty(String... paramNames) {
        StringBuilder sbd = new StringBuilder("参数集: ");
        if (!ArrayUtils.isEmpty(paramNames)) {
            for (String paramName: paramNames) {
                sbd.append(String.format("\'%s\'=\'\',", paramName));
            }
        }
        sbd.substring(0, sbd.length() - 1);
        return sbd.toString();
    }

    public static String paramInvalidate(String paramName) {
        return String.format("参数: \'%s\' 不合法", paramName);
    }

    public static String anyParamInvalidate(String... paramNames) {
        StringBuilder sbd = new StringBuilder("参数集: ");
        if (!ArrayUtils.isEmpty(paramNames)) {
            for (String paramName: paramNames) {
                sbd.append(String.format("\'%s\' 不合法',", paramName));
            }
        }
        sbd.substring(0, sbd.length() - 1);
        return sbd.toString();
    }
}
