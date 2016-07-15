package com.jeecms.common.bbsaly;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jeecms.bbs.entity.FinanceLeadingIndex;
/**
 * 获取融资融券(Fin)、期指领先指数(Fur)的工具类
 * @author wzt3309
 *
 */
public class GetFinAndFurLeadingIndex extends SpiderBase {
	
	private static String URL="http://datainterface.eastmoney.com/EM_DataCenter/JS.aspx?type=FD&sty=SHSZHSSUM";
	
	private static String getJsonData(String url){		
			doc=getDoc(url);
			return doc.text().replace("([\"", "").replace("\"])", "");	
	}
	private static String[][] analyDate(String data){
		String[] trs=data.split("\",\"");
		String[][] tbody=new String[trs.length][];
		for(int i=0,trLen=trs.length;i<trLen;i++){
			String[] tds=trs[i].split(",");
			int tdLen=tds.length;
			tbody[i]=new String[tdLen];
			for(int j=0;j<tdLen;j++){				
				tbody[i][j]=tds[j];				
			}			
		}		
		return tbody;		
	}	
	private static Date getDate(String dateStr){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
		
	}
	private static Double getIndex(String yestday,String today){
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		Double res= (new Double(today))-(new Double(yestday));		
		return new Double(df.format(res/new Double("100000000")));
	}
	public static List<FinanceLeadingIndex> getFinIndexList(int page){
		String[][] tbody=analyDate(getJsonData(URL+"&p="+page));
		List<FinanceLeadingIndex> list=new ArrayList<FinanceLeadingIndex>();
		for(int i=0,l=tbody.length-1;i<l;i++){
			FinanceLeadingIndex el=new FinanceLeadingIndex();			
			el.setDate(getDate(tbody[i][0]));
			el.setIndex(getIndex(tbody[i+1][1],tbody[i][1]));
			list.add(el);
		}
		String[][] tbody2=analyDate(getJsonData(URL+"&p="+(page+1)));
		FinanceLeadingIndex el=new FinanceLeadingIndex();	
		el.setDate(getDate(tbody[tbody.length-1][0]));
		el.setIndex(getIndex(tbody2[0][1],tbody[tbody.length-1][1]));
		list.add(el);
		return list;
	}
	public static List<FinanceLeadingIndex> getFinIndexList(int from ,int to){
		List<FinanceLeadingIndex> list=new ArrayList<FinanceLeadingIndex>();
		for(int i=from;i<=to;i++){
			list.addAll(getFinIndexList(i));
		}
		return list;
	}
	public static List<FinanceLeadingIndex> getFuIndexList(int page){
		return null;
	}
	public static List<FinanceLeadingIndex> getFuIndexList(int from ,int to){
		return null;
	}
}
