package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.AbstractNewsCrawl;
import jeebbs.restful.service.news.model.NewsParser;
import jeebbs.restful.service.news.model.NewsParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ztwang on 2017/7/3 0003.
 */
public class SouhuNewsCrawl extends AbstractNewsCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(SouhuNewsCrawl.class);
    private static final String source = "souhu";
    public SouhuNewsCrawl() {
        super(source, LOG);
    }

    @Override
    protected NewsParser getNewsParser() {
        return NewsParserFactory.createNewsParser(this);
    }
}
