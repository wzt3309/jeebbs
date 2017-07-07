package jeebbs.restful;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ztwang on 2017/7/7 0007.
 */
public class SimpleTest {

    @Test
    public void test1() {
        Calendar today = Calendar.getInstance();
        System.out.println(today.get(Calendar.DAY_OF_MONTH));
        System.out.println(today.get(Calendar.DATE));

        today.add(Calendar.DAY_OF_MONTH, -10);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(fmt.format(today.getTime()));
    }
}
