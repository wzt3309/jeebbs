package jeebbs.restful.service.news.mng;

import jeebbs.restful.service.news.model.News;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/7/7 0007.
 */
@Mapper
public interface NewsMapper {
    @Select("SELECT * FROM news WHERE id = #{id}")
    News findById(@Param("id") Long id);

    //24小时自动获取新闻，默认取最新的新闻
    @Select("SELECT * FROM news WHERE source = #{source} ORDER BY stmp DESC")
    List<News> news24h(@Param("source") String source);

    @Select("SELECT * FROM news WHERE title = #{title}")
    List<News> findByTitle(@Param("title") String title);

    @Select("SELECT * FROM news WHERE href = #{href}")
    List<News> findByHref(@Param("href") String href);

    // 程序内控制map和查询字段的有效性, 模糊查找
    @Select({"<script>" +
            "SELECT * FROM news" +
            "<where>" +
                "<foreach collection=\"searchMap\" " +
                    "index=\"name\" item=\"value\" separator=\"AND \">" +
                    "${name} like #{value} " +
                "</foreach>" +
            "</where>" +
            "ORDER BY stmp DESC" +
            "</script>"})
    List<News> findBySearchMap(@Param("searchMap") Map<String, String> searchMap);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news WHERE stmp BETWEEN #{from} AND #{to} ORDER BY stmp DESC")
    List<News> findByStmp(@Param("from") String from, @Param("to") String to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news WHERE stmp <= #{to} ORDER BY stmp DESC")
    List<News> findByStmpTo(@Param("to") String to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news WHERE stmp >= #{from} ORDER BY stmp DESC")
    List<News> findByStmpFrom(@Param("from") String from);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news WHERE datediff(stmp, now()) = 0 ORDER BY stmp DESC")
    List<News> findToday();

    /**
     * 查询days内的所有记录 today-days~today的记录
     * @param days 天数
     * @return 记录
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news where date_sub(curdate(), INTERVAL #{days} DAY) <= date(stmp) ORDER BY stmp DESC")
    List<News> findForDays(@Param("days") int days);

    /**
     * 查询当月的所有记录
     * @return 记录
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news where date_format(stmp, '%Y%m') = date_format(curdate(), '%Y%m') ORDER BY stmp DESC")
    List<News> findMonth();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news ORDER BY stmp DESC LIMIT #{limit}")
    List<News> findAllByLimit(@Param("limit") int limit);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news")
    List<News> findAll();

    @Select("SELECT COUNT(*) FROM news")
    int count();

    /**
     * today-days~today内的记录数量
     * @param days 天数
     * @return 记录数量
     */
    @Select("SELECT COUNT(*) FROM news WHERE date_sub(curdate(), INTERVAL #{days} DAY) <= date(stmp)")
    int countForDays(@Param("days") int days);

    //成功返回1,不是返回主键
    @Insert("INSERT INTO news(source, title, href, profile, stmp) VALUES(" +
            "#{source}, #{title}, #{href}, #{profile}, #{stmp})")
    int insert(News news);

    @Delete("DELETE FROM news WHERE id = #{id}")
    void delete(Long id);

    /**
     * 删除days之前的所有记录，只保留today-days~today的记录
     * @param days 天数
     */
    @Delete("DELETE FROM news WHERE date_sub(curdate(), INTERVAL #{days} DAY) > date(stmp)")
    void deleteDaysAgo(@Param("days")int days);

    @Delete("DELETE FROM news WHERE 1=1 ORDER BY stmp LIMIT #{limit}")
    void deleteFirstNews(@Param("limit") int limit);

    //yth
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "source", column = "source"),
            @Result(property = "title", column = "title"),
            @Result(property = "href", column = "href"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "stmp", column = "stmp")
    })
    @Select("SELECT * FROM news WHERE source = #{source} ORDER BY stmp DESC LIMIT #{limit}")
    List<News> findLimitBySource(@Param("source") String source,@Param("limit") int limit);

}
