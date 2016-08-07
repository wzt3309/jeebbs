package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.manager.StockmessageMng;
import com.jeecms.common.bbsddx.GetDdxDate;
import com.jeecms.common.bbsddx.GetDdxDateImpl;

public class GetDdxDateJob {
	private static final Logger log=LoggerFactory.getLogger(GetDdxDateJob.class);
	@Autowired
	private StockmessageMng mng;
	public synchronized void execute() {
		try{
			Thread current=Thread.currentThread();
			log.info("线程:"+current.getId()+"["+"]"+"Get Ddx msg  Daily Job begin");
			System.out.println("线程:"+current.getId()+"["+"]"+"Get Ddx msg  Daily Job begin");
			GetDdxDate getDdxDate=new GetDdxDateImpl();
			List<Stockmessage> lists=getDdxDate.getStockmessages();
			if(lists!=null){
				for(Stockmessage stockMsg:lists){
					if(Stockmessage.validate(stockMsg, log)){
						mng.save(stockMsg);
					}
				}
			}
			log.info("线程:"+current.getId()+"["+"]"+"Get Ddx msg  Daily Job end");
			System.out.println("线程:"+current.getId()+"["+"]"+"Get Ddx msg  Daily Job end");
		}catch(Exception e){
			log.error("Get Ddx msg  Daily Job Fail",e);
		}
		
	}
}
