package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.HttpUtil;
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
        if(newsCrawl.name.equals("搜狐财经")){//json中data属性才是jason数组
            Map<String, Object> maps= JacksonUtil.json2Map(response);
            Object list= maps.get("data");
            response=JacksonUtil.pojo2Json(list);
        }

        List<Map<String, Object>> convertedListMap = JacksonUtil.jsonArray2ListMap(response);//新闻json对象数组
        if (CollectionUtils.isEmpty(convertedListMap)) return null;
        List<News> newsList = new ArrayList<>();


        if(newsCrawl.name.equals("搜狐财经")){//搜狐财经执行的逻辑
            //遍历
            for (Map<String, Object> map: convertedListMap) {
                String title = (String) map.get(newsCrawl.titleSelector);//标题
                String href ="http://business.sohu.com"+(String) map.get("url");//新闻详情链接
                if(href.contains("null")){//如果href不合法，直接跳过
                    continue;
                }
                String profileHtml = HttpUtil.sendGET(href);//获取新闻详情链接内容

                HTMLNewsParser parser=new HTMLNewsParser(newsCrawl);//需要借用HTMLNewsParser类的两个函数
                String profile = parser.getProfile(profileHtml, newsCrawl.profileSelector,
                        newsCrawl.abstractLength);//新闻详情
                Timestamp timestamp = parser.getTimestamp(profileHtml, newsCrawl.timeSelector,
                        newsCrawl.timeAttr,
                        newsCrawl.timeFormat);//新闻发布时间

                News news = new News(newsCrawl.name, title, href, profile, timestamp);//新建新闻实体
                newsList.add(news);//添加入列表
            }


        }else {//不是搜狐财经时执行的逻辑
            for (Map<String, Object> map: convertedListMap) {
                String title = (String) map.get(newsCrawl.selector);//标题
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

        }



        return newsList;
    }
}
