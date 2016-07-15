package com.jeecms.bbs.entity.base;

import java.io.Serializable;
import java.util.Date;

public class BaseStockUpDownRate implements Serializable {
	private Integer id;
	private Double qiangRuoRate;
	private Double upAndDownRate;
	private Date date;
	
	public BaseStockUpDownRate(){
		
	}
	public BaseStockUpDownRate(Integer id,Double qiangRuoRate,Double upAndDownRate,Date date){
		this.id=id;
		this.qiangRuoRate=qiangRuoRate;
		this.date=date;
		this.upAndDownRate=upAndDownRate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getQiangRuoRate() {
		return qiangRuoRate;
	}
	public void setQiangRuoRate(Double qiangRuoRate) {
		this.qiangRuoRate = qiangRuoRate;
	}
	
	public Double getUpAndDownRate() {
		return upAndDownRate;
	}
	public void setUpAndDownRate(Double upAndDownRate) {
		this.upAndDownRate = upAndDownRate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
