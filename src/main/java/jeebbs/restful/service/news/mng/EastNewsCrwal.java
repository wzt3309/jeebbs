package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.HtmlParse2News;
import jeebbs.restful.service.news.model.News;
import jeebbs.restful.service.news.model.Parser2News;
import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.HttpUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztwang on 2017/6/30 0030.
 */
public class EastNewsCrwal extends AbstractNewsCrawl{
    private static final Logger LOG = LoggerFactory.getLogger(EastNewsCrwal.class);
    private static final String SOURCE = "east";
    public EastNewsCrwal() {
        super(SOURCE, LOG);
    }

    @Override
    protected Parser2News getParser() {
        return new EastHtmlParse2News();
    }

    private class EastHtmlParse2News extends HtmlParse2News {
        EastHtmlParse2News() {
            selector = EastNewsCrwal.this.selector;
        }
        @Override
        protected List<News> parse2News(Elements containers) {
            if (containers == null || containers.isEmpty()) return null;
            List<News> newsList = new ArrayList<>();
            for (Element content: containers) {
                Element titleElem = HtmlUtil.getChildrenBySelector(content, titleSelector, 0);
                String title = titleElem.text();
                String href = titleElem.attr("href");
                if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;
                href = HtmlUtil.normalizeHref(href, url);
                String html = HttpUtil.sendGET(href);
                String profile = getProfile(html, profileSelector, abstractLength);
                Timestamp stmp = getTimestamp(html, timeSelector, null, "yyyy年MM月dd日 HH:mm");
                News news = new News(name, title, href, profile, stmp);
                newsList.add(news);
            }
            return newsList.isEmpty() ? null : newsList;
        }
    }
}
