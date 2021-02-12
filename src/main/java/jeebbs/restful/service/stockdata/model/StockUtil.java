package jeebbs.restful.service.stockdata.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;

/**
 * Created by ztwang on 2017/7/20 0020.
 */
public final class StockUtil {
    private static final Logger LOG = LoggerFactory.getLogger(StockUtil.class);

    private StockUtil() {
    }

    public static <T> Comparator<T> getComparator(String sort, Class cls) {
        return new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Object val1 = null, val2 = null;
                Class clazz = null;
                int res = 1;
                try {
                    PropertyDescriptor property = new PropertyDescriptor(sort, cls);
                    clazz = property.getPropertyType();
                    Method getter = property.getReadMethod();
                    val1 = getter.invoke(o1);
                    val2 = getter.invoke(o2);
                    if (val1 != null && val2 != null) {
                        if (clazz.equals(String.class)) {
                            res = ((String) val1).compareTo((String) val2);
                        }
                        if (clazz.equals(Date.class)) {
                            res = ((Date) val1).compareTo((Date) val2);
                        }
                        if (clazz.equals(BigDecimal.class)) {
                            res = ((BigDecimal) val1).compareTo((BigDecimal) val2);
                        }
                    } else if (val1 == null) {
                        res = val2 == null ? 0 : -1;
                    } else {
                        res = 1;
                    }
                } catch (Exception e) {
                    LOG.error("StockDaily get comparator error, because " + e.getMessage());
                }
                return res;
            }
        };
    }
}
