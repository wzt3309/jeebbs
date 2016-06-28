package com.jeecms.bbs.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsPostType;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.bbs.manager.BbsPostTypeMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.common.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsSite;
import static com.jeecms.common.page.SimplePage.cpn;

@Controller
public class BbsPostTypeAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsPostTypeAct.class);

	@RequestMapping("/posttype/v_list.do")
	public String list(Integer forumId,Integer parentId,Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = bbsPostTypeMng.getPage(CmsUtils
				.getSite(request).getId(), forumId,parentId,cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.put("pagination", pagination);
		model.put("forumId", forumId);
		model.put("parentId", parentId);
		return "posttype/list";
	}

	@RequestMapping("/posttype/v_add.do")
	public String add(Integer forumId,Integer parentId,HttpServletRequest request, ModelMap model) {
		model.put("forumId", forumId);
		model.put("parentId", parentId);
		return "posttype/add";
	}

	@RequestMapping("/posttype/v_edit.do")
	public String edit(Integer id, Integer forumId,Integer parentId,HttpServletRequest request, ModelMap model) {
		BbsPostType posttype = bbsPostTypeMng.findById(id);
		model.put("posttype", posttype);
		model.put("forumId", forumId);
		model.put("parentId", parentId);
		return "posttype/edit";
	}

	@RequestMapping("/posttype/o_save.do")
	public String save(BbsPostType posttype,Integer forumId,Integer parentId,Integer pageNo,HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if(forumId!=null){
			posttype.setForum(bbsForumMng.findById(forumId));
		}
		if(parentId!=null){
			posttype.setParent(bbsPostTypeMng.findById(parentId));
		}
		posttype.setSite(site);
		bbsPostTypeMng.save(posttype);
		return list(forumId, parentId, pageNo, request, model);
	}

	@RequestMapping("/posttype/o_update.do")
	public String update(BbsPostType posttype,Integer forumId,Integer parentId,Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		if(forumId!=null){
			posttype.setForum(bbsForumMng.findById(forumId));
		}
		if(parentId!=null){
			posttype.setParent(bbsPostTypeMng.findById(parentId));
		}
		bbsPostTypeMng.update(posttype);
		return list(forumId, parentId, pageNo, request, model);
	}

	@RequestMapping("/posttype/o_delete.do")
	public String delete(Integer[] ids,Integer forumId,Integer parentId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		BbsPostType[] beans = bbsPostTypeMng.deleteByIds(ids);
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		for (BbsPostType bean : beans) {
			log.info("delete BbsPostType id={}", bean.getId());
		}
		return list(forumId,parentId,pageNo, request, model);
	}

	@RequestMapping("/posttype/o_priority.do")
	public String priorityUpdate(Integer forumId,Integer parentId,Integer[] wids, Integer[] prioritys,
			Integer pageNo, HttpServletRequest request, ModelMap model) {
		if (wids == null || wids.length <= 0) {
			return "redirect:v_list.do";
		}
		CmsSite site = CmsUtils.getSite(request);
		BbsPostType t;
		Integer id;
		Integer priority;
		for (int i = 0; i < wids.length; i++) {
			id = wids[i];
			priority = prioritys[i];
			if (id != null && priority != null) {
				t = bbsPostTypeMng.findById(id);
				if (t != null && t.getSite().getId().equals(site.getId())) {
					t.setPriority(priority);
					bbsPostTypeMng.update(t);
				}
			}
		}
		return list(forumId,parentId,cpn(pageNo), request, model);
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		errors.ifEmpty(ids, "ids");
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		BbsPostType entity = bbsPostTypeMng.findById(id);
		if (errors.ifNotExist(entity, BbsPostType.class, id)) {
			return true;
		}
		return false;
	}

	@Autowired
	private BbsPostTypeMng bbsPostTypeMng;
	@Autowired
	private BbsForumMng bbsForumMng;
}
