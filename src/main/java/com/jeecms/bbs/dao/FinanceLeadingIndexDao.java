package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.FinanceLeadingIndex;
import com.jeecms.common.hibernate3.Updater;

/**
 * 
 * @ClassName FinanceLeadingIndexDao
 * @Description 融资融券领先指数DAO接口
 * @author wzt3309
 */
public interface FinanceLeadingIndexDao {
	/*获取融资融券领先指数的集合*/
	public List<FinanceLeadingIndex> getList(int day);
	/*根据id获取融资融券领先指数*/
	public FinanceLeadingIndex findById(Integer id);
	/*保存融资融券领先指数*/
	public FinanceLeadingIndex save(FinanceLeadingIndex bean);	
	/*删除融资融券领先指数*/
	public FinanceLeadingIndex deleteById(Integer id);
	/*更新融资融券领先指数*/
	public FinanceLeadingIndex updateByUpdater(Updater<FinanceLeadingIndex> updater);
	/*监测要保存的记录是否已经存在 */
	public boolean isExist(FinanceLeadingIndex bean);
}
