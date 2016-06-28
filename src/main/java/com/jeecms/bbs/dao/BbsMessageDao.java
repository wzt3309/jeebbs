package com.jeecms.bbs.dao;


import java.util.List;

import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsMessageDao {
	public BbsMessage findById(Integer id);

	public BbsMessage save(BbsMessage bean);

	public BbsMessage updateByUpdater(Updater<BbsMessage> updater);

	public BbsMessage deleteById(Integer id);

	public BbsMessage getSendRelation(Integer userId, Integer senderId, Integer receiverId,Integer typeId);

	public Pagination getPageByUserId(Integer userId,Integer typeId, Integer pageNo,
			Integer pageSize);
	public List getListByUserIdStatus(Integer userId,Integer typeId,Boolean status);
}