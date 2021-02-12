package jeebbs.restful.service.news.model;

/**
 * Created by ztwang on 2017/7/3 0003.
 */
public class NewsParserFactory {
    public static NewsParser createNewsParser(AbstractNewsCrawl newsCrawl) {
        if (NewsUtil.LAYOUT_TYPE_HTML.equals(newsCrawl.layout)) {
            return createHtmlNewsParser(newsCrawl);
        }
        if (NewsUtil.LAYOUT_TYPE_JSON.equals(newsCrawl.layout)) {
            return createJsonNewsParser(newsCrawl);
        }
        return null;
    }

    private static NewsParser createHtmlNewsParser(AbstractNewsCrawl newsCrawl) {
        return new HTMLNewsParser(newsCrawl);
    }

    private static NewsParser createJsonNewsParser(AbstractNewsCrawl newsCrawl) {
        return new JSONNewsParser(newsCrawl);
    }
}
