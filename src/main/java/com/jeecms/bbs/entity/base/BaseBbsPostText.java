package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the BBS_POST_TEXT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="BBS_POST_TEXT"
 */

public abstract class BaseBbsPostText  implements Serializable {

	public static String REF = "BbsPostText";
	public static String PROP_POST = "post";
	public static String PROP_TITLE = "title";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";


	// constructors
	public BaseBbsPostText () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsPostText (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String title;
	private java.lang.String content;

	// one to one
	private com.jeecms.bbs.entity.BbsPost post;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
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
	 * Return the value associated with the column: POST_TITLE
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: POST_TITLE
	 * @param title the POST_TITLE value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
	}


	/**
	 * Return the value associated with the column: POST_CONTENT
	 */
	public java.lang.String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: POST_CONTENT
	 * @param content the POST_CONTENT value
	 */
	public void setContent (java.lang.String content) {
		this.content = content;
	}


	/**
	 * Return the value associated with the column: post
	 */
	public com.jeecms.bbs.entity.BbsPost getPost () {
		return post;
	}

	/**
	 * Set the value related to the column: post
	 * @param post the post value
	 */
	public void setPost (com.jeecms.bbs.entity.BbsPost post) {
		this.post = post;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsPostText)) return false;
		else {
			com.jeecms.bbs.entity.BbsPostText bbsPostText = (com.jeecms.bbs.entity.BbsPostText) obj;
			if (null == this.getId() || null == bbsPostText.getId()) return false;
			else return (this.getId().equals(bbsPostText.getId()));
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