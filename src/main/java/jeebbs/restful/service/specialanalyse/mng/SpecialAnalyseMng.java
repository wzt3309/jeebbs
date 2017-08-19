package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.service.specialanalyse.model.FinanceRadio;
import jeebbs.restful.service.specialanalyse.model.StockRadio;
import jeebbs.restful.util.StockHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@Service
public class SpecialAnalyseMng {
    private static final Logger logger = LoggerFactory.getLogger(StockRadio.class);
    private static final int DEFAULT_DAYS = 30;

    private SpecialAnalyseMapper mapper;
    private StockRadioMng stockRadioMng;
    private FinanceRadioMng financeRadioMng;

    public void insertStockRadio() {
        insertStockRadio(StockHelper.getLatestTradeDate());
    }

    @Transactional
    public void insertStockRadio(Date date) {
        Double radio1 = getStockRadio1(date);
        Double radio2 = getStockRadio2(date);
        Long radio3 = getFinanceRadio(date);
        if (ObjectUtils.allNotNull(radio1, radio2)) {
            StockRadio bean = new StockRadio(radio1, radio2,
                    new java.sql.Date(date == null ? System.currentTimeMillis() : date.getTime()));
            if (mapper.isStockRadioExisted(bean) != 1) {
                mapper.insertStockRadio(bean);
            }
        }
    }

    public void checkStockRadio() {
        checkStockRadio(DEFAULT_DAYS);
    }

    @Transactional
    public void checkStockRadio(int checkDays) {
        List<Date> dateList = StockHelper.getDateList(checkDays);   //获取今天之前的历史交易日期
        if (!CollectionUtils.isEmpty(dateList)) {
            for (Date date: dateList) {
                StockRadio bean = mapper.findStockRadioByDate(new java.sql.Date(date.getTime()));
                if (bean == null) {
                    insertStockRadio(date);
                }
            }
        }
    }

    public void insertFinanceRadio() {
        insertFinanceRadio(StockHelper.LatestTradeDateMinusOfDays(1));
    }

    @Transactional
    public void insertFinanceRadio(Date date) {
        Long radio = getFinanceRadio(date);
        if (radio != null) {
            FinanceRadio bean = new FinanceRadio(radio,
                    new java.sql.Date(date == null ? System.currentTimeMillis() : date.getTime()));
            if (mapper.isFinanceRadioExisted(bean) != 1) {
                mapper.insertFinanceRadio(bean);
            }
        }
    }

    public void checkFinanceRadio() {
        checkFinanceRadio(DEFAULT_DAYS);
    }

    @Transactional
    public void checkFinanceRadio(int checkDays) {
        List<Date> dateList = StockHelper.getDateList(checkDays);   //获取今天之前的历史交易日期
        if (!CollectionUtils.isEmpty(dateList)) {
            for (Date date: dateList) {
                FinanceRadio bean = mapper.findFinanceRadioByDate(new java.sql.Date(date.getTime()));
                if (bean == null) {
                    insertFinanceRadio(date);
                }
            }
        }
    }

    public List<StockRadio> findStockRadioListByDate(Date from, Date to) {
        if (from == null || to == null) {
            return mapper.findStockRadioListByDays(DEFAULT_DAYS);
        }
        return mapper.findStockRadioListByDate(new java.sql.Date(from.getTime()),
                new java.sql.Date(to.getTime()));
    }

    public List<StockRadio> findStockRadioListByDays(int days) {
        return mapper.findStockRadioListByDays(days);
    }

    public List<StockRadio> findStockRadioAll() {
        return mapper.findStockRadioAll();
    }

    public List<FinanceRadio> findFinanceRadioListByDate(Date from, Date to) {
        if (from == null || to == null) {
            return mapper.findFinanceRadioListByDays(DEFAULT_DAYS);
        }
        return mapper.findFinanceRadioListByDate(new java.sql.Date(from.getTime()),
                new java.sql.Date(to.getTime()));
    }

    public List<FinanceRadio> findFinanceRadioListByDays(int days) {
        return mapper.findFinanceRadioListByDays(days);
    }

    public List<FinanceRadio> findFinanceRadioAll() {
        return mapper.findFinanceRadioAll();
    }

    private Long getFinanceRadio(Date date) {
        return financeRadioMng.getRadio(date);
    }

    private Double getStockRadio2(Date date) {
        return stockRadioMng.getRadio2(date);
    }

    private Double getStockRadio1(Date date) {
        return stockRadioMng.getRadio1(date);
    }

    @Autowired
    public void setMapper(SpecialAnalyseMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setStockRadioMng(StockRadioMng stockRadioMng) {
        this.stockRadioMng = stockRadioMng;
    }

    @Autowired
    public void setFinanceRadioMng(FinanceRadioMng financeRadioMng) {
        this.financeRadioMng = financeRadioMng;
    }
}
