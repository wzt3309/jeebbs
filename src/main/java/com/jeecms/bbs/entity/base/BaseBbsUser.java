package com.jeecms.bbs.entity.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the jb_user table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * 
 * @hibernate.class table="jb_user"
 */

public abstract class BaseBbsUser implements Serializable {

	public static String REF = "BbsUser";
	public static String PROP_INTRODUCTION = "introduction";
	public static String PROP_REPLY_COUNT = "replyCount";
	public static String PROP_LAST_LOGIN_IP = "lastLoginIp";
	public static String PROP_SIGNED = "signed";
	public static String PROP_PROHIBIT_POST = "prohibitPost";
	public static String PROP_GRADE_TODAY = "gradeToday";
	public static String PROP_DISABLED = "disabled";
	public static String PROP_PRIME_COUNT = "primeCount";
	public static String PROP_GROUP = "group";
	public static String PROP_UPLOAD_TODAY = "uploadToday";
	public static String PROP_LOGIN_COUNT = "loginCount";
	public static String PROP_REGISTER_TIME = "registerTime";
	public static String PROP_UPLOAD_TOTAL = "uploadTotal";
	public static String PROP_AVATAR = "avatar";
	public static String PROP_LAST_LOGIN_TIME = "lastLoginTime";
	public static String PROP_UPLOAD_DATE = "uploadDate";
	public static String PROP_EMAIL = "email";
	public static String PROP_TELEPHONE = "telephone";
	public static String PROP_UPLOAD_SIZE = "uploadSize";
	public static String PROP_PROHIBIT_TIME = "prohibitTime";
	public static String PROP_AVATAR_TYPE = "avatarType";
	public static String PROP_POINT = "point";
	public static String PROP_TOPIC_COUNT = "topicCount";
	public static String PROP_ADMIN = "admin";
	public static String PROP_ID = "id";
	public static String PROP_REGISTER_IP = "registerIp";
	public static String PROP_POST_TODAY = "postToday";
	public static String PROP_USERNAME = "username";
	public static String PROP_INVITENAME="invitename";
	public static String PROP_LAST_POST_TIME = "lastPostTime";

