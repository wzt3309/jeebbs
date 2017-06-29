package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.News;
import jeebbs.restful.service.news.model.NewsUtil;
import jeebbs.restful.service.news.model.Parser2News;
import jeebbs.restful.util.HttpUtil;
import org.slf4j.Logger;

import java.util.List;

import static jeebbs.restful.service.news.model.NewsUtil.*;
import static jeebbs.restful.service.news.model.NewsUtil.PROP_NEWS_ABSTRACT_LEN;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public abstract class AbstractNewsCrawl implements Runnable{
    private final Logger LOG;
    protected String name;
    protected String url;
    protected String layout;
    protected String selector;
    protected String abstractLength;

    public AbstractNewsCrawl(String source, Logger LOG) {
        this.LOG = LOG;
        this.name = NewsUtil.get(source, PROP_NEWS_NAME);
        this.url = NewsUtil.get(source, PROP_NEWS_URL);
        this.layout = NewsUtil.get(source, PROP_NEWS_LAYOUT);
        this.selector = NewsUtil.get(source, PROP_NEWS_SELECTOR);
        this.abstractLength = NewsUtil.get(source, PROP_NEWS_ABSTRACT_LEN);
    }

    @Override
    public void run() {
        List<News> newsList = getNewsList();
        for (News news: newsList) {
            LOG.debug(news.toString());
        }
    }

    public List<News> getNewsList() {
        String response = HttpUtil.sendGET(url);
        Parser2News parser = getParser();
        if (parser != null) return parser.parse2News(response);
        return null;
    }


    public abstract Parser2News getParser();
}
