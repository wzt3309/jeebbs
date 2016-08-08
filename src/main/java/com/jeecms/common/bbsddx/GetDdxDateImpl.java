package com.jeecms.common.bbsddx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.bbsaly.GetStockCodeFromDdx;
import com.jeecms.common.bbsaly.GetStockCodeFromDdx.StockCode;

public class GetDdxDateImpl implements GetDdxDate {
	private static final Logger log=LoggerFactory.getLogger(GetDdxDateImpl.class);
	private String date;//进行查询的日期
	/**
	 * 获取的json的基本信息
	 * pageSize:页数 每页20条的话
	 * total:总记录数
	 * nowdate:返回的是几号的股票数据 如2016-07-08
	 */
	private Map<String,String> comMsg;
	private static int DEFAULT_STOCK_TIME_OUT=5000;
	private static int DEFAULT_CONN_TIME_OUT=5000;
	/**
	 * 包含所有的股票代码以及名称
	 * 股票代码以sh sz开头
	 */
	private List<StockCode> stockCodes=GetStockCodeFromDdx.getStockCodeList();
	
	public GetDdxDateImpl(){		
		this.date="";
		this.comMsg=new HashMap<String,String>();
	}
	public GetDdxDateImpl(String date){
		this.date=date;
		this.comMsg=new HashMap<String,String>();
	}

	@Override
	public List<Stockmessage> getStockmessages() {
		List<Stockmessage> msgs=null;		
		StringBuffer url=new StringBuffer(GetDdxDate.URL_PREFIX);
		try{
			url.append("?&lsdate="+this.date+"&getlsdate=1");
			int pageSize=2;
			msgs=new ArrayList<Stockmessage>();			
			for(int i=1;i<=pageSize;i++){
				String tmpUrl=url.append("&page="+i).toString().trim();				
				JSONObject jsonObj=getJson(tmpUrl);
				if(i==1){
					pageSize=Integer.parseInt(comMsg.get("pageSize"));
				}
				JSONArray data=null;
				if(jsonObj.has("data"))
					data=jsonObj.getJSONArray("data");
				if(data!=null){					
					for(int j=0,length=data.length();j<length;j++){
						JSONArray data_r=data.getJSONArray(j);
						Stockmessage temp=toStockmessage(data_r,i,j);
						if(temp!=null)
							msgs.add(temp);
					}
				}
				Thread.sleep(500);
			}			
			
		}catch(Exception e){
			log.error("get Stockmessages from ddx fail",e);
			System.out.println("get Stockmessages from ddx fail");
		}finally{
			System.out.println(this.date+"------get Stockmessages from ddx end");
		}
		return msgs;
	}

	@Override
	public List<Stockmessage> getStockmessages(String date) {
		this.date=date;
		return getStockmessages();
	}
	@Override
	public List<Stockmessage> getStockmessages(int pageNo) {
		List<Stockmessage> msgs=null;		
		StringBuffer url=new StringBuffer(GetDdxDate.URL_PREFIX);
		try{
			url.append("?&lsdate="+this.date+"&getlsdate=1&page="+pageNo);
			JSONObject jsonObj=getJson(url.toString());			
			JSONArray data=null;
			if(jsonObj.has("data"))
				data=jsonObj.getJSONArray("data");
			msgs=new ArrayList<Stockmessage>();
			if(data!=null){					
				for(int j=0,length=data.length();j<length;j++){
					JSONArray data_r=data.getJSONArray(j);
					Stockmessage temp=toStockmessage(data_r,pageNo,j);
					if(temp!=null)
						msgs.add(temp);
				}
			}
		}catch(Exception e){
			log.error("get Stockmessages from ddx by pageNo fail",e);
			System.out.println("get Stockmessages from ddx by pageNo fail");
		}finally{
			System.out.println(this.date+"--get Stockmessages from ddx by pageNo="
					+pageNo+" end");
		}
		return msgs;
	}
	
