package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.FutureLeadingIndex;
import com.jeecms.common.hibernate3.Updater;

/**
 * 
 * @ClassName FutureLeadingIndexDao
 * @Description 期指领先指数DAO接口
 * @author wzt3309 
 */
public interface FutureLeadingIndexDao {
	/*获取期指领先指数的集合*/
	public List<FutureLeadingIndex> getList(int day);
	/*根据id获取期指领先指数*/
	public FutureLeadingIndex findById(Integer id);
	/*保存期指领先指数*/
	public FutureLeadingIndex save(FutureLeadingIndex bean);	
	/*删除期指领先指数*/
	public FutureLeadingIndex deleteById(Integer id);
	/*更新期指领先指数*/
	public FutureLeadingIndex updateByUpdater(Updater<FutureLeadingIndex> updater);
	/*监测要保存的记录是否已经存在 */
	public boolean isExist(FutureLeadingIndex bean);
}