	// constructors
	public BaseBbsUser() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsUser(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsUser(java.lang.Integer id,
			com.jeecms.bbs.entity.BbsUserGroup group,
			java.lang.String username,java.lang.String invitename, java.util.Date registerTime,
			java.lang.String registerIp, java.lang.Integer loginCount,
			java.lang.Long uploadTotal, java.lang.Integer uploadToday,
			java.lang.Integer uploadSize, java.lang.Boolean admin,
			java.lang.Boolean disabled, java.lang.Long point,
			java.lang.Short avatarType, java.lang.Integer topicCount,
			java.lang.Integer replyCount, java.lang.Integer primeCount,
			java.lang.Integer postToday, java.lang.Short prohibitPost) {

		this.setId(id);
		this.setGroup(group);
		this.setUsername(username);
		this.setInvitename(invitename);
		this.setRegisterTime(registerTime);
		this.setRegisterIp(registerIp);
		this.setLoginCount(loginCount);
		this.setUploadTotal(uploadTotal);
		this.setUploadToday(uploadToday);
		this.setUploadSize(uploadSize);
		this.setAdmin(admin);
		this.setDisabled(disabled);
		this.setPoint(point);
		this.setAvatarType(avatarType);
		this.setTopicCount(topicCount);
		this.setReplyCount(replyCount);
		this.setPrimeCount(primeCount);
		this.setPostToday(postToday);
		this.setProhibitPost(prohibitPost);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String username;
	private java.lang.String email;
	private java.lang.String invitename;
	private java.lang.String telephone;
	private java.util.Date registerTime;
	private java.lang.String registerIp;
	private java.util.Date lastLoginTime;
	private java.lang.String lastLoginIp;
	private java.lang.Integer loginCount;
	private java.lang.Long uploadTotal;
	private java.lang.Integer uploadToday;
	private java.lang.Integer uploadSize;
	private java.sql.Date uploadDate;
	private java.lang.Boolean admin;
	private java.lang.Boolean disabled;
	private java.lang.Long point;
	private java.lang.Long prestige;
	private java.lang.String introduction;
	private java.lang.String signed;
	private java.lang.String avatar;
	private java.lang.Short avatarType;
	private java.lang.Integer topicCount;
	private java.lang.Integer replyCount;
	private java.lang.Integer primeCount;
	private java.lang.Integer postToday;
	private java.util.Date lastPostTime;
	private java.lang.Short prohibitPost;
	private java.util.Date prohibitTime;
	private java.lang.Integer gradeToday;
	private java.lang.Integer magicPacketSize;

	// one to one
	private com.jeecms.bbs.entity.BbsUserOnline userOnline;

	// many to one
	private com.jeecms.bbs.entity.BbsUserGroup group;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsUserExt> userExtSet;
	private java.util.Set<com.jeecms.bbs.entity.BbsLoginLog> loginLogs;
	private java.util.Set<com.jeecms.bbs.entity.BbsMemberMagic> memberMagics;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="assigned" column="user_id"
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
	 * Return the value associated with the column: username
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * Set the value related to the column: username
	 * 
	 * @param username
	 *            the username value
	 */
	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	/**
	 * Return the value associated with the column: email
	 */
	public java.lang.String getEmail() {
		return email;
	}
	public java.lang.String getInvitename(){
		return invitename;
	}
	public java.lang.String getTelephone() {
		return telephone;
	}

	/**
	 * Set the value related to the column: email
	 * 
	 * @param email
	 *            the email value
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public void setInvitename(java.lang.String invitename){
		this.invitename=invitename;
	}
	
	public void setTelephone(java.lang.String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Return the value associated with the column: register_time
	 */
	public java.util.Date getRegisterTime() {
		return registerTime;
	}

	/**
	 * Set the value related to the column: register_time
	 * 
	 * @param registerTime
	 *            the register_time value
	 */
	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}

	/**
	 * Return the value associated with the column: register_ip
	 */
	public java.lang.String getRegisterIp() {
		return registerIp;
	}

	/**
	 * Set the value related to the column: register_ip
	 * 
	 * @param registerIp
	 *            the register_ip value
	 */
	public void setRegisterIp(java.lang.String registerIp) {
		this.registerIp = registerIp;
	}

	/**
	 * Return the value associated with the column: last_login_time
	 */
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * Set the value related to the column: last_login_time
	 * 
	 * @param lastLoginTime
	 *            the last_login_time value
	 */
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * Return the value associated with the column: last_login_ip
	 */
	public java.lang.String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * Set the value related to the column: last_login_ip
	 * 
	 * @param lastLoginIp
	 *            the last_login_ip value
	 */
	public void setLastLoginIp(java.lang.String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * Return the value associated with the column: login_count
	 */
	public java.lang.Integer getLoginCount() {
		return loginCount;
	}

	/**
	 * Set the value related to the column: login_count
	 * 
	 * @param loginCount
	 *            the login_count value
	 */
	public void setLoginCount(java.lang.Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * Return the value associated with the column: upload_total
	 */
	public java.lang.Long getUploadTotal() {
		return uploadTotal;
	}

	/**
	 * Set the value related to the column: upload_total
	 * 
	 * @param uploadTotal
	 *            the upload_total value
	 */
	public void setUploadTotal(java.lang.Long uploadTotal) {
		this.uploadTotal = uploadTotal;
	}

	/**
	 * Return the value associated with the column: UPLOAD_TODAY
	 */
	public java.lang.Integer getUploadToday() {
		return uploadToday;
	}

	/**
	 * Set the value related to the column: UPLOAD_TODAY
	 * 
	 * @param uploadToday
	 *            the UPLOAD_TODAY value
	 */
	public void setUploadToday(java.lang.Integer uploadToday) {
		this.uploadToday = uploadToday;
	}

	/**
	 * Return the value associated with the column: upload_size
	 */
	public java.lang.Integer getUploadSize() {
		return uploadSize;
	}

	/**
	 * Set the value related to the column: upload_size
	 * 
	 * @param uploadSize
	 *            the upload_size value
	 */
	public void setUploadSize(java.lang.Integer uploadSize) {
		this.uploadSize = uploadSize;
	}

	/**
	 * Return the value associated with the column: upload_date
	 */
	public java.sql.Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * Set the value related to the column: upload_date
	 * 
	 * @param uploadDate
	 *            the upload_date value
	 */
	public void setUploadDate(java.sql.Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * Return the value associated with the column: is_admin
	 */
	public java.lang.Boolean getAdmin() {
		return admin;
	}

	/**
	 * Set the value related to the column: is_admin
	 * 
	 * @param admin
	 *            the is_admin value
	 */
	public void setAdmin(java.lang.Boolean admin) {
		this.admin = admin;
	}

	/**
	 * Return the value associated with the column: is_disabled
	 */
	public java.lang.Boolean getDisabled() {
		return disabled;
	}

	/**
	 * Set the value related to the column: is_disabled
	 * 
	 * @param disabled
	 *            the is_disabled value
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Return the value associated with the column: POINT
	 */
	public java.lang.Long getPoint() {
		return point;
	}

	/**
	 * Set the value related to the column: POINT
	 * 
	 * @param point
	 *            the POINT value
	 */
	public void setPoint(java.lang.Long point) {
		this.point = point;
	}

	public java.lang.Long getPrestige() {
		return prestige;
	}

	public void setPrestige(java.lang.Long prestige) {
		this.prestige = prestige;
	}

	/**
	 * Return the value associated with the column: INTRODUCTION
	 */
	public java.lang.String getIntroduction() {
		return introduction;
	}

	/**
	 * Set the value related to the column: INTRODUCTION
	 * 
	 * @param introduction
	 *            the INTRODUCTION value
	 */
	public void setIntroduction(java.lang.String introduction) {
		this.introduction = introduction;
	}

	/**
	 * Return the value associated with the column: SIGNED
	 */
	public java.lang.String getSigned() {
		return signed;
	}

	/**
	 * Set the value related to the column: SIGNED
	 * 
	 * @param signed
	 *            the SIGNED value
	 */
	public void setSigned(java.lang.String signed) {
		this.signed = signed;
	}

	/**
	 * Return the value associated with the column: AVATAR
	 */
	public java.lang.String getAvatar() {
		return avatar;
	}

	/**
	 * Set the value related to the column: AVATAR
	 * 
	 * @param avatar
	 *            the AVATAR value
	 */
	public void setAvatar(java.lang.String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Return the value associated with the column: AVATAR_TYPE
	 */
	public java.lang.Short getAvatarType() {
		return avatarType;
	}

	/**
	 * Set the value related to the column: AVATAR_TYPE
	 * 
	 * @param avatarType
	 *            the AVATAR_TYPE value
	 */
	public void setAvatarType(java.lang.Short avatarType) {
		this.avatarType = avatarType;
	}

	/**
	 * Return the value associated with the column: TOPIC_COUNT
	 */
	public java.lang.Integer getTopicCount() {
		return topicCount;
	}

	/**
	 * Set the value related to the column: TOPIC_COUNT
	 * 
	 * @param topicCount
	 *            the TOPIC_COUNT value
	 */
	public void setTopicCount(java.lang.Integer topicCount) {
		this.topicCount = topicCount;
	}

	/**
	 * Return the value associated with the column: REPLY_COUNT
	 */
	public java.lang.Integer getReplyCount() {
		return replyCount;
	}

	/**
	 * Set the value related to the column: REPLY_COUNT
	 * 
	 * @param replyCount
	 *            the REPLY_COUNT value
	 */
	public void setReplyCount(java.lang.Integer replyCount) {
		this.replyCount = replyCount;
	}

	/**
	 * Return the value associated with the column: PRIME_COUNT
	 */
	public java.lang.Integer getPrimeCount() {
		return primeCount;
	}

	/**
	 * Set the value related to the column: PRIME_COUNT
	 * 
	 * @param primeCount
	 *            the PRIME_COUNT value
	 */
	public void setPrimeCount(java.lang.Integer primeCount) {
		this.primeCount = primeCount;
	}

	/**
	 * Return the value associated with the column: POST_TODAY
	 */
	public java.lang.Integer getPostToday() {
		return postToday;
	}

	/**
	 * Set the value related to the column: POST_TODAY
	 * 
	 * @param postToday
	 *            the POST_TODAY value
	 */
	public void setPostToday(java.lang.Integer postToday) {
		this.postToday = postToday;
	}

	/**
	 * Return the value associated with the column: LAST_POST_TIME
	 */
	public java.util.Date getLastPostTime() {
		return lastPostTime;
	}

	/**
	 * Set the value related to the column: LAST_POST_TIME
	 * 
	 * @param lastPostTime
	 *            the LAST_POST_TIME value
	 */
	public void setLastPostTime(java.util.Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}

	/**
	 * Return the value associated with the column: PROHIBIT_POST
	 */
	public java.lang.Short getProhibitPost() {
		return prohibitPost;
	}

	/**
	 * Set the value related to the column: PROHIBIT_POST
	 * 
	 * @param prohibitPost
	 *            the PROHIBIT_POST value
	 */
	public void setProhibitPost(java.lang.Short prohibitPost) {
		this.prohibitPost = prohibitPost;
	}

	/**
	 * Return the value associated with the column: PROHIBIT_TIME
	 */
	public java.util.Date getProhibitTime() {
		return prohibitTime;
	}

	/**
	 * Set the value related to the column: PROHIBIT_TIME
	 * 
	 * @param prohibitTime
	 *            the PROHIBIT_TIME value
	 */
	public void setProhibitTime(java.util.Date prohibitTime) {
		this.prohibitTime = prohibitTime;
	}

	/**
	 * Return the value associated with the column: GRADE_TODAY
	 */
	public java.lang.Integer getGradeToday() {
		return gradeToday;
	}

	/**
	 * Set the value related to the column: GRADE_TODAY
	 * 
	 * @param gradeToday
	 *            the GRADE_TODAY value
	 */
	public void setGradeToday(java.lang.Integer gradeToday) {
		this.gradeToday = gradeToday;
	}
	


	public java.lang.Integer getMagicPacketSize() {
		return magicPacketSize;
	}

	public void setMagicPacketSize(java.lang.Integer magicPacketSize) {
		this.magicPacketSize = magicPacketSize;
	}

	public com.jeecms.bbs.entity.BbsUserOnline getUserOnline() {
		return userOnline;
	}

	public void setUserOnline(com.jeecms.bbs.entity.BbsUserOnline userOnline) {
		this.userOnline = userOnline;
	}

	/**
	 * Return the value associated with the column: group_id
	 */
	public com.jeecms.bbs.entity.BbsUserGroup getGroup() {
		return group;
	}

	/**
	 * Set the value related to the column: group_id
	 * 
	 * @param group
	 *            the group_id value
	 */
	public void setGroup(com.jeecms.bbs.entity.BbsUserGroup group) {
		this.group = group;
	}

	/**
	 * Return the value associated with the column: userExtSet
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsUserExt> getUserExtSet() {
		return userExtSet;
	}

	/**
	 * Set the value related to the column: userExtSet
	 * 
	 * @param userExtSet
	 *            the userExtSet value
	 */
	public void setUserExtSet(
			java.util.Set<com.jeecms.bbs.entity.BbsUserExt> userExtSet) {
		this.userExtSet = userExtSet;
	}

	public java.util.Set<com.jeecms.bbs.entity.BbsLoginLog> getLoginLogs() {
		return loginLogs;
	}

	public void setLoginLogs(
			java.util.Set<com.jeecms.bbs.entity.BbsLoginLog> loginLogs) {
		this.loginLogs = loginLogs;
	}

	

	public java.util.Set<com.jeecms.bbs.entity.BbsMemberMagic> getMemberMagics() {
		return memberMagics;
	}

	public void setMemberMagics(
			java.util.Set<com.jeecms.bbs.entity.BbsMemberMagic> memberMagics) {
		this.memberMagics = memberMagics;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsUser))
			return false;
		else {
			com.jeecms.bbs.entity.BbsUser bbsUser = (com.jeecms.bbs.entity.BbsUser) obj;
			if (null == this.getId() || null == bbsUser.getId())
				return false;
			else
				return (this.getId().equals(bbsUser.getId()));
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