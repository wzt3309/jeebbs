package com.jeecms.bbs.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.jeecms.bbs.entity.base.BaseBbsCommonMagic;



public class BbsCommonMagic extends BaseBbsCommonMagic {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsCommonMagic () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsCommonMagic (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsCommonMagic (
		java.lang.Integer id,
		java.lang.Boolean available,
		java.lang.String name,
		java.lang.String identifier,
		java.lang.String description,
		java.lang.Byte displayorder,
		java.lang.Byte credit,
		java.lang.Integer price,
		java.lang.Integer num,
		java.lang.Integer salevolume,
		java.lang.Integer supplytype,
		java.lang.Integer supplynum,
		java.lang.Integer useperoid,
		java.lang.Integer usenum,
		java.lang.Integer weight,
		java.lang.Boolean useevent) {

		super (
			id,
			available,
			name,
			identifier,
			description,
			displayorder,
			credit,
			price,
			num,
			salevolume,
			supplytype,
			supplynum,
			useperoid,
			usenum,
			weight,
			useevent);
	}
	public void addToGroups(BbsUserGroup group) {
		Set<BbsUserGroup> groups = getUseGroups();
		if (groups == null) {
			groups = new HashSet<BbsUserGroup>();
			setUseGroups(groups);
		}
		groups.add(group);
	}
	public void addToToUseGroups(BbsUserGroup group) {
		Set<BbsUserGroup> groups = getToUseGroups();
		if (groups == null) {
			groups = new HashSet<BbsUserGroup>();
			setToUseGroups(groups);
		}
		groups.add(group);
	}
	public Integer[] getGroupIds() {
		Set<BbsUserGroup> groups = getUseGroups();
		return fetchIds(groups);
	}
	public Integer[] getToUseGroupIds() {
		Set<BbsUserGroup> groups = getToUseGroups();
		return fetchIds(groups);
	}
	public static Integer[] fetchIds(Collection<BbsUserGroup> groups) {
		if (groups == null) {
			return null;
		}
		Integer[] ids = new Integer[groups.size()];
		int i = 0;
		for (BbsUserGroup g : groups) {
			ids[i++] =g.getId();
		}
		return ids;
	}

/*[CONSTRUCTOR MARKER END]*/


}