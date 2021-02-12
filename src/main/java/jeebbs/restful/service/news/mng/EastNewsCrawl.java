package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.AbstractNewsCrawl;
import jeebbs.restful.service.news.model.NewsParser;
import jeebbs.restful.service.news.model.NewsParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by ztwang on 2017/7/3 0003.
 */
@Service
public class EastNewsCrawl extends AbstractNewsCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(EastNewsCrawl.class);
    private static final String source = "east";

    @Autowired
    public EastNewsCrawl(NewsMapper newsMapper) {
        super(source, LOG, newsMapper);
    }

    @Override
    protected NewsParser getNewsParser() {
        return NewsParserFactory.createNewsParser(this);
    }
}
