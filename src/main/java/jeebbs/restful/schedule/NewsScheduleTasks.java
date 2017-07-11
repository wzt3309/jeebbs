package jeebbs.restful.schedule;

import jeebbs.restful.service.news.mng.*;
import jeebbs.restful.service.news.model.AbstractNewsCrawl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ztwang on 2017/7/11 0011.
 */
@Component
public class NewsScheduleTasks {
    private static final int NEWS_LIMIT_SIZE = 3000;    //新闻在数据库中最多保存3000条
    private static final int NEWS_REMIN_DAYS = 3 * 30;  //新闻保存时间为三个月
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 每天从6:00~23:00,每隔10分钟收集一次新闻
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
//    @Scheduled(fixedDelay = 10000, initialDelay = 1000)
    public void collectNewsDaily() {
        List<AbstractNewsCrawl> newsCrawlList = new ArrayList<>();
        newsCrawlList.add(new EastNewsCrawl(newsMapper));
        newsCrawlList.add(new SinaNewsCrawl(newsMapper));
        newsCrawlList.add(new SouhuNewsCrawl(newsMapper));
        newsCrawlList.add(new XueqiuNewsCrawl(newsMapper));
        ExecutorService ex = Executors.newFixedThreadPool(newsCrawlList.size());
        for (AbstractNewsCrawl newsCrawl: newsCrawlList) {
            ex.execute(newsCrawl);
        }
    }

    /**
     * 每月的1号23:00，清理旧新闻
     */
    @Scheduled(cron = "0 0 23 1 * ?")
    public void clearNewsMonthly() {
        int total = newsMapper.count();
        if (total > NEWS_LIMIT_SIZE) {
            // NEWS_REMIN_DAYS 内的新闻数大于 NEWS_LIMIT_SIZE
            // 删除NEWS_REMIN_DAYS之前的新闻
            if (newsMapper.countForDays(NEWS_REMIN_DAYS) > NEWS_LIMIT_SIZE) {
                newsMapper.deleteDaysAgo(NEWS_REMIN_DAYS);
            } else {
                //默认spring 定时任务是单线程池，数据库记录数在此期间不会被改变
                int needToDel = total - NEWS_LIMIT_SIZE;
                newsMapper.deleteFirstNews(needToDel);
            }
        }
    }
}