	@Override
	public Stockmessage getStockmessage(String stockId) {
		StringBuffer url=new StringBuffer(GetDdxDate.URL_PREFIX);
		Stockmessage msg=null;
		try{
			url.append("?getlsdate=1&stockid="+stockId);
			JSONObject jsonObj=getJson(url.toString());
			JSONArray data=null;
			if(jsonObj.has("data"))
				data=jsonObj.getJSONArray("data");			
			if(data!=null){					
				JSONArray data_r=data.getJSONArray(0);
				msg=toStockmessage(data_r,1,0);
			}
		}catch(Exception e){
			log.error("get Stockmessages from ddx by stockId="+stockId+" fail",e);
			System.out.println("get Stockmessages from ddx by stockId="+stockId+" fail");
		}finally{
			System.out.println(this.date+"--get Stockmessages from ddx by stockId="
					+stockId+" end");
		}
		return msg;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, String> getComMsg() {
		return comMsg;
	}
	public void setComMsg(Map<String, String> comMsg) {
		this.comMsg = comMsg;
	}
	public static int getDEFAULT_STOCK_TIME_OUT() {
		return DEFAULT_STOCK_TIME_OUT;
	}
	public static void setDEFAULT_STOCK_TIME_OUT(int dEFAULT_STOCK_TIME_OUT) {
		DEFAULT_STOCK_TIME_OUT = dEFAULT_STOCK_TIME_OUT;
	}
	public static int getDEFAULT_CONN_TIME_OUT() {
		return DEFAULT_CONN_TIME_OUT;
	}
	public static void setDEFAULT_CONN_TIME_OUT(int dEFAULT_CONN_TIME_OUT) {
		DEFAULT_CONN_TIME_OUT = dEFAULT_CONN_TIME_OUT;
	}
	public List<StockCode> getStockCodes() {
		return stockCodes;
	}
	public void setStockCodes(List<StockCode> stockCodes) {
		this.stockCodes = stockCodes;
	}
	/**
	 * 将data数据包装成Stockmessage
	 */
	private Stockmessage toStockmessage(JSONArray dataBean,int nowPage,int row){
		Stockmessage bean=null;	
		
		String NUM="",RIQI="",GPDM="",GPMC="",ZF="",HSL="";		
		double ZXJ=0.0,LB=0.0;
		double DDX1=0.0,DDY1=0.0,DDZ=0.0
				,DDX3=0.0,DDX5=0.0,DDX10=0,DDX60=0.0
				,DDX5H=0.0,DDX10H=0.0,DDXLH=0.0,DDXLZ=0.0;	
		double ZF3R=0.0,ZF5R=0.0,ZF10R=0.0;
		double DDY3=0.0,DDY5=0.0,DDY10=0.0,DDY60=0.0;	   
		double CJL=0.0,BBD=0.0;
		double TCL1R=0.0,TCL5R=0.0,TCL10R=0.0,TCL20R=0.0;
		double DSB=0.0,TDC=0.0,DDC=0.0,ZDC=0.0,XDC=0.0;
		double ZDL1R=0.0,ZDL5R=0.0,ZDL10R=0.0;	  
		double LTP=0.0;
		int Reccomendation=0;
		
	
		NUM=(nowPage-1)*20+(row+1)+"";
		RIQI=this.date;
		try {
			GPDM=fixGPDM(dataBean.getString(0));
			GPMC=findGPMC(GPDM);
			ZXJ=dataBean.getDouble(1);
			ZF=dataBean.getDouble(2)+"%";
			HSL=dataBean.getDouble(3)+"%";
			LB=dataBean.getDouble(4);
			DDX1=dataBean.getDouble(5);
			DDY1=dataBean.getDouble(6);
			DDZ=dataBean.getDouble(7);
			DDX3=dataBean.getDouble(8);
			DDX5=dataBean.getDouble(9);
			DDX10=dataBean.getDouble(10);
			DDX60=dataBean.getDouble(11);
			DDX5H=dataBean.getDouble(12);
			DDX10H=dataBean.getDouble(13);
			DDXLH=dataBean.getDouble(14);
			DDXLZ=dataBean.getDouble(15);
			ZF3R=dataBean.getDouble(16);
			ZF5R=dataBean.getDouble(17);
			ZF10R=dataBean.getDouble(18);
			DDY3=dataBean.getDouble(19);
			DDY5=dataBean.getDouble(20);
			DDY10=dataBean.getDouble(21);
			DDY60=dataBean.getDouble(22);
			CJL=dataBean.getDouble(23);
			BBD=dataBean.getDouble(24);
			TCL1R=dataBean.getDouble(25);
			TCL5R=dataBean.getDouble(26);
			TCL10R=dataBean.getDouble(27);
			TCL20R=dataBean.getDouble(28);
			DSB=dataBean.getDouble(29);
			TDC=dataBean.getDouble(30);
			DDC=dataBean.getDouble(31);
			ZDC=dataBean.getDouble(32);
			XDC=dataBean.getDouble(33);
			ZDL1R=dataBean.getDouble(34);
			ZDL5R=dataBean.getDouble(35);
			ZDL10R=dataBean.getDouble(36);
			LTP=dataBean.getDouble(37);
		} catch (JSONException e) {			
			log.error("json data to stockmessage fail",e);
			System.out.println("json data to stockmessage fail");
		}
		if(!"".equals(GPMC)){
			bean=new Stockmessage();
			
			bean.setBBD(BBD);bean.setDDX10H(DDX10H);bean.setDDX60(DDX60);
			bean.setCJL(CJL);bean.setDDX10H(DDX10H);bean.setDDXLH(DDXLH);
			bean.setDDC(DDC);bean.setDDX3(DDX3);bean.setDDXLZ(DDXLZ);
			bean.setDDX1(DDX1);bean.setDDX5(DDX5);bean.setDDXLZ(DDXLZ);
			bean.setDDX10(DDX10);bean.setDDX5H(DDX5H);bean.setDDY1(DDY1);
			
			bean.setDDY10(DDY10);bean.setDDY60(DDY60);bean.setGPDM(GPDM);
			bean.setDDY3(DDY3);bean.setDDZ(DDZ);bean.setGPMC(GPMC);
			bean.setDDY5(DDY5);bean.setDSB(DSB);bean.setHSL(HSL);
			
			bean.setZXJ(ZXJ);bean.setZF5R(ZF5R);bean.setZF3R(ZF3R);
			bean.setZF10R(ZF10R);bean.setZF(ZF);bean.setZDL5R(ZDL5R);
			bean.setZDL1R(ZDL1R);bean.setZDL10R(ZDL10R);bean.setZDC(ZDC);
			bean.setXDC(XDC);bean.setTDC(TDC);bean.setTCL5R(TCL5R);
			
			bean.setTCL10R(TCL10R);bean.setTCL1R(TCL1R);bean.setTCL20R(TCL20R);
			bean.setRIQI(RIQI);bean.setReccomendation(Reccomendation);
			bean.setNUM(NUM);bean.setLB(LB);bean.setLTP(LTP);
			
		}
		
		return bean;
	}	
	/**
	 * 根据股票代码得到股票名称
	 */
	private String findGPMC(String gpdm){
		String gpmc="";
		for(int i=0,size=this.stockCodes.size();i<size;i++){
			StockCode stockCode=stockCodes.get(i);
			Pattern p=Pattern.compile("(\\d+)");   
			Matcher m=p.matcher(stockCode.getStockCode());  
			String tempCode="";
			if(m.find()){
				tempCode=m.group(1);
			}
			if(tempCode.equals(gpdm))
				gpmc=stockCode.getStockName();			
		}		
		return gpmc;
	}
	/**
	 * 补全data返回的股票代码
	 */
	private String fixGPDM(String gpdm){
		int fixLength=6-gpdm.length();
		String fix="";
		if(fixLength>0){
			for(int i=0;i<fixLength;i++){
				fix+="0";
			}
		}
		fix+=gpdm;
		return fix;
	}
	/**
	 * 解析返回的json数据
	 */
	private JSONObject getJson(String url){
		CloseableHttpClient httpClient=null;
		HttpGet httpGet=null;
		CloseableHttpResponse response=null;
		HttpEntity httpEntity=null;
		JSONObject jsonObj=null;
		try{
			httpClient=HttpClients.createDefault();
			httpGet=getHttpGet(url);
			response=httpClient.execute(httpGet);
			httpEntity=response.getEntity();
			String textJson=EntityUtils.toString(httpEntity);			
			if(!StringUtil.isBlank(textJson)){
				jsonObj=StrtoJson(textJson);			
				if(jsonObj!=null){
					int page=jsonObj.getInt("page");					
					int total=jsonObj.getInt("total");						
					String nowdate=jsonObj.getString("nowdate");
					int pageSize=Math.round((float)(total/20.0));
					comMsg.put("pageSize", String.valueOf(pageSize));
					comMsg.put("nowdate", nowdate);
					comMsg.put("total", String.valueOf(total));
					if(this.date==null||this.date.equals(""))
						this.date=nowdate;
										
				}
			}
			
		}catch(Exception e){
			log.error("ddx msg http getJson fail",e);
			System.out.println("ddx msg http getJson fail");
		}finally{
			try{
				if(response!=null){
					response.close();
				}
				if(httpClient!=null){
					httpClient.close();
				}
			}catch(Exception e){
				log.error("ddx msg http getJson close something fail",e);
				System.out.println("ddx msg http getJson close something fail");
			}
		}
		return jsonObj;
	}
	/**
	 * 将返回的字符串包装成json
	 */
	private JSONObject StrtoJson(String str){
		JSONObject jsonObj=null;
		try{			
			jsonObj=new JSONObject(str);		
		}catch(Exception e){
			log.error("ddx msg str to json fail",e);
			System.out.println("ddx msg str to json fail");
		}finally{
			
		}
		return jsonObj;
	}
	/**
	 * 设置httpget的头部与连接时长
	 */
	private HttpGet getHttpGet(String url){		
		HttpGet httpGet=new HttpGet(url.trim());
		RequestConfig requestConfig=RequestConfig.custom()
				.setSocketTimeout(DEFAULT_STOCK_TIME_OUT)
				.setConnectTimeout(DEFAULT_CONN_TIME_OUT).build();
		httpGet.setConfig(requestConfig);
		httpGet.setHeader("Accept", "*/*");
		httpGet.setHeader("User-Agent"
				, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 " +
						"(KHTML, like Gecko) Chrome/48.0.2560.0 Safari/537.36");
		httpGet.setHeader("Referer", "http://ddx.gubit.cn/xg/ddx.html");
		httpGet.setHeader("Host", "ddx.gubit.cn");
		httpGet.setHeader("X-Requested-With", "XMLHttpRequest");		
		return httpGet;
	}
	
	
}
