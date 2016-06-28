package com.jeecms.bbs.entity.base;

import java.io.Serializable;
import java.util.HashSet;

import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.entity.BbsPostType;

/**
 * This is an object that contains data related to the BBS_USER_GROUP table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="BBS_USER_GROUP"
 */

public abstract class BaseBbsUserGroup implements Serializable {

	public static String REF = "BbsUserGroup";
	public static String PROP_TYPE = "type";
	public static String PROP_SITE = "site";
	public static String PROP_IMG_PATH = "imgPath";
	public static String PROP_POINT = "point";
	public static String PROP_DEFAULT = "default";
	public static String PROP_NAME = "name";
	public static String PROP_GRADE_NUM = "gradeNum";
	public static String PROP_ID = "id";

	// constructors
	public BaseBbsUserGroup() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsUserGroup(java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsUserGroup(java.lang.Integer id,
			com.jeecms.core.entity.CmsSite site, java.lang.String name,
			java.lang.Short type, java.lang.Long point,
			java.lang.Boolean m_default, java.lang.Integer gradeNum) {

		this.setId(id);
		this.setSite(site);
		this.setName(name);
		this.setType(type);
		this.setPoint(point);
		this.setDefault(m_default);
		this.setGradeNum(gradeNum);
		initialize();
	}

	protected void initialize() {
	}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.Short type;
	private java.lang.String imgPath;
	private java.lang.Long point;
	private java.lang.Boolean m_default;
	private java.lang.Integer gradeNum;
	private java.lang.Integer magicPacketSize;

	// many to one
	private com.jeecms.core.entity.CmsSite site;

	// collections
	private java.util.Map<java.lang.String, java.lang.String> perms;
	private java.util.Set<BbsPostType> postTypes;
	private java.util.Set<BbsCommonMagic> magics;
	private java.util.Set<BbsCommonMagic> beUsedMagics;

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="native" column="GROUP_ID"
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
	 * Return the value associated with the column: NAME
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * 
	 * @param name
	 *            the NAME value
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Return the value associated with the column: GROUP_TYPE
	 */
	public java.lang.Short getType() {
		return type;
	}

	/**
	 * Set the value related to the column: GROUP_TYPE
	 * 
	 * @param type
	 *            the GROUP_TYPE value
	 */
	public void setType(java.lang.Short type) {
		this.type = type;
	}

	/**
	 * Return the value associated with the column: GROUP_IMG
	 */
	public java.lang.String getImgPath() {
		return imgPath;
	}

	/**
	 * Set the value related to the column: GROUP_IMG
	 * 
	 * @param imgPath
	 *            the GROUP_IMG value
	 */
	public void setImgPath(java.lang.String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * Return the value associated with the column: GROUP_POINT
	 */
	public java.lang.Long getPoint() {
		return point;
	}

	/**
	 * Set the value related to the column: GROUP_POINT
	 * 
	 * @param point
	 *            the GROUP_POINT value
	 */
	public void setPoint(java.lang.Long point) {
		this.point = point;
	}

	/**
	 * Return the value associated with the column: IS_DEFAULT
	 */
	public java.lang.Boolean getDefault() {
		return m_default;
	}

	/**
	 * Set the value related to the column: IS_DEFAULT
	 * 
	 * @param m_default
	 *            the IS_DEFAULT value
	 */
	public void setDefault(java.lang.Boolean m_default) {
		this.m_default = m_default;
	}

	/**
	 * Return the value associated with the column: GRADE_NUM
	 */
	public java.lang.Integer getGradeNum() {
		return gradeNum;
	}

	/**
	 * Set the value related to the column: GRADE_NUM
	 * 
	 * @param gradeNum
	 *            the GRADE_NUM value
	 */
	public void setGradeNum(java.lang.Integer gradeNum) {
		this.gradeNum = gradeNum;
	}
	

	public java.lang.Integer getMagicPacketSize() {
		return magicPacketSize;
	}

	public void setMagicPacketSize(java.lang.Integer magicPacketSize) {
		this.magicPacketSize = magicPacketSize;
	}

	/**
	 * Return the value associated with the column: site_id
	 */
	public com.jeecms.core.entity.CmsSite getSite() {
		return site;
	}

	/**
	 * Set the value related to the column: site_id
	 * 
	 * @param site
	 *            the site_id value
	 */
	public void setSite(com.jeecms.core.entity.CmsSite site) {
		this.site = site;
	}

	/**
	 * Return the value associated with the column: perms
	 */
	public java.util.Map<java.lang.String, java.lang.String> getPerms() {
		return perms;
	}

	/**
	 * Set the value related to the column: perms
	 * 
	 * @param perms
	 *            the perms value
	 */
	public void setPerms(java.util.Map<java.lang.String, java.lang.String> perms) {
		this.perms = perms;
	}

	public java.util.Set<BbsPostType> getPostTypes() {
		return postTypes;
	}

	public void setPostTypes(java.util.Set<BbsPostType> postTypes) {
		this.postTypes = postTypes;
	}

	public java.util.Set<BbsCommonMagic> getMagics() {
		return magics;
	}

	public void setMagics(java.util.Set<BbsCommonMagic> magics) {
		this.magics = magics;
	}
	
	public java.util.Set<BbsCommonMagic> getBeUsedMagics() {
		return beUsedMagics;
	}

	public void setBeUsedMagics(java.util.Set<BbsCommonMagic> beUsedMagics) {
		this.beUsedMagics = beUsedMagics;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsUserGroup))
			return false;
		else {
			com.jeecms.bbs.entity.BbsUserGroup bbsUserGroup = (com.jeecms.bbs.entity.BbsUserGroup) obj;
			if (null == this.getId() || null == bbsUserGroup.getId())
				return false;
			else
				return (this.getId().equals(bbsUserGroup.getId()));
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