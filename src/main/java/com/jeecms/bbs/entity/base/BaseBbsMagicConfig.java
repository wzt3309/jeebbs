package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_magic_config table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_magic_config"
 */

public abstract class BaseBbsMagicConfig  implements Serializable {

	public static String REF = "BbsMagicConfig";
	public static String PROP_MAGIC_SOFA_LINES = "magicSofaLines";
	public static String PROP_MAGIC_DISCOUNT = "magicDiscount";
	public static String PROP_MAGIC_SWITCH = "magicSwitch";
	public static String PROP_ID = "Id";


	// constructors
	public BaseBbsMagicConfig () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsMagicConfig (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsMagicConfig (
		java.lang.Integer id,
		boolean magicSwitch) {

		this.setId(id);
		this.setMagicSwitch(magicSwitch);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private boolean magicSwitch;
	private java.lang.Integer magicDiscount;
	private java.lang.String magicSofaLines;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
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
	 * Return the value associated with the column: magic_switch
	 */
	public boolean isMagicSwitch () {
		return magicSwitch;
	}
	
	public boolean getMagicSwitch () {
		return magicSwitch;
	}

	/**
	 * Set the value related to the column: magic_switch
	 * @param magicSwitch the magic_switch value
	 */
	public void setMagicSwitch (boolean magicSwitch) {
		this.magicSwitch = magicSwitch;
	}


	/**
	 * Return the value associated with the column: magic_discount
	 */
	public java.lang.Integer getMagicDiscount () {
		return magicDiscount;
	}

	/**
	 * Set the value related to the column: magic_discount
	 * @param magicDiscount the magic_discount value
	 */
	public void setMagicDiscount (java.lang.Integer magicDiscount) {
		this.magicDiscount = magicDiscount;
	}


	/**
	 * Return the value associated with the column: magic_sofa_lines
	 */
	public java.lang.String getMagicSofaLines () {
		return magicSofaLines;
	}

	/**
	 * Set the value related to the column: magic_sofa_lines
	 * @param magicSofaLines the magic_sofa_lines value
	 */
	public void setMagicSofaLines (java.lang.String magicSofaLines) {
		this.magicSofaLines = magicSofaLines;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsMagicConfig)) return false;
		else {
			com.jeecms.bbs.entity.BbsMagicConfig bbsMagicConfig = (com.jeecms.bbs.entity.BbsMagicConfig) obj;
			if (null == this.getId() || null == bbsMagicConfig.getId()) return false;
			else return (this.getId().equals(bbsMagicConfig.getId()));
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