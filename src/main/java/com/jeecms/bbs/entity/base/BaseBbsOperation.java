package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_OPERATION table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_OPERATION"
 */

public abstract class BaseBbsOperation  implements Serializable {

	public static String REF = "BbsOperation";
	public static String PROP_OPERATER = "operater";
	public static String PROP_OPT_TIME = "optTime";
	public static String PROP_OPT_REASON = "optReason";
	public static String PROP_SITE = "site";
	public static String PROP_POST = "post";
	public static String PROP_OPT_NAME = "optName";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsOperation () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsOperation (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsOperation (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		com.jeecms.bbs.entity.BbsUser operater,
		com.jeecms.bbs.entity.BbsPost post,
		java.lang.String optName,
		java.util.Date optTime) {

		this.setId(id);
		this.setSite(site);
		this.setOperater(operater);
		this.setPost(post);
		this.setOptName(optName);
		this.setOptTime(optTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String optName;
	private java.lang.String optReason;
	private java.util.Date optTime;

	// many to one
	private com.jeecms.core.entity.CmsSite site;
	private com.jeecms.bbs.entity.BbsUser operater;
	private com.jeecms.bbs.entity.BbsPost post;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="OPERATOR_ID"
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
	 * Return the value associated with the column: OPT_NAME
	 */
	public java.lang.String getOptName () {
		return optName;
	}

	/**
	 * Set the value related to the column: OPT_NAME
	 * @param optName the OPT_NAME value
	 */
	public void setOptName (java.lang.String optName) {
		this.optName = optName;
	}


	/**
	 * Return the value associated with the column: OPT_REASON
	 */
	public java.lang.String getOptReason () {
		return optReason;
	}

	/**
	 * Set the value related to the column: OPT_REASON
	 * @param optReason the OPT_REASON value
	 */
	public void setOptReason (java.lang.String optReason) {
		this.optReason = optReason;
	}


	/**
	 * Return the value associated with the column: OPT_TIME
	 */
	public java.util.Date getOptTime () {
		return optTime;
	}

	/**
	 * Set the value related to the column: OPT_TIME
	 * @param optTime the OPT_TIME value
	 */
	public void setOptTime (java.util.Date optTime) {
		this.optTime = optTime;
	}


	/**
	 * Return the value associated with the column: site_id
	 */
	public com.jeecms.core.entity.CmsSite getSite () {
		return site;
	}

	/**
	 * Set the value related to the column: site_id
	 * @param site the site_id value
	 */
	public void setSite (com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}


	/**
	 * Return the value associated with the column: operater_id
	 */
	public com.jeecms.bbs.entity.BbsUser getOperater () {
		return operater;
	}

	/**
	 * Set the value related to the column: operater_id
	 * @param operater the operater_id value
	 */
	public void setOperater (com.jeecms.bbs.entity.BbsUser operater) {
		this.operater = operater;
	}


	/**
	 * Return the value associated with the column: post_id
	 */
	public com.jeecms.bbs.entity.BbsPost getPost () {
		return post;
	}

	/**
	 * Set the value related to the column: post_id
	 * @param post the post_id value
	 */
	public void setPost (com.jeecms.bbs.entity.BbsPost post) {
		this.post = post;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsOperation)) return false;
		else {
			com.jeecms.bbs.entity.BbsOperation bbsOperation = (com.jeecms.bbs.entity.BbsOperation) obj;
			if (null == this.getId() || null == bbsOperation.getId()) return false;
			else return (this.getId().equals(bbsOperation.getId()));
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