package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.service.specialanalyse.model.FinanceRadio;
import jeebbs.restful.service.specialanalyse.model.StockRadio;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@Mapper
public interface SpecialAnalyseMapper {

    @Insert("INSERT INTO `special_analyse_stockradio` (`radio1`, `radio2`, `updateDate`) " +
            "VALUE(#{radio1}, #{radio2}, #{updateDate})")
    int insertStockRadio(StockRadio bean);

    @Select("SELECT * FROM `special_analyse_stockradio` WHERE `updateDate` = #{updateDate}")
    StockRadio findStockRadioByDate(@Param("updateDate")Date updateDate);

    @Select("SELECT count(*) FROM `special_analyse_stockradio` " +
            "WHERE `radio1`=#{radio1} AND `radio2`=#{radio2}")
    int isStockRadioExisted(StockRadio bean);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_stockradio` WHERE `updateDate` BETWEEN #{from} AND #{to}")
    List<StockRadio> findStockRadioListByDate(@Param("from")Date from, @Param("to")Date to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_stockradio` where date_sub(curdate(), INTERVAL #{days} DAY) <= date(`updateDate`)")
    List<StockRadio> findStockRadioListByDays(@Param("days")int days);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio1", column = "radio1"),
            @Result(property = "radio2", column = "radio2"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_stockradio`")
    List<StockRadio> findStockRadioAll();

    @Insert("INSERT INTO `special_analyse_financeradio` (`radio`, `updateDate`) " +
            "VALUE(#{radio}, #{updateDate})")
    int insertFinanceRadio(FinanceRadio bean);

    @Select("SELECT * FROM `special_analyse_financeradio` WHERE `updateDate` = #{updateDate}")
    FinanceRadio findFinanceRadioByDate(@Param("updateDate")Date updateDate);

    @Select("SELECT count(*) FROM `special_analyse_financeradio` " +
            "WHERE `radio`=#{radio}")
    int isFinanceRadioExisted(FinanceRadio bean);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio", column = "radio"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_financeradio` WHERE `updateDate` BETWEEN #{from} AND #{to}")
    List<FinanceRadio> findFinanceRadioListByDate(@Param("from")Date from, @Param("to")Date to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio", column = "radio"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_financeradio` where date_sub(curdate(), INTERVAL #{days} DAY) <= date(`updateDate`)")
    List<FinanceRadio> findFinanceRadioListByDays(@Param("days")int days);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "radio", column = "radio"),
            @Result(property = "updateDate", column = "updateDate")
    })
    @Select("SELECT * FROM `special_analyse_financeradio`")
    List<FinanceRadio> findFinanceRadioAll();


}
