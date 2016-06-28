package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_CONFIG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_CONFIG"
 */

public abstract class BaseBbsConfig  implements Serializable {

	public static String REF = "BbsConfig";
	public static String PROP_AVATAR_WIDTH = "avatarWidth";
	public static String PROP_POST_MAX = "postMax";
	public static String PROP_AVATAR_HEIGHT = "avatarHeight";
	public static String PROP_SITE = "site";
	public static String PROP_DEF_AVATAR = "defAvatar";
	public static String PROP_REGISTER_GROUP = "registerGroup";
	public static String PROP_TOPIC_TOTAL = "topicTotal";
	public static String PROP_LAST_USER = "lastUser";
	public static String PROP_REGISTER_RULE = "registerRule";
	public static String PROP_DEFAULT_GROUP = "defaultGroup";
	public static String PROP_POST_MAX_DATE = "postMaxDate";
	public static String PROP_KEYWORDS = "keywords";
	public static String PROP_POST_YESTERDAY = "postYesterday";
	public static String PROP_AUTO_REGISTER = "autoRegister";
	public static String PROP_REGISTER_STATUS = "registerStatus";
	public static String PROP_POST_TOTAL = "postTotal";
	public static String PROP_TOPIC_COUNT_PER_PAGE = "topicCountPerPage";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_TOPIC_HOT_COUNT = "topicHotCount";
	public static String PROP_USER_TOTAL = "userTotal";
	public static String PROP_POST_COUNT_PER_PAGE = "postCountPerPage";
	public static String PROP_ID = "id";
	public static String PROP_POST_TODAY = "postToday";


