package com.jeecms.bbs.entity;

import java.util.Iterator;
import java.util.Set;

import com.jeecms.bbs.entity.base.BaseBbsReport;



public class BbsReport extends BaseBbsReport {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsReport () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsReport (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsReport (
		java.lang.Integer id,
		java.lang.String reportUrl,
		java.util.Date reportTime) {

		super (
			id,
			reportUrl,
			reportTime);
	}
	public BbsReportExt getReportExt(){
		Set<BbsReportExt>sets=getBbsReportExtSet(); 
		Iterator<BbsReportExt>it=sets.iterator();
		return it.next();
	}

/*[CONSTRUCTOR MARKER END]*/


}