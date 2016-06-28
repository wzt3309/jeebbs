package com.jeecms.bbs.entity.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the jc_user_ext table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="jc_user_ext"
 */

public abstract class BaseBbsUserExt implements Serializable {

	public static String REF = "CmsUserExt";
	public static String PROP_MOBLE = "moble";
	public static String PROP_MSN = "msn";
	public static String PROP_BIRTHDAY = "birthday";
	public static String PROP_GENDER = "gender";
	public static String PROP_COMEFROM = "comefrom";
	public static String PROP_USER = "user";
	public static String PROP_INTRO = "intro";
	public static String PROP_REALNAME = "realname";
	public static String PROP_QQ = "qq";
	public static String PROP_ID = "id";
	public static String PROP_PHONE = "phone";

	// constructors
	public BaseBbsUserExt() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsUserExt(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String realname;
	private java.lang.Boolean gender;
	private java.util.Date birthday;
	private java.lang.String intro;
	private java.lang.String comefrom;
	private java.lang.String qq;
	private java.lang.String msn;
	private java.lang.String phone;
	private java.lang.String moble;

	// one to one
	private com.jeecms.bbs.entity.BbsUser user;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="foreign" column="user_id"
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: realname
	 */
	public java.lang.String getRealname() {
		return realname;
	}

	/**
	 * Set the value related to the column: realname
	 * 
	 * @param realname
	 *            the realname value
	 */
	public void setRealname(java.lang.String realname) {
		this.realname = realname;
	}

	/**
	 * Return the value associated with the column: gender
	 */
	public java.lang.Boolean getGender() {
		return gender;
	}

	/**
	 * Set the value related to the column: gender
	 * 
	 * @param gender
	 *            the gender value
	 */
	public void setGender(java.lang.Boolean gender) {
		this.gender = gender;
	}

	/**
	 * Return the value associated with the column: birthday
	 */
	public java.util.Date getBirthday() {
		return birthday;
	}

	/**
	 * Set the value related to the column: birthday
	 * 
	 * @param birthday
	 *            the birthday value
	 */
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Return the value associated with the column: intro
	 */
	public java.lang.String getIntro() {
		return intro;
	}

	/**
	 * Set the value related to the column: intro
	 * 
	 * @param intro
	 *            the intro value
	 */
	public void setIntro(java.lang.String intro) {
		this.intro = intro;
	}

	/**
	 * Return the value associated with the column: comefrom
	 */
	public java.lang.String getComefrom() {
		return comefrom;
	}

	/**
	 * Set the value related to the column: comefrom
	 * 
	 * @param comefrom
	 *            the comefrom value
	 */
	public void setComefrom(java.lang.String comefrom) {
		this.comefrom = comefrom;
	}

	/**
	 * Return the value associated with the column: qq
	 */
	public java.lang.String getQq() {
		return qq;
	}

	/**
	 * Set the value related to the column: qq
	 * 
	 * @param qq
	 *            the qq value
	 */
	public void setQq(java.lang.String qq) {
		this.qq = qq;
	}

	/**
	 * Return the value associated with the column: msn
	 */
	public java.lang.String getMsn() {
		return msn;
	}

	/**
	 * Set the value related to the column: msn
	 * 
	 * @param msn
	 *            the msn value
	 */
	public void setMsn(java.lang.String msn) {
		this.msn = msn;
	}

	/**
	 * Return the value associated with the column: phone
	 */
	public java.lang.String getPhone() {
		return phone;
	}

	/**
	 * Set the value related to the column: phone
	 * 
	 * @param phone
	 *            the phone value
	 */
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	/**
	 * Return the value associated with the column: moble
	 */
	public java.lang.String getMoble() {
		return moble;
	}

	/**
	 * Set the value related to the column: moble
	 * 
	 * @param moble
	 *            the moble value
	 */
	public void setMoble(java.lang.String moble) {
		this.moble = moble;
	}

	/**
	 * Return the value associated with the column: user
	 */
	public com.jeecms.bbs.entity.BbsUser getUser() {
		return user;
	}

	/**
	 * Set the value related to the column: user
	 * 
	 * @param user
	 *            the user value
	 */
	public void setUser(com.jeecms.bbs.entity.BbsUser user) {
		this.user = user;
		this.moble=user.getTelephone();
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsUserExt))
			return false;
		else {
			com.jeecms.bbs.entity.BbsUserExt bbsUserExt = (com.jeecms.bbs.entity.BbsUserExt) obj;
			if (null == this.getId() || null == bbsUserExt.getId())
				return false;
			else
				return (this.getId().equals(bbsUserExt.getId()));
		}
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":"
						+ this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		return super.toString();
	}

}