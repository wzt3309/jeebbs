package com.jeecms.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.MagicConstants;
import com.jeecms.bbs.entity.BbsMagicLog;
import com.jeecms.bbs.dao.BbsMagicLogDao;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsMemberMagicDaoImpl extends HibernateBaseDao<BbsMagicLog, Integer>
		implements BbsMagicLogDao {
	public Pagination getPage(Byte operator,Integer userId, int pageNo, int pageSize) {
		String hql = "from BbsMagicLog magiclog where 1=1 ";
		Finder finder = Finder.create(hql);
		if(operator!=null){
			finder.append(" and magiclog.operator=:operator").setParam("operator", operator);
		}
		if(MagicConstants.MAGIC_OPERATOR_GIVE==operator){
			if(userId!=null){
				finder.append(" and (magiclog.user.id=:userId or magiclog.targetUser.id=:userId)").setParam("userId", userId);
			}
		}else{
			if(userId!=null){
				finder.append(" and magiclog.user.id=:userId").setParam("userId", userId);
			}
		}
		finder.append(" order by magiclog.logTime desc");
		Pagination page = find(finder, pageNo, pageSize);
		return page;
	}

	public BbsMagicLog findById(Integer id) {
		BbsMagicLog entity = get(id);
		return entity;
	}

	public BbsMagicLog save(BbsMagicLog bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsMagicLog deleteById(Integer id) {
		BbsMagicLog entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	protected Class<BbsMagicLog> getEntityClass() {
		return BbsMagicLog.class;
	}
}