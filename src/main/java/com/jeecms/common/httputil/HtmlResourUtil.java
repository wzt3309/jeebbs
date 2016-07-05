package com.jeecms.common.httputil;

import java.util.List;





public abstract class HtmlResourUtil {		
	protected static HtmlRulesReader reader;
	
	public static final String A_CONTENT="a.content";
	public static final String A_HREF="a.href";
	public static final String A_ID="a.id";
	public static final String A_CLASS="a.class";
	
	public static final String DIV_CONTENT="div.content";
	public static final String DIV_HREF="div.href";
	public static final String DIV_ID="div.id";
	public static final String DIV_CLASS="div.class";
	
	
	
	public static HtmlRulesReader getReader() {
		return reader;
	}

	public static void setReader(HtmlRulesReader reader) {
		HtmlResourUtil.reader = reader;
	}
	
	static{
		HtmlResourUtil.reader=new HtmlRulesReader();
	}
	/**
	 * 根据id返回Tag内的内容
	 * @param id
	 * @return
	 */
	public static String getTextById(String id){return null;}
	/**
	 * 根据clazz返回Tags内的内容
	 * @param clazz
	 * @return
	 */
	public static List<String> getTextByClass(String clazz){return null;}
	/**
	 * 根据css选择器返回Tags内的内容
	 * @param css
	 * @return
	 */
	public static List<String> getTextByCss(String css){return null;}
	/**
	 * 根据id返回Tag内的HTML内容
	 * @param id
	 * @return
	 */
	public static String getHtmlById(String id){return null;}
	/**
	 * 根据clazz返回Tags内的HTML内容
	 * @param clazz
	 * @return
	 */
	public static List<String> getHtmlByClass(String clazz){return null;}
	/**
	 * 根据css选择器返回Tags内的HTML内容
	 * @param css
	 * @return
	 */
	public static List<String> getHtmlByCss(String css){return null;}
	public static String getMessage(String code){
		return HtmlResourUtil.reader.getMessage(code);
	}
	
}
