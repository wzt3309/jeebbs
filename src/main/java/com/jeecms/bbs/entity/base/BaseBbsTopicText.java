package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_POST_TEXT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_POST_TEXT"
 */

public abstract class BaseBbsTopicText  implements Serializable {

	public static String REF = "BbsTopicText";
	public static String PROP_TOPIC = "topic";
	public static String PROP_ID = "id";
	public static String PROP_TITLE = "title";


	// constructors
	public BaseBbsTopicText () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsTopicText (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsTopicText (
		java.lang.Integer id,
		java.lang.String title) {

		this.setId(id);
		this.setTitle(title);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String title;

	// one to one
	private com.jeecms.bbs.entity.BbsTopic topic;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="POST_ID"
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
	 * Return the value associated with the column: TITLE
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: TITLE
	 * @param title the TITLE value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}


	/**
	 * Return the value associated with the column: topic
	 */
	public com.jeecms.bbs.entity.BbsTopic getTopic () {
		return topic;
	}

	/**
	 * Set the value related to the column: topic
	 * @param topic the topic value
	 */
	public void setTopic (com.jeecms.bbs.entity.BbsTopic topic) {
		this.topic = topic;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsTopicText)) return false;
		else {
			com.jeecms.bbs.entity.BbsTopicText bbsTopicText = (com.jeecms.bbs.entity.BbsTopicText) obj;
			if (null == this.getId() || null == bbsTopicText.getId()) return false;
			else return (this.getId().equals(bbsTopicText.getId()));
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