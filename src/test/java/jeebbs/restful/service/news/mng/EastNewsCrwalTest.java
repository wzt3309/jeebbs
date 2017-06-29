package jeebbs.restful.service.news.mng;

import org.junit.Test;

/**
 * Created by ztwang on 2017/6/30 0030.
 */
public class EastNewsCrwalTest {
    @Test
    public void run() throws Exception {
//        Thread t = new Thread(new EastNewsCrwal());
//        t.start();
//        t.join();
        new EastNewsCrwal().run();
    }
}