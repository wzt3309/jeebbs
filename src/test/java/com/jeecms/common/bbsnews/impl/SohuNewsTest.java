package com.jeecms.common.bbsnews.impl;



import org.junit.Test;

public class SohuNewsTest {

	@Test
	public void test() {
		SohuNews news=new SohuNews();
		System.out.println(news.getTodayBbsNews());
	}

}
