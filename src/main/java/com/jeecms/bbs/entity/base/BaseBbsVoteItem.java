package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_vote_item table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_vote_item"
 */

public abstract class BaseBbsVoteItem  implements Serializable {

	public static String REF = "BbsVoteItem";
	public static String PROP_NAME = "name";
	public static String PROP_VOTE_COUNT = "voteCount";
	public static String PROP_TOPIC = "topic";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsVoteItem () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsVoteItem (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsVoteItem (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsVoteTopic topic,
		java.lang.String name,
		java.lang.Integer voteCount) {

		this.setId(id);
		this.setTopic(topic);
		this.setName(name);
		this.setVoteCount(voteCount);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.Integer voteCount;

	// many to one
	private com.jeecms.bbs.entity.BbsVoteTopic topic;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="item_id"
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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: vote_count
	 */
	public java.lang.Integer getVoteCount () {
		return voteCount;
	}

	/**
	 * Set the value related to the column: vote_count
	 * @param voteCount the vote_count value
	 */
	public void setVoteCount (java.lang.Integer voteCount) {
		this.voteCount = voteCount;
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
		if (!(obj instanceof com.jeecms.bbs.entity.BbsVoteItem)) return false;
		else {
			com.jeecms.bbs.entity.BbsVoteItem bbsVoteItem = (com.jeecms.bbs.entity.BbsVoteItem) obj;
			if (null == this.getId() || null == bbsVoteItem.getId()) return false;
			else return (this.getId().equals(bbsVoteItem.getId()));
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