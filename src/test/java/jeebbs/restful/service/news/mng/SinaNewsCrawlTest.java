package jeebbs.restful.service.news.mng;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/6/30 0030.
 */
public class SinaNewsCrawlTest {
    @Test
    public void run() throws Exception {
//        Thread t = new Thread(new SinaNewsCrawl());
//        t.start();
//        t.join();
        new SinaNewsCrawl().run();
    }
}