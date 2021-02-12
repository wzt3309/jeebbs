package jeebbs.restful.service.stockdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 股票K线数据
 * Created by ztwang on 2017/7/18 0018.
 */
public class StockDaily {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "股票代码", position = 2)
    private String code;
    @ApiModelProperty(value = "日期", example = "yyyy-MM-dd", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    @ApiModelProperty(value = "开盘价", position = 4)
    private BigDecimal open;
    @ApiModelProperty(value = "最高价", position = 5)
    private BigDecimal high;
    @ApiModelProperty(value = "收盘价", position = 6)
    private BigDecimal close;
    @ApiModelProperty(value = "最低价", position = 7)
    private BigDecimal low;
    @ApiModelProperty(value = "成交量", position = 8)
    private BigDecimal volume;
    @ApiModelProperty(value = "价格变动", position = 9)
    private BigDecimal price_change;
    @ApiModelProperty(value = "涨跌幅", position = 10)
    private BigDecimal p_change;
    @ApiModelProperty(value = "5日均价", position = 11)
    private BigDecimal ma5;
    @ApiModelProperty(value = "10日均价", position = 12)
    private BigDecimal ma10;
    @ApiModelProperty(value = "20日均价", position = 13)
    private BigDecimal ma20;
    @ApiModelProperty(value = "5日均量", position = 14)
    private BigDecimal v_ma5;
    @ApiModelProperty(value = "10日均量", position = 15)
    private BigDecimal v_ma10;
    @ApiModelProperty(value = "20日均量", position = 16)
    private BigDecimal v_ma20;
    @ApiModelProperty(value = "换手率", position = 17)
    private BigDecimal turnover;

    public StockDaily() {
    }

    public StockDaily(String code, Date date, BigDecimal open, BigDecimal high,
                      BigDecimal close, BigDecimal low, BigDecimal volume,
                      BigDecimal price_change, BigDecimal p_change,
                      BigDecimal ma5, BigDecimal ma10, BigDecimal ma20,
                      BigDecimal v_ma5, BigDecimal v_ma10, BigDecimal v_ma20,
                      BigDecimal turnover) {
        this.code = code;
        this.date = date;
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
        this.price_change = price_change;
        this.p_change = p_change;
        this.ma5 = ma5;
        this.ma10 = ma10;
        this.ma20 = ma20;
        this.v_ma5 = v_ma5;
        this.v_ma10 = v_ma10;
        this.v_ma20 = v_ma20;
        this.turnover = turnover;
    }

    public StockDaily(Long id,
                      String code, Date date, BigDecimal open, BigDecimal high,
                      BigDecimal close, BigDecimal low, BigDecimal volume,
                      BigDecimal price_change, BigDecimal p_change,
                      BigDecimal ma5, BigDecimal ma10, BigDecimal ma20,
                      BigDecimal v_ma5, BigDecimal v_ma10, BigDecimal v_ma20,
                      BigDecimal turnover) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
        this.price_change = price_change;
        this.p_change = p_change;
        this.ma5 = ma5;
        this.ma10 = ma10;
        this.ma20 = ma20;
        this.v_ma5 = v_ma5;
        this.v_ma10 = v_ma10;
        this.v_ma20 = v_ma20;
        this.turnover = turnover;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getPrice_change() {
        return price_change;
    }

    public void setPrice_change(BigDecimal price_change) {
        this.price_change = price_change;
    }

    public BigDecimal getP_change() {
        return p_change;
    }

    public void setP_change(BigDecimal p_change) {
        this.p_change = p_change;
    }

    public BigDecimal getMa10() {
        return ma10;
    }

    public BigDecimal getMa5() {
        return ma5;
    }

    public void setMa5(BigDecimal ma5) {
        this.ma5 = ma5;
    }

    public void setMa10(BigDecimal ma10) {
        this.ma10 = ma10;
    }

    public BigDecimal getMa20() {
        return ma20;
    }

    public void setMa20(BigDecimal ma20) {
        this.ma20 = ma20;
    }

    public BigDecimal getV_ma5() {
        return v_ma5;
    }

    public void setV_ma5(BigDecimal v_ma5) {
        this.v_ma5 = v_ma5;
    }

    public BigDecimal getV_ma10() {
        return v_ma10;
    }

    public void setV_ma10(BigDecimal v_ma10) {
        this.v_ma10 = v_ma10;
    }

    public BigDecimal getV_ma20() {
        return v_ma20;
    }

    public void setV_ma20(BigDecimal v_ma20) {
        this.v_ma20 = v_ma20;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockDaily that = (StockDaily) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockDaily{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", close=" + close +
                ", low=" + low +
                ", volume=" + volume +
                ", price_change=" + price_change +
                ", p_change=" + p_change +
                ", ma10=" + ma10 +
                ", ma20=" + ma20 +
                ", v_ma5=" + v_ma5 +
                ", v_ma10=" + v_ma10 +
                ", v_ma20=" + v_ma20 +
                ", turnover=" + turnover +
                '}';
    }
}
