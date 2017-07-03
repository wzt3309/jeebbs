package jeebbs.restful.service.news.model;

import java.util.List;

/**
 * Created by ztwang on 2017/6/29 0029.
 */
public interface NewsParser {
    List<News> parse2News(String response);
}
