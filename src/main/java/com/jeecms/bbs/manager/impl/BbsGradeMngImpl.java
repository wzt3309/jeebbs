package com.jeecms.bbs.manager.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsGradeDao;
import com.jeecms.bbs.entity.BbsGrade;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsGradeMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsGradeMngImpl implements BbsGradeMng {

	public BbsGrade saveGrade(BbsGrade entity, BbsUser bbsuser, BbsPost post) {
		entity.setGrader(bbsuser);
		entity.setGradeTime(new Timestamp(System.currentTimeMillis()));
		entity.setPost(post);
		if (bbsuser.getGradeToday() != null) {
			bbsuser.setGradeToday(bbsuser.getGradeToday() + entity.getScore());
		} else {
			bbsuser.setGradeToday(entity.getScore());
		}
		post.getCreater().setPoint(
				post.getCreater().getPoint() + entity.getScore());
		dao.save(entity);
		return entity;
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsGrade findById(Integer id) {
		BbsGrade entity = dao.findById(id);
		return entity;
	}

	public BbsGrade save(BbsGrade bean) {
		dao.save(bean);
		return bean;
	}

	public BbsGrade update(BbsGrade bean) {
		Updater<BbsGrade> updater = new Updater<BbsGrade>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsGrade deleteById(Integer id) {
		BbsGrade bean = dao.deleteById(id);
		return bean;
	}

	public BbsGrade[] deleteByIds(Integer[] ids) {
		BbsGrade[] beans = new BbsGrade[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private BbsGradeDao dao;

	@Autowired
	public void setDao(BbsGradeDao dao) {
		this.dao = dao;
	}

}
