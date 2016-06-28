package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jb_message_reply table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jb_message_reply"
 */

public abstract class BaseBbsMessageReply  implements Serializable {

	public static String REF = "BbsMessageReply";
	public static String PROP_SENDER = "sender";
	public static String PROP_MESSAGE = "message";
	public static String PROP_RECEIVER = "receiver";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_CONTENT = "content";


	// constructors
	public BaseBbsMessageReply () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsMessageReply (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsMessageReply (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsMessage message,
		com.jeecms.bbs.entity.BbsUser receiver,
		java.util.Date createTime) {

		this.setId(id);
		this.setMessage(message);
		this.setReceiver(receiver);
		this.setCreateTime(createTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String content;
	private java.util.Date createTime;

	// many to one
	private com.jeecms.bbs.entity.BbsMessage message;
	private com.jeecms.bbs.entity.BbsUser sender;
	private com.jeecms.bbs.entity.BbsUser receiver;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="reply_id"
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
	 * Return the value associated with the column: content
	 */
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content
	 * @param content the content value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
	}


	/**
	 * Return the value associated with the column: create_time
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: create_time
	 * @param createTime the create_time value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: msg_id
	 */
	public com.jeecms.bbs.entity.BbsMessage getMessage () {
		return message;
	}

	/**
	 * Set the value related to the column: msg_id
	 * @param message the msg_id value
	 */
	public void setMessage (com.jeecms.bbs.entity.BbsMessage message) {
		this.message = message;
	}


	/**
	 * Return the value associated with the column: sender
	 */
	public com.jeecms.bbs.entity.BbsUser getSender () {
		return sender;
	}

	/**
	 * Set the value related to the column: sender
	 * @param sender the sender value
	 */
	public void setSender (com.jeecms.bbs.entity.BbsUser sender) {
		this.sender = sender;
	}


	/**
	 * Return the value associated with the column: receiver
	 */
	public com.jeecms.bbs.entity.BbsUser getReceiver () {
		return receiver;
	}

	/**
	 * Set the value related to the column: receiver
	 * @param receiver the receiver value
	 */
	public void setReceiver (com.jeecms.bbs.entity.BbsUser receiver) {
		this.receiver = receiver;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsMessageReply)) return false;
		else {
			com.jeecms.bbs.entity.BbsMessageReply bbsMessageReply = (com.jeecms.bbs.entity.BbsMessageReply) obj;
			if (null == this.getId() || null == bbsMessageReply.getId()) return false;
			else return (this.getId().equals(bbsMessageReply.getId()));
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