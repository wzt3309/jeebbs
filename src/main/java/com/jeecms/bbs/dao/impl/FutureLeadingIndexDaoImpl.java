package com.jeecms.bbs.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.FutureLeadingIndexDao;
import com.jeecms.bbs.entity.FutureLeadingIndex;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

/**
 * 
 * @ClassName FutureLeadingIndexDaoImpl
 * @Description 期指领先指数数据库操作Dao实现类
 * @author wzt3309
 * @date 2015-11-4
 */
@Repository
public class FutureLeadingIndexDaoImpl extends HibernateBaseDao<FutureLeadingIndex, Integer>
		implements FutureLeadingIndexDao {
	/**
	 * 获取期指领先指数的集合
	 * @param day 代表选出多少日的期指领先指数
	 */
	@Override
	public List<FutureLeadingIndex> getList(int day) {
		String hql="select bean from FutureLeadingIndex as bean where bean.date<=(select max(bean.date) from FutureLeadingIndex)";
		Finder f=Finder.create(hql);
		int countNum=countQueryResult(Finder.create("select * from FutureLeadingIndex s"));
		if(countNum<day){
			f.setMaxResults(countNum);
		}else{
			f.setMaxResults(day);
		}		
		f.append(" order by bean.date desc");		
		@SuppressWarnings("unchecked")
		List<FutureLeadingIndex> result= find(f);	
		return result;
	}
	/**
	 * 根据id获取期指领先指数
	 * @param id 数据表中每条记录的主键
	 */
	@Override
	public FutureLeadingIndex findById(Integer id) {
		FutureLeadingIndex entity=get(id);
		return entity;
	}
	/**
	 * 保存期指领先指数
	 * 在保存前，外部方法要先调用isExist（）看是否已经存在
	 */
	@Override
	public FutureLeadingIndex save(FutureLeadingIndex bean) {			
		if(!isExist(bean))
			getSession().save(bean);	
		else
			System.out.println("要插入的FutureLeadingIndex已存在");
		return bean;
	}
	/**
	 * 删除期指领先指数
	 */
	@Override
	public FutureLeadingIndex deleteById(Integer id) {
		FutureLeadingIndex entity =super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
		return entity;
	}
	/**
	 * 获取实体类类型
	 * @reture FutureLeadingIndex.class
	 */
	@Override
	protected Class<FutureLeadingIndex> getEntityClass() {
		return FutureLeadingIndex.class;
	}
	/**
	 * 
	 * @Description 监测要保存的记录是否已经存在 
	 * @throws Exception
	 * @author wzt3309
	 * @date 2015-11-4
	 * @return true：为已经存在  false：不存在可以保存
	 */
	@Override
	public boolean isExist(FutureLeadingIndex bean){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");		
		String hql="select bean from FutureLeadingIndex as bean where bean.date like :date and bean.index like :index";
		Finder f=Finder.create(hql);
		try {
			f.setParam("date", fmt.parse(fmt.format(bean.getDate())));
			f.setParam("index",bean.getIndex());
			return find(f).size()>0;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
