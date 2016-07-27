package com.jeecms.common.bbsaly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.common.bbsaly.GetStockCodeFromDdx.StockCode;


/**
 * 从Sina股票API接口获取股票数据的工具类
 * @author wzt3309
 *
 */
public class GetStockDataFromSina {
	
	private static final String STOCK_DATE_LIST_URL="http://hq.sinajs.cn/list=";
	/**
	 * 日志对象
	 */
	private static final Logger log=LoggerFactory.getLogger(GetStockDataFromSina.class);
	private  List<String[]> dataStr=new ArrayList<String[]>();
	private  List<StockDataSina> stockDateSinaList=new ArrayList<StockDataSina>();
	
	public GetStockDataFromSina(){
		getStockDataStrList();
	}	
	
	public  class StockDataSina implements Serializable {
		
		private Integer id;				//数据库自增标识id
		private String stockCode;		//股票代码	0
		private String stockName;		//股票名称	1
		private Double openPriceToday;	//今日开盘价 2
		private Double endPriceYesDay;	//昨日收盘价 3
		private Double nowPrice;		//现价 4
		private Double upOrDownRate;	//涨跌幅 
		private Integer upOrDown;		//涨跌标识符，：涨：1 平：0 跌：-1
		private Double topPrice;		//最高价 5
		private Double downPrice;		//最低价 6
		private Double buyPrice;		//竞买价 7
		private Double soldPrice;		//竞卖价8
		private Double turnoverNum;		//成交股票数 9
		private Double turnoverMony;	//成交金额 10
		
		
		private Date date;				//日期与id作为共同主键 31
		
		
		public StockDataSina(){
			super();
		}
		public StockDataSina(String stockCode, String stockName,
				Double openPriceToday, Double endPriceYesDay, Double nowPrice,
				Double upOrDownRate,Integer upOrDown, Double topPrice, Double downPrice,
				Double buyPrice, Double soldPrice, Double turnoverNum,
				Double turnoverMony,Date date) {
			super();		
			this.stockCode = stockCode;
			this.stockName = stockName;
			this.openPriceToday = openPriceToday;
			this.endPriceYesDay = endPriceYesDay;
			this.nowPrice = nowPrice;
			this.upOrDownRate = upOrDownRate;
			this.upOrDown=upOrDown;
			this.topPrice = topPrice;
			this.downPrice = downPrice;
			this.buyPrice = buyPrice;
			this.soldPrice = soldPrice;
			this.turnoverNum = turnoverNum;
			this.turnoverMony = turnoverMony;		
			this.date = date;
		}
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getStockCode() {
			return stockCode;
		}

		public void setStockCode(String stockCode) {
			this.stockCode = stockCode;
		}

		public String getStockName() {
			return stockName;
		}

		public void setStockName(String stockName) {
			this.stockName = stockName;
		}

		public Double getOpenPriceToday() {
			return openPriceToday;
		}

		public void setOpenPriceToday(Double openPriceToday) {
			this.openPriceToday = openPriceToday;
		}

		public Double getEndPriceYesDay() {
			return endPriceYesDay;
		}

		public void setEndPriceYesDay(Double endPriceYesDay) {
			this.endPriceYesDay = endPriceYesDay;
		}

		public Double getNowPrice() {
			return nowPrice;
		}

		public void setNowPrice(Double nowPrice) {
			this.nowPrice = nowPrice;
		}

		public Double getUpOrDownRate() {
			return upOrDownRate;
		}

		public void setUpOrDownRate(Double upOrDownRate) {
			this.upOrDownRate = upOrDownRate;
		}
		
		public Integer getUpOrDown() {
			return upOrDown;
		}
		public void setUpOrDown(Integer upOrDown) {
			this.upOrDown = upOrDown;
		}
		public Double getTopPrice() {
			return topPrice;
		}

		public void setTopPrice(Double topPrice) {
			this.topPrice = topPrice;
		}

		public Double getDownPrice() {
			return downPrice;
		}

		public void setDownPrice(Double downPrice) {
			this.downPrice = downPrice;
		}

		public Double getBuyPrice() {
			return buyPrice;
		}

		public void setBuyPrice(Double buyPrice) {
			this.buyPrice = buyPrice;
		}

		public Double getSoldPrice() {
			return soldPrice;
		}

		public void setSoldPrice(Double soldPrice) {
			this.soldPrice = soldPrice;
		}

		public Double getTurnoverNum() {
			return turnoverNum;
		}

		public void setTurnoverNum(Double turnoverNum) {
			this.turnoverNum = turnoverNum;
		}

		public Double getTurnoverMony() {
			return turnoverMony;
		}

