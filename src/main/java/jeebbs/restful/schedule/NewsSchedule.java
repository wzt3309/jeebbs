package jeebbs.restful.schedule;

import jeebbs.restful.service.news.mng.*;
import jeebbs.restful.service.news.model.AbstractNewsCrawl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class NewsSchedule {
    private static final Logger logger = LoggerFactory.getLogger(NewsSchedule.class);
    private static final int NEWS_LIMIT_SIZE = 3000;    //新闻在数据库中最多保存3000条
    private static final int NEWS_REMIN_DAYS = 3 * 30;  //新闻保存时间为三个月

    private AbstractNewsCrawl eastNewsCrawl;
    private AbstractNewsCrawl sinaNewsCrawl;
    private AbstractNewsCrawl sohuNewsCrawl;
    private AbstractNewsCrawl xueqiuNewsCrawl;
    private NewsMapper newsMapper;
    /**
     * 每天从6:00~23:00,每隔10分钟收集一次新闻
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
//    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void eastNewsCrawlDaily() {
        logger.info("Start Crawling East News");
        eastNewsCrawl.crawl();
        logger.info("Finish Crawling East News");

    }

    /**
     * 每天从6:00~23:00,每隔10分钟收集一次新闻
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
//    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void sinaNewsCrawlDaily() {
        logger.info("Start Crawling Sina News");
        sinaNewsCrawl.crawl();
        logger.info("Finish Crawling Sina News");
    }

    /**
     * 每天从6:00~23:00,每隔10分钟收集一次新闻
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
//    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void sohuNewsCrawlDaily() {
        logger.info("Start Crawling Sohu News");
        sohuNewsCrawl.crawl();
        logger.info("Finish Crawling Sohu News");
    }

    /**
     * 每天从6:00~23:00,每隔10分钟收集一次新闻
     */
    @Scheduled(cron = "0 0/10 6-23 * * ?")
//    @Scheduled(fixedDelay = 3000, initialDelay = 1000)
    public void xueqiuNewsCrawlDaily() {
        logger.info("Start Crawling Xueqiu News");
        xueqiuNewsCrawl.crawl();
        logger.info("Finish Crawling Xueqiu News");
    }

    /**
     * 每月的1号23:00，清理旧新闻
     */
    @Scheduled(cron = "0 0 23 1 * ?")
    public void clearNewsMonthly() {
        logger.info("Start Clean Overdue News");
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
        logger.info("Finish Clean Overdue News");
    }

    @Autowired
    @Qualifier("eastNewsCrawl")
    public void setEastNewsCrawl(AbstractNewsCrawl eastNewsCrawl) {
        this.eastNewsCrawl = eastNewsCrawl;
    }

    @Autowired
    @Qualifier("sinaNewsCrawl")
    public void setSinaNewsCrawl(AbstractNewsCrawl sinaNewsCrawl) {
        this.sinaNewsCrawl = sinaNewsCrawl;
    }

    @Autowired
    @Qualifier("souhuNewsCrawl")
    public void setSohuNewsCrawl(AbstractNewsCrawl sohuNewsCrawl) {
        this.sohuNewsCrawl = sohuNewsCrawl;
    }

    @Autowired
    @Qualifier("xueqiuNewsCrawl")
    public void setXueqiuNewsCrawl(AbstractNewsCrawl xueqiuNewsCrawl) {
        this.xueqiuNewsCrawl = xueqiuNewsCrawl;
    }

    @Autowired
    public void setNewsMapper(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }
}
