package com.jeecms.common.bbsnews;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.jeecms.common.httputil.HtmlResourParserUtil;
import com.jeecms.common.httputil.HttpUtil;



public abstract class BbsNewsCrawlerBase implements BbsNewsCrawler {	
	
	public static final String HREF="href";
	public static final String TOPIC="topic";
	public static final String ABSTRACT="abstract";
	public static final int HREF_MAX_CUT=18;
	public static final String DEFAULT_ABSTRACT="本条新闻不存在已知的新闻摘要" +
			"，如果想了解本新闻的详细内容，请到其来源网站去查看详细内容，<code>么么哒</code>...";
	public static String getHtmlByGetMethod(String url){
		return HttpUtil.getHtmlByGetMethod(url);
	}	
	protected void init(){};
	/**
	 * 按字数要求返回抓取特定html元素的文本内容
	 * 结尾以[...]表示截取的部分
	 * @param url
	 * @param css
	 * @param textNum
	 * @return
	 */
	public static String getTextContent(String url,String css,int textNum){
		String textContent=getTextContent(url, css);		
		if(textContent!=null&&textContent.length()>2){
			int begin=0;
			int last=textContent.length()-1;
			last=textNum<last?textNum:last;			
			return textContent.substring(begin,last)+"[...]";
		}
		return "";
	}
	/**
	 * 返回抓取特定html元素的文本内容
	 * @param url
	 * @param css
	 * @return
	 */
	public static String getTextContent(String url,String css){
		StringBuffer textContent=new StringBuffer();	
		String htmlContent=getHtmlByGetMethod(url);		
		if(!StringUtils.isBlank(htmlContent)){
			NodeList fieldNodeList=HtmlResourParserUtil.getNodesByConditions(css,htmlContent);
			SimpleNodeIterator itField=fieldNodeList.elements();
//			if(fieldNodeList.size()==1){
//				Node node=fieldNodeList.elementAt(0);
//				String tempStr=node.toPlainTextString();
//				if(tempStr!=null){					
//		            Matcher m = Pattern.compile("\\s*|\t|\r|\n").matcher(tempStr);
//		            tempStr = m.replaceAll("").replace("　", "");					
//					textContent.append(tempStr);
//				}
//				return textContent.toString();
//			}
			while(itField.hasMoreNodes()){
				Node node=itField.nextNode();
				String tempStr=node.toPlainTextString();
				if(tempStr!=null){					
		            Matcher m = Pattern.compile("\\s*|\t|\r|\n").matcher(tempStr);
		            tempStr = m.replaceAll("").replace("　", "");					
					textContent.append(tempStr);
				}
			}
		}
		return textContent.toString();
	}
	/**
	 * getLastNodeText()迭代方法返回的迭代次数
	 * 使用前请先清零
	 */
	public static int itCount=0;
	/**
	 * 以迭代的方式获取某个NodeList块中最后子节点的文本内容
	 * 每个子节点都以||隔开
	 * @param nodeList
	 * @param content
	 */
	public static void getLastNodeTextSplit(NodeList nodeList,StringBuffer content){
		SimpleNodeIterator itField=nodeList.elements();
		while(itField.hasMoreNodes()){
			Node node=itField.nextNode();
			NodeList children=node.getChildren();
			if(children!=null){
				itCount++;
				getLastNodeText(children,content);
			}else{
				String tempStr=node.toPlainTextString();
				if(!StringUtils.isBlank(tempStr)){
					if(content.length()>0){
						content.append("||");
					}
					content.append(tempStr);
				}
			}			
		}
	}
	/**
	 * 以迭代的方式获取某个NodeList块中最后子节点的文本内容	 
	 * @param nodeList
	 * @param content
	 */
	public static void getLastNodeText(NodeList nodeList,StringBuffer content){
		SimpleNodeIterator itField=nodeList.elements();
		while(itField.hasMoreNodes()){
			Node node=itField.nextNode();
			NodeList children=node.getChildren();
			if(children!=null){
				itCount++;
				getLastNodeText(children,content);
			}else{
				String tempStr=node.toPlainTextString();
				if(!StringUtils.isBlank(tempStr)){					
					content.append(tempStr);
				}
			}			
		}
	}
	public static String cutString(String textContent){
		return cutString(textContent,HREF_MAX_CUT);
	}
	public static String cutString(String textContent,int textNum){
		if(textContent!=null&&textContent.length()>2){
			int begin=0;
			int last=textContent.length();			
			if(last<=textNum){
				return textContent.substring(begin,last);
			}else{
				return textContent.substring(begin,textNum)+"...";
			}
			
		}
		return "";
	}
}
