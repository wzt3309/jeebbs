package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseCmsSensitivity;



public class CmsSensitivity extends BaseCmsSensitivity {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsSensitivity () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsSensitivity (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsSensitivity (
		java.lang.Integer id,
		java.lang.String search,
		java.lang.String replacement) {

		super (
			id,
			search,
			replacement);
	}

/*[CONSTRUCTOR MARKER END]*/


}