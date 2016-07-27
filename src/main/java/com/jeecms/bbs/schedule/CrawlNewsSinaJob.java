package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.bbsnews.impl.SinaNews;

public class CrawlNewsSinaJob {
private static final Logger log = LoggerFactory.getLogger(CrawlNewsSinaJob.class);
	
	public synchronized void execute(){
		try{
			Thread current=Thread.currentThread();
			log.info("线程:"+current.getId()+"["+"]"+"Crawl News From Sina Daily Job begin");
			System.out.println("线程:"+current.getId()+"["+"]"+"Crawl News From Sina Daily Job begin");
			SinaNews crawlNews=new SinaNews();
			List<BbsNews> bbsNews=crawlNews.getTodayBbsNews();
			if(bbsNews!=null){
				for(BbsNews bbsNew:bbsNews){					
					mng.save(bbsNew);
				
				}
			}
			System.out.println("线程:"+current.getId()+"["+"]"+"Crawl News From Sina Daily Job end");
			log.info("线程:"+current.getId()+"["+"]"+"Crawl News From Sina Daily Job end");
		}catch(Exception e){
			log.error("Crawl News From Sina Daily Job Fail",e);
		}
	}
	@Autowired
	private BbsNewsMng mng;
}
