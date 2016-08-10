package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.bbsnews.impl.XueQiuNews;

public class CrawlNewsXiuqiuJob {
	private static final Logger log = LoggerFactory.getLogger(CrawlNewsXiuqiuJob.class);
	
	public synchronized void execute(){
		try{
			Thread current=Thread.currentThread();
			log.info("线程:"+current.getId()+"["+"]"+"Crawl News From Xue qiu Daily Job begin");
			System.out.println("线程:"+current.getId()+"["+"]"+"Crawl News From Xue qiu Daily Job begin");
			XueQiuNews crawlNews=new XueQiuNews();
			List<BbsNews> bbsNews=crawlNews.getTodayBbsNews();
			if(bbsNews!=null){
				for(BbsNews bbsNew:bbsNews){					
					mng.save(bbsNew);
					//System.out.println("--------进行中"+current.getId());
				}
			}
			System.out.println("线程:"+current.getId()+"["+"]"+"Crawl News From Xue qiu Daily Job end");
			log.info("线程:"+current.getId()+"["+"]"+"Crawl News From Xue qiu Daily Job end");
		}catch(Exception e){
			log.error("Crawl News From Xue qiu Daily Job Fail",e);
		}
	}
	@Autowired
	private BbsNewsMng mng;
}
