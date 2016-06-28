package com.jeecms.bbs.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsReportDao;
import com.jeecms.bbs.entity.BbsReport;
import com.jeecms.bbs.manager.BbsReportMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsReportMngImpl implements BbsReportMng {

	@Transactional(readOnly = true)
	public Pagination getPage(Boolean status,Integer pageNo, Integer pageSize) {
		Pagination page = dao.getPage(status,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsReport findById(Integer id) {
		BbsReport entity = dao.findById(id);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public BbsReport findByUrl(String  url) {
		BbsReport entity = dao.findByUrl(url);
		return entity;
	}

	public BbsReport save(BbsReport bean) {
		dao.save(bean);
		return bean;
	}

	public BbsReport update(BbsReport bean) {
		Updater<BbsReport> updater = new Updater<BbsReport>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsReport deleteById(Integer id) {
		BbsReport bean = dao.deleteById(id);
		return bean;
	}

	public BbsReport[] deleteByIds(Integer[] ids) {
		BbsReport[] beans = new BbsReport[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsReportDao dao;

	@Autowired
	public void setDao(BbsReportDao dao) {
		this.dao = dao;
	}

}
