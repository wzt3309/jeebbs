package com.jeecms.bbs.entity.base;

import java.io.Serializable;

public class Basereccomendstock implements Serializable{
	private Integer id;
	private String stockID;
	private String time;
	private int reccomendation;
	private String stockName;
	
	public Basereccomendstock(){}
	
	public Basereccomendstock( Integer id,String stockID, String time
			,int reccomendation,String stockName){
		this.id=id;
		this.stockID=stockID;
		this.time=time;
		this.reccomendation=reccomendation;
		this.stockName=stockName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStockID() {
		return stockID;
	}

	public void setStockID(String stockID) {
		this.stockID = stockID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getReccomendation() {
		return reccomendation;
	}

	public void setReccomendation(int reccomendation) {
		this.reccomendation = reccomendation;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	
}
