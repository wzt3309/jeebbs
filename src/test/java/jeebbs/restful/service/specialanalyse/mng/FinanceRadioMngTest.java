package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/8/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FinanceRadioMngTest {
    @Autowired
    private FinanceRadioMng mng;

    @Test(expected = IllegalArgumentException.class)
    public void getRadio3() throws Exception {
        Date d1, d2 ,d3, d4;
        d1 = null;
        d2 = new Date();
        d3 = Date.from(LocalDate.of(2017, 8, 15)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
        d4 = Date.from(LocalDate.of(2017, 8, 14)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant());
        System.out.println(mng.getRadio3(d1));
        System.out.println(mng.getRadio3(d3));
        System.out.println(mng.getRadio3(d4));
        System.out.println(mng.getRadio3(d2));
    }

    @Test
    public void getRadio4() throws Exception {
    }

}