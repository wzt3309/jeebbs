package com.jeecms.bbs.schedule;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.StockUpDownRate;
import com.jeecms.bbs.manager.StockUpDownRateMng;
import com.jeecms.common.bbsaly.GetStockDataFromSina;

public class StockUpDownRateJob {
	@Autowired
	private StockUpDownRateMng mng;
	private static final Logger log = LoggerFactory.getLogger(StockUpDownRateJob.class);

	public void execute() {
//		Date logDate=new Date();
//		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String edate=s.format(logDate);
		try{
			System.out.println("------->股票涨跌率计算开始");			
			StockUpDownRate bean=new StockUpDownRate();
			bean.setQiangRuoRate(GetStockDataFromSina.getQiangRuoRate());
			bean.setUpAndDownRate(GetStockDataFromSina.getUpAndDownRate());
			bean.setDate(GetStockDataFromSina.getDateNow());			
			mng.saveRate(bean);
			System.out.println("<-------股票涨跌率计算完成");
		}catch(Exception e){
			log.error("股票涨跌率计算失败",e);
		}
	}
}
