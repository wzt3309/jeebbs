package jeebbs.restful.service.stockdata.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 股票日交易数据
 * Created by ztwang on 2017/7/19 0019.
 */
public class StockTrade {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "股票标记", position = 2)
    private String symbol;
    @ApiModelProperty(value = "股票代码", position = 3)
    private String code;
    @ApiModelProperty(value = "股票名称", position = 4)
    private String name;
    @ApiModelProperty(value = "现价", position = 5)
    private BigDecimal trade;
    @ApiModelProperty(value = "价格变动", position = 6)
    private BigDecimal pricechange;
    @ApiModelProperty(value = "涨跌幅", position = 7)
    private BigDecimal changepercent;
    @ApiModelProperty(value = "买入价", position = 8)
    private BigDecimal buy;
    @ApiModelProperty(value = "卖出价", position = 9)
    private BigDecimal sell;
    @ApiModelProperty(value = "昨日收盘价", position = 10)
    private BigDecimal settlement;
    @ApiModelProperty(value = "开盘价", position = 11)
    private BigDecimal open;
    @ApiModelProperty(value = "最高价", position = 12)
    private BigDecimal high;
    @ApiModelProperty(value = "最低价", position = 13)
    private BigDecimal low;
    @ApiModelProperty(value = "成交量", position = 14)
    private BigDecimal volume;
    @ApiModelProperty(value = "成交金额", position = 15)
    private BigDecimal amount;
    @ApiModelProperty(value = "时间", example = "yyyy-MM-dd HH:mm:ss", position = 16)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ticktime;
    @ApiModelProperty(value = "市盈率", position = 17)
    private BigDecimal per;
    @ApiModelProperty(value = "市净率", position = 18)
    private BigDecimal pb;
    @ApiModelProperty(value = "总市值", position = 19)
    private BigDecimal mktcap;
    @ApiModelProperty(value = "流通市值", position = 20)
    private BigDecimal nmc;
    @ApiModelProperty(value = "换手率", position = 21)
    private BigDecimal turnoverratio;

    public StockTrade() {
    }

    public StockTrade(String symbol, String code, String name,
                      BigDecimal trade, BigDecimal pricechange, BigDecimal changepercent,
                      BigDecimal buy, BigDecimal sell, BigDecimal settlement, BigDecimal open,
                      BigDecimal high, BigDecimal low, BigDecimal volume, BigDecimal amount,
                      Date ticktime, BigDecimal per, BigDecimal pb, BigDecimal mktcap,
                      BigDecimal nmc, BigDecimal turnoverratio) {
        this.symbol = symbol;
        this.code = code;
        this.name = name;
        this.trade = trade;
        this.pricechange = pricechange;
        this.changepercent = changepercent;
        this.buy = buy;
        this.sell = sell;
        this.settlement = settlement;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.amount = amount;
        this.ticktime = ticktime;
        this.per = per;
        this.pb = pb;
        this.mktcap = mktcap;
        this.nmc = nmc;
        this.turnoverratio = turnoverratio;
    }

    public StockTrade(Long id, String symbol, String code, String name,
                      BigDecimal trade, BigDecimal pricechange, BigDecimal changepercent,
                      BigDecimal buy, BigDecimal sell, BigDecimal settlement, BigDecimal open,
                      BigDecimal high, BigDecimal low, BigDecimal volume, BigDecimal amount,
                      Date ticktime, BigDecimal per, BigDecimal pb, BigDecimal mktcap,
                      BigDecimal nmc, BigDecimal turnoverratio) {
        this.id = id;
        this.symbol = symbol;
        this.code = code;
        this.name = name;
        this.trade = trade;
        this.pricechange = pricechange;
        this.changepercent = changepercent;
        this.buy = buy;
        this.sell = sell;
        this.settlement = settlement;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.amount = amount;
        this.ticktime = ticktime;
        this.per = per;
        this.pb = pb;
        this.mktcap = mktcap;
        this.nmc = nmc;
        this.turnoverratio = turnoverratio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public BigDecimal getTrade() {
        return trade;
    }

    public void setTrade(BigDecimal trade) {
        this.trade = trade;
    }

    public BigDecimal getPricechange() {
        return pricechange;
    }

    public void setPricechange(BigDecimal pricechange) {
        this.pricechange = pricechange;
    }

    public BigDecimal getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(BigDecimal changepercent) {
        this.changepercent = changepercent;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getSettlement() {
        return settlement;
    }

    public void setSettlement(BigDecimal settlement) {
        this.settlement = settlement;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTicktime() {
        return ticktime;
    }

    public void setTicktime(Date ticktime) {
        this.ticktime = ticktime;
    }

    public BigDecimal getPer() {
        return per;
    }

    public void setPer(BigDecimal per) {
        this.per = per;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public BigDecimal getMktcap() {
        return mktcap;
    }

    public void setMktcap(BigDecimal mktcap) {
        this.mktcap = mktcap;
    }

    public BigDecimal getNmc() {
        return nmc;
    }

    public void setNmc(BigDecimal nmc) {
        this.nmc = nmc;
    }

    public BigDecimal getTurnoverratio() {
        return turnoverratio;
    }

    public void setTurnoverratio(BigDecimal turnoverratio) {
        this.turnoverratio = turnoverratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockTrade that = (StockTrade) o;

        return symbol != null ? symbol.equals(that.symbol) : that.symbol == null;
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StockTrade{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", trade=" + trade +
                ", pricechange=" + pricechange +
                ", changepercent=" + changepercent +
                ", buy=" + buy +
                ", sell=" + sell +
                ", settlement=" + settlement +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", amount=" + amount +
                ", ticktime=" + ticktime +
                ", per=" + per +
                ", pb=" + pb +
                ", mktcap=" + mktcap +
                ", nmc=" + nmc +
                ", turnoverratio=" + turnoverratio +
                '}';
    }
}
