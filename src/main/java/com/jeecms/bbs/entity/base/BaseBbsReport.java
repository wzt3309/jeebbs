package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_report table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_report"
 */

public abstract class BaseBbsReport  implements Serializable {

	public static String REF = "BbsReport";
	public static String PROP_STATUS = "status";
	public static String PROP_REPORT_URL = "reportUrl";
	public static String PROP_PROCESS_TIME = "processTime";
	public static String PROP_ID = "id";
	public static String PROP_PROCESS_USER = "processUser";
	public static String PROP_REPORT_TIME = "reportTime";
	public static String PROP_PROCESS_RESULT = "processResult";


	// constructors
	public BaseBbsReport () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsReport (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsReport (
		java.lang.Integer id,
		java.lang.String reportUrl,
		java.util.Date reportTime) {

		this.setId(id);
		this.setReportUrl(reportUrl);
		this.setReportTime(reportTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String reportUrl;
	private java.util.Date processTime;
	private java.lang.String processResult;
	private boolean status;
	private java.util.Date reportTime;

	// many to one
	private com.jeecms.bbs.entity.BbsUser processUser;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsReportExt> bbsReportExtSet;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: report_url
	 */
	public java.lang.String getReportUrl () {
		return reportUrl;
	}

	/**
	 * Set the value related to the column: report_url
	 * @param reportUrl the report_url value
	 */
	public void setReportUrl (java.lang.String reportUrl) {
		this.reportUrl = reportUrl;
	}


	/**
	 * Return the value associated with the column: process_time
	 */
	public java.util.Date getProcessTime () {
		return processTime;
	}

	/**
	 * Set the value related to the column: process_time
	 * @param processTime the process_time value
	 */
	public void setProcessTime (java.util.Date processTime) {
		this.processTime = processTime;
	}


	/**
	 * Return the value associated with the column: process_result
	 */
	public java.lang.String getProcessResult () {
		return processResult;
	}

	/**
	 * Set the value related to the column: process_result
	 * @param processResult the process_result value
	 */
	public void setProcessResult (java.lang.String processResult) {
		this.processResult = processResult;
	}


	/**
	 * Return the value associated with the column: status
	 */
	public boolean isStatus () {
		return status;
	}
	
	public boolean getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (boolean status) {
		this.status = status;
	}


	/**
	 * Return the value associated with the column: report_time
	 */
	public java.util.Date getReportTime () {
		return reportTime;
	}

	/**
	 * Set the value related to the column: report_time
	 * @param reportTime the report_time value
	 */
	public void setReportTime (java.util.Date reportTime) {
		this.reportTime = reportTime;
	}


	/**
	 * Return the value associated with the column: process_user
	 */
	public com.jeecms.bbs.entity.BbsUser getProcessUser () {
		return processUser;
	}

	/**
	 * Set the value related to the column: process_user
	 * @param processUser the process_user value
	 */
	public void setProcessUser (com.jeecms.bbs.entity.BbsUser processUser) {
		this.processUser = processUser;
	}


	/**
	 * Return the value associated with the column: bbsReportExtSet
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsReportExt> getBbsReportExtSet () {
		return bbsReportExtSet;
	}

	/**
	 * Set the value related to the column: bbsReportExtSet
	 * @param bbsReportExtSet the bbsReportExtSet value
	 */
	public void setBbsReportExtSet (java.util.Set<com.jeecms.bbs.entity.BbsReportExt> bbsReportExtSet) {
		this.bbsReportExtSet = bbsReportExtSet;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsReport)) return false;
		else {
			com.jeecms.bbs.entity.BbsReport bbsReport = (com.jeecms.bbs.entity.BbsReport) obj;
			if (null == this.getId() || null == bbsReport.getId()) return false;
			else return (this.getId().equals(bbsReport.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}