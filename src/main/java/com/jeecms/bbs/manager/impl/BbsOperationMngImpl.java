package com.jeecms.bbs.manager.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsOperationDao;
import com.jeecms.bbs.entity.BbsOperation;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsOperationMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsSite;

@Service
@Transactional
public class BbsOperationMngImpl implements BbsOperationMng {

	public BbsOperation saveOpt(CmsSite site, BbsUser operator, String optName,
			String reason, Object target) {
		BbsOperation opt = new BbsOperation();
		opt.setSite(site);
		opt.setOperater(operator);
		opt.setOptTime(new Timestamp(System.currentTimeMillis()));
		opt.setOptName(optName);
		opt.setOptReason(reason);
		opt.setTarget(target);
		return dao.save(opt);
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsOperation findById(Integer id) {
		BbsOperation entity = dao.findById(id);
		return entity;
	}

	public BbsOperation save(BbsOperation bean) {
		dao.save(bean);
		return bean;
	}

	public BbsOperation update(BbsOperation bean) {
		Updater<BbsOperation> updater = new Updater<BbsOperation>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsOperation deleteById(Integer id) {
		BbsOperation bean = dao.deleteById(id);
		return bean;
	}
	
	public BbsOperation[] deleteByIds(Integer[] ids) {
		BbsOperation[] beans = new BbsOperation[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsOperationDao dao;

	@Autowired
	public void setDao(BbsOperationDao dao) {
		this.dao = dao;
	}

}
