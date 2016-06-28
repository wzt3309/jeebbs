package com.jeecms.bbs.action.front;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.entity.BbsLoginLog;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsLoginLogMng;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.web.session.SessionProvider;

@Controller
public class BbsKeepSession {
	private static String LAST_KEEP_SESSION_TIME="last_keep_session_time";
	// 保持连接
	@RequestMapping(value = "/keepSession.jspx")
	public void keepSession(Double interval, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		if (user != null) {
			// 用户还在线，更新在线时长
			BbsLoginLog log = user.getUserLaestLoginLog();
			BbsUserOnline online=user.getUserOnline();
			Calendar calendar = Calendar.getInstance();
			if (log.getLogoutTime() == null) {
				log.setLogoutTime(calendar.getTime());
				loginLogMng.update(log);
			} else if (log.getLogoutTime().before(calendar.getTime())) {
				log.setLogoutTime(calendar.getTime());
				loginLogMng.update(log);
			}
			Date lastKeepSessionTime=(Date) session.getAttribute(request, LAST_KEEP_SESSION_TIME);
			if(DateUtils.diffTwoDate(lastKeepSessionTime, calendar.getTime())>=interval){
				System.out.println("interval:"+interval);
				online.updateOnline(interval);
				userOnlineMng.update(online);
				session.setAttribute(request, response, LAST_KEEP_SESSION_TIME, calendar.getTime());
			}
		}
	}

	@Autowired
	private BbsLoginLogMng loginLogMng;
	@Autowired
	private BbsUserOnlineMng userOnlineMng;
	@Autowired
	private SessionProvider session;
}
