package com.jeecms.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsMessageReplyDao;
import com.jeecms.bbs.entity.BbsMessageReply;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsMessageReplyDaoImpl extends
		HibernateBaseDao<BbsMessageReply, Integer> implements BbsMessageReplyDao {
	public BbsMessageReply findById(Integer id) {
		BbsMessageReply entity = get(id);
		return entity;
	}

	public BbsMessageReply save(BbsMessageReply bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsMessageReply deleteById(Integer id) {
		BbsMessageReply entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	public Pagination getPageByMsgId(Integer msgId, Integer pageNo,
			Integer pageSize) {
		String hql = "from BbsMessageReply bean where bean.message.id=:msgId order by bean.createTime desc";
		Finder f = Finder.create(hql).setParam("msgId", msgId);
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<BbsMessageReply> getEntityClass() {
		return BbsMessageReply.class;
	}
}