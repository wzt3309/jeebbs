package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.FinanceLeadingIndexDao;
import com.jeecms.bbs.entity.FinanceLeadingIndex;
import com.jeecms.bbs.manager.FinanceLeadingIndexMng;
import com.jeecms.common.hibernate3.Updater;
/**
 * 
 * @ClassName FinanceLeadingIndexMngImpl
 * @Description 融资融券领先指数 Manage接口实现
 * @author wzt3309
 */
@Service
@Transactional
public class FinanceLeadingIndexMngImpl implements FinanceLeadingIndexMng {
	
	@Override
	public FinanceLeadingIndex save(FinanceLeadingIndex bean) {
		dao.save(bean);
		return bean;
	}
	
	@Override
	public FinanceLeadingIndex findById(Integer id) {
		FinanceLeadingIndex entity=dao.findById(id);
		return entity;
	}

	@Override
	public List<FinanceLeadingIndex> getList(int day) {
		List<FinanceLeadingIndex> result=dao.getList(day);
		return result;
	}

	@Override
	public FinanceLeadingIndex deleteById(Integer id) {
		FinanceLeadingIndex entity=dao.deleteById(id);
		return entity;
	}

	@Override
	public FinanceLeadingIndex update(FinanceLeadingIndex bean) {
		Updater<FinanceLeadingIndex> updater = new Updater<FinanceLeadingIndex>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}
	@Override
	public boolean isExist(FinanceLeadingIndex bean) {
		boolean result=dao.isExist(bean);
		return result;
	}
	
	private FinanceLeadingIndexDao dao;
	
	@Autowired
	public void setDao(FinanceLeadingIndexDao dao){
		this.dao=dao;
	}

}
