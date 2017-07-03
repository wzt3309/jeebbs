package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HttpUtil;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static jeebbs.restful.service.news.model.NewsUtil.*;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public abstract class AbstractNewsCrawl implements Runnable{
    private final Logger LOG;
    protected String name;
    protected String url;
    protected String layout;
    protected String selector;
    protected String titleSelector;
    protected String timeSelector;
    protected String profileSelector;
    protected String timeAttr;
    protected String timeFormat;
    protected int abstractLength;

    public AbstractNewsCrawl(String source, Logger LOG) {
        this.LOG = LOG;
        this.name = NewsUtil.get(source, PROP_NEWS_NAME);
        this.url = NewsUtil.get(source, PROP_NEWS_URL);
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
    }

    @Override
    public void run() {
        List<News> newsList = getNewsList();
        if (CollectionUtils.isEmpty(newsList)) {
            LOG.info("Get nothing from url: " + url);
            return;
        }
        for (News news: newsList) {
            LOG.debug(news.toString());
        }
    }

    private List<News> getNewsList() {
        String response = HttpUtil.sendGET(url);
        NewsParser parser = getNewsParser();
        return parser == null ? null : parser.parse2News(response);
    }

    protected abstract NewsParser getNewsParser();
}