		public void setTurnoverMony(Double turnoverMony) {
			this.turnoverMony = turnoverMony;
		}
		
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		@Override
		public String toString() {
			return "StockDateSina [id=" + id + ", stockCode=" + stockCode
					+ ", stockName=" + stockName + ", openPriceToday="
					+ openPriceToday + ", endPriceYesDay=" + endPriceYesDay
					+ ", nowPrice=" + nowPrice + ", upOrDownRate=" + upOrDownRate
					+ ", upOrDown=" + upOrDown + ", topPrice=" + topPrice
					+ ", downPrice=" + downPrice + ", buyPrice=" + buyPrice
					+ ", soldPrice=" + soldPrice + ", turnoverNum=" + turnoverNum
					+ ", turnoverMony=" + turnoverMony + ", date=" + date + "]";
		}
		
		
	}
	/**
	 * 返回上涨、平、下跌的股票数目
	 * @return
	 * @throws Exception 
	 */
	public  Integer[] getUpAndDownNum(){
		Integer[] result={new Integer("0"),new Integer("0"),new Integer("0")};
		List<String[]> stockCodeList=dataStr;	    
		for(String[] arg:stockCodeList){				
			try {				
				if(arg!=null&&arg.length>4){
					 Integer upOrDown=getupOrDown(arg[3],arg[4]);
					 if(upOrDown==1)
						 result[0]++;
					 else if(upOrDown==0)
						 result[1]++;
					 else if(upOrDown==-1)
						 result[2]++;
				 }
			} catch (Exception e) {				
				log.error("返回上涨、平、下跌的股票数目出错",e);
			}		  
		}
		log.info("返回上涨、平、下跌的股票数目为："+result[0]+","+result[1]+","+result[2]);
		System.out.println();
		return result;
	}	
	/**
	 * 返回涨停\跌停的股票数目
	 * @return
	 * @throws Exception 
	 */
	public  Integer[] getTopAndBottomNum(){		
		Integer[] result={new Integer("0"),new Integer("0")};
		List<String[]> stockCodeList=dataStr;	   
		for(String[] arg:stockCodeList){								
			if(arg!=null&&arg.length>4){				
				BigDecimal upOrDownRateB=getupOrDownRate(arg[3],arg[4]);				
				BigDecimal standUp=new BigDecimal("9.50");
				BigDecimal standDown=new BigDecimal("-9.50");
				if(upOrDownRateB.compareTo(standUp)!=-1)
					result[0]++;
				else if(upOrDownRateB.compareTo(standDown)!=1)
					result[1]++;
			 }
		}
		log.info("返回涨停,跌停的股票数目："+result[0]+","+result[1]);
		System.out.println();
		return result;
	}	
	/**
	 * 返回股票强弱比 （涨停-跌停）/(涨停+跌停)
	 * @return
	 * @throws Exception 
	 */
	public  Double getQiangRuoRate(){
		Integer[] topAndBottom=getTopAndBottomNum();
		Double top=new Double(topAndBottom[0]);
		Double bottom=new Double(topAndBottom[1]);
		//System.out.println("top,bottom:"+top+" "+bottom);
		Double result=(top-bottom)/(top+bottom);
		result=new Double(new DecimalFormat("0.00").format(result*100));
		log.info("返回股票强弱比 （涨停-跌停）/(涨停+跌停)："+result);
		return result;
	}
	/**
	 * 返回股票涨跌比 （上涨-下跌）/（上涨+下跌）
	 * @return
	 * @throws Exception 
	 */
	public  Double getUpAndDownRate(){
		Integer[] topAndDown= getUpAndDownNum();
		Double top=new Double(topAndDown[0]);
		Double bottom=new Double(topAndDown[2]);
		Double result=(top-bottom)/(top+bottom);
		result=new Double(new DecimalFormat("0.00").format(result*100));
		log.info("返回股票涨跌比 （上涨-下跌）/（上涨+下跌）："+result);
		return result;
	}
	/**
	 * 返回股票数据的字符串数组形式
	 * @return
	 * @throws Exception 
	 */
	public  List<String[]> getStockDataStrList(){
			//dataStr.clear();
		 //获取所有的股票代码
		 List<StockCode> stockCodeList=GetStockCodeFromDdx.getStockCodeList();
		 System.out.println("获取股票代码数量------>"+stockCodeList.size());
		 log.info("获取股票代码数量------>"+stockCodeList.size());
		 ConsoleProgressBar cpb = new ConsoleProgressBar(0, stockCodeList.size(), 20, '=',"getStockDataStrList()");
		 int i=1;
		 for(StockCode sc:stockCodeList){	
			//进度条 开始
				if(i%50==1)
					cpb.show(i);
			     i++;
			   //进度条 结束	
			 String code=sc.getStockCode();		
			 String[] arg=getStockDataStr(code);				
			 if(arg!=null){
				//测试 开始
//				 System.out.print("获取股票数据字符串------>");				
//					for(String s:arg)
//						System.out.print("["+s+"]");
//				 System.out.println();
					//测试 结束
				dataStr.add(arg);
			 }			
		 }	
		 System.out.println("获取股票代码形成的  dataStr  数量------>"+dataStr.size());
		 log.info("获取股票代码形成的  dataStr  数量------>"+dataStr.size());
		 return dataStr;
	}
	/**
	 * 根据股票代码返回股票数据字符串数组，数组长度必大于1(有数据)不然则返回null
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private  String[] getStockDataStr(String code){
		String[] arg=null;
		 String url=STOCK_DATE_LIST_URL+code;
		 URL myUrl;
		 URLConnection con;
		 InputStreamReader ins;
		try {
			myUrl = new URL(url);
			con = myUrl.openConnection();
			ins=new InputStreamReader(con.getInputStream(),"GBK");
			BufferedReader in = new BufferedReader(ins);
			Pattern p=Pattern.compile("\".*\"");	
			Matcher m=p.matcher(in.readLine());
			if(m.find()){
				arg=(code+","+m.group()).replaceAll("\"", "").split(","); 			
			}			
		} catch (Exception e) {			
			log.error("返回股票数据字符串数组出错",e);
		}		  
		return arg;		
	}
	/**
	 * 获取所有股票的所有数据
	 * @return 所有股票的所有数据的集合	 
	 */
	public  List<StockDataSina> getStockDataSinaList(){		
		 List<String[]> stockDataStr=dataStr;
		 if(dataStr!=null){
			 for(String[] sds:stockDataStr){			
				stockDateSinaList.add(getStockDataSina(sds));
			} 
		 }		 	
		 return stockDateSinaList;
	}
	/**
	 * 根据单个股票数据的字符串数组，创建保存股票数据的对象 StockDataSina
	 * @param code
	 * @param arg
	 * @return 保存股票数据的对象 StockDataSina
	 * @throws Exception
	 */
	private  StockDataSina getStockDataSina(String[] s){			
		Double upOrDownRate=getupOrDownRate(s[3],s[4]).doubleValue();			
		Integer upOrDown=getupOrDown(s[3],s[4]);	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		StockDataSina sds=null;
		Date date;
		try {
			date = dateFormat.parse(s[31]);
			sds=new GetStockDataFromSina.StockDataSina(
					s[0],s[1],new Double(s[2]),new Double(s[3]),new Double(s[4]),upOrDownRate,upOrDown,
					new Double(s[5]),new Double(s[6]),new Double(s[7]),new Double(s[8]),new Double(s[9])
					,new Double(s[10]),date);
		} catch (ParseException e) {				
			log.error("创建保存股票数据的对象出错",e);
		}			
		return sds;			
	}
	/**
	 * 返回涨跌率
	 * @param openStr
	 * @param endStr
	 * @return
	 */
	private  BigDecimal getupOrDownRate(String openStr,String endStr){
		Double open=new Double(openStr);
		Double end=new Double(endStr);
		BigDecimal upOrDownRate=new BigDecimal("0.00");
		if(open>0&&end>0){
			upOrDownRate=new BigDecimal(100*(end-open)/open).setScale(2,BigDecimal.ROUND_HALF_UP);		
		}	
		return upOrDownRate;
	}
	/**
	 * 返回是否涨跌标记 //涨跌标识符，：涨：1 平：0 跌：-1
	 * @param openStr
	 * @param endStr
	 * @return
	 */
	private  Integer getupOrDown(String openStr,String endStr){
		BigDecimal open=new BigDecimal(openStr);
		BigDecimal end=new BigDecimal(endStr);
		return end.compareTo(open);
	}
	/**
	 * 获取股票当前日期
	 * @return
	 * @throws Exception
	 */
	public  Date getDateNow(){
		String[] forDate=getStockDataStr("sz399001");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = dateFormat.parse(forDate[31]);
		} catch (ParseException e) {			
			log.error("获取股票当前日期出错",e);
		}
		log.info("获取股票当前日期成功"+forDate[31]);
		return date;
	}
	public  List<String[]> getDataStr() {
		return dataStr;
	}	
	
	
//	/**
//	 * @param args
//	 * @throws Exception 
//	 */
//	public static void main(String[] args) throws Exception {
//		 List<StockDataSina> stockDataSinaList=getStockDataSinaList();
//		 List<String[]> stockDataStr=getStockDataStrList();
//		 System.out.println(stockDataStr.size());
//		 for(StockDataSina sds:stockDataSinaList)
//			 System.out.println(stockDataSinaList.get(100).toString());
//		Double upOrDownRateD=getupOrDownRate("20.23","23.12");	
//		System.out.println(upOrDownRateD);
//		 BigDecimal upOrDownRateB=new BigDecimal(upOrDownRateD);
//		 BigDecimal stand=new BigDecimal("-9.5");
//		 if(upOrDownRateB.compareTo(stand)!=1)
//			 System.out.println("低");
//		System.out.println(getQiangRuoRate() );
//		System.out.println(getDateNow());
//		
//	}

}
