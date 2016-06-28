package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_TOPIC table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_TOPIC"
 */

public abstract class BaseBbsTopic  implements Serializable {

	public static String REF = "BbsTopic";
	public static String PROP_SORT_TIME = "sortTime";
	public static String PROP_SITE = "site";
	public static String PROP_REPLY_COUNT = "replyCount";
	public static String PROP_OUTER_URL = "outerUrl";
	public static String PROP_LAST_REPLY = "lastReply";
	public static String PROP_TOPIC_TEXT = "topicText";
	public static String PROP_LAST_TIME = "lastTime";
	public static String PROP_TOP_LEVEL = "topLevel";
	public static String PROP_PRIME_LEVEL = "primeLevel";
	public static String PROP_STYLE_TIME = "styleTime";
	public static String PROP_LAST_POST = "lastPost";
	public static String PROP_FORUM = "forum";
	public static String PROP_CREATER = "creater";
	public static String PROP_MODERATOR_REPLY = "moderatorReply";
	public static String PROP_STATUS = "status";
	public static String PROP_AFFIX = "affix";
	public static String PROP_HAVE_REPLY = "haveReply";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_STYLE_COLOR = "styleColor";
	public static String PROP_VIEW_COUNT = "viewCount";
	public static String PROP_FIRST_POST = "firstPost";
	public static String PROP_ID = "id";
	public static String PROP_STYLE_BOLD = "styleBold";
	public static String PROP_STYLE_ITALIC = "styleItalic";


