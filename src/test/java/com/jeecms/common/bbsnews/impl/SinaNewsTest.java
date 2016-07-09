package com.jeecms.common.bbsnews.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class SinaNewsTest {

	@Test
	public void test() {
		SinaNews news=new SinaNews();
		System.out.println(news.getTodayBbsNews().size());
		System.out.println(news.getTodayBbsNews());
	}

}
