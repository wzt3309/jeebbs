package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the attachment table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="attachment"
 */

public abstract class BaseAttachment  implements Serializable {

	public static String REF = "Attachment";
	public static String PROP_PICTURE = "picture";
	public static String PROP_FILE_PATH = "filePath";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_POST = "post";
	public static String PROP_FILE_NAME = "fileName";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_FILE_SIZE = "fileSize";


	// constructors
	public BaseAttachment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAttachment (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAttachment (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsPost post,
		java.lang.Boolean picture) {

		this.setId(id);
		this.setPost(post);
		this.setPicture(picture);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.String filePath;
	private java.lang.String fileName;
	private java.lang.Integer fileSize;
	private java.util.Date createTime;
	private java.lang.Boolean picture;

	// many to one
	private com.jeecms.bbs.entity.BbsPost post;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="attachment_id"
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
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: file_path
	 */
	public java.lang.String getFilePath () {
		return filePath;
	}

	/**
	 * Set the value related to the column: file_path
	 * @param filePath the file_path value
	 */
	public void setFilePath (java.lang.String filePath) {
		this.filePath = filePath;
	}


	/**
	 * Return the value associated with the column: file_name
	 */
	public java.lang.String getFileName () {
		return fileName;
	}

	/**
	 * Set the value related to the column: file_name
	 * @param fileName the file_name value
	 */
	public void setFileName (java.lang.String fileName) {
		this.fileName = fileName;
	}


	/**
	 * Return the value associated with the column: file_size
	 */
	public java.lang.Integer getFileSize () {
		return fileSize;
	}

	/**
	 * Set the value related to the column: file_size
	 * @param fileSize the file_size value
	 */
	public void setFileSize (java.lang.Integer fileSize) {
		this.fileSize = fileSize;
	}


	/**
	 * Return the value associated with the column: create_time
	 */
	public java.util.Date getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: create_time
	 * @param createTime the create_time value
	 */
	public void setCreateTime (java.util.Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: is_picture
	 */
	public java.lang.Boolean getPicture () {
		return picture;
	}

	/**
	 * Set the value related to the column: is_picture
	 * @param picture the is_picture value
	 */
	public void setPicture (java.lang.Boolean picture) {
		this.picture = picture;
	}


	/**
	 * Return the value associated with the column: post_id
	 */
	public com.jeecms.bbs.entity.BbsPost getPost () {
		return post;
	}

	/**
	 * Set the value related to the column: post_id
	 * @param post the post_id value
	 */
	public void setPost (com.jeecms.bbs.entity.BbsPost post) {
		this.post = post;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.Attachment)) return false;
		else {
			com.jeecms.bbs.entity.Attachment attachment = (com.jeecms.bbs.entity.Attachment) obj;
			if (null == this.getId() || null == attachment.getId()) return false;
			else return (this.getId().equals(attachment.getId()));
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