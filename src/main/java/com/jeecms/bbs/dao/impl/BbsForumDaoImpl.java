package com.jeecms.bbs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsForumDao;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsForumDaoImpl extends HibernateBaseDao<BbsForum, Integer>
		implements BbsForumDao {

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsForum> getList(Integer siteId) {
		String hql = "select bean from BbsForum bean inner join bean.category ctg where bean.site.id=? order by ctg.priority, bean.priority";
		return find(hql, siteId);
	}

	@SuppressWarnings("unchecked")
	public List<BbsForum> getList(Integer siteId, Integer categoryId) {
		String hql = "from BbsForum bean where bean.site.id=? and bean.category.id=? order by bean.priority";
		return find(hql, siteId, categoryId);
	}

	public int countPath(Integer siteId, String path) {
		String hql = "select count(*) from BbsForum bean where bean.site.id=? and bean.path=?";
		return ((Number) getSession().createQuery(hql).setParameter(0, siteId)
				.setParameter(1, path).iterate().next()).intValue();
	}

	public BbsForum getByPath(Integer siteId, String path) {
		String hql = "select bean from BbsForum bean where bean.site.id=? and bean.path=?";
		return (BbsForum) findUnique(hql, siteId, path);
	}

	public BbsForum findById(Integer id) {
		BbsForum entity = get(id);
		return entity;
	}

	public BbsForum save(BbsForum bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsForum deleteById(Integer id) {
		BbsForum entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsForum> getEntityClass() {
		return BbsForum.class;
	}

	public void updateAll_topic_today() {
		// TODO Auto-generated method stub
		String hql = "update BbsForum forum set forum.postToday=0";
		getSession().createQuery(hql).executeUpdate();
	}
}