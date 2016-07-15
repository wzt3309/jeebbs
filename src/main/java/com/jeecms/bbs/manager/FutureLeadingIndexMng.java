package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.FutureLeadingIndex;
/**
 * 
 * @ClassName FutureLeadingIndexMng
 * @Description 期指领先指数 Manage接口
 * @author wzt3309
 */
public interface FutureLeadingIndexMng {
		/**
		 * 保存期指领先指数
		 * @param bean 期指领先指数
		 */
		public FutureLeadingIndex save(FutureLeadingIndex bean);
		/**
		 * 获得期指领先指数
		 * @param id 期指领先指数记录数据库id
		 */
		public FutureLeadingIndex findById(Integer id);
		/**
		 * 获得指定天数的期指领先指数
		 * @param day 指定天数
		 */
		public List<FutureLeadingIndex> getList(int day);
		/**
		 * 删除期指领先指数
		 * @param id 期指领先指数记录数据库id
		 */
		public FutureLeadingIndex deleteById(Integer id);
		/**
		 * 修改已存在的期指领先指数
		 * @param bean 期指领先指数
		 */
		public FutureLeadingIndex update(FutureLeadingIndex bean);
		/**
		 * 监测要保存的记录是否存在
		 */
		public boolean isExist(FutureLeadingIndex bean);
}
