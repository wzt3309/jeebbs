package jeebbs.restful.schedule;

import jeebbs.restful.service.stockanalyse.mng.StockAnalyseMng;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class FundFlowSchedule {

    private static final Logger logger = LoggerFactory.getLogger(FundFlowSchedule.class);
    private StockAnalyseMng mng;

    //每天收市之后自动触发该函数，获取当日资金流数据
    //每天下午6点触发函数
    @Scheduled(cron = "0 0 18 * * ?")
    //@Scheduled(cron = "00 31 16 * * ?") //test
    public void fundFlowUpdate() {
        mng.updateFundFlow();
    }


    @Autowired
    public void setMng(StockAnalyseMng mng) {
        this.mng = mng;
    }

}
