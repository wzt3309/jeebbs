package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.JacksonUtil;
import org.jsoup.nodes.Element;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/7/3 0003.
 */
public class JSONNewsParser implements NewsParser {

    protected AbstractNewsCrawl newsCrawl;

    public JSONNewsParser(AbstractNewsCrawl newsCrawl) {
        this.newsCrawl = newsCrawl;
    }
    @Override
    public List<News> parse2News(String response) {
        List<Map<String, Object>> convertedListMap = JacksonUtil.jsonArray2ListMap(response);
        if (CollectionUtils.isEmpty(convertedListMap)) return null;
        List<News> newsList = new ArrayList<>();
        for (Map<String, Object> map: convertedListMap) {
            String title = (String) map.get(newsCrawl.selector);
            String text = (String) map.get(newsCrawl.profileSelector);
            Element aElemt = HtmlUtil.getFirstChildBySelector(text, newsCrawl.titleSelector);
            String href = aElemt != null ? aElemt.attr("href") : null;
            if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;
            href = HtmlUtil.normalizeHref(href, newsCrawl.url);
            String profile = null;
            if (!StringUtils.isEmpty(text)) {
                String notInclude = aElemt.outerHtml();
                int at = text.indexOf(notInclude);
                at = at <= newsCrawl.abstractLength ? at : newsCrawl.abstractLength;
                if (at > 0 && at < text.length())
                    profile = text.substring(0, at).trim();
            }

            Long data_val = (Long) map.get(newsCrawl.timeSelector);
            data_val = data_val == null ? System.currentTimeMillis() : data_val;
            Timestamp timestamp = new Timestamp(data_val);
            News news = new News(newsCrawl.name, title, href, profile, timestamp);
            newsList.add(news);
        }
        return newsList;
    }
}
