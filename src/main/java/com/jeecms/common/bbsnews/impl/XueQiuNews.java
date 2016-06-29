package com.jeecms.common.bbsnews.impl;

import java.util.List;

import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.common.bbsnews.BbsNewsCrawler;
import com.jeecms.common.bbsnews.BbsNewsCrawlerBase;

public class XueQiuNews extends BbsNewsCrawlerBase {

	private static final String URL="http://xueqiu.com/today/";
	@Override
	public List<BbsNews> getTodayBbsNews() {
		// TODO Auto-generated method stub
		return null;
	}
	public static String getUrl() {
		return URL;
	}
	
	
}
