package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/8/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpecialAnalyseMngTest {
    @Autowired
    private SpecialAnalyseMng mng;

    @Test
    public void insert() throws Exception {
        mng.insert();
    }

    @Test
    public void insert1() throws Exception {
    }

    @Test
    public void check() throws Exception {
    }

    @Test
    public void findAllByDate() throws Exception {
    }

}