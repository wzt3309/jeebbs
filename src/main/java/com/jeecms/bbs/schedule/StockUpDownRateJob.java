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

	public synchronized void execute() {
//		Date logDate=new Date();
//		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String edate=s.format(logDate);
		try{
			Thread current=Thread.currentThread();
			log.info("线程:"+current.getId()+"["+"]"+"------->股票涨跌率计算开始");	
			System.out.println("线程:"+current.getId()+"["+"]"+"------->股票涨跌率计算开始");
			StockUpDownRate bean=new StockUpDownRate();
			GetStockDataFromSina newStart=new GetStockDataFromSina();
			bean.setQiangRuoRate(newStart.getQiangRuoRate());
			bean.setUpAndDownRate(newStart.getUpAndDownRate());
			bean.setDate(newStart.getDateNow());			
			mng.saveRate(bean);
			System.out.println("线程:"+current.getId()+"["+"]"+"<-------股票涨跌率计算完成");
			log.info("线程:"+current.getId()+"["+"]"+"<-------股票涨跌率计算完成");
		}catch(Exception e){
			log.error("股票涨跌率计算失败",e);
		}
	}
}
