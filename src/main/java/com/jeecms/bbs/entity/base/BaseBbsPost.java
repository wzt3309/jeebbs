package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_POST table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_POST"
 */

public abstract class BaseBbsPost  implements Serializable {

	public static String REF = "BbsPost";
	public static String PROP_EDIT_TIME = "editTime";
	public static String PROP_CONFIG = "config";
	public static String PROP_SITE = "site";
	public static String PROP_INDEX_COUNT = "indexCount";
	public static String PROP_EDITER = "editer";
	public static String PROP_POSTER_IP = "posterIp";
	public static String PROP_POST_TEXT = "postText";
	public static String PROP_HIDDEN = "hidden";
	public static String PROP_CREATER = "creater";
	public static String PROP_TITLE = "title";
	public static String PROP_EDITER_IP = "editerIp";
	public static String PROP_STATUS = "status";
	public static String PROP_AFFIX = "affix";
	public static String PROP_EDIT_COUNT = "editCount";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_TOPIC = "topic";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsPost () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsPost (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsPost (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		com.jeecms.bbs.entity.BbsConfig config,
		com.jeecms.bbs.entity.BbsTopic topic,
		com.jeecms.bbs.entity.BbsUser creater,
		java.lang.String title,
		java.util.Date createTime,
		java.lang.String posterIp,
		java.lang.Integer editCount,
		java.lang.Integer indexCount,
		java.lang.Short status,
		java.lang.Boolean affix,
		java.lang.Boolean hidden) {

		this.setId(id);
		this.setSite(site);
		this.setConfig(config);
		this.setTopic(topic);
		this.setCreater(creater);
		this.setTitle(title);
		this.setCreateTime(createTime);
		this.setPosterIp(posterIp);
		this.setEditCount(editCount);
		this.setIndexCount(indexCount);
		this.setStatus(status);
		this.setAffix(affix);
		this.setHidden(hidden);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String title;
	private java.util.Date createTime;
	private java.lang.String posterIp;
	private java.util.Date editTime;
	private java.lang.String editerIp;
	private java.lang.Integer editCount;
	private java.lang.Integer indexCount;
	private java.lang.Short status;
	private java.lang.Boolean affix;
	private java.lang.Boolean hidden;
	private java.lang.Boolean anonymous;

	// one to one
	private com.jeecms.bbs.entity.BbsPostText postText;

	// many to one
	private com.jeecms.core.entity.CmsSite site;
	private com.jeecms.bbs.entity.BbsConfig config;
	private com.jeecms.bbs.entity.BbsTopic topic;
	private com.jeecms.bbs.entity.BbsUser creater;
	private com.jeecms.bbs.entity.BbsUser editer;
	private com.jeecms.bbs.entity.BbsPostType postType;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsGrade> grade;
	private java.util.Set<com.jeecms.bbs.entity.Attachment> attachments;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="POST_ID"
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
	 * Return the value associated with the column: TITLE
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: TITLE
	 * @param title the TITLE value
	 */
	public void setTitle (java.lang.String title) {
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
	 * Return the value associated with the column: POSTER_IP
	 */
	public java.lang.String getPosterIp () {
		return posterIp;
	}

	/**
	 * Set the value related to the column: POSTER_IP
	 * @param posterIp the POSTER_IP value
	 */
	public void setPosterIp (java.lang.String posterIp) {
		this.posterIp = posterIp;
	}


	/**
	 * Return the value associated with the column: EDIT_TIME
	 */
	public java.util.Date getEditTime () {
		return editTime;
	}

	/**
	 * Set the value related to the column: EDIT_TIME
	 * @param editTime the EDIT_TIME value
	 */
	public void setEditTime (java.util.Date editTime) {
		this.editTime = editTime;
	}


	/**
	 * Return the value associated with the column: EDITER_IP
	 */
	public java.lang.String getEditerIp () {
		return editerIp;
	}

	/**
	 * Set the value related to the column: EDITER_IP
	 * @param editerIp the EDITER_IP value
	 */
	public void setEditerIp (java.lang.String editerIp) {
		this.editerIp = editerIp;
	}


	/**
	 * Return the value associated with the column: EDIT_COUNT
	 */
	public java.lang.Integer getEditCount () {
		return editCount;
	}

	/**
	 * Set the value related to the column: EDIT_COUNT
	 * @param editCount the EDIT_COUNT value
	 */
	public void setEditCount (java.lang.Integer editCount) {
		this.editCount = editCount;
	}


	/**
	 * Return the value associated with the column: INDEX_COUNT
	 */
	public java.lang.Integer getIndexCount () {
		return indexCount;
	}

	/**
	 * Set the value related to the column: INDEX_COUNT
	 * @param indexCount the INDEX_COUNT value
	 */
	public void setIndexCount (java.lang.Integer indexCount) {
		this.indexCount = indexCount;
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
	 * Return the value associated with the column: IS_HIDDEN
	 */
	public java.lang.Boolean getHidden () {
		return hidden;
	}

	/**
	 * Set the value related to the column: IS_HIDDEN
	 * @param hidden the IS_HIDDEN value
	 */
	public void setHidden (java.lang.Boolean hidden) {
		this.hidden = hidden;
	}

	public java.lang.Boolean getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(java.lang.Boolean anonymous) {
		this.anonymous = anonymous;
	}

	/**
	 * Return the value associated with the column: postText
	 */
	public com.jeecms.bbs.entity.BbsPostText getPostText () {
		return postText;
	}

	/**
	 * Set the value related to the column: postText
	 * @param postText the postText value
	 */
	public void setPostText (com.jeecms.bbs.entity.BbsPostText postText) {
		this.postText = postText;
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
	 * Return the value associated with the column: CONFIG_ID
	 */
	public com.jeecms.bbs.entity.BbsConfig getConfig () {
		return config;
	}

	/**
	 * Set the value related to the column: CONFIG_ID
	 * @param config the CONFIG_ID value
	 */
	public void setConfig (com.jeecms.bbs.entity.BbsConfig config) {
		this.config = config;
	}


	/**
	 * Return the value associated with the column: TOPIC_ID
	 */
	public com.jeecms.bbs.entity.BbsTopic getTopic () {
		return topic;
	}

	/**
	 * Set the value related to the column: TOPIC_ID
	 * @param topic the TOPIC_ID value
	 */
	public void setTopic (com.jeecms.bbs.entity.BbsTopic topic) {
		this.topic = topic;
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
	 * Return the value associated with the column: editer_id
	 */
	public com.jeecms.bbs.entity.BbsUser getEditer () {
		return editer;
	}

	/**
	 * Set the value related to the column: editer_id
	 * @param editer the editer_id value
	 */
	public void setEditer (com.jeecms.bbs.entity.BbsUser editer) {
		this.editer = editer;
	}

	public com.jeecms.bbs.entity.BbsPostType getPostType() {
		return postType;
	}

	public void setPostType(com.jeecms.bbs.entity.BbsPostType postType) {
		this.postType = postType;
	}

	/**
	 * Return the value associated with the column: grade
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsGrade> getGrade () {
		return grade;
	}

	/**
	 * Set the value related to the column: grade
	 * @param grade the grade value
	 */
	public void setGrade (java.util.Set<com.jeecms.bbs.entity.BbsGrade> grade) {
		this.grade = grade;
	}


	/**
	 * Return the value associated with the column: attachments
	 */
	public java.util.Set<com.jeecms.bbs.entity.Attachment> getAttachments () {
		return attachments;
	}

	/**
	 * Set the value related to the column: attachments
	 * @param attachments the attachments value
	 */
	public void setAttachments (java.util.Set<com.jeecms.bbs.entity.Attachment> attachments) {
		this.attachments = attachments;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsPost)) return false;
		else {
			com.jeecms.bbs.entity.BbsPost bbsPost = (com.jeecms.bbs.entity.BbsPost) obj;
			if (null == this.getId() || null == bbsPost.getId()) return false;
			else return (this.getId().equals(bbsPost.getId()));
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