	// constructors
	public BaseBbsConfig () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsConfig (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsConfig (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser lastUser,
		com.jeecms.bbs.entity.BbsUserGroup registerGroup,
		com.jeecms.bbs.entity.BbsUserGroup defaultGroup,
		java.lang.String defAvatar,
		java.lang.Integer avatarWidth,
		java.lang.Integer avatarHeight,
		java.lang.Integer topicCountPerPage,
		java.lang.Integer postCountPerPage,
		java.lang.String keywords,
		java.lang.String description,
		java.lang.Short registerStatus,
		java.lang.Integer topicHotCount,
		java.lang.Integer postToday,
		java.lang.Integer postYesterday,
		java.lang.Integer postMax,
		java.util.Date postMaxDate,
		java.lang.Integer topicTotal,
		java.lang.Integer postTotal,
		java.lang.Integer userTotal) {

		this.setId(id);
		this.setLastUser(lastUser);
		this.setRegisterGroup(registerGroup);
		this.setDefaultGroup(defaultGroup);
		this.setDefAvatar(defAvatar);
		this.setAvatarWidth(avatarWidth);
		this.setAvatarHeight(avatarHeight);
		this.setTopicCountPerPage(topicCountPerPage);
		this.setPostCountPerPage(postCountPerPage);
		this.setKeywords(keywords);
		this.setDescription(description);
		this.setRegisterStatus(registerStatus);
		this.setTopicHotCount(topicHotCount);
		this.setPostToday(postToday);
		this.setPostYesterday(postYesterday);
		this.setPostMax(postMax);
		this.setPostMaxDate(postMaxDate);
		this.setTopicTotal(topicTotal);
		this.setPostTotal(postTotal);
		this.setUserTotal(userTotal);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String defAvatar;
	private java.lang.Integer avatarWidth;
	private java.lang.Integer avatarHeight;
	private java.lang.Integer topicCountPerPage;
	private java.lang.Integer postCountPerPage;
	private java.lang.String keywords;
	private java.lang.String description;
	private java.lang.Short registerStatus;
	private java.lang.String registerRule;
	private java.lang.Integer topicHotCount;
	private java.lang.Integer postToday;
	private java.lang.Integer postYesterday;
	private java.lang.Integer postMax;
	private java.util.Date postMaxDate;
	private java.lang.Integer topicTotal;
	private java.lang.Integer postTotal;
	private java.lang.Integer userTotal;
	private java.lang.Boolean autoRegister;
	private java.lang.Boolean emailValidate;

	// one to one
	private com.jeecms.core.entity.CmsSite site;

	// many to one
	private com.jeecms.bbs.entity.BbsUser lastUser;
	private com.jeecms.bbs.entity.BbsUserGroup registerGroup;
	private com.jeecms.bbs.entity.BbsUserGroup defaultGroup;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="CONFIG_ID"
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
	 * Return the value associated with the column: DEF_AVATAR
	 */
	public java.lang.String getDefAvatar () {
		return defAvatar;
	}

	/**
	 * Set the value related to the column: DEF_AVATAR
	 * @param defAvatar the DEF_AVATAR value
	 */
	public void setDefAvatar (java.lang.String defAvatar) {
		this.defAvatar = defAvatar;
	}


	/**
	 * Return the value associated with the column: AVATAR_WIDTH
	 */
	public java.lang.Integer getAvatarWidth () {
		return avatarWidth;
	}

	/**
	 * Set the value related to the column: AVATAR_WIDTH
	 * @param avatarWidth the AVATAR_WIDTH value
	 */
	public void setAvatarWidth (java.lang.Integer avatarWidth) {
		this.avatarWidth = avatarWidth;
	}


	/**
	 * Return the value associated with the column: AVATAR_HEIGHT
	 */
	public java.lang.Integer getAvatarHeight () {
		return avatarHeight;
	}

	/**
	 * Set the value related to the column: AVATAR_HEIGHT
	 * @param avatarHeight the AVATAR_HEIGHT value
	 */
	public void setAvatarHeight (java.lang.Integer avatarHeight) {
		this.avatarHeight = avatarHeight;
	}


	/**
	 * Return the value associated with the column: TOPIC_COUNT_PER_PAGE
	 */
	public java.lang.Integer getTopicCountPerPage () {
		return topicCountPerPage;
	}

	/**
	 * Set the value related to the column: TOPIC_COUNT_PER_PAGE
	 * @param topicCountPerPage the TOPIC_COUNT_PER_PAGE value
	 */
	public void setTopicCountPerPage (java.lang.Integer topicCountPerPage) {
		this.topicCountPerPage = topicCountPerPage;
	}


	/**
	 * Return the value associated with the column: POST_COUNT_PER_PAGE
	 */
	public java.lang.Integer getPostCountPerPage () {
		return postCountPerPage;
	}

	/**
	 * Set the value related to the column: POST_COUNT_PER_PAGE
	 * @param postCountPerPage the POST_COUNT_PER_PAGE value
	 */
	public void setPostCountPerPage (java.lang.Integer postCountPerPage) {
		this.postCountPerPage = postCountPerPage;
	}


	/**
	 * Return the value associated with the column: KEYWORDS
	 */
	public java.lang.String getKeywords () {
		return keywords;
	}

	/**
	 * Set the value related to the column: KEYWORDS
	 * @param keywords the KEYWORDS value
	 */
	public void setKeywords (java.lang.String keywords) {
		this.keywords = keywords;
	}


	/**
	 * Return the value associated with the column: DESCRIPTION
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: REGISTER_STATUS
	 */
	public java.lang.Short getRegisterStatus () {
		return registerStatus;
	}

	/**
	 * Set the value related to the column: REGISTER_STATUS
	 * @param registerStatus the REGISTER_STATUS value
	 */
	public void setRegisterStatus (java.lang.Short registerStatus) {
		this.registerStatus = registerStatus;
	}


	/**
	 * Return the value associated with the column: REGISTER_RULE
	 */
	public java.lang.String getRegisterRule () {
		return registerRule;
	}

	/**
	 * Set the value related to the column: REGISTER_RULE
	 * @param registerRule the REGISTER_RULE value
	 */
	public void setRegisterRule (java.lang.String registerRule) {
		this.registerRule = registerRule;
	}


	/**
	 * Return the value associated with the column: TOPIC_HOT_COUNT
	 */
	public java.lang.Integer getTopicHotCount () {
		return topicHotCount;
	}

	/**
	 * Set the value related to the column: TOPIC_HOT_COUNT
	 * @param topicHotCount the TOPIC_HOT_COUNT value
	 */
	public void setTopicHotCount (java.lang.Integer topicHotCount) {
		this.topicHotCount = topicHotCount;
	}


	/**
	 * Return the value associated with the column: CACHE_POST_TODAY
	 */
	public java.lang.Integer getPostToday () {
		return postToday;
	}

	/**
	 * Set the value related to the column: CACHE_POST_TODAY
	 * @param postToday the CACHE_POST_TODAY value
	 */
	public void setPostToday (java.lang.Integer postToday) {
		this.postToday = postToday;
	}


	/**
	 * Return the value associated with the column: CACHE_POST_YESTERDAY
	 */
	public java.lang.Integer getPostYesterday () {
		return postYesterday;
	}

	/**
	 * Set the value related to the column: CACHE_POST_YESTERDAY
	 * @param postYesterday the CACHE_POST_YESTERDAY value
	 */
	public void setPostYesterday (java.lang.Integer postYesterday) {
		this.postYesterday = postYesterday;
	}


	/**
	 * Return the value associated with the column: CACHE_POST_MAX
	 */
	public java.lang.Integer getPostMax () {
		return postMax;
	}

	/**
	 * Set the value related to the column: CACHE_POST_MAX
	 * @param postMax the CACHE_POST_MAX value
	 */
	public void setPostMax (java.lang.Integer postMax) {
		this.postMax = postMax;
	}


	/**
	 * Return the value associated with the column: CACHE_POST_MAX_DATE
	 */
	public java.util.Date getPostMaxDate () {
		return postMaxDate;
	}

	/**
	 * Set the value related to the column: CACHE_POST_MAX_DATE
	 * @param postMaxDate the CACHE_POST_MAX_DATE value
	 */
	public void setPostMaxDate (java.util.Date postMaxDate) {
		this.postMaxDate = postMaxDate;
	}


	/**
	 * Return the value associated with the column: CACHE_TOPIC_TOTAL
	 */
	public java.lang.Integer getTopicTotal () {
		return topicTotal;
	}

	/**
	 * Set the value related to the column: CACHE_TOPIC_TOTAL
	 * @param topicTotal the CACHE_TOPIC_TOTAL value
	 */
	public void setTopicTotal (java.lang.Integer topicTotal) {
		this.topicTotal = topicTotal;
	}


	/**
	 * Return the value associated with the column: CACHE_POST_TOTAL
	 */
	public java.lang.Integer getPostTotal () {
		return postTotal;
	}

	/**
	 * Set the value related to the column: CACHE_POST_TOTAL
	 * @param postTotal the CACHE_POST_TOTAL value
	 */
	public void setPostTotal (java.lang.Integer postTotal) {
		this.postTotal = postTotal;
	}


	/**
	 * Return the value associated with the column: CACHE_USER_TOTAL
	 */
	public java.lang.Integer getUserTotal () {
		return userTotal;
	}

	/**
	 * Set the value related to the column: CACHE_USER_TOTAL
	 * @param userTotal the CACHE_USER_TOTAL value
	 */
	public void setUserTotal (java.lang.Integer userTotal) {
		this.userTotal = userTotal;
	}


	/**
	 * Return the value associated with the column: AUTO_REGISTER
	 */
	public java.lang.Boolean getAutoRegister () {
		return autoRegister;
	}

	/**
	 * Set the value related to the column: AUTO_REGISTER
	 * @param autoRegister the AUTO_REGISTER value
	 */
	public void setAutoRegister (java.lang.Boolean autoRegister) {
		this.autoRegister = autoRegister;
	}
	


	public java.lang.Boolean getEmailValidate() {
		return emailValidate;
	}

	public void setEmailValidate(java.lang.Boolean emailValidate) {
		this.emailValidate = emailValidate;
	}

	/**
	 * Return the value associated with the column: site
	 */
	public com.jeecms.core.entity.CmsSite getSite () {
		return site;
	}

	/**
	 * Set the value related to the column: site
	 * @param site the site value
	 */
	public void setSite (com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}


	/**
	 * Return the value associated with the column: last_user_id
	 */
	public com.jeecms.bbs.entity.BbsUser getLastUser () {
		return lastUser;
	}

	/**
	 * Set the value related to the column: last_user_id
	 * @param lastUser the last_user_id value
	 */
	public void setLastUser (com.jeecms.bbs.entity.BbsUser lastUser) {
		this.lastUser = lastUser;
	}


	/**
	 * Return the value associated with the column: REGISTER_GROUP_ID
	 */
	public com.jeecms.bbs.entity.BbsUserGroup getRegisterGroup () {
		return registerGroup;
	}

	/**
	 * Set the value related to the column: REGISTER_GROUP_ID
	 * @param registerGroup the REGISTER_GROUP_ID value
	 */
	public void setRegisterGroup (com.jeecms.bbs.entity.BbsUserGroup registerGroup) {
		this.registerGroup = registerGroup;
	}


	/**
	 * Return the value associated with the column: DEFAULT_GROUP_ID
	 */
	public com.jeecms.bbs.entity.BbsUserGroup getDefaultGroup () {
		return defaultGroup;
	}

	/**
	 * Set the value related to the column: DEFAULT_GROUP_ID
	 * @param defaultGroup the DEFAULT_GROUP_ID value
	 */
	public void setDefaultGroup (com.jeecms.bbs.entity.BbsUserGroup defaultGroup) {
		this.defaultGroup = defaultGroup;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsConfig)) return false;
		else {
			com.jeecms.bbs.entity.BbsConfig bbsConfig = (com.jeecms.bbs.entity.BbsConfig) obj;
			if (null == this.getId() || null == bbsConfig.getId()) return false;
			else return (this.getId().equals(bbsConfig.getId()));
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