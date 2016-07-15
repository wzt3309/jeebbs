package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseFinanceLeadingIndex;
/**
 * 
 * @ClassName FutureLeadingIndex
 * @Description 期指领先指标实体类
 * @author wzt3309 
 */
public class FutureLeadingIndex extends BaseFinanceLeadingIndex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FutureLeadingIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FutureLeadingIndex(Date date, Double index) {
		super(date, index);
		// TODO Auto-generated constructor stub
	}
	

}
