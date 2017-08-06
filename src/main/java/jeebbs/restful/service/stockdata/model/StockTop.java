package jeebbs.restful.service.stockdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by ztwang on 2017/7/25 0025.
 */
public class StockTop {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "股票代码", position = 2)
    private String code;
    @ApiModelProperty(value = "股票名称", position = 3)
    private String name;
    @ApiModelProperty(value = "涨跌幅", position = 4)
    private BigDecimal pchange; //涨跌幅
    @ApiModelProperty(value = "龙虎榜成交额(万)", position = 5)
    private BigDecimal amount;  //龙虎榜成交额(万)
    @ApiModelProperty(value = "买入额(万)", position = 6)
    private BigDecimal buy;     //买入额(万)
    @ApiModelProperty(value = "占总成交比例", position = 7)
    private BigDecimal brati;   //占总成交比例
    @ApiModelProperty(value = "卖出额(万)", position = 8)
    private BigDecimal sell;    //卖出额(万)
    @ApiModelProperty(value = "占总成交比例", position = 9)
    private BigDecimal sratio;  //占总成交比例
    @ApiModelProperty(value = "上榜原因", position = 10)
    private String reason;  //上榜原因
    @ApiModelProperty(value = "日期", example = "yyyy-MM-dd", position = 11)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;    //日期

    public StockTop() {
    }

    public StockTop(String code, String name,
                    BigDecimal pchange, BigDecimal amount, BigDecimal buy,
                    BigDecimal brati, BigDecimal sell, BigDecimal sratio,
                    String reason, Date date) {
        this.code = code;
        this.name = name;
        this.pchange = pchange;
        this.amount = amount;
        this.buy = buy;
        this.brati = brati;
        this.sell = sell;
        this.sratio = sratio;
        this.reason = reason;
        this.date = date;
    }

    public StockTop(Long id, String code, String name,
                    BigDecimal pchange, BigDecimal amount, BigDecimal buy,
                    BigDecimal brati, BigDecimal sell, BigDecimal sratio,
                    String reason, Date date) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.pchange = pchange;
        this.amount = amount;
        this.buy = buy;
        this.brati = brati;
        this.sell = sell;
        this.sratio = sratio;
        this.reason = reason;
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

    public BigDecimal getPchange() {
        return pchange;
    }

    public void setPchange(BigDecimal pchange) {
        this.pchange = pchange;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getBrati() {
        return brati;
    }

    public void setBrati(BigDecimal brati) {
        this.brati = brati;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getSratio() {
        return sratio;
    }

    public void setSratio(BigDecimal sratio) {
        this.sratio = sratio;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StockTop{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pchange=" + pchange +
                ", amount=" + amount +
                ", buy=" + buy +
                ", brati=" + brati +
                ", sell=" + sell +
                ", sratio=" + sratio +
                ", reason=" + reason +
                ", date=" + date +
                '}';
    }
}
