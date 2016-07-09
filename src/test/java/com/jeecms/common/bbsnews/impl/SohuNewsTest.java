package com.jeecms.common.bbsnews.impl;



import org.junit.Test;

public class SohuNewsTest {

	@Test
	public void test() {
		SohuNews news=new SohuNews();
		StringBuffer hh=new StringBuffer();
//		String ss="";
//		ss=SohuNews.getTextContent("http://business.sohu.com/20160709/n458506819.shtml"
//				, "div#contentText div[itemprop='articleBody']",30);
//		System.out.println(ss);
//		System.out.println("迭代次数:"+SohuNews.itCount);
//		System.out.println(hh);
//		System.out.println(news.getTodayBbsNews());
		System.out.println(news.getTodayBbsNews().size());
		System.out.println(news.getTodayBbsNews());
	}

}
