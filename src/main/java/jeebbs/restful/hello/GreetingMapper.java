package jeebbs.restful.hello;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/6/22 0022.
 */
@Mapper
public interface GreetingMapper {

    @Select("SELECT * FROM greeting WHERE id = #{id}")
    Greeting findById(@Param("id") Long id);

    @Select("SELECT * FROM greeting WHERE content = #{content}")
    Greeting findByContent(@Param("content") String content);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content")
    })
    @Select("SELECT id, content FROM greeting")
    List<Greeting> findAll();

    @Insert("INSERT INTO greeting(content) VALUES(#{content})")
    int insert(@Param("content") String content);

    @Update("UPDATE greeting SET content=#{content} WHERE id = #{id}")
    void update(Greeting bean);

    @Delete("DELETE FROM greeting WHERE id = #{id}")
    void delete(Long id);

    @Insert("INSERT INTO greeting(content) VALUES(#{content})")
    int insertByGreeting(Greeting bean);

    @Insert("INSERT INTO greeting(content) VALUES(#{content, jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

}
