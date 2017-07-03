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
            if (StringUtils.isEmpty(title) || StringUtils.isEmpty(href)) continue;
            String profileHtml = HttpUtil.sendGET(href);
            String profile = getProfile(profileHtml, newsCrawl.profileSelector,
                                        newsCrawl.abstractLength);
            Timestamp stmp = getTimestamp(profileHtml, newsCrawl.timeSelector,
                                          newsCrawl.timeAttr,
                                          newsCrawl.timeFormat);
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

    protected Timestamp getTimestamp(String html, String selector,
                                     String attr, String format) {
        Timestamp res = null;
        long data_val = System.currentTimeMillis();
        Elements times = HtmlUtil.getElementsBySelector(html, selector);
        if (times == null || times.isEmpty()) return new Timestamp(data_val);
        Element timeElem = times.first();
        String data_val_str;
        boolean isLong = true;
        if (!StringUtils.isEmpty(attr)) {
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
            data_val_str = timeElem.ownText();
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
}
