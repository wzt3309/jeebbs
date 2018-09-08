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
        //yth
        if(newsCrawl.name.equals("和讯网")){//json中list属性才是jason数组
            Map<String, Object> maps= JacksonUtil.json2Map(response);
            Object list= maps.get("list");
            response=JacksonUtil.pojo2Json(list);
        }

        List<Map<String, Object>> convertedListMap = JacksonUtil.jsonArray2ListMap(response);
        if (CollectionUtils.isEmpty(convertedListMap)) return null;
        List<News> newsList = new ArrayList<>();
        for (Map<String, Object> map: convertedListMap) {
            String title = (String) map.get(newsCrawl.selector);
            String text = (String) map.get(newsCrawl.profileSelector);
            Element aElemt = HtmlUtil.getFirstChildBySelector(text, newsCrawl.titleSelector);
            String href = aElemt != null ? aElemt.attr("href") : null;//获取超链接
            //if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;//yth
            String profile;//yth 设为全局变量
            if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)){//yth，如果链接为空
                if(href==null){
                    href=newsCrawl.baseUrl;//数据库的href不能为null，设为主页面链接
                }
                profile=text;//此时profile为text
                //此时profile只取前面100个字符，防止数据库字符截断
                if(profile.length()>100){
                    profile=profile.substring(0,100);
                }
                //title的格式设置
                if(title.contains("】"))
                {
                    title=title.substring(title.indexOf("【"),title.indexOf("】")+1);
                }else {
                    if(title.length()>30){
                        title=title.substring(0,30);
                    }
                }
            }else {
                href = HtmlUtil.normalizeHref(href, newsCrawl.url);
                profile = null;
                if (!StringUtils.isEmpty(text)) {
                    String notInclude = aElemt.outerHtml();
                    int at = text.indexOf(notInclude);
                    at = at <= newsCrawl.abstractLength ? at : newsCrawl.abstractLength;
                    if (at > 0 && at < text.length())
                        profile = text.substring(0, at).trim();
                }
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
