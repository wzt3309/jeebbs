package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsMagicLog;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsMagicLogDao {

	public Pagination getPage(Byte operator,Integer userId,int pageNo, int pageSize);

	public BbsMagicLog findById(Integer id);

	public BbsMagicLog save(BbsMagicLog bean);

	public BbsMagicLog updateByUpdater(Updater<BbsMagicLog> bean);

	public BbsMagicLog deleteById(Integer id);

}