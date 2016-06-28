package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsVoteItem;
import com.jeecms.common.hibernate3.Updater;


public interface BbsVoteItemDao {
	public BbsVoteItem findById(Integer id);
	
	public List<BbsVoteItem> findByTopic(Integer topicId);
	
	public BbsVoteItem save(BbsVoteItem bean);
	
	public BbsVoteItem updateByUpdater(Updater<BbsVoteItem> updater);
}
