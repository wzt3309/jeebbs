package com.jeecms.bbs.dao;

import com.jeecms.bbs.entity.BbsMemberMagic;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface BbsMemberMagicDao {

	public Pagination getPage(Integer userId, int pageNo, int pageSize);

	public BbsMemberMagic findById(Integer id);

	public BbsMemberMagic save(BbsMemberMagic bean);

	public BbsMemberMagic updateByUpdater(Updater<BbsMemberMagic> bean);

	public BbsMemberMagic deleteById(Integer id);

}