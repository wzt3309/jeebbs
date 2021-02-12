package jeebbs.restful.service.stockdata.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by ztwang on 2017/7/18 0018.
 */
public class StockDailyBuilder {
    private StockDaily stockDaily;
    private StockDailyBuilder(String code) {
        stockDaily = new StockDaily();
        stockDaily.setCode(code);
    }

    public static StockDailyBuilder newBuilder(String code) {
        return new StockDailyBuilder(code);
    }

    public StockDailyBuilder setDate(Date date) {
        this.stockDaily.setDate(date);
        return this;
    }

    public StockDailyBuilder setOpen(BigDecimal open) {
        this.stockDaily.setOpen(open);
        return this;
    }

    public StockDailyBuilder setHigh(BigDecimal high) {
        this.stockDaily.setHigh(high);
        return this;
    }

    public StockDailyBuilder setClose(BigDecimal close) {
        this.stockDaily.setClose(close);
        return this;
    }

    public StockDailyBuilder setLow(BigDecimal low) {
        this.stockDaily.setLow(low);
        return this;
    }

    public StockDailyBuilder setVolume(BigDecimal volume) {
        this.stockDaily.setVolume(volume);
        return this;
    }

    public StockDailyBuilder setPrice_change(BigDecimal price_change) {
        this.stockDaily.setPrice_change(price_change);
        return this;
    }

    public StockDailyBuilder setP_change(BigDecimal p_change) {
        this.stockDaily.setP_change(p_change);
        return this;
    }

    public StockDailyBuilder setMa5(BigDecimal ma5) {
        this.stockDaily.setMa5(ma5);
        return this;
    }

    public StockDailyBuilder setMa10(BigDecimal ma10) {
        this.stockDaily.setMa10(ma10);
        return this;
    }

    public StockDailyBuilder setMa20(BigDecimal ma20) {
        this.stockDaily.setMa20(ma20);
        return this;
    }

    public StockDailyBuilder setV_ma5(BigDecimal v_ma5) {
        this.stockDaily.setV_ma5(v_ma5);
        return this;
    }

    public StockDailyBuilder setV_ma10(BigDecimal v_ma10) {
        this.stockDaily.setV_ma10(v_ma10);
        return this;
    }

    public StockDailyBuilder setV_ma20(BigDecimal v_ma20) {
        this.stockDaily.setV_ma20(v_ma20);
        return this;
    }

    public StockDailyBuilder setTurnover(BigDecimal turnover) {
        this.stockDaily.setTurnover(turnover);
        return this;
    }

    public StockDaily build() {
        return this.stockDaily;
    }
}
