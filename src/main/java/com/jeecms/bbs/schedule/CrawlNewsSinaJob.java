package com.jeecms.bbs.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.bbsnews.impl.SinaNews;

public class CrawlNewsSinaJob {
private static final Logger log = LoggerFactory.getLogger(CrawlNewsXiuqiuJob.class);
	
	public void execute(){
		try{
			log.info("Crawl News From Sina Daily Job begin");
			System.out.println("Crawl News From Sina Daily Job begin");
			SinaNews crawlNews=new SinaNews();
			List<BbsNews> bbsNews=crawlNews.getTodayBbsNews();
			for(BbsNews bbsNew:bbsNews){
				if(bbsNews!=null){
					mng.save(bbsNew);
				}
			}
			System.out.println("Crawl News From Sina Daily Job end");
			log.info("Crawl News From Sina Daily Job end");
		}catch(Exception e){
			log.error("Crawl News From Sina Daily Job Fail",e);
		}
	}
	@Autowired
	private BbsNewsMng mng;
}
