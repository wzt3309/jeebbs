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
//yth
@Service
public class Fx168NewsCrawl extends AbstractNewsCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(Fx168NewsCrawl.class);
    private static final String source = "fx168";

    @Autowired
    public Fx168NewsCrawl(NewsMapper newsMapper) {
        super(source, LOG, newsMapper);
    }

    @Override
    protected NewsParser getNewsParser() {//重写AbstractNewsCrawl的方法
        return NewsParserFactory.createNewsParser(this);
    }
}
