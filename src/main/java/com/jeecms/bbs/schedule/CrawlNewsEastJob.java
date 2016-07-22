package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.bbsnews.impl.EastNews;

public class CrawlNewsEastJob {
private static final Logger log = LoggerFactory.getLogger(CrawlNewsXiuqiuJob.class);
	
	public void execute(){
		try{
			log.info("Crawl News From East Daily Job begin");
			System.out.println("Crawl News From East Daily Job begin");
			EastNews crawlNews=new EastNews();
			List<BbsNews> bbsNews=crawlNews.getTodayBbsNews();
			if(bbsNews!=null){
				for(BbsNews bbsNew:bbsNews){					
					mng.save(bbsNew);
				
				}
			}
			
			System.out.println("Crawl News From East Daily Job end");
			log.info("Crawl News From East Daily Job end");
		}catch(Exception e){
			log.error("Crawl News From East Daily Job Fail",e);
		}
	}
	@Autowired
	private BbsNewsMng mng;
}