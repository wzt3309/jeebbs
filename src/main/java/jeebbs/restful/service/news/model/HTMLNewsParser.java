package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HtmlUtil;
import jeebbs.restful.util.HttpUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public class HTMLNewsParser implements NewsParser {
    protected AbstractNewsCrawl newsCrawl;

    public HTMLNewsParser(AbstractNewsCrawl newsCrawl) {
        this.newsCrawl = newsCrawl;
    }

    @Override
    public List<News> parse2News(String response) {
        Elements containers = HtmlUtil.getElementsBySelector(response, newsCrawl.selector);
        if (HtmlUtil.isEmpty(containers)) return null;
        List<News> newsList = new ArrayList<>();
        for (Element content: containers) {
            Element titleElem = HtmlUtil.getFirstChildBySelector(content, newsCrawl.titleSelector);
            String title = getTitle(titleElem);
            String href = getHref(titleElem, newsCrawl.url);

            //if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue; //yth
            //yth
            String profile;
            Timestamp stmp;
            if(StringUtils.isEmpty(href)){//如果标题不是超链接
                //yth
                if(href==null){
                    href=newsCrawl.baseUrl;//设为主页面链接
                }
                 /*profile = getProfile(newsCrawl.url, newsCrawl.profileSelector,
                        newsCrawl.abstractLength);*/
                profile=HtmlUtil.getFirstChildBySelector(content,newsCrawl.profileSelector).text();
                stmp = getTimestamp(content,newsCrawl.timeSelector);
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

            }else{//如果标题是超链接
                String profileHtml = HttpUtil.sendGET(href);
                profile = getProfile(profileHtml, newsCrawl.profileSelector,
                        newsCrawl.abstractLength);
                stmp = getTimestamp(profileHtml, newsCrawl.timeSelector,
                        newsCrawl.timeAttr,
                        newsCrawl.timeFormat);
            }
            News news = new News(newsCrawl.name, title, href, profile, stmp);
            newsList.add(news);
        }
        return newsList.isEmpty() ? null : newsList;
    }

    protected String getTitle(Element titleElem) {
        if (titleElem == null) return null;
        return titleElem.text();
    }

    protected String getHref(Element titleElem, String defaultURL) {
        if (titleElem == null) return null;
        String href = titleElem.attr("href");
        return HtmlUtil.normalizeHref(href, defaultURL);
    }

    protected String getProfile(String html, String selector, int len) {
        String profile = null;
        Element description = HtmlUtil.getFirstChildBySelector(html, selector);
        String content;
        if (description != null) {
            content = description.attr("content");
        } else {
            content = HtmlUtil.getTitle(html);
        }
        if (!StringUtils.isEmpty(content)) {
            Pattern p = Pattern.compile("(\\s*\\S+)");
            Matcher m = p.matcher(content);
            List<String> finds = new ArrayList<>();
            while (m.find()) {
                String temp = m.group().trim();
                finds.add(temp);
            }
            Collections.sort(finds, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1 != null && o2 != null) return o1.length() - o2.length();
                    else return o1 == null ? (o2 == null ? 0 : -1) : 1;
                }
            });
            profile = finds.get(finds.size() - 1);
        }
        if (!StringUtils.isEmpty(profile)) {
            profile = StringUtils.trimLeadingWhitespace(profile);
            int end = len < profile.length() ? len : profile.length();
            return profile.substring(0, end);
        }
        return null;
    }

    protected Timestamp getTimestamp(String html, String selector, String attr, String format) {
        Timestamp res = null;//返回结果
        long data_val = System.currentTimeMillis();
        Elements times = HtmlUtil.getElementsBySelector(html, selector);
        if (times == null || times.isEmpty()) return new Timestamp(data_val);//如果时间元素为空，则返回当今时间
        Element timeElem = times.first();//第一个元素
        String data_val_str;
        boolean isLong = true;
        if (!StringUtils.isEmpty(attr)) {//此时attr可能是时间格式或者long
            data_val_str = timeElem.attr(attr);
            try {
                data_val = Long.parseLong(data_val_str);
                res = new Timestamp(data_val);
            }catch (NumberFormatException e) {
                //ignore
                isLong = false;
            }

            if (!isLong){
                res = Timestamp.valueOf(data_val_str);
            }
        } else if (!StringUtils.isEmpty(format)){
            data_val_str = timeElem.ownText();//时间元素的文本值
            if (!StringUtils.isEmpty(data_val_str)) {
                data_val_str = StringUtils.trimWhitespace(
                        data_val_str.replaceAll("\"", ""));
            }
            //"yyyy年MM月dd日 HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date = sdf.parse(data_val_str);
                res = new Timestamp(date.getTime());
            } catch (ParseException e) {
                //ignore
            }
        }

        return res == null ? new Timestamp(data_val) : res;
    }

    //yth
    protected Timestamp getTimestamp(Element timeElement, String attr){
        String data_val_str = timeElement.attr(attr);//获取属性值
        Timestamp res = Timestamp.valueOf(data_val_str);
        return res;
    }


}
