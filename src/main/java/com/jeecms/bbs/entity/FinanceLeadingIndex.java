package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseFinanceLeadingIndex;
/**
 * @ClassName FinanceLeadingIndex
 * @Description 融资融券领先指数 
 * @author wzt3309
 *
 */
public class FinanceLeadingIndex extends BaseFinanceLeadingIndex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FinanceLeadingIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinanceLeadingIndex(Date date, Double index) {
		super(date, index);
		// TODO Auto-generated constructor stub
	}
	

}
