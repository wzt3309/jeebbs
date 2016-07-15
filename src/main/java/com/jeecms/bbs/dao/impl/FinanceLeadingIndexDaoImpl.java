package com.jeecms.bbs.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.FinanceLeadingIndexDao;
import com.jeecms.bbs.entity.FinanceLeadingIndex;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

/**
 * 
 * @ClassName FinanceLeadingIndexDaoImpl
 * @Description 融资融券领先指数数据库操作Dao实现类
 * @author wzt3309
 * @date 2015-11-4
 */
@Repository
public class FinanceLeadingIndexDaoImpl extends HibernateBaseDao<FinanceLeadingIndex, Integer>
		implements FinanceLeadingIndexDao {
	/**
	 * 获取融资融券领先指数的集合
	 * @param day 代表选出多少日的期指领先指数
	 */
	@Override
	public List<FinanceLeadingIndex> getList(int day) {
		String hql="select bean from FinanceLeadingIndex as bean where bean.date<=(select max(bean.date) from FinanceLeadingIndex)";
		Finder f=Finder.create(hql);
		int countNum=countQueryResult(Finder.create("select * from FinanceLeadingIndex s"));
		if(countNum<day){
			f.setMaxResults(countNum);
		}else{
			f.setMaxResults(day);
		}		
		f.append(" order by bean.date desc");		
		@SuppressWarnings("unchecked")
		List<FinanceLeadingIndex> result= find(f);	
		return result;
	}
	/**
	 * 根据id获取融资融券领先指数
	 * @param id 数据表中每条记录的主键
	 */
	@Override
	public FinanceLeadingIndex findById(Integer id) {
		FinanceLeadingIndex entity=get(id);
		return entity;
	}
	/**
	 * 保存融资融券领先指数
	 * 在保存前，外部方法要先调用isExist（）看是否已经存在
	 */
	@Override
	public FinanceLeadingIndex save(FinanceLeadingIndex bean) {
		if(!isExist(bean))
			getSession().save(bean);	
		else
			System.out.println("要插入的financeleadingindex已存在");
		return bean;
	}
	/**
	 * 删除期指领先指数
	 */
	@Override
	public FinanceLeadingIndex deleteById(Integer id) {
		FinanceLeadingIndex entity =super.get(id);
		if(entity!=null){
			getSession().delete(entity);
		}
		return entity;
	}
	/**
	 * 获取实体类类型
	 * @reture FinanceLeadingIndex.class
	 */
	@Override
	protected Class<FinanceLeadingIndex> getEntityClass() {
		return FinanceLeadingIndex.class;
	}
	/**
	 * 
	 * @Description 监测要保存的记录是否已经存在 
	 * @author wzt3309
	 * @date 2015-11-4
	 * @return true：为已经存在  false：不存在可以保存
	 */
	@Override
	public boolean isExist(FinanceLeadingIndex bean){
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");		
		String hql="select bean from FinanceLeadingIndex as bean where bean.date like :date";
		Finder f=Finder.create(hql);
		try {
			f.setParam("date", fmt.parse(fmt.format(bean.getDate())));			
			System.out.print(fmt.format(bean.getDate())+","+find(f).size());
			return find(f).size()>0;
		} catch (ParseException e) {			
			e.printStackTrace();
			return false;
		}
		
		
	}
}
