package com.jeecms.bbs.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsCommonMagicDao;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsCommonMagicMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class BbsCommonMagicMngImpl implements BbsCommonMagicMng {

	@Transactional(readOnly = true)
	public List<BbsCommonMagic> getList() {
		return dao.getList();
	}

	@Transactional(readOnly = true)
	public BbsCommonMagic findById(Integer id) {
		BbsCommonMagic config = dao.findById(id);
		return config;
	}

	@Transactional(readOnly = true)
	public BbsCommonMagic findByIdentifier(String mid) {
		return dao.findByIdentifier(mid);
	}

	public BbsCommonMagic update(BbsCommonMagic bean) {
		BbsCommonMagic entity = findById(bean.getId());
		Updater<BbsCommonMagic> updater = new Updater<BbsCommonMagic>(bean);
		entity = dao.updateByUpdater(updater);
		return entity;
	}

	public BbsCommonMagic updateByGroup(BbsCommonMagic bean,
			Integer[] groupIds, Integer[] beUsedGroupIds) {
		BbsCommonMagic entity = findById(bean.getId());
		Updater<BbsCommonMagic> updater = new Updater<BbsCommonMagic>(bean);
		entity = dao.updateByUpdater(updater);
		// 可使用组权限
		Set<BbsUserGroup> groups = entity.getUseGroups();
		groups.clear();
		if (groupIds != null && groupIds.length > 0) {
			for (Integer groupId : groupIds) {
				entity.addToGroups(groupMng.findById(groupId));
			}
		}
		// 允许被使用的用户组
		Set<BbsUserGroup> beUsedGroups = entity.getToUseGroups();
		beUsedGroups.clear();
		if (beUsedGroupIds != null&& beUsedGroupIds.length > 0) {
			for (Integer groupId : beUsedGroupIds) {
				entity.addToToUseGroups(groupMng.findById(groupId));
			}
		}
		return entity;
	}

	private BbsCommonMagicDao dao;
	@Autowired
	private BbsUserGroupMng groupMng;

	@Autowired
	public void setDao(BbsCommonMagicDao dao) {
		this.dao = dao;
	}

}
