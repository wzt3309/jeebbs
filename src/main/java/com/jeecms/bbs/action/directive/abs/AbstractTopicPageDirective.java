package com.jeecms.bbs.action.directive.abs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 主题列表标签基类
 * 
 * @author liufang
 * 
 */
public abstract class AbstractTopicPageDirective implements
		TemplateDirectiveModel {

	/**
	 * 输入参数，版块ID。
	 */
	public static final String PARAM_FORUM_ID = "forumId";
	/**
	 * 输入参数，级别精华。
	 */
	public static final String PARAM_PRIME_LEVEL = "primeLevel";
	/**
	 * 输入参数，置顶级别。
	 */
	public static final String PARAM_TOP_LEVEL = "topLevel";
	/**
	 * 输入参数，发布者。
	 */
	public static final String PARAM_CREATER = "creater";
	/**
	 * 输入参数，发布者ID。
	 */
	public static final String PARAM_CREATER_ID = "createrId";
	/**
	 * 输入参数，关键字。
	 */
	public static final String PARAM_KEY_WORDS = "keyWords";
	/**
	 * 输入参数，状态。
	 */
	public static final String PARAM_STATUS = "status";
	/**
	 * 输入参数，帖子小类。
	 */
	public static final String PARAM_POSTTYPE_ID = "typeId";
	/**
	 * 输入参数，帖子大类。
	 */
	public static final String PARAM_PARENT_POSTTYPE_ID = "parentTypeId";
	/**
	 * 输入参数，排列顺序。0：按评论时间降序；1：按评论时间升序。默认降序。
	 */
	public static final String PARAM_ORDER_BY = "orderBy";

	protected Integer getForumId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_FORUM_ID, params);
	}

	protected String getFindType(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getString("findType", params);
	}

	protected Short getPrimeLevel(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getShort(PARAM_PRIME_LEVEL, params);
	}

	protected Short getTopLevel(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getShort(PARAM_TOP_LEVEL, params);
	}

	protected String getCreater(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getString(PARAM_CREATER, params);
	}

	protected Integer getCreaterId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_CREATER_ID, params);
	}

	protected String getKeyWords(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getString(PARAM_KEY_WORDS, params);
	}

	protected Short getStatus(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getShort(PARAM_STATUS, params);
	}

	protected Integer getPostTypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_POSTTYPE_ID, params);
	}

	protected Integer getParentPostTypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		return DirectiveUtils.getInt(PARAM_PARENT_POSTTYPE_ID, params);
	}

	protected boolean getDesc(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer orderBy = DirectiveUtils.getInt(PARAM_ORDER_BY, params);
		if (orderBy == null || orderBy == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Autowired
	protected BbsTopicMng bbsTopicMng;

}
