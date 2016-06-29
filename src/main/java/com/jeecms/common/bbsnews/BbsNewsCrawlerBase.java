package com.jeecms.common.bbsnews;

import com.jeecms.common.httputil.HttpUtil;



public abstract class BbsNewsCrawlerBase implements BbsNewsCrawler {

	public static String getHtmlByGetMethod(String url){
		return HttpUtil.getHtmlByGetMethod(url);
	}

}
