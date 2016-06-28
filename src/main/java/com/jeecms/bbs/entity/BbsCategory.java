package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsCategory;

public class BbsCategory extends BaseBbsCategory {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsCategory () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsCategory (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsCategory (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		java.lang.String path,
		java.lang.String title,
		java.lang.Integer priority,
		java.lang.Integer forumCols) {

		super (
			id,
			site,
			path,
			title,
			priority,
			forumCols);
	}

	/* [CONSTRUCTOR MARKER END] */

}