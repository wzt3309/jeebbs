package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsMemberMagic;



public class BbsMemberMagic extends BaseBbsMemberMagic {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsMemberMagic () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsMemberMagic (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsMemberMagic (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser user,
		com.jeecms.bbs.entity.BbsCommonMagic magic) {

		super (
			id,
			user,
			magic);
	}

/*[CONSTRUCTOR MARKER END]*/


}