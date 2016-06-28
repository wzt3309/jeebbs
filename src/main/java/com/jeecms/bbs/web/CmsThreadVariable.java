package com.jeecms.bbs.web;

import java.util.List;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.CmsSensitivity;
import com.jeecms.core.entity.CmsSite;

/**
 * CMS线程变量
 * 
 * @author liufang
 * 
 */
public class CmsThreadVariable {
	/**
	 * 当前用户线程变量
	 */
	private static ThreadLocal<BbsUser> BbsUserVariable = new ThreadLocal<BbsUser>();
	/**
	 * 当前站点线程变量
	 */
	private static ThreadLocal<CmsSite> cmsSiteVariable = new ThreadLocal<CmsSite>();

	/**
	 * 当前站点敏感词线程变量
	 */
	private static ThreadLocal<List<CmsSensitivity>> cmsSensitivityVariable = new ThreadLocal<List<CmsSensitivity>>();

	/**
	 * 获得当前用户
	 * 
	 * @return
	 */
	public static BbsUser getUser() {
		return BbsUserVariable.get();
	}

	/**
	 * 设置当前用户
	 * 
	 * @param user
	 */
	public static void setUser(BbsUser user) {
		BbsUserVariable.set(user);
	}

	/**
	 * 移除当前用户
	 */
	public static void removeUser() {
		BbsUserVariable.remove();
	}

	/**
	 * 获得当前站点
	 * 
	 * @return
	 */
	public static CmsSite getSite() {
		return cmsSiteVariable.get();
	}

	/**
	 * 设置当前站点
	 * 
	 * @param site
	 */
	public static void setSite(CmsSite site) {
		cmsSiteVariable.set(site);
	}

	/**
	 * 移除当前站点
	 */
	public static void removeSite() {
		cmsSiteVariable.remove();
	}
	
	/**
	 * 获得当前站点敏感词线程变量
	 * 
	 * @return
	 */
	public static List<CmsSensitivity> getSensitivityList(){
		return cmsSensitivityVariable.get();
	}
	
	/**
	 * 设置当前站点敏感词
	 * 
	 * @param cmsSensitivityList
	 */
	public static void setSensitivityList(List<CmsSensitivity> sensitivityList) {
		cmsSensitivityVariable.set(sensitivityList);
	}
	
	/**
	 * 移除当前站点敏感词
	 */
	public static void removeSensitivityList() {
		cmsSensitivityVariable.remove();
	}
}
