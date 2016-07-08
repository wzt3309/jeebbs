package com.jeecms.bbs.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsNewsDao;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.manager.BbsNewsMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsNewsMngImpl implements BbsNewsMng {
	
	private BbsNewsDao dao;
	@Autowired
	public void setDao(BbsNewsDao dao) {
		this.dao = dao;
	}
	@Transactional(readOnly = true)
	@Override
	public Pagination getPage(Map<String,String> params,String orderBy,int pageNo, int pageSize) {
		Pagination page =dao.getPage(params,orderBy,pageNo, pageSize);
		return page;
	}
	@Transactional(readOnly = true)
	@Override
	public List<BbsNews> getList(String newsFrom) {		
		return dao.getList(newsFrom);
	}
	@Transactional(readOnly = true)
	@Override
	public BbsNews findById(Integer id) {
		BbsNews entity=dao.findById(id);
		return entity;
	}
	
	@Override
	public BbsNews save(BbsNews bean) {	
		dao.save(bean);
		return bean;
	}

	@Override
	public BbsNews update(BbsNews bean) {	
		Updater<BbsNews> updater=new Updater<BbsNews>(bean);
		bean=dao.updateByUpdater(updater);
		return bean;
	}

	@Override
	public BbsNews deleteById(Integer id) {
		BbsNews bean=dao.deleteById(id);
		return bean;
	}

	@Override
	public BbsNews[] deleteByIds(Integer[] ids) {
		BbsNews[] beans=new BbsNews[ids.length];
		for(int i=0,len=beans.length;i<len;i++){
			beans[i]=dao.deleteById(ids[i]);
		}
		return beans;
	}
	

}
