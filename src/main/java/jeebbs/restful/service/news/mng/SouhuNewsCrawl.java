package jeebbs.restful.service.news.mng;


import jeebbs.restful.service.news.model.HtmlParse2News;
import jeebbs.restful.service.news.model.News;
import jeebbs.restful.service.news.model.NewsUtil;
import jeebbs.restful.service.news.model.Parser2News;
import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.HttpUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ztwang on 2017/6/29 0029.
 */
public class SouhuNewsCrawl extends AbstractNewsCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractNewsCrawl.class);
    private static final String SOURCE = "souhu";
    private String titleSelector;
    private String timeSelector;
    private String profileSelector;

    public SouhuNewsCrawl() {
        super(SOURCE, LOG);
        this.titleSelector = NewsUtil.get(SOURCE, "selector.title");
        this.timeSelector = NewsUtil.get(SOURCE, "selector.time");
        this.profileSelector = NewsUtil.get(SOURCE, "selector.profile");
    }

    @Override
    public Parser2News getParser() {
        return new SouhuHtmlParse2News();
    }

    private class SouhuHtmlParse2News extends HtmlParse2News{

        SouhuHtmlParse2News() {
            selector = SouhuNewsCrawl.this.selector;
        }

        @Override
        protected List<News> parse2News(Elements containers) {
            if (containers == null || containers.isEmpty()) return null;
            List<News> newsList = new ArrayList<>();
            for (Element content: containers) {
                Element titleElem = HtmlUtil.getChildrenBySelector(content, titleSelector, 0);
                Element timeElem = HtmlUtil.getChildrenBySelector(content, timeSelector, 0);
                String title = titleElem.text();
                String href = titleElem.attr("href");
                // 临时手段
                href = "http:" + href;
                if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;
                String profile = getProfile(href);

                long data_val = System.currentTimeMillis();
                try {
                    data_val = Long.parseLong(timeElem.attr("data-val"));
                }catch (NumberFormatException e) {
                    //ignore
                }
                Timestamp stmp = new Timestamp(data_val);
                News news = new News(name, title, href, profile, stmp);
                newsList.add(news);
            }

            return newsList.isEmpty() ? null : newsList ;
        }

        private String getProfile(String href) {
            String profile = null;
            String html = HttpUtil.sendGET(href);
            Elements article = HtmlUtil.getElementsBySelector(html, profileSelector);
            if (article == null || !article.isEmpty()) return null;
            for (Element p: article) {
                Attributes pAttrs = p.attributes();
                if (pAttrs == null || pAttrs.size() == 0) {
                    profile = p.ownText();
                    if (!StringUtils.isEmpty(profile)) {
                        return profile;
                    }
                }
            }
            return profile;
        }
    }
}
