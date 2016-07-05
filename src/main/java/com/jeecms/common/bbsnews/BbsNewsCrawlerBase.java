package com.jeecms.common.bbsnews;

import com.jeecms.common.httputil.HttpUtil;



public abstract class BbsNewsCrawlerBase implements BbsNewsCrawler {	
	
	public static final String HREF="href";
	public static final String TOPIC="topic";
	public static final String ABSTRACT="abstract";
	public static String getHtmlByGetMethod(String url){
		return HttpUtil.getHtmlByGetMethod(url);
	}	
	protected void init(){};
	
}
