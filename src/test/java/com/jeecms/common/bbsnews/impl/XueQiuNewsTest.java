package com.jeecms.common.bbsnews.impl;



import org.junit.Test;

public class XueQiuNewsTest {

	@Test
	public void test() {
		//XueQiuNews.getHtmlByGetMethod(XueQiuNews.getUrl());
		XueQiuNews news=new XueQiuNews();
		//System.out.println(news.getNewsMap());
		System.out.println(news.getTodayBbsNews().size());
		System.out.println(news.getTodayBbsNews());
		
	}

}
