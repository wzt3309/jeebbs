package jeebbs.restful.schedule;

import jeebbs.restful.Application;
import jeebbs.restful.service.news.mng.*;
import jeebbs.restful.service.news.model.AbstractNewsCrawl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by ztwang on 2017/7/11 0011.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class NewsScheduleTest {
    @Autowired
    private NewsMapper newsMapper;
    @Test
    @Rollback
    public void collectNewsDaily() throws Exception {
//        List<AbstractNewsCrawl> newsCrawlList = new ArrayList<>();
//        newsCrawlList.add(new EastNewsCrawl(newsMapper));
//        newsCrawlList.add(new SinaNewsCrawl(newsMapper));
//        newsCrawlList.add(new SouhuNewsCrawl(newsMapper));
//        newsCrawlList.add(new XueqiuNewsCrawl(newsMapper));
//        for (AbstractNewsCrawl newsCrawl: newsCrawlList) {
//            newsCrawl.run();
//        }
    }

}