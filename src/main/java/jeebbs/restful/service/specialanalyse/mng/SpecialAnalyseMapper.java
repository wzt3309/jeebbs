package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.service.specialanalyse.model.SpecialAnalyse;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@Mapper
public interface SpecialAnalyseMapper {

    @Insert("INSERT INTO `sprcial_analyse` (`radio1`, `radio2`, `radio3`, `radio4`, `date`}) " +
            "VALUE(#{radio1}, #{radio2}, #{radio3}, #{radio4}, #{date})")
    int insert(SpecialAnalyse bean);

    @Select("SELECT * FROM `special_analyse` WHERE `updateDate` = #{updateDate}")
    SpecialAnalyse findByDate(@Param("updateDate")Date updateDate);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "radio3", column = "radio3"),
            @Result(property = "radio4", column = "radio4"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse` WHERE `updateDate` BETWEEN #{from} AND #{to}")
    List<SpecialAnalyse> findListByDate(@Param("from")Date from, @Param("to")Date to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "radio3", column = "radio3"),
            @Result(property = "radio4", column = "radio4"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse` where date_sub(curdate(), INTERVAL #{days} DAY) <= date(`updateDate`)")
    List<SpecialAnalyse> findListByDays(@Param("days")int days);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "radio3", column = "radio3"),
            @Result(property = "radio4", column = "radio4"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse`")
    List<SpecialAnalyse> findAll();


}
