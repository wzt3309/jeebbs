package com.jeecms.bbs.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.StockmessageDao;
import com.jeecms.bbs.entity.Stockmessage;
import com.jeecms.bbs.entity.reccomendstock;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class StockmessageDaoImpl extends HibernateBaseDao<Stockmessage, Integer> implements StockmessageDao{

		
	@Override
	public Pagination getmess(String sql,int pageNO,int pageSize) {		
		Finder f=null;
		if(StringUtils.isBlank(sql)){
			sql=" select bean from Stockmessage bean where 1=1 ";
		}
		f=Finder.create(sql);
		f.append(" order by bean.RIQI desc, bean.GPDM asc ");
		return find(f,pageNO,pageSize);
		
	}	
	public List<Stockmessage> getlist(String sql){
		
		return find(sql);
	}

	@Override
	protected Class<Stockmessage> getEntityClass() {
		// TODO Auto-generated method stub
		return Stockmessage.class;
	}
	@Override
	public Stockmessage save(Stockmessage bean) {
		String RIQI=bean.getRIQI();
		String GPDM=bean.getGPDM();
		String hql=" select bean from Stockmessage bean where " +
				"bean.GPDM = ? and bean.RIQI = ? ";
		List<Stockmessage> list=find(hql,GPDM,RIQI);
		if(list==null||list.size()<1){
			getSession().save(bean);
			return bean;
		}
		
		return null;
	}
	@Override
	public List getReccomendation_detail(String type,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		System.out.println("Reccomendation:get in dao");
		
		
			Finder f = Finder.create("select bean from Stockmessage as bean where 1=1");
			if(type==null){
				f.append(" and bean.GPDM in(select bean1.GPDM from Stockmessage as bean1,Reccomendstock as bean2 where bean2.stockID=bean1.GPDM and bean2.time=bean1.RIQI  ) ");//and RIQI=(select max(bean3.time) from Reccomendstock as bean3)
//				f.append("and bean.RIQI =(select max(RIQI) from Stockmessage)");
			}
			else{
				f.append(" and bean.GPDM in(select bean1.GPDM from Stockmessage as bean1,Reccomendstock as bean2 where bean2.stockID=bean1.GPDM and bean2.reccomendation="+Integer.valueOf(type)+" and bean2.time=bean1.RIQI) ");// and RIQI=(select max(bean3.time) from Reccomendstock as bean3)
//				f.append("and bean.RIQI =(select max(RIQI) from Stockmessage)");
				
			}			
			return find(f);
		
		
	}

	@Override
	public Pagination getReccomendation_simp(String type,int pageNO,int pageSize) {
		// TODO Auto-generated method stub
		Calendar ca=Calendar.getInstance();
		ca.add(Calendar.DATE, -15);
		SimpleDateFormat sdf=new SimpleDateFormat("Y-MM-d");		
		String date=sdf.format(ca.getTime());
		//System.out.println(date);
		Finder maxDate=Finder.create("select max(bean.time) from reccomendstock as bean where ");
		maxDate.append(" bean.reccomendation=:reccomendaion").setParam("reccomendaion", Integer.valueOf(type));
		List kk=find(maxDate);
		if(kk!=null&&kk.size()>0){
			String R=(String)(kk.get(0));
			if(R.compareTo(date)<0)
				return null;
		}
		Finder f = Finder.create("from reccomendstock bean where bean.time>=:date  ").setParam("date",date);//
		if(type!=null){
			f.append("and bean.reccomendation=:reccomendaion").setParam("reccomendaion", Integer.valueOf(type));		
			
		}
		f.setMaxResults(500);
		f.append(" order by bean.time desc");	
		
		@SuppressWarnings("unchecked")
		List<reccomendstock> list=find(f);	
		int totalsize=list.size();
		Pagination p = new Pagination(pageNO, pageSize, totalsize);
		List<reccomendstock> newList=new ArrayList<reccomendstock>();
		int first=p.getFirstResult();
		int last=first+pageSize;
		last=last<totalsize?last:totalsize;
		for(int i=first;i<last;i++){
			newList.add(list.get(i));
		}
		p.setList(newList);
		return p;
	}

	@Override
	public Pagination getReccomendation_simp2(String type,int pageNO,int pageSize) {
		Finder f = Finder.create("select bean from reccomendstock as bean where bean.time=(select max(bean.time) from reccomendstock)");
		if(type!=null){
			f.append("and bean.reccomendation=:reccomendaion").setParam("reccomendaion", Integer.valueOf(type));		
			
		}
		f.setMaxResults(500);
		f.append(" order by bean.time desc");
		
		List<reccomendstock> list=find(f);	
		int totalsize=list.size();
		Pagination p = new Pagination(pageNO, pageSize, totalsize);
		List<reccomendstock> newList=new ArrayList<reccomendstock>();
		int first=p.getFirstResult();
		int last=first+pageSize;
		last=last<totalsize?last:totalsize;
		for(int i=first;i<last;i++){
			newList.add(list.get(i));
		}
		p.setList(newList);
		return p;
	}
	@Override
	public Stockmessage getStockmsgById(String id) {
		String hql="select bean from Stockmessage bean where bean.GPDM = ? order by bean.RIQI desc";
		Stockmessage stockmsg=(Stockmessage) findUnique(hql,id);
		return stockmsg;
	}

}
