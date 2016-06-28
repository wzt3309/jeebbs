package com.jeecms.bbs.entity;

import com.jeecms.bbs.entity.base.BaseBbsFriendShip;



public class BbsFriendShip extends BaseBbsFriendShip {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请中
	 */
	public static final int APPLYING = 0;
	/**
	 * 接受
	 */
	public static final int ACCEPT = 1;
	/**
	 * 拒绝
	 */
	public static final int REFUSE = 2;
	
	public void init(){
		if(getStatus()==null){
			setStatus(APPLYING);
		}
	}

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsFriendShip () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsFriendShip (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsFriendShip (
		java.lang.Integer id,
		com.jeecms.bbs.entity.BbsUser user,
		com.jeecms.bbs.entity.BbsUser friend,
		java.lang.Integer status) {

		super (
			id,
			user,
			friend,
			status);
	}

/*[CONSTRUCTOR MARKER END]*/


}