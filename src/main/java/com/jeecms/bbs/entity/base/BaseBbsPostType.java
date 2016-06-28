package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_post_type table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_post_type"
 */

public abstract class BaseBbsPostType  implements Serializable {

	public static String REF = "BbsPostType";
	public static String PROP_PARENT = "parent";
	public static String PROP_SITE = "site";
	public static String PROP_FORUM = "forum";
	public static String PROP_ID = "id";
	public static String PROP_TYPE_NAME = "typeName";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseBbsPostType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsPostType (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsPostType (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		com.jeecms.bbs.entity.BbsForum forum) {

		this.setId(id);
		this.setSite(site);
		this.setForum(forum);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String typeName;
	private java.lang.Integer priority;

	// many to one
	private com.jeecms.core.entity.CmsSite site;
	private com.jeecms.bbs.entity.BbsForum forum;
	private com.jeecms.bbs.entity.BbsPostType parent;

	// collections
	private java.util.Set<com.jeecms.bbs.entity.BbsPostType> childs;
	private java.util.Set<com.jeecms.bbs.entity.BbsUserGroup> groups;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="type_id"
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
	 * Return the value associated with the column: type_name
	 */
	public java.lang.String getTypeName () {
		return typeName;
	}

	/**
	 * Set the value related to the column: type_name
	 * @param typeName the type_name value
	 */
	public void setTypeName (java.lang.String typeName) {
		this.typeName = typeName;
	}


	/**
	 * Return the value associated with the column: priority
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: priority
	 * @param priority the priority value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
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
	 * Return the value associated with the column: forum_id
	 */
	public com.jeecms.bbs.entity.BbsForum getForum () {
		return forum;
	}

	/**
	 * Set the value related to the column: forum_id
	 * @param forum the forum_id value
	 */
	public void setForum (com.jeecms.bbs.entity.BbsForum forum) {
		this.forum = forum;
	}


	/**
	 * Return the value associated with the column: parent_id
	 */
	public com.jeecms.bbs.entity.BbsPostType getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: parent_id
	 * @param parent the parent_id value
	 */
	public void setParent (com.jeecms.bbs.entity.BbsPostType parent) {
		this.parent = parent;
	}


	/**
	 * Return the value associated with the column: childs
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsPostType> getChilds () {
		return childs;
	}

	/**
	 * Set the value related to the column: childs
	 * @param childs the childs value
	 */
	public void setChilds (java.util.Set<com.jeecms.bbs.entity.BbsPostType> childs) {
		this.childs = childs;
	}


	/**
	 * Return the value associated with the column: groups
	 */
	public java.util.Set<com.jeecms.bbs.entity.BbsUserGroup> getGroups () {
		return groups;
	}

	/**
	 * Set the value related to the column: groups
	 * @param groups the groups value
	 */
	public void setGroups (java.util.Set<com.jeecms.bbs.entity.BbsUserGroup> groups) {
		this.groups = groups;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsPostType)) return false;
		else {
			com.jeecms.bbs.entity.BbsPostType bbsPostType = (com.jeecms.bbs.entity.BbsPostType) obj;
			if (null == this.getId() || null == bbsPostType.getId()) return false;
			else return (this.getId().equals(bbsPostType.getId()));
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