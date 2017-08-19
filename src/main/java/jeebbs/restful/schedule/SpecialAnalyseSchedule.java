package jeebbs.restful.schedule;

import jeebbs.restful.service.specialanalyse.mng.SpecialAnalyseMng;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ztwang on 2017/8/19 0019.
 */
@Component
public class SpecialAnalyseSchedule {
    private static final Logger logger = LoggerFactory.getLogger(SpecialAnalyseSchedule.class);
    private SpecialAnalyseMng mng;

    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30, initialDelay = 1000)
    public void checkStockRadio() {
        logger.info("Start Check Items in Table `special_analyse_stockradio`");
        mng.checkStockRadio();
        logger.info("Finish Check Items in Table `special_analyse_stockradio`");
    }

//    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    @Scheduled(cron = "0 0 18 * * *")
    public void insertStockRadio() {
        logger.info("Start Insert Item In Table `special_analyse_stockradio`");
        mng.insertStockRadio();
        logger.info("Finish Insert Item In Table `special_analys_stockradioe`");
    }

    @Scheduled(fixedDelay = DateUtils.MILLIS_PER_MINUTE * 30, initialDelay = 1000)
    public void checkFinanceRadio() {
        logger.info("Start Check Items in Table `special_analyse_financeradio`");
        mng.checkFinanceRadio();
        logger.info("Finish Check Items in Table `special_analyse_financeradio`");
    }

//    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    @Scheduled(cron = "0 0 18 * * *")
    public void insertFinanceRadio() {
        logger.info("Start Insert Item In Table `special_analyse_financeradio`");
        mng.insertFinanceRadio();
        logger.info("Finish Insert Item In Table `special_analyse_financeradio`");
    }

    @Autowired
    public void setMng(SpecialAnalyseMng mng) {
        this.mng = mng;
    }
}
