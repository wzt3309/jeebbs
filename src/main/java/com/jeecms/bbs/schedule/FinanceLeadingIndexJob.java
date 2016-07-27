package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.FinanceLeadingIndex;
import com.jeecms.bbs.manager.FinanceLeadingIndexMng;
import com.jeecms.common.bbsaly.GetFinAndFurLeadingIndex;



public class FinanceLeadingIndexJob {
	@Autowired
	private FinanceLeadingIndexMng mng;
	private static final Logger log = LoggerFactory.getLogger(FinanceLeadingIndexJob.class);
	
	public synchronized void execute() {		
		try{
			Thread current=Thread.currentThread();
			log.info("线程:"+current.getId()+"["+"]"+"------->融资融券领先指数 计算开始");	
			System.out.println("线程:"+current.getId()+"["+"]"+"------->融资融券领先指数 计算开始");
			List<FinanceLeadingIndex> list=GetFinAndFurLeadingIndex.getFinIndexList(1);
			if(list!=null){
				for(FinanceLeadingIndex l:list){
					mng.save(l);
				}
			}
			System.out.println("线程:"+current.getId()+"["+"]"+"<-------融资融券领先指数 计算结束");
			log.info("线程:"+current.getId()+"["+"]"+"<-------融资融券领先指数 计算结束");
		}catch(Exception e){
			log.error("融资融券领先指数 计算失败",e);
		}
		
			
	}

	

}