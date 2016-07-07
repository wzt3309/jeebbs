package com.jeecms.common.bbsnews.impl;



import org.junit.Test;

public class EastNewsTest {

	@Test
	public void test() {
		EastNews news=new EastNews();
		System.out.println(news.getTodayBbsNews().size());
		System.out.println(news.getTodayBbsNews());
	}

}
