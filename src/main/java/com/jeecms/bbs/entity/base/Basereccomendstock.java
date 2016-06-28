package com.jeecms.bbs.entity.base;

import java.io.Serializable;

public class Basereccomendstock implements Serializable{
	private String stockID;
	private String time;
	private int reccomendation;
	private String stockName;
	
	public Basereccomendstock(){}
	
	public Basereccomendstock( String stockID, String time,int reccomendation,String stockName){
		this.setreccomendation(reccomendation);
		this.setstockID(stockID);
		this.setstockName(stockName);
		this.settime(time);
	}

	public String getstockID(){
		return this.stockID;
	}
	public String gettime(){
		return this.time;
	}
	public int getreccomendation(){
		return this.reccomendation;
	}
	public String getstockName(){
		return this.stockName;
	}
	
	//set
	
	public void setstockID(String stockID){
		this.stockID=stockID;
	}
	public void settime(String time){
		this.time=time;
	}
	public void setreccomendation(int reccomendation){
		this.reccomendation=reccomendation;
	}
	public void setstockName(String stockName){
		this.stockName=stockName;
	}
}
