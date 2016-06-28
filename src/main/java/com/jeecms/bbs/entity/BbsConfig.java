package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsConfig;

public class BbsConfig extends BaseBbsConfig {
	private static final long serialVersionUID = 1L;
	/**
	 * 关闭注册
	 */
	public static final short REGISTER_CLOSE = 0;
	/**
	 * 开发注册
	 */
	public static final short REGISTER_OPEN = 1;
	/**
	 * 邀请注册
	 */
	public static final short REGISTER_INVITATION = 2;


	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsConfig () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsConfig (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsConfig (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser lastUser,
		com.jeecms.bbs.entity.BbsUserGroup registerGroup,
		com.jeecms.bbs.entity.BbsUserGroup defaultGroup,
		java.lang.String defAvatar,
		java.lang.Integer avatarWidth,
		java.lang.Integer avatarHeight,
		java.lang.Integer topicCountPerPage,
		java.lang.Integer postCountPerPage,
		java.lang.String keywords,
		java.lang.String description,
		java.lang.Short registerStatus,
		java.lang.Integer topicHotCount,
		java.lang.Integer postToday,
		java.lang.Integer postYesterday,
		java.lang.Integer postMax,
		java.util.Date postMaxDate,
		java.lang.Integer topicTotal,
		java.lang.Integer postTotal,
		java.lang.Integer userTotal) {

		super (
			id,
			lastUser,
			registerGroup,
			defaultGroup,
			defAvatar,
			avatarWidth,
			avatarHeight,
			topicCountPerPage,
			postCountPerPage,
			keywords,
			description,
			registerStatus,
			topicHotCount,
			postToday,
			postYesterday,
			postMax,
			postMaxDate,
			topicTotal,
			postTotal,
			userTotal);
	}

	/* [CONSTRUCTOR MARKER END] */

}