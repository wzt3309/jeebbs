package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 用户DAO接口
 * 
 * @author liufang
 * 
 */
public interface BbsUserDao {
	public Pagination getPage(String username, String email, Integer groupId,
			Boolean disabled, Boolean admin, Long pointMin,
			Long pointMax, Long prestigeMin, Long prestigeMax,
			Integer orderBy, int pageNo, int pageSize);

	public List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank);

	public BbsUser findById(Integer id);

	public BbsUser findByUsername(String username);

	public int countByUsername(String username);

	public int countByEmail(String email);

	public BbsUser save(BbsUser bean);

	public BbsUser updateByUpdater(Updater<BbsUser> updater);

	public BbsUser deleteById(Integer id);
	
	public List<BbsUser> getSuggestMember(String username, Integer count);
	
}