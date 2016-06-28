package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_CATEGORY table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_CATEGORY"
 */

public abstract class BaseBbsCategory  implements Serializable {

	public static String REF = "BbsCategory";
	public static String PROP_FORUM_COLS = "forumCols";
	public static String PROP_SITE = "site";
	public static String PROP_MODERATORS = "moderators";
	public static String PROP_PATH = "path";
	public static String PROP_PRIORITY = "priority";
	public static String PROP_TITLE = "title";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsCategory () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsCategory (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsCategory (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		java.lang.String path,
		java.lang.String title,
		java.lang.Integer priority,
		java.lang.Integer forumCols) {

		this.setId(id);
		this.setSite(site);
		this.setPath(path);
		this.setTitle(title);
		this.setPriority(priority);
		this.setForumCols(forumCols);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String path;
	private java.lang.String title;
	private java.lang.Integer priority;
	private java.lang.Integer forumCols;
	private java.lang.String moderators;

	// many to one
	private com.jeecms.core.entity.CmsSite site;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsForum> forums;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="CATEGORY_ID"
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
	 * Return the value associated with the column: PATH
	 */
	public java.lang.String getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: PATH
	 * @param path the PATH value
	 */
	public void setPath (java.lang.String path) {
		this.path = path;
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
	 * Return the value associated with the column: PRIORITY
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: PRIORITY
	 * @param priority the PRIORITY value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
	}


	/**
	 * Return the value associated with the column: FORUM_COLS
	 */
	public java.lang.Integer getForumCols () {
		return forumCols;
	}

	/**
	 * Set the value related to the column: FORUM_COLS
	 * @param forumCols the FORUM_COLS value
	 */
	public void setForumCols (java.lang.Integer forumCols) {
		this.forumCols = forumCols;
	}


	/**
	 * Return the value associated with the column: moderators
	 */
	public java.lang.String getModerators () {
		return moderators;
	}

	/**
	 * Set the value related to the column: moderators
	 * @param moderators the moderators value
	 */
	public void setModerators (java.lang.String moderators) {
		this.moderators = moderators;
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
	 * Return the value associated with the column: forums
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsForum> getForums () {
		return forums;
	}

	/**
	 * Set the value related to the column: forums
	 * @param forums the forums value
	 */
	public void setForums (java.util.Set<com.jeecms.bbs.entity.BbsForum> forums) {
		this.forums = forums;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsCategory)) return false;
		else {
			com.jeecms.bbs.entity.BbsCategory bbsCategory = (com.jeecms.bbs.entity.BbsCategory) obj;
			if (null == this.getId() || null == bbsCategory.getId()) return false;
			else return (this.getId().equals(bbsCategory.getId()));
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