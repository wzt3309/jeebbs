package com.jeecms.bbs.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsCategory;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.manager.BbsCategoryMng;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.core.entity.CmsSite;

@Controller
public class BbsCategoryAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsCategoryAct.class);

	@RequestMapping("/category/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<BbsCategory> list = bbsCategoryMng.getList(CmsUtils
				.getSiteId(request));
		model.put("list", list);
		return "category/list";
	}

	@RequestMapping("/category/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		return "category/add";
	}

	@RequestMapping("/category/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		BbsCategory category = bbsCategoryMng.findById(id);
		model.put("category", category);
		return "category/edit";
	}

	@RequestMapping("/category/o_save.do")
	public String save(BbsCategory category, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		category.setSite(site);
		bbsCategoryMng.save(category);
		return "redirect:v_list.do";
	}

	@RequestMapping("/category/o_update.do")
	public String update(BbsCategory category, HttpServletRequest request,
			ModelMap model) {
		bbsCategoryMng.update(category);
		return "redirect:v_list.do";
	}

	@RequestMapping("/category/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		for (int i = 0; i < ids.length; i++) {
			List<BbsForum> listForum = bbsForumMng.getList(site.getId(), ids[i]);
			Integer[] forumIds = new Integer[listForum.size()];
			for (int ii = 0; ii < listForum.size(); ii++) {
				forumIds[ii]=listForum.get(ii).getId();
				List<BbsTopic> listTopic = bbsTopicMng.getListByForumId(listForum.get(ii).getId());
				for (int j = 0; j < listTopic.size(); j++) {
					BbsTopic topic = bbsTopicMng.deleteById(listTopic.get(j).getId());
					log.info("delete BbsTopic id={}", topic.getId());
				}
			}
			BbsForum[] beans = bbsForumMng.deleteByIds(forumIds);
			for (BbsForum bean : beans) {
				log.info("delete BbsForum id={}", bean.getId());
			}
		}
		BbsCategory[] beans = bbsCategoryMng.deleteByIds(ids);
		for (BbsCategory bean : beans) {
			log.info("delete BbsCategory id={}", bean.getId());
		}
		return "redirect:v_list.do";
	}

	@RequestMapping("/category/o_priority.do")
	public String priorityUpdate(Integer[] wids, Integer[] prioritys,
			HttpServletRequest request, ModelMap model) {
		if (wids == null || wids.length <= 0) {
			return "redirect:v_list.do";
		}
		CmsSite site = CmsUtils.getSite(request);
		BbsCategory t;
		Integer id;
		Integer priority;
		for (int i = 0; i < wids.length; i++) {
			id = wids[i];
			priority = prioritys[i];
			if (id != null && priority != null) {
				t = bbsCategoryMng.findById(id);
				if (t != null && t.getSite().getId().equals(site.getId())) {
					t.setPriority(priority);
					bbsCategoryMng.update(t);
				}
			}
		}
		return list(request, model);
	}

	@Autowired
	private BbsCategoryMng bbsCategoryMng;
	@Autowired
	private BbsForumMng bbsForumMng;
	@Autowired
	private BbsTopicMng bbsTopicMng;
}
