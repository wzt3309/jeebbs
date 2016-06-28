package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_sensitivity table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_sensitivity"
 */

public abstract class BaseCmsSensitivity  implements Serializable {

	public static String REF = "CmsSensitivity";
	public static String PROP_REPLACEMENT = "replacement";
	public static String PROP_SEARCH = "search";
	public static String PROP_SITE = "site";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsSensitivity () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsSensitivity (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsSensitivity (
		java.lang.Integer id,
		java.lang.String search,
		java.lang.String replacement) {

		this.setId(id);
		this.setSearch(search);
		this.setReplacement(replacement);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String search;
	private java.lang.String replacement;

	// many to one
	private com.jeecms.core.entity.CmsSite site;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="sensitivity_id"
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
	 * Return the value associated with the column: search
	 */
	public java.lang.String getSearch () {
		return search;
	}

	/**
	 * Set the value related to the column: search
	 * @param search the search value
	 */
	public void setSearch (java.lang.String search) {
		this.search = search;
	}


	/**
	 * Return the value associated with the column: replacement
	 */
	public java.lang.String getReplacement () {
		return replacement;
	}

	/**
	 * Set the value related to the column: replacement
	 * @param replacement the replacement value
	 */
	public void setReplacement (java.lang.String replacement) {
		this.replacement = replacement;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.CmsSensitivity)) return false;
		else {
			com.jeecms.bbs.entity.CmsSensitivity cmsSensitivity = (com.jeecms.bbs.entity.CmsSensitivity) obj;
			if (null == this.getId() || null == cmsSensitivity.getId()) return false;
			else return (this.getId().equals(cmsSensitivity.getId()));
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