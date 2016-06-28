package com.jeecms.bbs.dao;


import com.jeecms.bbs.entity.BbsMessageReply;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsMessageReplyDao {
	public BbsMessageReply findById(Integer id);

	public BbsMessageReply save(BbsMessageReply bean);

	public BbsMessageReply updateByUpdater(Updater<BbsMessageReply> updater);

	public BbsMessageReply deleteById(Integer id);

	public Pagination getPageByMsgId(Integer msgId, Integer pageNo,
			Integer pageSize);
}