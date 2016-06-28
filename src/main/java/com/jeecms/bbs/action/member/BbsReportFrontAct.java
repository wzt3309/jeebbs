package com.jeecms.bbs.action.member;

import static com.jeecms.bbs.Constants.TPLDIR_MEMBER;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.bbs.entity.BbsReport;
import com.jeecms.bbs.entity.BbsReportExt;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsReportExtMng;
import com.jeecms.bbs.manager.BbsReportMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.core.entity.CmsSite;

@Controller
public class BbsReportFrontAct {
	private static final Logger log = LoggerFactory
			.getLogger(BbsReportFrontAct.class);
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_REPORT_PAGE = "tpl.reportpage";
	public static final String ANCHOR = "#pid";

	@RequestMapping("/member/getreportpage.jspx")
	public String getbuymagicpage(String url,String pid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.addAttribute("url",site.getProtocol()+site.getDomain()+":"+site.getPort()+url+ANCHOR+pid);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, TPL_REPORT_PAGE);
	}

	@RequestMapping(value="/member/report.jhtml",method = RequestMethod.POST)
	public void report(String url, String reason, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user=CmsUtils.getUser(request);
		Calendar calendar=Calendar.getInstance();
		BbsReport report=reportMng.findByUrl(url);
		BbsReportExt reportExt;
		//已经举报过该地址
		if(report!=null){
			reportExt=reportExtMng.findByReportUid(report.getId(), user.getId());
			//同一个人重复举报同一个地址
			if(reportExt!=null){
				reportExt.setReportReason(reason);
				reportExtMng.update(reportExt);
			}else{
				//不同人举报同一个地址
				reportExt=new BbsReportExt();
				reportExt.setReport(report);
				reportExt.setReportReason(reason);
				reportExt.setReportTime(calendar.getTime());
				reportExt.setReportUser(user);
				reportExtMng.save(reportExt);
			}
		}else{
			//举报新的地址
			report=new BbsReport();
			report.setReportUrl(url);
			report.setStatus(false);
			report.setReportTime(calendar.getTime());
			reportMng.save(report);
			reportExt=new BbsReportExt();
			reportExt.setReport(report);
			reportExt.setReportReason(reason);
			reportExt.setReportTime(calendar.getTime());
			reportExt.setReportUser(user);
			reportExtMng.save(reportExt);
		}
	}

	@Autowired
	private BbsReportMng reportMng;
	@Autowired
	private BbsReportExtMng reportExtMng;
}
