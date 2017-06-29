package jeebbs.restful.service.news.model;

import jeebbs.restful.util.HtmlUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public abstract class HtmlParse2News implements Parser2News{
    protected String selector;

    @Override
    public List<News> parse2News(String reponse) {
        Elements containers = HtmlUtil.getElementsBySelector(reponse, selector);
        return parse2News(containers);
    }

    protected String getProfile(String html, String selector, int len) {
        String profile = null;
        Elements desc = HtmlUtil.getElementsBySelector(html, selector);
        if (desc == null || desc.isEmpty()) return null;
        Element contentElemt = desc.first();
        String content = contentElemt.attr("content");
        if (!StringUtils.isEmpty(content)) {
            Pattern p = Pattern.compile("(\\s*\\S+)");
            Matcher m = p.matcher(content);
            List<String> finds = new ArrayList<>();
            int maxLen = 0;
            int maxIndex = 0;
            int curIndex = 0;
            while (m.find()) {
                String temp = m.group().trim();
                if (maxLen < temp.length()) {
                    maxLen = temp.length();
                    maxIndex = curIndex;
                }
                finds.add(temp);
                curIndex++;
            }

            profile = finds.isEmpty() ? null : finds.get(maxIndex);
            profile = profile.replaceAll("\\s*","");
        }
        int end = len < profile.length() ? len : profile.length();
        return profile == null ? null : profile.substring(0, end);

    }

    protected Timestamp getTimestamp(String html, String selector,
                                     String attrKey, String timeformat) {
        Timestamp res = null;
        long data_val = System.currentTimeMillis();
        Elements times = HtmlUtil.getElementsBySelector(html, selector);
        if (times == null || times.isEmpty()) return new Timestamp(data_val);
        Element timeElem = times.first();
        String data_val_str;
        boolean isLong = true;
        if (!StringUtils.isEmpty(attrKey)) {
            data_val_str = timeElem.attr(attrKey);
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
        } else if (!StringUtils.isEmpty(timeformat)){
            data_val_str = timeElem.ownText().replaceAll("\"", "").trim();
            //"yyyy年MM月dd日 HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat(timeformat);
            try {
                Date date = sdf.parse(data_val_str);
                res = new Timestamp(date.getTime());
            } catch (ParseException e) {
                //ignore
            }
        }

        return res == null ? new Timestamp(data_val) : res;
    }

    protected abstract List<News> parse2News(Elements containers);
}
