package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsVoteRecord;



public class BbsVoteRecord extends BaseBbsVoteRecord {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsVoteRecord () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsVoteRecord (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsVoteRecord (
		java.lang.Integer id,
		java.util.Date voteTime) {

		super (
			id,
			voteTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}