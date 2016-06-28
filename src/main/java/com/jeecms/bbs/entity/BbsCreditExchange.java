package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsCreditExchange;



public class BbsCreditExchange extends BaseBbsCreditExchange {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsCreditExchange () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsCreditExchange (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsCreditExchange (
		java.lang.Integer id,
		java.lang.Integer expoint,
		java.lang.Integer exprestige,
		java.lang.Boolean pointoutavailable,
		java.lang.Boolean pointinavailable,
		java.lang.Boolean prestigeoutavailable,
		java.lang.Boolean prestigeinavailable,
		java.lang.Float exchangetax,
		java.lang.Integer miniBalance) {

		super (
			id,
			expoint,
			exprestige,
			pointoutavailable,
			pointinavailable,
			prestigeoutavailable,
			prestigeinavailable,
			exchangetax,
			miniBalance);
	}

/*[CONSTRUCTOR MARKER END]*/


}