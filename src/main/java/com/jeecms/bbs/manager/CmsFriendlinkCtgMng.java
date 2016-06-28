package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.CmsFriendlinkCtg;


public interface CmsFriendlinkCtgMng {
	public List<CmsFriendlinkCtg> getList(Integer siteId);

	public int countBySiteId(Integer siteId);

	public CmsFriendlinkCtg findById(Integer id);

	public CmsFriendlinkCtg save(CmsFriendlinkCtg bean);

	public CmsFriendlinkCtg update(CmsFriendlinkCtg bean);

	public void updateFriendlinkCtgs(Integer[] ids, String[] names,
			Integer[] priorities);

	public CmsFriendlinkCtg deleteById(Integer id);

	public CmsFriendlinkCtg[] deleteByIds(Integer[] ids);
}