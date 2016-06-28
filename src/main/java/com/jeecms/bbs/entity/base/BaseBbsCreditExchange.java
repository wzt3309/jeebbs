package com.jeecms.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the bbs_credit_exchange table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="bbs_credit_exchange"
 */

public abstract class BaseBbsCreditExchange  implements Serializable {

	public static String REF = "BbsCreditExchange";
	public static String PROP_PRESTIGEINAVAILABLE = "prestigeinavailable";
	public static String PROP_POINTINAVAILABLE = "pointinavailable";
	public static String PROP_POINTOUTAVAILABLE = "pointoutavailable";
	public static String PROP_PRESTIGEOUTAVAILABLE = "prestigeoutavailable";
	public static String PROP_MINI_BALANCE = "miniBalance";
	public static String PROP_ID = "id";
	public static String PROP_EXCHANGETAX = "exchangetax";
	public static String PROP_EXPOINT = "expoint";
	public static String PROP_EXPRESTIGE = "exprestige";


	// constructors
	public BaseBbsCreditExchange () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsCreditExchange (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsCreditExchange (
		java.lang.Integer id,
		java.lang.Integer expoint,
		java.lang.Integer exprestige,
		java.lang.Boolean pointoutavailable,
		java.lang.Boolean pointinavailable,
		java.lang.Boolean prestigeoutavailable,
		java.lang.Boolean prestigeinavailable,
		java.lang.Float exchangetax,
		java.lang.Integer miniBalance) {

		this.setId(id);
		this.setExpoint(expoint);
		this.setExprestige(exprestige);
		this.setPointoutavailable(pointoutavailable);
		this.setPointinavailable(pointinavailable);
		this.setPrestigeoutavailable(prestigeoutavailable);
		this.setPrestigeinavailable(prestigeinavailable);
		this.setExchangetax(exchangetax);
		this.setMiniBalance(miniBalance);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer expoint;
	private java.lang.Integer exprestige;
	private java.lang.Boolean pointoutavailable;
	private java.lang.Boolean pointinavailable;
	private java.lang.Boolean prestigeoutavailable;
	private java.lang.Boolean prestigeinavailable;
	private java.lang.Float exchangetax;
	private java.lang.Integer miniBalance;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="eid"
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
	 * Return the value associated with the column: expoint
	 */
	public java.lang.Integer getExpoint () {
		return expoint;
	}

	/**
	 * Set the value related to the column: expoint
	 * @param expoint the expoint value
	 */
	public void setExpoint (java.lang.Integer expoint) {
		this.expoint = expoint;
	}


	/**
	 * Return the value associated with the column: exprestige
	 */
	public java.lang.Integer getExprestige () {
		return exprestige;
	}

	/**
	 * Set the value related to the column: exprestige
	 * @param exprestige the exprestige value
	 */
	public void setExprestige (java.lang.Integer exprestige) {
		this.exprestige = exprestige;
	}


	/**
	 * Return the value associated with the column: pointoutavailable
	 */
	public java.lang.Boolean getPointoutavailable () {
		return pointoutavailable;
	}

	/**
	 * Set the value related to the column: pointoutavailable
	 * @param pointoutavailable the pointoutavailable value
	 */
	public void setPointoutavailable (java.lang.Boolean pointoutavailable) {
		this.pointoutavailable = pointoutavailable;
	}


	/**
	 * Return the value associated with the column: pointinavailable
	 */
	public java.lang.Boolean getPointinavailable () {
		return pointinavailable;
	}

	/**
	 * Set the value related to the column: pointinavailable
	 * @param pointinavailable the pointinavailable value
	 */
	public void setPointinavailable (java.lang.Boolean pointinavailable) {
		this.pointinavailable = pointinavailable;
	}


	/**
	 * Return the value associated with the column: prestigeoutavailable
	 */
	public java.lang.Boolean getPrestigeoutavailable () {
		return prestigeoutavailable;
	}

	/**
	 * Set the value related to the column: prestigeoutavailable
	 * @param prestigeoutavailable the prestigeoutavailable value
	 */
	public void setPrestigeoutavailable (java.lang.Boolean prestigeoutavailable) {
		this.prestigeoutavailable = prestigeoutavailable;
	}


	/**
	 * Return the value associated with the column: prestigeinavailable
	 */
	public java.lang.Boolean getPrestigeinavailable () {
		return prestigeinavailable;
	}

	/**
	 * Set the value related to the column: prestigeinavailable
	 * @param prestigeinavailable the prestigeinavailable value
	 */
	public void setPrestigeinavailable (java.lang.Boolean prestigeinavailable) {
		this.prestigeinavailable = prestigeinavailable;
	}


	/**
	 * Return the value associated with the column: exchangetax
	 */
	public java.lang.Float getExchangetax () {
		return exchangetax;
	}

	/**
	 * Set the value related to the column: exchangetax
	 * @param exchangetax the exchangetax value
	 */
	public void setExchangetax (java.lang.Float exchangetax) {
		this.exchangetax = exchangetax;
	}


	/**
	 * Return the value associated with the column: mini_balance
	 */
	public java.lang.Integer getMiniBalance () {
		return miniBalance;
	}

	/**
	 * Set the value related to the column: mini_balance
	 * @param miniBalance the mini_balance value
	 */
	public void setMiniBalance (java.lang.Integer miniBalance) {
		this.miniBalance = miniBalance;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.bbs.entity.BbsCreditExchange)) return false;
		else {
			com.jeecms.bbs.entity.BbsCreditExchange bbsCreditExchange = (com.jeecms.bbs.entity.BbsCreditExchange) obj;
			if (null == this.getId() || null == bbsCreditExchange.getId()) return false;
			else return (this.getId().equals(bbsCreditExchange.getId()));
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