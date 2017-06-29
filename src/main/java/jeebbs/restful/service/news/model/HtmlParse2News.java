package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HtmlUtil;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public abstract class HtmlParse2News implements Parser2News{
    protected String selector;

    @Override
    public List<News> parse2News(String reponse) {
        List<News> newsList = new ArrayList<>();
        Elements containers = HtmlUtil.getElementsBySelector(reponse, selector);
        return parse2News(containers);
    }

    protected abstract List<News> parse2News(Elements containers);
}
