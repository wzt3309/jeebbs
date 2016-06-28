package com.jeecms.bbs.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.bbs.dao.AttachmentDao;
import com.jeecms.bbs.entity.Attachment;

@Repository
public class AttachmentDaoImpl extends HibernateBaseDao<Attachment, Integer> implements AttachmentDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public Attachment findById(Integer id) {
		Attachment entity = get(id);
		return entity;
	}

	public Attachment save(Attachment bean) {
		getSession().save(bean);
		return bean;
	}

	public Attachment deleteById(Integer id) {
		Attachment entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<Attachment> getEntityClass() {
		return Attachment.class;
	}
}