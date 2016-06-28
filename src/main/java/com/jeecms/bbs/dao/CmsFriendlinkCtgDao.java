package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.CmsFriendlinkCtg;
import com.jeecms.common.hibernate3.Updater;

public interface CmsFriendlinkCtgDao {
	public List<CmsFriendlinkCtg> getList(Integer siteId);

	public int countBySiteId(Integer siteId);

	public CmsFriendlinkCtg findById(Integer id);

	public CmsFriendlinkCtg save(CmsFriendlinkCtg bean);

	public CmsFriendlinkCtg updateByUpdater(Updater<CmsFriendlinkCtg> updater);

	public CmsFriendlinkCtg deleteById(Integer id);
}