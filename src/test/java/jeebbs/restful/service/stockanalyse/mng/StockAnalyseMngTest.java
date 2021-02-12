package jeebbs.restful.service.stockanalyse.mng;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import jeebbs.restful.Application;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/8/22 0022.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StockAnalyseMngTest {
    @Autowired
    private StockAnalyseMng mng;

    @Test
    public void findStockAnalyse() throws Exception {
        PageInfo pageInfo = mng.findStockAnalyse(1, 50, "code", 1);
    }

}