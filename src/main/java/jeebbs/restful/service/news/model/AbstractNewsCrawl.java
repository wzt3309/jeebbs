package jeebbs.restful.service.news.model;

import jeebbs.restful.service.news.mng.NewsMapper;
import jeebbs.restful.util.HttpUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static jeebbs.restful.service.news.model.NewsUtil.*;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public abstract class AbstractNewsCrawl {
    String name;
    String url;
    String baseUrl;//yth
    String layout;
    String selector;
    String titleSelector;
    String timeSelector;
    String profileSelector;
    String timeAttr;
    String timeFormat;
    int abstractLength;
    private final Logger LOG;
    private NewsMapper newsMapper;

    public AbstractNewsCrawl(String source, Logger LOG, NewsMapper newsMapper) {
        this.LOG = LOG;
        this.name = NewsUtil.get(source, PROP_NEWS_NAME);
        this.url = NewsUtil.get(source, PROP_NEWS_URL);
        this.baseUrl = NewsUtil.get(source, PROP_NEWS_BASEURL);//yth
        this.layout = NewsUtil.get(source, PROP_NEWS_LAYOUT);
        this.selector = NewsUtil.get(source, PROP_NEWS_SELECTOR);

        int len = Integer.valueOf(NewsUtil.DEFAULT_NEWS_PROFILE_LEN);
        try {
            String val = NewsUtil.get(source, PROP_NEWS_PROFILE_LEN);
            val = !StringUtils.isEmpty(val) ? val : NewsUtil.DEFAULT_NEWS_PROFILE_LEN;
            len = Integer.valueOf(val);
        }catch (NumberFormatException e) {
            //ignore
        }
        this.abstractLength = len;
        this.titleSelector = NewsUtil.get(source, PROP_NEWS_SELECTOR_TITLE);
        this.timeSelector = NewsUtil.get(source, PROP_NEWS_SELECTOR_TIME);
        this.profileSelector = NewsUtil.get(source, PROP_NEWS_SELECTOR_PROFILE);
        this.timeAttr = NewsUtil.get(source, PROP_NEWS_TIME_ATTR);
        this.timeFormat = NewsUtil.get(source, PROP_NEWS_TIME_FORMAT);
        this.newsMapper = newsMapper;
    }

    @Transactional
    public void crawl() {
        List<News> newsList = getNewsList();
        if (CollectionUtils.isEmpty(newsList)) {
            LOG.info("Get nothing from url: " + url);
            return;
        }
        for (News news: newsList) {
            LOG.debug(news.toString());
            //news有内容
            if (!isNewsEmpty(news)) {
                //判断数据库中是否已经有了相同的news(title&&source同)
                boolean hasSame = false;
                String title = news.getTitle();
                List<News> sameNewsList= newsMapper.findByTitle(title);
                //sameNewsList不为空，比较是否有相同的新闻
                if (!ObjectUtils.isEmpty(sameNewsList)) {
                    for (News sameNews: sameNewsList) {
                        if (news.equals(sameNews)) {
                            hasSame = true;
                            break;
                        }
                    }
                }

                if (!hasSame) {
                    LOG.debug("INSERT: " + news.toString());
                    newsMapper.insert(news);
                }
            }
        }
    }

    public void run2() {
        List<News> newsList = getNewsList();
        if (CollectionUtils.isEmpty(newsList)) {
            LOG.info("Get nothing from url: " + url);
            return;
        }
        for (News news : newsList) {
            LOG.debug(news.toString());
        }
    }

    private boolean isNewsEmpty(News news) {
        if (ObjectUtils.isEmpty(news)) return true;
        if (org.apache.commons.lang3.StringUtils
                .isAnyEmpty(news.getSource(),
                            news.getTitle(),
                            //news.getHref(),//yth,href可以为空
                            news.getProfile())) return true;
        if (ObjectUtils.isEmpty(news.getStmp())) news.setStmp(new Timestamp(new Date().getTime()));
        return false;
    }

    private List<News> getNewsList() {
        String response = HttpUtil.sendGET(url);
        NewsParser parser = getNewsParser();
        return parser == null ? null : parser.parse2News(response);
    }

    protected abstract NewsParser getNewsParser();
}
