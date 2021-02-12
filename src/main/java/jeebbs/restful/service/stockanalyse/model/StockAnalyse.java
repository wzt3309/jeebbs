package jeebbs.restful.service.stockanalyse.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ztwang on 2017/8/22 0022.
 */
public class StockAnalyse {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "股票代码", position = 2)
    private String code;
    @ApiModelProperty(value = "股票名称", position = 3)
    private String name;
    @ApiModelProperty(value = "投资建议", position = 4)
    private String suggestion;
    @ApiModelProperty(value = "现价", position = 5)
    private BigDecimal trade;
    @ApiModelProperty(value = "涨跌幅", position = 6)
    private BigDecimal changepercent;
    @ApiModelProperty(value = "换手率", position = 7)
    private BigDecimal turnoverratio;
    @ApiModelProperty(value = "市盈率", position = 8)
    private BigDecimal per;
    @ApiModelProperty(value = "主力成本", position = 9)
    private BigDecimal main;
    @ApiModelProperty(value = "机构参与度", position = 10)
    private BigDecimal partIn;
    @ApiModelProperty(value = "日期", position = 11)
    private Date date;

    public StockAnalyse() {
    }

    public StockAnalyse(String code, String name, String suggestion,
                        BigDecimal trade, BigDecimal changepercent, BigDecimal turnoverratio,
                        BigDecimal per, BigDecimal main, BigDecimal partIn, Date date) {
        this.id = null;
        this.code = code;
        this.name = name;
        this.suggestion = suggestion;
        this.trade = trade;
        this.changepercent = changepercent;
        this.turnoverratio = turnoverratio;
        this.per = per;
        this.main = main;
        this.partIn = partIn;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public BigDecimal getTrade() {
        return trade;
    }

    public void setTrade(BigDecimal trade) {
        this.trade = trade;
    }

    public BigDecimal getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(BigDecimal changepercent) {
        this.changepercent = changepercent;
    }

    public BigDecimal getTurnoverratio() {
        return turnoverratio;
    }

    public void setTurnoverratio(BigDecimal turnoverratio) {
        this.turnoverratio = turnoverratio;
    }

    public BigDecimal getPer() {
        return per;
    }

    public void setPer(BigDecimal per) {
        this.per = per;
    }

    public BigDecimal getMain() {
        return main;
    }

    public void setMain(BigDecimal main) {
        this.main = main;
    }

    public BigDecimal getPartIn() {
        return partIn;
    }

    public void setPartIn(BigDecimal partIn) {
        this.partIn = partIn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
