package com.jeecms.bbs.action.directive;

import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_PAGINATION;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MyMsgPageDirective implements TemplateDirectiveModel {
	public static final String PARAM_USERID = "userId";
	public static final String PARAM_TYPEID = "typeId";

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer userId = getUserId(params);
		Integer typeId=getTypeId(params);
		Pagination pagination = bbsMessageMng.getPageByUserId(userId,typeId,
				FrontUtils.getPageNo(env), FrontUtils.getCount(params));
		//查询信息的时候设置所有未读信息状态
		List<BbsMessage>messages=bbsMessageMng.getListUserIdStatus(userId, typeId, false);
		for(BbsMessage msg:messages){
			msg.setStatus(true);
			bbsMessageMng.update(msg);
		}
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_PAGINATION, DEFAULT_WRAPPER.wrap(pagination));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	private Integer getUserId(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer userId = DirectiveUtils.getInt(PARAM_USERID, params);
		return userId == null ? 0 : userId;
	}

	private Integer getTypeId(Map<String, TemplateModel> params)
			throws TemplateException {
		Integer typeId = DirectiveUtils.getInt(PARAM_TYPEID, params);
//		默认消息
		return typeId == null ? 1 : typeId;
	}

	@Autowired
	private BbsMessageMng bbsMessageMng;
}
