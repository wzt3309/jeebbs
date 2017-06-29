package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.News;
import jeebbs.restful.service.news.model.Parser2News;
import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.JacksonUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/6/30 0030.
 */
public class XueQiuNewsCrwal extends AbstractNewsCrawl {
    private static final Logger LOG = LoggerFactory.getLogger(XueQiuNewsCrwal.class);
    private static final String SOURCE = "xueqiu";

    public XueQiuNewsCrwal() {
        super(SOURCE, LOG);
    }
    @Override
    protected Parser2News getParser() {
        return new XueQiuJsonParse2News();
    }

    private class XueQiuJsonParse2News implements Parser2News {

        @Override
        public List<News> parse2News(String reponse) {
            List<Map<String, Object>> convertedListMap = JacksonUtil.jsonArray2ListMap(reponse);
            if (convertedListMap == null || convertedListMap.isEmpty()) return null;
            List<News> newsList = new ArrayList<>();
            for (Map<String, Object> map: convertedListMap) {
                String title = (String) map.get(selector);

                String text = (String) map.get(profileSelector);
                Elements aElemts = HtmlUtil.getElementsBySelector(text, titleSelector);
                Element aElemt = aElemts != null ? aElemts.first() : null;
                String href = aElemt != null ? aElemt.attr("href") : null;
                if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;
                href = HtmlUtil.normalizeHref(href, url);
                String profile = null;
                if (!StringUtils.isEmpty(text)) {
                    String notInclude = aElemt.outerHtml();
                    int at = text.indexOf(notInclude);
                    at = at <= abstractLength ? at : abstractLength;
                    if (at > 0 && at < text.length())
                        profile = text.substring(0, at).trim();
                }

                Long data_val = (Long) map.get(timeSelector);
                data_val = data_val == null ? System.currentTimeMillis() : data_val;
                Timestamp stmp = new Timestamp(data_val);
                News news = new News(name, title, href, profile, stmp);
                newsList.add(news);
            }
            return newsList;
        }
    }
}
