package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsOperation;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsOperationDao {

	public Pagination getPage(int pageNo, int pageSize);

	public BbsOperation findById(Integer id);

	public BbsOperation save(BbsOperation bean);

	public BbsOperation updateByUpdater(Updater<BbsOperation> updater);

	public BbsOperation deleteById(Integer id);

}