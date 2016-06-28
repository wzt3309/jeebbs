package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_report_ext table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_report_ext"
 */

public abstract class BaseBbsReportExt  implements Serializable {

	public static String REF = "BbsReportExt";
	public static String PROP_REPORT = "report";
	public static String PROP_ID = "id";
	public static String PROP_REPORT_USER = "reportUser";
	public static String PROP_REPORT_REASON = "reportReason";
	public static String PROP_REPORT_TIME = "reportTime";


	// constructors
	public BaseBbsReportExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsReportExt (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsReportExt (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser reportUser,
		com.jeecms.bbs.entity.BbsReport report,
		java.util.Date reportTime) {

		this.setId(id);
		this.setReportUser(reportUser);
		this.setReport(report);
		this.setReportTime(reportTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date reportTime;
	private java.lang.String reportReason;

	// many to one
	private com.jeecms.bbs.entity.BbsUser reportUser;
	private com.jeecms.bbs.entity.BbsReport report;



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
	 * Return the value associated with the column: report_reason
	 */
	public java.lang.String getReportReason () {
		return reportReason;
	}

	/**
	 * Set the value related to the column: report_reason
	 * @param reportReason the report_reason value
	 */
	public void setReportReason (java.lang.String reportReason) {
		this.reportReason = reportReason;
	}


	/**
	 * Return the value associated with the column: report_user
	 */
	public com.jeecms.bbs.entity.BbsUser getReportUser () {
		return reportUser;
	}

	/**
	 * Set the value related to the column: report_user
	 * @param reportUser the report_user value
	 */
	public void setReportUser (com.jeecms.bbs.entity.BbsUser reportUser) {
		this.reportUser = reportUser;
	}


	/**
	 * Return the value associated with the column: report_id
	 */
	public com.jeecms.bbs.entity.BbsReport getReport () {
		return report;
	}

	/**
	 * Set the value related to the column: report_id
	 * @param report the report_id value
	 */
	public void setReport (com.jeecms.bbs.entity.BbsReport report) {
		this.report = report;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsReportExt)) return false;
		else {
			com.jeecms.bbs.entity.BbsReportExt bbsReportExt = (com.jeecms.bbs.entity.BbsReportExt) obj;
			if (null == this.getId() || null == bbsReportExt.getId()) return false;
			else return (this.getId().equals(bbsReportExt.getId()));
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