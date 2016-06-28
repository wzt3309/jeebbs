package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsMessageDao;
import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsMessageDaoImpl extends HibernateBaseDao<BbsMessage, Integer>
		implements BbsMessageDao {
	public BbsMessage findById(Integer id) {
		BbsMessage entity = get(id);
		return entity;
	}

	public BbsMessage save(BbsMessage bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsMessage deleteById(Integer id) {
		BbsMessage entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public BbsMessage getSendRelation(Integer userId, Integer senderId,
			Integer receiverId,Integer typeId) {
		String hql = "from BbsMessage bean where bean.user.id=:userId and bean.msgType=:typeId and ((bean.sender.id=:senderId and bean.receiver.id=:receiverId) or (bean.sender.id=:receiverId and bean.receiver.id=:senderId))";
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		f.setParam("typeId", typeId);
		f.setParam("senderId", senderId);
		f.setParam("receiverId", receiverId);
		f.setMaxResults(1);
		Query query = f.createQuery(getSession());
		return (BbsMessage) query.uniqueResult();
	}

	public Pagination getPageByUserId(Integer userId, Integer typeId,Integer pageNo,
			Integer pageSize) {
		String hql = "from BbsMessage bean where bean.user.id=:userId and bean.msgType=:typeId";
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		f.setParam("typeId", typeId);
		return find(f, pageNo, pageSize);
	}

	public List getListByUserIdStatus(Integer userId, Integer typeId,
			Boolean status) {
		String hql = "from BbsMessage bean where bean.user.id=:userId and bean.msgType=:typeId and bean.status=:status";
		Finder f = Finder.create(hql);
		f.setParam("userId", userId);
		f.setParam("typeId", typeId);
		f.setParam("status", status);
		return find(f);
	}

	@Override
	protected Class<BbsMessage> getEntityClass() {
		return BbsMessage.class;
	}

}