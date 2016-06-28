package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_vote_record table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_vote_record"
 */

public abstract class BaseBbsVoteRecord  implements Serializable {

	public static String REF = "BbsVoteRecord";
	public static String PROP_USER = "user";
	public static String PROP_VOTE_TIME = "voteTime";
	public static String PROP_TOPIC = "topic";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsVoteRecord () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsVoteRecord (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsVoteRecord (
		java.lang.Integer id,
		java.util.Date voteTime) {

		this.setId(id);
		this.setVoteTime(voteTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date voteTime;

	// many to one
	private com.jeecms.bbs.entity.BbsUser user;
	private com.jeecms.bbs.entity.BbsVoteTopic topic;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="record_id"
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
	 * Return the value associated with the column: vote_time
	 */
	public java.util.Date getVoteTime () {
		return voteTime;
	}

	/**
	 * Set the value related to the column: vote_time
	 * @param voteTime the vote_time value
	 */
	public void setVoteTime (java.util.Date voteTime) {
		this.voteTime = voteTime;
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


	/**
	 * Return the value associated with the column: topic_id
	 */
	public com.jeecms.bbs.entity.BbsVoteTopic getTopic () {
		return topic;
	}

	/**
	 * Set the value related to the column: topic_id
	 * @param topic the topic_id value
	 */
	public void setTopic (com.jeecms.bbs.entity.BbsVoteTopic topic) {
		this.topic = topic;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsVoteRecord)) return false;
		else {
			com.jeecms.bbs.entity.BbsVoteRecord bbsVoteRecord = (com.jeecms.bbs.entity.BbsVoteRecord) obj;
			if (null == this.getId() || null == bbsVoteRecord.getId()) return false;
			else return (this.getId().equals(bbsVoteRecord.getId()));
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