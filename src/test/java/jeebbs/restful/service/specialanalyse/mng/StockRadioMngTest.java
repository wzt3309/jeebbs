package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StockRadioMngTest {
    @Autowired
    private StockRadioMng mng;

    @Test
    public void getRadio1() throws Exception {
        System.out.println(mng.getRadio1(null));
        System.out.println(mng.getRadio2(new SimpleDateFormat("yyyy-MM-dd").parse("2017-8-16")));
    }

}