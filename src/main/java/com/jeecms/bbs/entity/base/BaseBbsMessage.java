package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jb_message table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jb_message"
 */

public abstract class BaseBbsMessage  implements Serializable {

	public static String REF = "BbsMessage";
	public static String PROP_USER = "user";
	public static String PROP_SENDER = "sender";
	public static String PROP_RECEIVER = "receiver";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ID = "id";
	public static String PROP_CONTENT = "content";
	public static String PROP_SYS = "sys";


	// constructors
	public BaseBbsMessage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsMessage (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsMessage (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser user,
		com.jeecms.bbs.entity.BbsUser receiver,
		java.util.Date createTime,
		java.lang.Boolean sys) {

		this.setId(id);
		this.setUser(user);
		this.setReceiver(receiver);
		this.setCreateTime(createTime);
		this.setSys(sys);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String content;
	private java.util.Date createTime;
	private java.lang.Boolean sys;
	private Integer msgType;
	private java.lang.Boolean status;

	// many to one
	private com.jeecms.bbs.entity.BbsUser user;
	private com.jeecms.bbs.entity.BbsUser sender;
	private com.jeecms.bbs.entity.BbsUser receiver;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsMessageReply> reply;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="msg_id"
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
	 * Return the value associated with the column: is_sys
	 */
	public java.lang.Boolean getSys () {
		return sys;
	}
	

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	/**
	 * Set the value related to the column: is_sys
	 * @param sys the is_sys value
	 */
	public void setSys (java.lang.Boolean sys) {
		this.sys = sys;
	}
	


	public java.lang.Boolean getStatus() {
		return status;
	}

	public void setStatus(java.lang.Boolean status) {
		this.status = status;
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


	/**
	 * Return the value associated with the column: reply
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsMessageReply> getReply () {
		return reply;
	}

	/**
	 * Set the value related to the column: reply
	 * @param reply the reply value
	 */
	public void setReply (java.util.Set<com.jeecms.bbs.entity.BbsMessageReply> reply) {
		this.reply = reply;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsMessage)) return false;
		else {
			com.jeecms.bbs.entity.BbsMessage bbsMessage = (com.jeecms.bbs.entity.BbsMessage) obj;
			if (null == this.getId() || null == bbsMessage.getId()) return false;
			else return (this.getId().equals(bbsMessage.getId()));
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