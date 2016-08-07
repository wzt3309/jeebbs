package com.jeecms.bbs.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsTopicDao;
import com.jeecms.bbs.entity.BbsNews;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.entity.reccomendstock;
import com.jeecms.bbs.entity.Stockbasicmessage;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsTopicDaoImpl extends HibernateBaseDao<BbsTopic, Integer>
		implements BbsTopicDao {
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId) {
		Finder f = Finder.create("from BbsTopic bean where 1=1");
		f.append(" and bean.website.id=:webId").setParam("webId", webId);
		f.append(" and bean.status>=").append(String.valueOf(BbsTopic.NORMAL));
		f.append(" and (bean.topLevel=3 ");
		f.append(" or (bean.topLevel=2 and bean.category.id=:ctgId)");
		f.setParam("ctgId", ctgId);
		f.append(" or (bean.topLevel=1 and bean.forum.id=:forumId))");
		f.setParam("forumId", forumId);
		f.append(" order by bean.topLevel desc");
		return find(f);
	}
	
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId, Integer postTypeId, Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		if(parentPostTypeId!=null){
			f.append(" and bean.postType.parent.id=:parentPostTypeId")
			.setParam("parentPostTypeId", parentPostTypeId);
		}
		if(postTypeId!=null){
			f.append(" and bean.postType.id=:postTypeId")
			.setParam("postTypeId", postTypeId);
		}
		if (status == null) {
			status = 0;
		}
		f.append(" and bean.status>=:status").setParam("status", status);
		if (topLevel != null) {
			if (topLevel != 0) {
				f.append(" and bean.topLevel>=:topLevel").setParam("topLevel",
						topLevel);
			} else {
				f.append(" and bean.topLevel=0");
			}
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		if (!StringUtils.isBlank(keyWords)) {
			f.append(" and bean.topicText.title like :keyWords").setParam("keyWords",
					"%" + keyWords + "%");
		}
		if (!StringUtils.isBlank(creater)) {
			f.append(" and bean.creater.username like :creater").setParam(
					"creater", "%" + creater + "%");
		}
		if (createrId != null) {
			f.append(" and bean.creater.id =:createrId").setParam("createrId",
					createrId);
		}
		if("index_jing".equals(jinghua)){
			f.append(" and bean.primeLevel != 0");
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		//System.out.println("pageNO="+pageNo);
		//System.out.println("pageSize="+pageSize);
		return find(f, pageNo, pageSize);
		
	}

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		f.append(" and bean.status>=0");
		if (day != null) {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date(System.currentTimeMillis()));
			now.add(Calendar.DATE, -day);
			f.append(" and bean.createTime>:day")
					.setParam("day", now.getTime());
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getTopicByTime(Integer siteId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberTopic(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.creater.id=:userId");
		f.setParam("userId", userId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberReply(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.haveReply like :userId").setParam("userId",
				"%," + userId + ",%");
		f.append(" order by bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public BbsTopic findById(Integer id) {
		BbsTopic entity = get(id);
		return entity;
	}

	public BbsTopic save(BbsTopic bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsTopic deleteById(Integer id) {
		BbsTopic entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsTopic> getEntityClass() {
		return BbsTopic.class;
	}

	@SuppressWarnings("unchecked")
	public List<BbsTopic> getListByForumId(Integer forumId) {
		String hql = "from BbsTopic bean where bean.forum.id=:forumId ";
		return getSession().createQuery(hql).setInteger("forumId", forumId).setCacheable(false).list();
	}

	@SuppressWarnings("unchecked")
	public List<BbsTopic> getNewList(Integer count) {
		String hql = "from BbsTopic bean order by bean.createTime desc";
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(count).setCacheable(false).list();
	}

	@Override
	public List<Stockbasicmessage> getmess(String GPDM) {
		// TODO Auto-generated method stub
		//System.out.println("get in Dao");
		Finder f = Finder.create("from Stockbasicmessage bean where 1=1");
		
		//System.out.println("successed in creation");
		
		if(GPDM!=null){
			f.append(" and bean.GPDM=:GPDM").setParam("GPDM", GPDM);
			
			//System.out.println("successed in setParam");
		}
		
		//System.out.println("sql:"+f.getOrigHql());
		
		return find(f);
	}
	
	public List getGPMC(){
		Finder f = Finder.create("select bean.GPMC from Stockbasicmessage bean where 1=1");
		f.append(" and bean.GPDM=:GPDM").setParam("GPDM", "000001");
		return find(f);
	}

	@Override
	public List<Stockbasicmessage> getlist(String sql) {
		// TODO Auto-generated method stub
		
		Finder f = Finder.create(sql);
		f.append("and bean.RIQI=:RIQI").setParam("RIQI", "2015-01-09");
		return find(f);
	}

	@Override
	public List<BbsNews> getWebNews(Integer Id) {
		// TODO Auto-generated method stub
		//System.out.println("get in Dao");
		Finder f = Finder.create("from BbsNews bean where 1=1");
		
		//System.out.println("successed in creation");
		
		if(Id!=null){
			f.append(" and bean.Id=:Id").setParam("Id",Id);
			
			//System.out.println("successed in setParam");
		}
		
		//System.out.println("sql:"+f.getOrigHql());
		
		return find(f);
	}

	@Override
	public List<BbsNews> getWebNewsList(String newsFrom ,int limit) {
		// TODO Auto-generated method stub
		Calendar c=Calendar.getInstance();
		Finder f;
		if(newsFrom!=""){
			f = Finder.create("from BbsNews bean where NewsFrom='"+newsFrom+"'");
		}
		else{
			f=Finder.create("from BbsNews bean where 1=1");
		}
//		f.append("and bean.NewsDate=:NewsDate").setParam("NewsDate", c);
		if(limit>0){
			f.setMaxResults(limit);
		}		
		f.append(" order by NewsDate desc");
		return find(f);
	}

	@Override
	public List<Stockmessage> getReccomendation(String date,String type) {
		// TODO Auto-generated method stub
		System.out.println("Reccomendation:get in dao");
		Finder f = Finder.create("from Stockmessage bean where 1=1");
		if(type==null){
			f.append(" and bean.Reccomendation!=0");
		}
		else{
			f.append(" and bean.Reccomendation=:reccomendation").setParam("reccomendation", Integer.valueOf(type));
		}
		if(date!=null){
		f.append(" and bean.RIQI=:RIQI").setParam("RIQI", date);	
		}
		return find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stockmessage> getmessage(String sql) {
		// TODO Auto-generated method stub
		
		Finder f = Finder.create(sql);
		return find(f);
		
	}

	@Override
	public List<Stockmessage> getReccomendation_detail(String type) {
		// TODO Auto-generated method stub
		System.out.println("Reccomendation:get in dao");
		
		
			Finder f = Finder.create("select bean from Stockmessage as bean where 1=1");
			if(type==null){
				f.append(" and bean.GPDM in(select bean1.GPDM from Stockmessage as bean1,reccomendstock as bean2 where bean2.stockID=bean1.GPDM and bean2.time=bean1.RIQI  ) ");//and RIQI=(select max(bean3.time) from reccomendstock as bean3)
//				f.append("and bean.RIQI =(select max(RIQI) from Stockmessage)");
			}
			else{
				f.append(" and bean.GPDM in(select bean1.GPDM from Stockmessage as bean1,reccomendstock as bean2 where bean2.stockID=bean1.GPDM and bean2.reccomendation="+Integer.valueOf(type)+" and bean2.time=bean1.RIQI) ");// and RIQI=(select max(bean3.time) from reccomendstock as bean3)
//				f.append("and bean.RIQI =(select max(RIQI) from Stockmessage)");
				
			}
			
			return find(f);
		
		
	}

	@Override
	public List<reccomendstock> getReccomendation_simp(String type) {
		// TODO Auto-generated method stub
		Calendar ca=Calendar.getInstance();
		ca.add(Calendar.DATE, -15);
		SimpleDateFormat sdf=new SimpleDateFormat("Y-MM-d");		
		String date=sdf.format(ca.getTime());
		System.out.println(date);
	
		Finder f = Finder.create("select bean from reccomendstock as bean where bean.time>=:date  ").setParam("date",date);//
		if(type!=null){
			f.append("and bean.reccomendation=:reccomendaion").setParam("reccomendaion", Integer.valueOf(type));		
			
		}
		f.setMaxResults(60);
		f.append(" order by bean.time desc");
		return find(f);
	}

	@Override
	public List<reccomendstock> getReccomendation_simp2(String type) {
		Finder f = Finder.create("select bean from reccomendstock as bean where bean.time<=(select max(bean.time) from reccomendstock)");
		if(type!=null){
			f.append("and bean.reccomendation=:reccomendaion").setParam("reccomendaion", Integer.valueOf(type));		
			
		}
		f.setMaxResults(60);
		f.append(" order by bean.time desc");
		return find(f);
	}
	
	

}