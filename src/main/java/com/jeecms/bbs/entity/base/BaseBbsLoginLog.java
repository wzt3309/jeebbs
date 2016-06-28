package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_login_log table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_login_log"
 */

public abstract class BaseBbsLoginLog  implements Serializable {

	public static String REF = "BbsLoginLog";
	public static String PROP_USER = "user";
	public static String PROP_IP = "ip";
	public static String PROP_LOGOUT_TIME = "logoutTime";
	public static String PROP_LOGIN_TIME = "loginTime";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsLoginLog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsLoginLog (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date loginTime;
	private java.util.Date logoutTime;
	private java.lang.String ip;

	// many to one
	private com.jeecms.bbs.entity.BbsUser user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
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
	 * Return the value associated with the column: login_time
	 */
	public java.util.Date getLoginTime () {
		return loginTime;
	}

	/**
	 * Set the value related to the column: login_time
	 * @param loginTime the login_time value
	 */
	public void setLoginTime (java.util.Date loginTime) {
		this.loginTime = loginTime;
	}


	/**
	 * Return the value associated with the column: logout_time
	 */
	public java.util.Date getLogoutTime () {
		return logoutTime;
	}

	/**
	 * Set the value related to the column: logout_time
	 * @param logoutTime the logout_time value
	 */
	public void setLogoutTime (java.util.Date logoutTime) {
		this.logoutTime = logoutTime;
	}


	/**
	 * Return the value associated with the column: ip
	 */
	public java.lang.String getIp () {
		return ip;
	}

	/**
	 * Set the value related to the column: ip
	 * @param ip the ip value
	 */
	public void setIp (java.lang.String ip) {
		this.ip = ip;
	}


	/**
	 * Return the value associated with the column: user_id
	 */
	public com.jeecms.bbs.entity.BbsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.jeecms.bbs.entity.BbsUser user) {
		this.user = user;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsLoginLog)) return false;
		else {
			com.jeecms.bbs.entity.BbsLoginLog bbsLoginLog = (com.jeecms.bbs.entity.BbsLoginLog) obj;
			if (null == this.getId() || null == bbsLoginLog.getId()) return false;
			else return (this.getId().equals(bbsLoginLog.getId()));
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