package com.jeecms.bbs.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrameAct {
	@RequestMapping("/frame/config_main.do")
	public String configMain(ModelMap model) {
		return "frame/config_main";
	}

	@RequestMapping("/frame/config_left.do")
	public String configLeft(ModelMap model) {
		return "frame/config_left";
	}

	@RequestMapping("/frame/config_right.do")
	public String configRight(ModelMap model) {
		return "frame/config_right";
	}

	@RequestMapping("/frame/user_main.do")
	public String userMain(ModelMap model) {
		return "frame/user_main";
	}

	@RequestMapping("/frame/user_left.do")
	public String userLeft(ModelMap model) {
		return "frame/user_left";
	}

	@RequestMapping("/frame/user_right.do")
	public String userRight(ModelMap model) {
		return "frame/user_right";
	}

	@RequestMapping("/frame/category_main.do")
	public String generateMain(ModelMap model) {
		return "frame/category_main";
	}

	@RequestMapping("/frame/category_left.do")
	public String generateLeft(ModelMap model) {
		return "frame/category_left";
	}

	@RequestMapping("/frame/category_right.do")
	public String generateRight(ModelMap model) {
		return "frame/category_right";
	}

	@RequestMapping("/frame/forum_main.do")
	public String forumMain(ModelMap model) {
		return "frame/forum_main";
	}

	@RequestMapping("/frame/forum_left.do")
	public String forumLeft(ModelMap model) {
		return "frame/forum_left";
	}

	@RequestMapping("/frame/forum_right.do")
	public String forumRight(ModelMap model) {
		return "frame/forum_right";
	}

	@RequestMapping("/frame/template_main.do")
	public String templateMain(ModelMap model) {
		return "frame/template_main";
	}

	@RequestMapping("/frame/resource_main.do")
	public String resourceMain(ModelMap model) {
		return "frame/resource_main";
	}
	
	@RequestMapping("/frame/maintain_main.do")
	public String maintainMain(ModelMap model){
		return "frame/maintain_main";
	}
	
	@RequestMapping("/frame/maintain_left.do")
	public String maintainLeft(ModelMap model){
		return "frame/maintain_left";
	}
	
	@RequestMapping("/frame/maintain_right.do")
	public String maintainRight(ModelMap model){
		return "frame/maintain_right";
	}

}
