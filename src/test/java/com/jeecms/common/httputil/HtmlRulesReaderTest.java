package com.jeecms.common.httputil;

import org.junit.Test;

public class HtmlRulesReaderTest {

	@Test
	public void test() {
		HtmlRulesReader reader=new HtmlRulesReader();
		String msg=	reader.getMessage("test.test01");
		System.out.println(msg);
	
	}

}
