package com.jeecms.bbs.action;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsReport;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsReportExtMng;
import com.jeecms.bbs.manager.BbsReportMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.web.CookieUtils;

@Controller
public class BbsReportAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsReportAct.class);

	@RequestMapping("/report/v_list.do")
	public String list(Boolean status, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		if(status==null){
			status=false;
		}
		Pagination pagination = reportMng.getPage(status, SimplePage
				.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("status", status);
		return "report/list";
	}

	@RequestMapping("/report/v_more.do")
	public String more(Integer reportId, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Pagination pagination = reportExtMng.getPage(reportId, SimplePage
				.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("reportId", reportId);
		return "report/more";
	}

	@RequestMapping("/report/o_process.do")
	public String process(Integer[] ids,Integer[] points,String[] results, HttpServletRequest request,
			ModelMap model) {
		BbsReport report;
		BbsUser reportUser;
		BbsUser user=CmsUtils.getUser(request);
		Calendar cal=Calendar.getInstance();
		for(int i=0;i<ids.length;i++){
			report=reportMng.findById(ids[i]);
			reportUser=report.getReportExt().getReportUser();
			userMng.updatePoint(reportUser.getId(), points[i], null, null,0, -1);
			report.setProcessResult(results[i]);
			report.setProcessTime(cal.getTime());
			report.setProcessUser(user);
			report.setStatus(true);
			reportMng.update(report);
		}
		return list(false, 1, request, model);
	}

	@RequestMapping("/report/o_delete.do")
	public String delete(Boolean status, Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		BbsReport[] beans = reportMng.deleteByIds(ids);
		for (BbsReport bean : beans) {
			log.info("delete BbsReport id={}", bean.getId());
		}
		return list(status, pageNo, request, model);
	}

	@Autowired
	private BbsReportExtMng reportExtMng;
	@Autowired
	private BbsReportMng reportMng;
	@Autowired
	private BbsUserMng userMng;
}