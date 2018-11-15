package jeebbs.restful.service.stockanalyse.mng;

import jeebbs.restful.service.stockanalyse.model.FundFlow;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;


@Mapper
public interface FundFlowMapper {
    //添加
    @Insert("INSERT INTO fund_flow (updateDate, fund_type,fund_index,name,flow_today,flow_10,flow_10_avg,flow_20,flow_20_avg,flow_60,flow_60_avg,flow_120,flow_120_avg) " +
            "VALUES(#{updateDate}, #{fund_type}, #{fund_index}, #{name}, #{flow_today}, #{flow_10}, #{flow_10_avg}, #{flow_20}, #{flow_20_avg}, #{flow_60}, #{flow_60_avg}, #{flow_120}, #{flow_120_avg})")
    int insertFund_Flow(FundFlow bean);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "updateDate", column = "updateDate"),
            @Result(property = "fund_type", column = "fund_type"),
            @Result(property = "fund_index", column = "fund_index"),
            @Result(property = "name", column = "name"),
            @Result(property = "flow_today", column = "flow_today"),
            @Result(property = "flow_10", column = "flow_10"),
            @Result(property = "flow_10_avg", column = "flow_10_avg"),
            @Result(property = "flow_20", column = "flow_20"),
            @Result(property = "flow_20_avg", column = "flow_20_avg"),
            @Result(property = "flow_60", column = "flow_60"),
            @Result(property = "flow_60_avg", column = "flow_60_avg"),
            @Result(property = "flow_120", column = "flow_120"),
            @Result(property = "flow_120_avg", column = "flow_120_avg")
    })
    //获取一定时间段的特定名称的资金流数据
    @Select("SELECT * FROM fund_flow WHERE name= #{name} AND fund_type=#{fund_type} AND updateDate BETWEEN #{from} AND #{to}")
    List<FundFlow> findFundFlowByDateGap(@Param("name")String name, @Param("fund_type")String fund_type, @Param("from")Date from, @Param("to")Date to);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "updateDate", column = "updateDate"),
            @Result(property = "fund_type", column = "fund_type"),
            @Result(property = "fund_index", column = "fund_index"),
            @Result(property = "name", column = "name"),
            @Result(property = "flow_today", column = "flow_today"),
            @Result(property = "flow_10", column = "flow_10"),
            @Result(property = "flow_10_avg", column = "flow_10_avg"),
            @Result(property = "flow_20", column = "flow_20"),
            @Result(property = "flow_20_avg", column = "flow_20_avg"),
            @Result(property = "flow_60", column = "flow_60"),
            @Result(property = "flow_60_avg", column = "flow_60_avg"),
            @Result(property = "flow_120", column = "flow_120"),
            @Result(property = "flow_120_avg", column = "flow_120_avg")
    })
    //获取特定日期某类型的资金流数据
    @Select("SELECT * FROM fund_flow WHERE fund_type=#{fund_type} AND updateDate BETWEEN #{dateFrom} AND #{dateTo} order by updateDate,fund_index")
    List<FundFlow> findFundFlowByDate(@Param("fund_type")String fund_type, @Param("dateFrom")Date dateFrom, @Param("dateTo")Date dateTo);
}
