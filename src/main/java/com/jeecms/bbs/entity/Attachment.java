package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseAttachment;



public class Attachment extends BaseAttachment {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Attachment () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Attachment (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Attachment (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsPost post,
		java.lang.Boolean picture) {

		super (
			id,
			post,
			picture);
	}

/*[CONSTRUCTOR MARKER END]*/


}