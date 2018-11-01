package jeebbs.restful.service.stockanalyse.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yutianhao on 2018/10/31.
 */
public class FundFlow {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "获取日期", position = 2)
    private Date updateDate;
    @ApiModelProperty(value = "类型（行业、概念）", position = 3)
    private String type;
    @ApiModelProperty(value = "序号", position = 4)
    private int index;
    @ApiModelProperty(value = "名称", position = 5)
    private String name;
    @ApiModelProperty(value = "今日主力净流入", position = 6)
    private double flow_today;
    @ApiModelProperty(value = "10日净流入", position = 7)
    private double flow_10;
    @ApiModelProperty(value = "10日净流入平均值", position = 8)
    private double flow_10_avg;
    @ApiModelProperty(value = "20日净流入", position = 9)
    private double flow_20;
    @ApiModelProperty(value = "20日净流入平均值", position = 10)
    private double flow_20_avg;
    @ApiModelProperty(value = "60日净流入", position = 11)
    private double flow_60;
    @ApiModelProperty(value = "60日净流入平均值", position = 12)
    private double flow_60_avg;
    @ApiModelProperty(value = "120日净流入", position = 13)
    private double flow_120;
    @ApiModelProperty(value = "120日净流入平均值", position = 14)
    private double flow_120_avg;

    public FundFlow(Date updateDate, String type, int index,String name,
                    double flow_today, double flow_10,double flow_10_avg, double flow_20,double flow_20_avg,
                    double flow_60, double flow_60_avg,double flow_120,double flow_120_avg) {
        this.id = null;
        this.updateDate = updateDate;
        this.type = type;
        this.index = index;
        this.name = name;
        this.flow_today = flow_today;
        this.flow_10 = flow_10;
        this.flow_10_avg = flow_10_avg;
        this.flow_20 = flow_20;
        this.flow_20_avg = flow_20_avg;
        this.flow_60 = flow_60;
        this.flow_60_avg = flow_60_avg;
        this.flow_120 = flow_120;
        this.flow_120_avg = flow_120_avg;
    }

    public double getFlow_today() {
        return flow_today;
    }

    public void setFlow_today(double flow_today) {
        this.flow_today = flow_today;
    }


}
