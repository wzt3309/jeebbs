package com.jeecms.common.httputil;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpUtilTest {

	@Test
	public void test() {
		HttpUtil.getHtmlByGetMethod("http://stock.sohu.com/20160722/n460527302.shtml");
	}

}
