package com.jeecms.bbs.manager;

import java.util.List;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.common.page.Pagination;

public interface BbsUserGroupMng {
	/**
	 * 获得论坛会员组
	 * 
	 * @param webId
	 *            站点ID
	 * @return
	 */
	public List<BbsUserGroup> getList(Integer webId);

	/**
	 * 获得论坛会员组
	 * 
	 * @param webId
	 *            站点ID
	 * @param groupType
	 *            组类别
	 * @return
	 */
	public List<BbsUserGroup> getList(Integer webId, short groupType);

	/**
	 * 保存、更新论坛会员组
	 * 
	 * @param siteId
	 *            站点ID
	 * @param groupType
	 *            组类别
	 * @param groups
	 *            待更新组
	 * @param news
	 *            待添加组
	 */
	public void saveOrUpdateGroups(Integer siteId, short groupType,
			String[] name, String[] imgPath, Integer[] id, Long[] point);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsUserGroup[] deleteByIds(Integer[] ids);

	public BbsUserGroup deleteById(Integer id);

	public BbsUserGroup update(BbsUserGroup bean);
	
	public BbsUserGroup update(BbsUserGroup bean,Integer postTypeIds[]);

	public BbsUserGroup save(BbsUserGroup bean);

	public void updateRegDef(Integer regDefId, Integer siteId);

	public BbsUserGroup getRegDef();

	public BbsUserGroup findById(Integer id);
	
	public BbsUserGroup findNearByPoint(Long point,BbsUser user);
}