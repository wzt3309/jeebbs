package com.jeecms.bbs.entity.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the bbs_user_online table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="bbs_user_online"
 */

public abstract class BaseBbsUserOnline implements Serializable {

	public static String REF = "BbsUserOnline";
	public static String PROP_USER = "user";
	public static String PROP_ONLINE_LATEST = "onlineLatest";
	public static String PROP_ONLINE_WEEK = "onlineWeek";
	public static String PROP_ONLINE_YEAR = "onlineYear";
	public static String PROP_ONLINE_MONTH = "onlineMonth";
	public static String PROP_ID = "id";
	public static String PROP_ONLINE_TOTAL = "onlineTotal";
	public static String PROP_ONLINE_DAY = "onlineDay";

	// constructors
	public BaseBbsUserOnline() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsUserOnline(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private Double onlineLatest;
	private Double onlineDay;
	private Double onlineWeek;
	private Double onlineMonth;
	private Double onlineYear;
	private Double onlineTotal;

	// one to one
	private com.jeecms.bbs.entity.BbsUser user;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="foreign" column="user_id"
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: online_latest
	 */
	public Double getOnlineLatest() {
		return onlineLatest;
	}

	/**
	 * Set the value related to the column: online_latest
	 * 
	 * @param onlineLatest
	 *            the online_latest value
	 */
	public void setOnlineLatest(Double onlineLatest) {
		this.onlineLatest = onlineLatest;
	}

	/**
	 * Return the value associated with the column: online_day
	 */
	public Double getOnlineDay() {
		return onlineDay;
	}

	/**
	 * Set the value related to the column: online_day
	 * 
	 * @param onlineDay
	 *            the online_day value
	 */
	public void setOnlineDay(Double onlineDay) {
		this.onlineDay = onlineDay;
	}

	/**
	 * Return the value associated with the column: online_week
	 */
	public Double getOnlineWeek() {
		return onlineWeek;
	}

	/**
	 * Set the value related to the column: online_week
	 * 
	 * @param onlineWeek
	 *            the online_week value
	 */
	public void setOnlineWeek(Double onlineWeek) {
		this.onlineWeek = onlineWeek;
	}

	/**
	 * Return the value associated with the column: online_month
	 */
	public Double getOnlineMonth() {
		return onlineMonth;
	}

	/**
	 * Set the value related to the column: online_month
	 * 
	 * @param onlineMonth
	 *            the online_month value
	 */
	public void setOnlineMonth(Double onlineMonth) {
		this.onlineMonth = onlineMonth;
	}

	/**
	 * Return the value associated with the column: online_year
	 */
	public Double getOnlineYear() {
		return onlineYear;
	}

	/**
	 * Set the value related to the column: online_year
	 * 
	 * @param onlineYear
	 *            the online_year value
	 */
	public void setOnlineYear(Double onlineYear) {
		this.onlineYear = onlineYear;
	}

	/**
	 * Return the value associated with the column: online_total
	 */
	public Double getOnlineTotal() {
		return onlineTotal;
	}

	/**
	 * Set the value related to the column: online_total
	 * 
	 * @param onlineTotal
	 *            the online_total value
	 */
	public void setOnlineTotal(Double onlineTotal) {
		this.onlineTotal = onlineTotal;
	}

	/**
	 * Return the value associated with the column: user
	 */
	public com.jeecms.bbs.entity.BbsUser getUser() {
		return user;
	}

	/**
	 * Set the value related to the column: user
	 * 
	 * @param user
	 *            the user value
	 */
	public void setUser(com.jeecms.bbs.entity.BbsUser user) {
		this.user = user;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsUserOnline))
			return false;
		else {
			com.jeecms.bbs.entity.BbsUserOnline bbsUserOnline = (com.jeecms.bbs.entity.BbsUserOnline) obj;
			if (null == this.getId() || null == bbsUserOnline.getId())
				return false;
			else
				return (this.getId().equals(bbsUserOnline.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}