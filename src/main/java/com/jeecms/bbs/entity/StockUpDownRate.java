package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseStockUpDownRate;
/**
 * @ClassName StockUpDownRate
 * @Description 股票升降比率实体类
 * @author wzt3309
 *
 */
public class StockUpDownRate extends BaseStockUpDownRate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockUpDownRate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockUpDownRate(Integer id, Double qiangRuoRate,
			Double upAndDownRate, Date date) {
		super(id, qiangRuoRate, upAndDownRate, date);
		// TODO Auto-generated constructor stub
	}
	

}
