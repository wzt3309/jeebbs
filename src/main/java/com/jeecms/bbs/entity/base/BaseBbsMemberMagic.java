package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_member_magic table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_member_magic"
 */

public abstract class BaseBbsMemberMagic  implements Serializable {

	public static String REF = "BbsMemberMagic";
	public static String PROP_USER = "user";
	public static String PROP_NUM = "num";
	public static String PROP_MAGIC = "magic";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsMemberMagic () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsMemberMagic (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsMemberMagic (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser user,
		com.jeecms.bbs.entity.BbsCommonMagic magic) {

		this.setId(id);
		this.setUser(user);
		this.setMagic(magic);
		initialize();
	}

	protected void initialize () {
		this.setNum(0);
	}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer num;

	// many to one
	private com.jeecms.bbs.entity.BbsUser user;
	private com.jeecms.bbs.entity.BbsCommonMagic magic;



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
	 * Return the value associated with the column: uid
	 */
	public com.jeecms.bbs.entity.BbsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: uid
	 * @param user the uid value
	 */
	public void setUser (com.jeecms.bbs.entity.BbsUser user) {
		this.user = user;
	}


	/**
	 * Return the value associated with the column: magicid
	 */
	public com.jeecms.bbs.entity.BbsCommonMagic getMagic () {
		return magic;
	}

	/**
	 * Set the value related to the column: magicid
	 * @param magic the magicid value
	 */
	public void setMagic (com.jeecms.bbs.entity.BbsCommonMagic magic) {
		this.magic = magic;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsMemberMagic)) return false;
		else {
			com.jeecms.bbs.entity.BbsMemberMagic bbsMemberMagic = (com.jeecms.bbs.entity.BbsMemberMagic) obj;
			if (null == this.getId() || null == bbsMemberMagic.getId()) return false;
			else return (this.getId().equals(bbsMemberMagic.getId()));
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