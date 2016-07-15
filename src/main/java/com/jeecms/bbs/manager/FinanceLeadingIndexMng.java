package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.FinanceLeadingIndex;


/**
 * 
 * @ClassName FinanceLeadingIndexMng
 * @Description  融资融券领先指数 Manage接口
 * @author wzt3309
 */
public interface FinanceLeadingIndexMng {
	/**
	 * 保存融资融券领先指数
	 * @param bean 融资融券领先指数
	 */
	public FinanceLeadingIndex save(FinanceLeadingIndex bean);
	/**
	 * 获得融资融券领先指数
	 * @param id 融资融券领先指数记录数据库id
	 */
	public FinanceLeadingIndex findById(Integer id);
	/**
	 * 获得指定天数的融资融券领先指数
	 * @param day 指定天数
	 */
	public List<FinanceLeadingIndex> getList(int day);
	/**
	 * 删除融资融券领先指数
	 * @param id 融资融券领先指数记录数据库id
	 */
	public FinanceLeadingIndex deleteById(Integer id);
	/**
	 * 修改已存在的融资融券领先指数
	 * @param bean 融资融券领先指数
	 */
	public FinanceLeadingIndex update(FinanceLeadingIndex bean);
	/**
	 * 监测要保存的记录是否存在
	 */
	public boolean isExist(FinanceLeadingIndex bean);
}
