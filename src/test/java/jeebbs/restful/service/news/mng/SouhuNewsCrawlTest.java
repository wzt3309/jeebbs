package jeebbs.restful.service.news.mng;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by ztwang on 2017/6/29 0029.
 */
public class SouhuNewsCrawlTest {
    @Test
    public void run() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new SouhuNewsCrawl());
        TimeUnit.SECONDS.sleep(5);
        executor.shutdown();
    }

}