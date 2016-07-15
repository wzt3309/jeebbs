package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.FutureLeadingIndexDao;
import com.jeecms.bbs.entity.FutureLeadingIndex;
import com.jeecms.bbs.manager.FutureLeadingIndexMng;
import com.jeecms.common.hibernate3.Updater;
/**
 * 
 * @ClassName FutureLeadingIndexMngImpl
 * @Description 期指领先指数 Manage接口实现
 * @author wzt3309
 */
@Service
@Transactional
public class FutureLeadingIndexMngImpl implements FutureLeadingIndexMng {
	
	@Override
	public FutureLeadingIndex save(FutureLeadingIndex bean) {
		dao.save(bean);
		return bean;
	}

	
	@Override
	public FutureLeadingIndex findById(Integer id) {
		FutureLeadingIndex entity=dao.findById(id);
		return entity;
	}

	@Override
	public List<FutureLeadingIndex> getList(int day) {
		List<FutureLeadingIndex> result=dao.getList(day);
		return result;
	}

	@Override
	public FutureLeadingIndex deleteById(Integer id) {
		FutureLeadingIndex entity=dao.deleteById(id);
		return entity;
	}

	@Override
	public FutureLeadingIndex update(FutureLeadingIndex bean) {
		Updater<FutureLeadingIndex> updater = new Updater<FutureLeadingIndex>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	@Override
	public boolean isExist(FutureLeadingIndex bean) {
		boolean result=dao.isExist(bean);
		return result;
	}
	private FutureLeadingIndexDao dao;
	
	@Autowired
	public void setDao(FutureLeadingIndexDao dao){
		this.dao=dao;
	}
}
