package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.Application;
import jeebbs.restful.service.specialanalyse.model.StockRadio;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ztwang on 2017/8/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class SpecialAnalyseMngTest {
    @Autowired
    private SpecialAnalyseMng mng;

    @Test
    @Rollback
    public void insert() throws Exception {
        mng.insertStockRadio();
    }

    @Test
    @Rollback
    public void insertByDate() throws Exception {
    }

    @Test
    @Rollback
    public void check() throws Exception {
        mng.checkStockRadio();
        List<StockRadio> list = mng.findStockRadioAll();
        for (StockRadio elem: list) {
            System.out.println(elem);
        }
    }

    @Test
    public void findAllByDate() throws Exception {
    }

}