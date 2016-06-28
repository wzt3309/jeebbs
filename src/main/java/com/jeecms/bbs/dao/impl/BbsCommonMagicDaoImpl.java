package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsCommonMagicDao;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsCommonMagicDaoImpl extends
		HibernateBaseDao<BbsCommonMagic, Integer> implements BbsCommonMagicDao {
	@Transactional(readOnly=true)
	public List<BbsCommonMagic> getList(){
		String hql="from BbsCommonMagic magic order by displayorder";
		return getSession().createQuery(hql).list();
	}
	@Transactional(readOnly=true)
	public BbsCommonMagic findById(Integer id) {
		BbsCommonMagic entity = get(id);
		return entity;
	}
	@Transactional(readOnly=true)
	public BbsCommonMagic findByIdentifier(String mid) {
		String hql="from BbsCommonMagic magic where magic.identifier=:mid";
		Finder finder=Finder.create(hql).setParam("mid", mid);
		List<BbsCommonMagic>li=find(finder);
		if(li!=null&&li.size()>0){
			return li.get(0);
		}else{
			return null;
		}
	}

	public BbsCommonMagic save(BbsCommonMagic bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsCommonMagic deleteById(Integer id) {
		BbsCommonMagic entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsCommonMagic> getEntityClass() {
		return BbsCommonMagic.class;
	}

}