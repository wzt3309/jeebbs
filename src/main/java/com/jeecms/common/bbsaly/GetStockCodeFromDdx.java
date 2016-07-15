package com.jeecms.common.bbsaly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 从ddx网获取股票代码与名称的工具类
 * @author wzt
 *
 */
public class GetStockCodeFromDdx {
	
	/**
	 * 股票代码来源网站
	 */
	private  final static String STOCK_CODE_LIST_URL="http://ddx.gubit.cn/js/stockCode.js";
//	/**
//	 * 股票代码名称来源网站---根据股票代码查询
//	 */
//	private  final static String STOCK_CODE_SEARCH="http://hq.sinajs.cn/list=";
	/**
	 * 日志对象
	 */
	private static final Logger log=LoggerFactory.getLogger(GetStockCodeFromDdx.class);
	
	/**
	 *内部类，记录股票代码与名称，
	 *便于之后按照股票代码进行股票日交易信息的查找与抓取
	 */
	public static class StockCode{
		private String stockName;
		private String stockCode;
		public StockCode(){}
		public StockCode(String stockName,String stockCode){
			this.stockCode=stockCode;
			this.stockName=stockName;
		}
		public String getStockName() {
			return stockName;
		}
		public void setStockName(String stockName) {
			this.stockName = stockName;
		}
		public String getStockCode() {
			return stockCode;
		}
		public void setStockCode(String stockCode) {
			this.stockCode = stockCode;
		}
		
	}
	/**
	 * 获得股票代码与股票名称的总和
	 * @return stockCodeList 股票代码与名称的集合
	 * @throws Exception 网络连接错误
	 */
	public static List<StockCode> getStockCodeList(){
		 URL myURL = null;
		 URLConnection con = null;
		 InputStreamReader ins = null;
		 BufferedReader in = null;
		 List<StockCode> stockCodeList=new ArrayList<StockCode>();
		 try{
			 myURL=new URL(STOCK_CODE_LIST_URL);
			 con=myURL.openConnection();
			 ins = new InputStreamReader(con.getInputStream(),"GBK");
			 in = new BufferedReader(ins);
			 Pattern p=Pattern.compile("\\[.*\\]");	
			 Matcher m=p.matcher(in.readLine());
			if(m.find()){
				String[] re=m.group().split("],");
				for(String a:re){
					StockCode sc=createObject(a);
					stockCodeList.add(sc);
//					System.out.println(sc.getStockCode()+sc.getStockName());
				}					
			}
		 }catch(Exception e){
			 log.error("获取股票代码列表失败",e);
		 }		
		return stockCodeList;    
	}
	/**
	 * 解析获取的字段得到股票名称与代码
	 * @param arg 包含股票代码与名称的字段
	 * @return	包装了股票名称与代码对象
	 * @throws Exception 解析错误
	 */
	public static StockCode createObject(String arg)throws Exception{
		String[] s=arg.replaceAll("\\[|\"|\\]", "").split(",");
		
//		 URL myURL = new URL(STOCK_CODE_SEARCH+"sh"+s[0]);
//		 URLConnection con = myURL.openConnection();
//		 InputStreamReader ins =new InputStreamReader(con.getInputStream(),"GBK");
//		 BufferedReader in = new BufferedReader(ins);
//		 Pattern p=Pattern.compile("\".*\"");	
//		 Matcher m=p.matcher(in.readLine());
//		if(m.find()&&m.group().length()>3){
//			StockCode sc=new GetStockCodeFromDdx.StockCode(s[1], "sh"+s[0]);
//			return sc;
//		}
//		else{
//			StockCode sc=new GetStockCodeFromDdx.StockCode(s[1], "sz"+s[0]);
//			return sc;
//		}
		if(s[0].charAt(0)=='6'||s[0].equals("000001")){
			StockCode sc=new GetStockCodeFromDdx.StockCode(s[1], "sh"+s[0]);
			return sc;
		}else{
			StockCode sc=new GetStockCodeFromDdx.StockCode(s[1], "sz"+s[0]);
			return sc;
		}
	}
	
//	/**
//	 * 用于测试的main方法
//	 */
//
//	public static void main(String[] args){
//		 List<StockCode> stockCodeList=GetStockCodeFromDdx.getStockCodeList();
//		 for(StockCode sc:stockCodeList){
//			 System.out.println(sc.getStockCode()+sc.getStockName());
//		 }
//	}

}