	// constructors
	public BaseBbsTopic () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsTopic (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsTopic (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		com.jeecms.bbs.entity.BbsForum forum,
		com.jeecms.bbs.entity.BbsUser creater,
		com.jeecms.bbs.entity.BbsUser lastReply,
		java.util.Date createTime,
		java.util.Date lastTime,
		java.util.Date sortTime,
		java.lang.Long viewCount,
		java.lang.Integer replyCount,
		java.lang.Short topLevel,
		java.lang.Short primeLevel,
		java.lang.Boolean styleBold,
		java.lang.Boolean styleItalic,
		java.lang.Short status,
		java.lang.Boolean affix,
		java.lang.Boolean moderatorReply) {

		this.setId(id);
		this.setSite(site);
		this.setForum(forum);
		this.setCreater(creater);
		this.setLastReply(lastReply);
		this.setCreateTime(createTime);
		this.setLastTime(lastTime);
		this.setSortTime(sortTime);
		this.setViewCount(viewCount);
		this.setReplyCount(replyCount);
		this.setTopLevel(topLevel);
		this.setPrimeLevel(primeLevel);
		this.setStyleBold(styleBold);
		this.setStyleItalic(styleItalic);
		this.setStatus(status);
		this.setAffix(affix);
		this.setModeratorReply(moderatorReply);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String title;
	private java.util.Date createTime;
	private java.util.Date lastTime;
	private java.util.Date sortTime;
	private java.lang.Long viewCount;
	private java.lang.Integer replyCount;
	private java.lang.Short topLevel;
	private java.lang.Short primeLevel;
	private java.lang.Boolean styleBold;
	private java.lang.Boolean styleItalic;
	private java.lang.String styleColor;
	private java.util.Date styleTime;
	private java.lang.String outerUrl;
	private java.lang.Short status;
	private java.lang.Boolean affix;
	private java.lang.Boolean moderatorReply;
	private java.lang.String haveReply;
	private java.lang.Boolean allayReply;
	private java.lang.Boolean hasSofeed;
	

	// one to one
	private com.jeecms.bbs.entity.BbsTopicText topicText;

	// many to one
	private com.jeecms.bbs.entity.BbsPost firstPost;
	private com.jeecms.core.entity.CmsSite site;
	private com.jeecms.bbs.entity.BbsForum forum;
	private com.jeecms.bbs.entity.BbsPost lastPost;
	private com.jeecms.bbs.entity.BbsUser creater;
	private com.jeecms.bbs.entity.BbsUser lastReply;
	private com.jeecms.bbs.entity.BbsPostType postType;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="TOPIC_ID"
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
	




	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	/**
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: LAST_TIME
	 */
	public java.util.Date getLastTime () {
		return lastTime;
	}

	/**
	 * Set the value related to the column: LAST_TIME
	 * @param lastTime the LAST_TIME value
	 */
	public void setLastTime (java.util.Date lastTime) {
		this.lastTime = lastTime;
	}


	/**
	 * Return the value associated with the column: SORT_TIME
	 */
	public java.util.Date getSortTime () {
		return sortTime;
	}

	/**
	 * Set the value related to the column: SORT_TIME
	 * @param sortTime the SORT_TIME value
	 */
	public void setSortTime (java.util.Date sortTime) {
		this.sortTime = sortTime;
	}


	/**
	 * Return the value associated with the column: VIEW_COUNT
	 */
	public java.lang.Long getViewCount () {
		return viewCount;
	}

	/**
	 * Set the value related to the column: VIEW_COUNT
	 * @param viewCount the VIEW_COUNT value
	 */
	public void setViewCount (java.lang.Long viewCount) {
		this.viewCount = viewCount;
	}


	/**
	 * Return the value associated with the column: REPLY_COUNT
	 */
	public java.lang.Integer getReplyCount () {
		return replyCount;
	}

	/**
	 * Set the value related to the column: REPLY_COUNT
	 * @param replyCount the REPLY_COUNT value
	 */
	public void setReplyCount (java.lang.Integer replyCount) {
		this.replyCount = replyCount;
	}


	/**
	 * Return the value associated with the column: TOP_LEVEL
	 */
	public java.lang.Short getTopLevel () {
		return topLevel;
	}

	/**
	 * Set the value related to the column: TOP_LEVEL
	 * @param topLevel the TOP_LEVEL value
	 */
	public void setTopLevel (java.lang.Short topLevel) {
		this.topLevel = topLevel;
	}


	/**
	 * Return the value associated with the column: PRIME_LEVEL
	 */
	public java.lang.Short getPrimeLevel () {
		return primeLevel;
	}

	/**
	 * Set the value related to the column: PRIME_LEVEL
	 * @param primeLevel the PRIME_LEVEL value
	 */
	public void setPrimeLevel (java.lang.Short primeLevel) {
		this.primeLevel = primeLevel;
	}


	/**
	 * Return the value associated with the column: STYLE_BOLD
	 */
	public java.lang.Boolean getStyleBold () {
		return styleBold;
	}

	/**
	 * Set the value related to the column: STYLE_BOLD
	 * @param styleBold the STYLE_BOLD value
	 */
	public void setStyleBold (java.lang.Boolean styleBold) {
		this.styleBold = styleBold;
	}


	/**
	 * Return the value associated with the column: STYLE_ITALIC
	 */
	public java.lang.Boolean getStyleItalic () {
		return styleItalic;
	}

	/**
	 * Set the value related to the column: STYLE_ITALIC
	 * @param styleItalic the STYLE_ITALIC value
	 */
	public void setStyleItalic (java.lang.Boolean styleItalic) {
		this.styleItalic = styleItalic;
	}


	/**
	 * Return the value associated with the column: STYLE_COLOR
	 */
	public java.lang.String getStyleColor () {
		return styleColor;
	}

	/**
	 * Set the value related to the column: STYLE_COLOR
	 * @param styleColor the STYLE_COLOR value
	 */
	public void setStyleColor (java.lang.String styleColor) {
		this.styleColor = styleColor;
	}


	/**
	 * Return the value associated with the column: STYLE_TIME
	 */
	public java.util.Date getStyleTime () {
		return styleTime;
	}

	/**
	 * Set the value related to the column: STYLE_TIME
	 * @param styleTime the STYLE_TIME value
	 */
	public void setStyleTime (java.util.Date styleTime) {
		this.styleTime = styleTime;
	}


	/**
	 * Return the value associated with the column: OUTER_URL
	 */
	public java.lang.String getOuterUrl () {
		return outerUrl;
	}

	/**
	 * Set the value related to the column: OUTER_URL
	 * @param outerUrl the OUTER_URL value
	 */
	public void setOuterUrl (java.lang.String outerUrl) {
		this.outerUrl = outerUrl;
	}


	/**
	 * Return the value associated with the column: STATUS
	 */
	public java.lang.Short getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (java.lang.Short status) {
		this.status = status;
	}


	/**
	 * Return the value associated with the column: IS_AFFIX
	 */
	public java.lang.Boolean getAffix () {
		return affix;
	}

	/**
	 * Set the value related to the column: IS_AFFIX
	 * @param affix the IS_AFFIX value
	 */
	public void setAffix (java.lang.Boolean affix) {
		this.affix = affix;
	}


	/**
	 * Return the value associated with the column: moderator_reply
	 */
	public java.lang.Boolean getModeratorReply () {
		return moderatorReply;
	}

	/**
	 * Set the value related to the column: moderator_reply
	 * @param moderatorReply the moderator_reply value
	 */
	public void setModeratorReply (java.lang.Boolean moderatorReply) {
		this.moderatorReply = moderatorReply;
	}


	/**
	 * Return the value associated with the column: HAVA_REPLY
	 */
	public java.lang.String getHaveReply () {
		return haveReply;
	}

	/**
	 * Set the value related to the column: HAVA_REPLY
	 * @param haveReply the HAVA_REPLY value
	 */
	public void setHaveReply (java.lang.String haveReply) {
		this.haveReply = haveReply;
	}
	

	public java.lang.Boolean getAllayReply() {
		return allayReply;
	}

	public void setAllayReply(java.lang.Boolean allayReply) {
		this.allayReply = allayReply;
	}
	

	public java.lang.Boolean getHasSofeed() {
		return hasSofeed;
	}

	public void setHasSofeed(java.lang.Boolean hasSofeed) {
		this.hasSofeed = hasSofeed;
	}

	/**
	 * Return the value associated with the column: topicText
	 */
	public com.jeecms.bbs.entity.BbsTopicText getTopicText () {
		return topicText;
	}

	/**
	 * Set the value related to the column: topicText
	 * @param topicText the topicText value
	 */
	public void setTopicText (com.jeecms.bbs.entity.BbsTopicText topicText) {
		this.topicText = topicText;
	}


	/**
	 * Return the value associated with the column: FIRST_POST_ID
	 */
	public com.jeecms.bbs.entity.BbsPost getFirstPost () {
		return firstPost;
	}

	/**
	 * Set the value related to the column: FIRST_POST_ID
	 * @param firstPost the FIRST_POST_ID value
	 */
	public void setFirstPost (com.jeecms.bbs.entity.BbsPost firstPost) {
		this.firstPost = firstPost;
	}


	/**
	 * Return the value associated with the column: site_id
	 */
	public com.jeecms.core.entity.CmsSite getSite () {
		return site;
	}

	/**
	 * Set the value related to the column: site_id
	 * @param site the site_id value
	 */
	public void setSite (com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}


	/**
	 * Return the value associated with the column: FORUM_ID
	 */
	public com.jeecms.bbs.entity.BbsForum getForum () {
		return forum;
	}

	/**
	 * Set the value related to the column: FORUM_ID
	 * @param forum the FORUM_ID value
	 */
	public void setForum (com.jeecms.bbs.entity.BbsForum forum) {
		this.forum = forum;
	}


	/**
	 * Return the value associated with the column: LAST_POST_ID
	 */
	public com.jeecms.bbs.entity.BbsPost getLastPost () {
		return lastPost;
	}

	/**
	 * Set the value related to the column: LAST_POST_ID
	 * @param lastPost the LAST_POST_ID value
	 */
	public void setLastPost (com.jeecms.bbs.entity.BbsPost lastPost) {
		this.lastPost = lastPost;
	}


	/**
	 * Return the value associated with the column: creater_id
	 */
	public com.jeecms.bbs.entity.BbsUser getCreater () {
		return creater;
	}

	/**
	 * Set the value related to the column: creater_id
	 * @param creater the creater_id value
	 */
	public void setCreater (com.jeecms.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}


	/**
	 * Return the value associated with the column: replyer_id
	 */
	public com.jeecms.bbs.entity.BbsUser getLastReply () {
		return lastReply;
	}

	/**
	 * Set the value related to the column: replyer_id
	 * @param lastReply the replyer_id value
	 */
	public void setLastReply (com.jeecms.bbs.entity.BbsUser lastReply) {
		this.lastReply = lastReply;
	}
	
	public com.jeecms.bbs.entity.BbsPostType getPostType() {
		return postType;
	}

	public void setPostType(com.jeecms.bbs.entity.BbsPostType postType) {
		this.postType = postType;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsTopic)) return false;
		else {
			com.jeecms.bbs.entity.BbsTopic bbsTopic = (com.jeecms.bbs.entity.BbsTopic) obj;
			if (null == this.getId() || null == bbsTopic.getId()) return false;
			else return (this.getId().equals(bbsTopic.getId()));
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