package com.jeecms.bbs.entity.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFutureLeadingIndex implements Serializable {
	private Integer id;
	private Date date;
	private Double index;
	
	public BaseFutureLeadingIndex(){
		
	}
	public BaseFutureLeadingIndex(Date date,Double index){
		this.date=date;
		this.index=index;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getIndex() {
		return index;
	}
	public void setIndex(Double index) {
		this.index = index;
	}
}
