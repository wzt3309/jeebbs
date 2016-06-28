package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jb_friendship table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jb_friendship"
 */

public abstract class BaseBbsFriendShip  implements Serializable {

	public static String REF = "BbsFriendShip";
	public static String PROP_USER = "user";
	public static String PROP_STATUS = "status";
	public static String PROP_ID = "id";
	public static String PROP_FRIEND = "friend";


	// constructors
	public BaseBbsFriendShip () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsFriendShip (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsFriendShip (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser user,
		com.jeecms.bbs.entity.BbsUser friend,
		java.lang.Integer status) {

		this.setId(id);
		this.setUser(user);
		this.setFriend(friend);
		this.setStatus(status);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer status;

	// many to one
	private com.jeecms.bbs.entity.BbsUser user;
	private com.jeecms.bbs.entity.BbsUser friend;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="friendlink_id"
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
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
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
	 * Return the value associated with the column: friend_id
	 */
	public com.jeecms.bbs.entity.BbsUser getFriend () {
		return friend;
	}

	/**
	 * Set the value related to the column: friend_id
	 * @param friend the friend_id value
	 */
	public void setFriend (com.jeecms.bbs.entity.BbsUser friend) {
		this.friend = friend;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsFriendShip)) return false;
		else {
			com.jeecms.bbs.entity.BbsFriendShip bbsFriendShip = (com.jeecms.bbs.entity.BbsFriendShip) obj;
			if (null == this.getId() || null == bbsFriendShip.getId()) return false;
			else return (this.getId().equals(bbsFriendShip.getId()));
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