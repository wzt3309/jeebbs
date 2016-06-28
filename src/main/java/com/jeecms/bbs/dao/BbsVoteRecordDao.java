package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsVoteRecord;




public interface BbsVoteRecordDao {
	public BbsVoteRecord findRecord(Integer userId, Integer topicId);
	
	public BbsVoteRecord save(BbsVoteRecord bean);
}
