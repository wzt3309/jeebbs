package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_magic_log table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_magic_log"
 */

public abstract class BaseBbsMagicLog  implements Serializable {

	public static String REF = "BbsMagicLog";
	public static String PROP_USER = "user";
	public static String PROP_NUM = "num";
	public static String PROP_TARGET_USER = "targetUser";
	public static String PROP_PRICE = "price";
	public static String PROP_OPERATOR = "operator";
	public static String PROP_MAGIC = "magic";
	public static String PROP_LOG_TIME = "logTime";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsMagicLog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsMagicLog (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsMagicLog (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsCommonMagic magic,
		com.jeecms.bbs.entity.BbsUser user,
		java.util.Date logTime) {

		this.setId(id);
		this.setMagic(magic);
		this.setUser(user);
		this.setLogTime(logTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date logTime;
	private java.lang.Byte operator;
	private java.lang.Integer price;
	private java.lang.Integer num;

	// many to one
	private com.jeecms.bbs.entity.BbsUser targetUser;
	private com.jeecms.bbs.entity.BbsCommonMagic magic;
	private com.jeecms.bbs.entity.BbsUser user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="log_id"
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
	 * Return the value associated with the column: log_time
	 */
	public java.util.Date getLogTime () {
		return logTime;
	}

	/**
	 * Set the value related to the column: log_time
	 * @param logTime the log_time value
	 */
	public void setLogTime (java.util.Date logTime) {
		this.logTime = logTime;
	}


	/**
	 * Return the value associated with the column: operator
	 */
	public java.lang.Byte getOperator () {
		return operator;
	}

	/**
	 * Set the value related to the column: operator
	 * @param operator the operator value
	 */
	public void setOperator (java.lang.Byte operator) {
		this.operator = operator;
	}


	/**
	 * Return the value associated with the column: price
	 */
	public java.lang.Integer getPrice () {
		return price;
	}

	/**
	 * Set the value related to the column: price
	 * @param price the price value
	 */
	public void setPrice (java.lang.Integer price) {
		this.price = price;
	}


	/**
	 * Return the value associated with the column: num
	 */
	public java.lang.Integer getNum () {
		return num;
	}

	/**
	 * Set the value related to the column: num
	 * @param num the num value
	 */
	public void setNum (java.lang.Integer num) {
		this.num = num;
	}


	/**
	 * Return the value associated with the column: targetuid
	 */
	public com.jeecms.bbs.entity.BbsUser getTargetUser () {
		return targetUser;
	}

	/**
	 * Set the value related to the column: targetuid
	 * @param targetUser the targetuid value
	 */
	public void setTargetUser (com.jeecms.bbs.entity.BbsUser targetUser) {
		this.targetUser = targetUser;
	}


	/**
	 * Return the value associated with the column: magic_id
	 */
	public com.jeecms.bbs.entity.BbsCommonMagic getMagic () {
		return magic;
	}

	/**
	 * Set the value related to the column: magic_id
	 * @param magic the magic_id value
	 */
	public void setMagic (com.jeecms.bbs.entity.BbsCommonMagic magic) {
		this.magic = magic;
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
		if (!(obj instanceof com.jeecms.bbs.entity.BbsMagicLog)) return false;
		else {
			com.jeecms.bbs.entity.BbsMagicLog bbsMagicLog = (com.jeecms.bbs.entity.BbsMagicLog) obj;
			if (null == this.getId() || null == bbsMagicLog.getId()) return false;
			else return (this.getId().equals(bbsMagicLog.getId()));
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