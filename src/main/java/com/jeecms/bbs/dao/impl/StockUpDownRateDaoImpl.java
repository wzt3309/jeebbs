package com.jeecms.bbs.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.StockUpDownRateDao;
import com.jeecms.bbs.entity.StockUpDownRate;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;


@Repository
public class StockUpDownRateDaoImpl extends HibernateBaseDao<StockUpDownRate, Integer> implements StockUpDownRateDao {
	
	
	
	/*找到最新日期的前day个或所有涨跌率*/
	public List<StockUpDownRate> getList(int day) {		
		String hql="select bean from StockUpDownRate as bean where bean.date<=(select max(bean.date) from StockUpDownRate)";
		Finder f=Finder.create(hql);
		int countNum=countQueryResult(Finder.create("select * from StockUpDownRate s"));
		if(countNum<day){
			f.setMaxResults(countNum);
		}else{
			f.setMaxResults(day);
		}		
		f.append(" order by bean.date desc");		
		@SuppressWarnings("unchecked")
		List<StockUpDownRate> result= find(f);	
		return result;
	}
	
	
	public StockUpDownRate findById(Integer id) {
		StockUpDownRate entity=get(id);
		return entity;
	}

	
	public StockUpDownRate save(StockUpDownRate bean) {	
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");		
		String hql="select bean from StockUpDownRate as bean where bean.date like :date";
		Finder f=Finder.create(hql);
		try {
			f.setParam("date",fmt.parse(fmt.format(bean.getDate())));
			if(find(f).size()<=0){
				getSession().save(bean);			
			}else{
				System.out.println(find(f));
				System.out.println("该股票涨跌率已存在");
			}	
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return bean;
	}	

	@Override
	protected Class<StockUpDownRate> getEntityClass() {
		
		return StockUpDownRate.class;
	}

	
	public StockUpDownRate deleteById(Integer id) {
		StockUpDownRate entity=super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
		
		return entity;
	}	
}
