package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeecms.bbs.MagicConstants;
import com.jeecms.bbs.action.directive.abs.AbstractPostPageDirective;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;
import com.jeecms.core.entity.CmsSite;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PostPageDirective extends AbstractPostPageDirective {

	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "post_page";
	/**
	 * 分页标签
	 */
	public static final String TPL_PAG = "topic";
	/**
	 *主题道具列表
	 */
	public static final String MAGIC_TOPIC_LIST = "magicTopicList";
	/**
	 *帖子道具列表
	 */
	public static final String MAGIC_POST_LIST = "magicPostList";
	/**
	 *用户道具列表
	 */
	public static final String MAGIC_USER_LIST = "magicUserList";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		InvokeType type = DirectiveUtils.getInvokeType(params);
		Pagination page = bbsPostMng.getForTag(site.getId(),
				getTopicId(params), getCreaterId(params), FrontUtils
						.getPageNo(env), FrontUtils.getCount(params));
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(page));
		Boolean magic_switch = magicConfigMng.findById(site.getId())
				.getMagicSwitch();
		paramWrap.put("magic_switch", DEFAULT_WRAPPER.wrap(magic_switch));
		if (magic_switch) {
			List<BbsCommonMagic> magicList = magicMng.getList();
			List<BbsCommonMagic> magicTopicList = new ArrayList<BbsCommonMagic>();
			List<BbsCommonMagic> magicPostList = new ArrayList<BbsCommonMagic>();
			List<BbsCommonMagic> magicUserList = new ArrayList<BbsCommonMagic>();
			BbsCommonMagic magic;
			String identifier;
			for (int i = 0; i < magicList.size(); i++) {
				magic = magicList.get(i);
				if (magic.getAvailable()) {
					identifier = magic.getIdentifier();
					if (identifier.equals(MagicConstants.MAGIC_OPEN)
							|| identifier.equals(MagicConstants.MAGIC_CLOSE)
							|| identifier.equals(MagicConstants.MAGIC_SOFA)
							|| identifier
									.equals(MagicConstants.MAGIC_HIGHTLIGHT)
							|| identifier.equals(MagicConstants.MAGIC_BUMP)
							|| identifier.equals(MagicConstants.MAGIC_JACK)
							|| identifier.equals(MagicConstants.MAGIC_STICK)) {
						magicTopicList.add(magic);
					} else if (identifier.equals(MagicConstants.MAGIC_NAMEPOST)
							|| identifier.equals(MagicConstants.MAGIC_REPENT)
							|| identifier
									.equals(MagicConstants.MAGIC_ANONYMOUSPOST)) {
						magicPostList.add(magic);
						magicTopicList.add(magic);
					} else if (identifier.equals(MagicConstants.MAGIC_SHOWIP)
							|| identifier
									.equals(MagicConstants.MAGIC_CHECKONLINE)) {
						magicUserList.add(magic);
					}
				}
			}
			paramWrap.put(MAGIC_TOPIC_LIST, DEFAULT_WRAPPER.wrap(magicTopicList));
			paramWrap.put(MAGIC_POST_LIST, DEFAULT_WRAPPER.wrap(magicPostList));
			paramWrap.put(MAGIC_USER_LIST, DEFAULT_WRAPPER.wrap(magicUserList));
		}
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		if (InvokeType.custom == type) {
			FrontUtils.includeTpl(TPL_NAME, site, params, env);
			FrontUtils.includePagination(site, params, env);
		} else if (InvokeType.body == type) {
			body.render(env.getOut());
			FrontUtils.includePagination(site, params, env);
		} else {
			throw new RuntimeException("invoke type not handled: " + type);
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
}
