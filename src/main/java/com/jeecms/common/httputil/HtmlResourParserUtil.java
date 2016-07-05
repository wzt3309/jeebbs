package com.jeecms.common.httputil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlResourParserUtil extends HtmlResourUtil{
	private static final Logger log = LoggerFactory
			.getLogger(HtmlResourParserUtil.class); 
	
	public static String getTextById(String id,String htmlStr){
		String resText=null;
		try {
			Parser parser=new Parser(htmlStr);
			HasAttributeFilter idFilter=new HasAttributeFilter("id",id);
			NodeList idNodeList=parser.extractAllNodesThatMatch(idFilter);			
			Node node = idNodeList.elementAt(0); 
			resText=node.getText();
			
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		
		return resText;
	}
	public static List<String> getTextByClass(String clazz,String htmlStr){
		List<String> resTextList=null;
		final String clazzFinal=clazz;
		try {
			Parser parser=new Parser(htmlStr);
			NodeFilter clazzFilter=new NodeFilter(){				
				@Override
				public boolean accept(Node node){
					if(node instanceof Tag){
						Tag tag=(Tag)node;
						String tagClass=tag.getAttribute("class");
						if(tagClass!=null&&tagClass.indexOf(clazzFinal)>=0){
							return true;
						}						
					}
					return false;
				}				
			};			
			NodeList clazzNodeList=parser.extractAllNodesThatMatch(clazzFilter);			
			int size=clazzNodeList.size();
			if(size>0){
				resTextList=new ArrayList<String>();
				for(int i=0;i<size;i++){
					Node node=clazzNodeList.elementAt(i);
					resTextList.add(node.getText());
				}
			}			
			
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		
		return resTextList;
	}
	public static List<String> getTextByCss(String css,String htmlStr){
		List<String> resTextList=null;
		try{
			Parser parser=new Parser(htmlStr);
			CssSelectorNodeFilter cssNext = new CssSelectorNodeFilter(css);
			NodeList cssNodeList=parser.extractAllNodesThatMatch(cssNext);
			int size=cssNodeList.size();
			if(size>0){
				resTextList=new ArrayList<String>();
				for(int i=0;i<size;i++){
					Node node=cssNodeList.elementAt(i);
					resTextList.add(node.getText());
				}
			}		
		}catch(ParserException e){
			log.error(e.getMessage(),e);
		}
		return resTextList;
	}
	
	public static String getHtmlById(String id,String htmlStr){
		String resText=null;
		try {
			Parser parser=new Parser(htmlStr);
			HasAttributeFilter idFilter=new HasAttributeFilter("id",id);
			NodeList idNodeList=parser.extractAllNodesThatMatch(idFilter);			
			Node node = idNodeList.elementAt(0); 
			resText=node.toHtml();
			
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		
		return resText;
	}
	public static List<String> getHtmlByClass(String clazz,String htmlStr){
		List<String> resTextList=null;
		final String clazzFinal=clazz;
		try {
			Parser parser=new Parser(htmlStr);
			NodeFilter clazzFilter=new NodeFilter(){				
				@Override
				public boolean accept(Node node){
					if(node instanceof Tag){
						Tag tag=(Tag)node;
						String tagClass=tag.getAttribute("class");
						if(tagClass!=null&&tagClass.indexOf(clazzFinal)>=0){
							return true;
						}						
					}
					return false;
				}				
			};			
			NodeList clazzNodeList=parser.extractAllNodesThatMatch(clazzFilter);			
			int size=clazzNodeList.size();
			if(size>0){
				resTextList=new ArrayList<String>();
				for(int i=0;i<size;i++){
					Node node=clazzNodeList.elementAt(i);
					resTextList.add(node.toHtml());
				}
			}			
			
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		
		return resTextList;
	}
	public static List<String> getHtmlByCss(String css,String htmlStr){
		List<String> resTextList=null;
		try{
			Parser parser=new Parser(htmlStr);
			CssSelectorNodeFilter cssNext = new CssSelectorNodeFilter(css);
			NodeList cssNodeList=parser.extractAllNodesThatMatch(cssNext);
			int size=cssNodeList.size();
			if(size>0){
				resTextList=new ArrayList<String>();
				for(int i=0;i<size;i++){
					Node node=cssNodeList.elementAt(i);
					resTextList.add(node.toHtml());
				}
			}		
		}catch(ParserException e){
			log.error(e.getMessage(),e);
		}
		return resTextList;
	}
	public static NodeList getChildren(Node node){		
		return node.getChildren();
	}
	public static Node getFirstChild(Node node){		
		return node.getFirstChild();
	}
	public static Node getLastChild(Node node){
		return node.getLastChild();
	}
	public static Node getParent(Node node){
		return node.getParent();
	}
	public static Node getNborNext(Node node){
		return node.getNextSibling();
	}
	public static Node getNborPrevious(Node node){
		return node.getPreviousSibling();
	}
	
	public static Node getNodesById(String id,String htmlStr){
		Node resNode=null;
		try {
			Parser parser=new Parser(htmlStr);
			HasAttributeFilter idFilter=new HasAttributeFilter("id",id);
			NodeList idNodeList=parser.extractAllNodesThatMatch(idFilter);			
			resNode = idNodeList.elementAt(0); 
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		return resNode;
	}
	public static NodeList getNodesByTagName(String name,String htmlStr){
		NodeList nodeList=null;
		try {
			Parser parser=new Parser(htmlStr);
			TagNameFilter tagFilter=new TagNameFilter(name);
			nodeList=parser.extractAllNodesThatMatch(tagFilter);
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		return nodeList;
	}
	public static NodeList getNodesByClazz(String clazz,String htmlStr){
		NodeList nodeList=null;
		final String clazzFinal=clazz;
		try {
			Parser parser=new Parser(htmlStr);
			NodeFilter clazzFilter=new NodeFilter(){				
				@Override
				public boolean accept(Node node){
					if(node instanceof Tag){
						Tag tag=(Tag)node;
						String tagClass=tag.getAttribute("class");
						if(tagClass!=null&&tagClass.indexOf(clazzFinal)>=0){
							return true;
						}						
					}
					return false;
				}				
			};			
			nodeList=parser.extractAllNodesThatMatch(clazzFilter);
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		return nodeList;
	}
	public static NodeList getNodesByCss(String css,String htmlStr){
		NodeList nodeList=null;
		try{
			Parser parser=new Parser(htmlStr);
			CssSelectorNodeFilter cssNodeFilter = new CssSelectorNodeFilter(css);
			nodeList=parser.extractAllNodesThatMatch(cssNodeFilter);
			
		}catch(ParserException e){
			log.error(e.getMessage(),e);
		}
		return nodeList;
	}
	public static NodeList getNodesByConditions(String condition,String htmlStr){
		if(condition.length()<1){
			return null;
		}
		NodeList nodeList=new NodeList();
		try {
			Parser parser = null;
			String[] conditionOrs=condition.split(";");
			for(String conditionOr:conditionOrs){				
				if(conditionOr!=null&&!"".equals(conditionOr)){
					AndFilter andFilter=new AndFilter();
					NodeList temNodeList=null;
					String[] conditionAnds=conditionOr.split("&&");
					CssSelectorNodeFilter[] cssNodeFilters=new CssSelectorNodeFilter[conditionAnds.length];
					for(int i=0;i<cssNodeFilters.length;i++){
						cssNodeFilters[i]=new CssSelectorNodeFilter(conditionAnds[i]);
					}	
					andFilter.setPredicates(cssNodeFilters);
					parser=new Parser(htmlStr);
					temNodeList=parser.extractAllNodesThatMatch(andFilter);	
					nodeList.add(temNodeList);
				}				
			}
		} catch (ParserException e) {
			log.error(e.getMessage(),e);
		}
		
		return nodeList;
	}	
	
	
	public static String getTagLink(Node node){
		if(node instanceof LinkTag){
			 LinkTag link = (LinkTag)node;  
			 return link.getLink();
		}
		return null;
	}
	public static String getNodeText(Node node){
		return node.getText();
	}
	public static String getNodesText(NodeList nodeList){
		StringBuffer resText=new StringBuffer();
		if(nodeList!=null&&nodeList.size()>0){
			for(int i=0;i<nodeList.size();i++){
				if(resText.length()>0){
					resText.append("\n");
				}
				resText.append(nodeList.elementAt(i).getText());
			}
		}
		return resText.toString();
	}
	public static String getNodeHtml(Node node){
		return node.toHtml();
	}
	public static String getNodesHtml(NodeList nodeList){
		StringBuffer resText=new StringBuffer();
		if(nodeList!=null&&nodeList.size()>0){
			for(int i=0;i<nodeList.size();i++){
				if(resText.length()>0){
					resText.append("\n");
				}
				resText.append(nodeList.elementAt(i).toHtml());
			}
		}
		return resText.toString();
	}
	
	public static NodeList filterByTagName(NodeList nodeList,String tagName){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		TagNameFilter tagFilter=new TagNameFilter(tagName);
		return nodeList.extractAllNodesThatMatch(tagFilter,true);
	}
	public static NodeList filterById(NodeList nodeList,String id){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		HasAttributeFilter idFilter=new HasAttributeFilter("id",id);
		return nodeList.extractAllNodesThatMatch(idFilter,true);
	}
	public static NodeList filterByClazz(NodeList nodeList,String clazz){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		final String clazzFinal=clazz;
		NodeFilter clazzFilter=new NodeFilter(){				
			@Override
			public boolean accept(Node node){
				if(node instanceof Tag){
					Tag tag=(Tag)node;
					String tagClass=tag.getAttribute("class");
					if(tagClass!=null&&tagClass.indexOf(clazzFinal)>=0){
						return true;
					}						
				}
				return false;
			}				
		};
		return nodeList.extractAllNodesThatMatch(clazzFilter,true);
	}
	public static NodeList filterByConditions(NodeList nodeList,String condition){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		NodeList resnodeList=new NodeList();
		String[] conditionOrs=condition.split(";");
		for(String conditionOr:conditionOrs){				
			if(conditionOr!=null&&!"".equals(conditionOr)){
				AndFilter andFilter=new AndFilter();
				NodeList temNodeList=null;
				String[] conditionAnds=conditionOr.split("&&");
				CssSelectorNodeFilter[] cssNodeFilters=new CssSelectorNodeFilter[conditionAnds.length];
				for(int i=0;i<cssNodeFilters.length;i++){
					cssNodeFilters[i]=new CssSelectorNodeFilter(conditionAnds[i]);
				}	
				andFilter.setPredicates(cssNodeFilters);			
				temNodeList=nodeList.extractAllNodesThatMatch(andFilter,true);	
				resnodeList.add(temNodeList);
			}				
		}	
		
		return resnodeList;
	}
	public static List<Map<String,String>> resATags(NodeList nodeList,String...keys){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		List<Map<String,String>> resATags=new ArrayList<Map<String,String>>();
		for(int i=0;i<nodeList.size();i++){
			Map<String,String> resATag=new HashMap<String,String>();
			Node node=nodeList.elementAt(i);
			if(node instanceof Tag){
				Tag tag=(Tag)node;
				if(keys!=null&&keys.length>0){
					for(String key:keys){
						if(key.equals(A_CONTENT)){
							resATag.put(A_CONTENT,tag.toPlainTextString());
							continue;
						}
						resATag.put("a."+key.toLowerCase(),tag.getAttribute(key));
						
					}					
				}else{
					resATag.put(A_HREF,tag.getAttribute("href"));
					resATag.put(A_CONTENT,tag.toPlainTextString());
				}
				resATags.add(resATag);
			}
			
		}
		return resATags;		
	}
	public static List<Map<String,String>> resDivTags(NodeList nodeList,String...keys){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		List<Map<String,String>> resDivTags=new ArrayList<Map<String,String>>();
		for(int i=0;i<nodeList.size();i++){
			Map<String,String> resDivTag=new HashMap<String,String>();
			Node node=nodeList.elementAt(i);
			if(node instanceof Tag){
				Tag tag=(Tag)node;
				if(keys!=null&&keys.length>0){
					for(String key:keys){
						if(key.equals(DIV_CONTENT)){
							resDivTag.put(DIV_CONTENT,tag.toPlainTextString());
							continue;
						}
						resDivTag.put("div."+key.toLowerCase(),tag.getAttribute(key));
						
					}					
				}else{
					resDivTag.put(DIV_ID,tag.getAttribute("id"));
					resDivTag.put(DIV_CLASS,tag.getAttribute("class"));
					resDivTag.put(DIV_CONTENT,tag.toPlainTextString());
				}
				resDivTags.add(resDivTag);
			}
		}
		return resDivTags;
	}
	public static List<Map<String,String>> resAllTags(NodeList nodeList,String...keys){
		if(nodeList==null||nodeList.size()<1){
			return null;
		}
		List<Map<String,String>> resTags=new ArrayList<Map<String,String>>();
		for(int i=0;i<nodeList.size();i++){
			Map<String,String> resTag=new HashMap<String,String>();
			Node node=nodeList.elementAt(i);
			if(node instanceof Tag){
				Tag tag=(Tag)node;
				String tagName=tag.getTagName();
				if(tagName.equalsIgnoreCase("a")){
					if(keys!=null&&keys.length>0){
						for(String key:keys){
							if(key.equals(A_CONTENT)){
								resTag.put(A_CONTENT,tag.toPlainTextString());
								continue;
							}else if(key.indexOf("a.")>=0){
								resTag.put(key.toLowerCase()
										,tag.getAttribute(key.replace("a.", "")));
							}
							
							
						}					
					}else{
						resTag.put(A_HREF,tag.getAttribute("href"));
						resTag.put(A_CONTENT,tag.toPlainTextString());
					}
				}else if(tagName.equalsIgnoreCase("div")){
					if(keys!=null&&keys.length>0){
						for(String key:keys){
							if(key.equals(DIV_CONTENT)){
								resTag.put(DIV_CONTENT,tag.toPlainTextString());
								continue;
							}else if(key.indexOf("div.")>=0){
								resTag.put(key.toLowerCase()
										,tag.getAttribute(key.replace("div.", "")));
							}
							
							
						}					
					}else{
						resTag.put(DIV_ID,tag.getAttribute("id"));
						resTag.put(DIV_CLASS,tag.getAttribute("class"));
						resTag.put(DIV_CONTENT,tag.toPlainTextString());
					}
				}
				
				resTags.add(resTag);
			}
		}
		return resTags;
	}
}