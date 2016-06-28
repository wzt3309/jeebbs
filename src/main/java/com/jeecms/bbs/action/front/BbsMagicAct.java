package com.jeecms.bbs.action.front;

import static com.jeecms.bbs.Constants.TPLDIR_MAGIC;
import static com.jeecms.bbs.Constants.TPLDIR_TOPIC;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.bbs.MagicConstants;
import com.jeecms.bbs.entity.BbsCommonMagic;
import com.jeecms.bbs.entity.BbsMagicConfig;
import com.jeecms.bbs.entity.BbsMagicLog;
import com.jeecms.bbs.entity.BbsMemberMagic;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsCommonMagicMng;
import com.jeecms.bbs.manager.BbsMagicConfigMng;
import com.jeecms.bbs.manager.BbsMagicLogMng;
import com.jeecms.bbs.manager.BbsPostMng;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.MagicMessage;

@Controller
public class BbsMagicAct {
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_NO_OPEN = "tpl.magic.noopen";
	public static final String TPL_MAGIC_SHOP = "tpl.magic.shop";
	public static final String TPL_MAGIC_MYBOX = "tpl.magic.mybox";
	public static final String TPL_MAGIC_LOG = "tpl.magic.log";
	public static final String TPL_MAGIC_SHOW = "tpl.magic.show";
	public static final String TPL_MAGIC_BUY = "tpl.magic.buy";
	public static final String TPL_MAGIC_SELL = "tpl.magic.sell";

	/**
	 * 
	 * @param request
	 * @param model
	 * @return 道具商店
	 */
	@RequestMapping("/magic/shop.jhtml")
	public String magicshop(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		List<BbsCommonMagic> magics = magicMng.getList();
		model.addAttribute("magics", magics);
		model.addAttribute("userPacketSize", user.getMagicPacketSize());
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_SHOP);
	}

	/**
	 * 
	 * @param request
	 * @param model
	 * @return 我的道具
	 */
	@RequestMapping("/magic/mybox.jhtml")
	public String magicmybox(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		Set<BbsMemberMagic> memberMagics = user.getMemberMagics();
		List<BbsMemberMagic> magics = new ArrayList<BbsMemberMagic>();
		Iterator<BbsMemberMagic> it = memberMagics.iterator();
		int discount = magicConfig.getMagicDiscount();
		BbsMemberMagic temp;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getNum() > 0) {
				magics.add(temp);
			}
		}
		model.addAttribute("memberMagics", magics);
		model.addAttribute("magicTotalPacketSize", user.getMagicPacketSize());
		model.addAttribute("discount", discount);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_MYBOX);
	}

	@RequestMapping("/magic/magiclog.jhtml")
	public String magiclog(Integer pageNo1, Integer pageNo2, Integer pageNo3,
			Integer tab, HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		Pagination logs1 = magicLogMng.getPage(
				MagicConstants.MAGIC_OPERATOR_USE, user.getId(), SimplePage
						.cpn(pageNo1), CookieUtils.getPageSize(request));
		Pagination logs2 = magicLogMng.getPage(
				MagicConstants.MAGIC_OPERATOR_BUY, user.getId(), SimplePage
						.cpn(pageNo2),  CookieUtils.getPageSize(request));
		Pagination logs3 = magicLogMng.getPage(
				MagicConstants.MAGIC_OPERATOR_GIVE, user.getId(), SimplePage
						.cpn(pageNo3),  CookieUtils.getPageSize(request));
		model.addAttribute("pagination1", logs1);
		model.addAttribute("pagination2", logs2);
		model.addAttribute("pagination3", logs3);
		model.addAttribute("tab", tab);
		FrontUtils.frontPageData(request, model);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_LOG);
	}

	
	@RequestMapping("/magic/getsellmagicpage.jspx")
	public String getsellmagicpage(String mid, int operator, Integer pid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		Boolean permiss = true;
		if (magicConfig.getMagicSwitch()) {
			int magicDiscount = 0;
			// operator=0出售，1使用,2丢弃
			magicDiscount = magicConfig.getMagicDiscount();
			MagicMessage magicMessage = MagicMessage.create(request);
			String sellMoneyCredit = "";
			// 获取道具的购买方式-积分或者威望
			if (magic.getCredit() == 1) {
				sellMoneyCredit = magicMessage.getMessage("cmsUser.point");
			} else if (magic.getCredit() == 2) {
				sellMoneyCredit = magicMessage
						.getMessage("cmsUser.prestige");
			}
			// 折扣为0的情况返回重量选择丢弃操作，否则给出回收价格
			if (operator == 2) {
				model.addAttribute("magicMsg", magicMessage.getMessage(
						"magic.weight", magic.getWeight()));
			} else if (operator == 0) {
				int sellMoney = magic.getPrice() * magicDiscount / 100;
				model.addAttribute("magicMsg", magicMessage.getMessage(
						"magic.sell.discount", sellMoney, sellMoneyCredit));
			} else if (operator == 1) {
				String magicMsg = "";
				int permission = hasPermission(magic, user, pid);
				if (permission == 1) {
					// 允许使用
					if (StringUtils.isNotBlank(mid)) {
						magicMsg = magicMessage.getMessage("magic." + mid);
					}
				} else if (permission == 0) {
					// 不允许使用
					magicMsg = magicMessage
							.getMessage("magic.use.hasnopermission");
					permiss = false;
				} else if (permission == -1) {
					// 不允许使用
					magicMsg = magicMessage
							.getMessage("magic.use.hasnobeusedpermission");
					permiss = false;
				}
				model.addAttribute("magicMsg", magicMsg);
			}
			model.addAttribute("operator", operator);
			model.addAttribute("permiss", permiss);
		}
		model.addAttribute("magicName", magic.getName());
		model.addAttribute("mid", mid);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_SELL);
	}
	
	@RequestMapping("/magic/getbuymagicpage.jspx")
	public String getbuymagicpage(String mid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		String credit = "";
		if (magicConfig.getMagicSwitch()) {
			MagicMessage magicMessage = MagicMessage.create(request);
			// 获取道具的购买方式-积分或者威望
			if (magic.getCredit() == 1) {
				credit = magicMessage.getMessage("cmsUser.point");
				model.addAttribute("point", user.getPoint());
			} else if (magic.getCredit() == 2) {
				credit = magicMessage.getMessage("cmsUser.prestige");
				model.addAttribute("point", user.getPrestige());
			}
		}
		model.addAttribute("magicName", magic.getName());
		model.addAttribute("mid", mid);
		model.addAttribute("magicswitch", magicConfig.getMagicSwitch());
		model.addAttribute("price", magic.getPrice());
		model.addAttribute("credit", credit);
		model.addAttribute("weight", magic.getWeight());
		model.addAttribute("userMagicPacketSize", user.getGroup()
				.getMagicPacketSize()
				- user.getMagicPacketSize());
		model.addAttribute("num", magic.getNum());
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_BUY);
	}

	@RequestMapping("/magic/buymagic.jspx")
	public void buymagic(String mid, int buynum, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		JSONObject object = new JSONObject();
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		Boolean pass;
		String message = "";
		Integer paymoney = magic.getPrice() * buynum;
		Integer buyMagicWeight = magic.getWeight() * buynum;
		if (user == null) {
			pass = false;
		} else {
			pass = true;
		}
		try {
			object.put("pass", pass);
			String credit = "";
			if (magicConfig.getMagicSwitch()) {
				MagicMessage magicMessage = MagicMessage.create(request);
				if (buynum <= 0) {
					// 非法数字
					message = magicMessage.getMessage("magic.buy.errorbuynum");
				} else if (buynum > magic.getNum()) {
					// 购买数量超出库存
					message = magicMessage
							.getMessage("magic.buy.nventory.miss");
				} else if ((user.getGroup().getMagicPacketSize()
						- user.getMagicPacketSize() - buyMagicWeight) < 0) {
					// 用户剩余的包容量不够
					message = magicMessage
							.getMessage("magic.buy.packetsize.noenough");
				} else {
					// 获取道具的购买方式-积分或者威望
					if (magic.getCredit() == 1) {
						credit = magicMessage.getMessage("cmsUser.point");
						if ((user.getPoint() - paymoney) < 0) {
							// 积分不足
							message = magicMessage
									.getMessage("magic.buy.point.noenough");
						} else {
							message = magicMessage.getMessage(
									"magic.buy.success", paymoney, credit,
									buynum, magic.getName());
							// 需要增加用户道具数量，减少系统道具数量，减少道具包容量，减少用户积分
							userMng.updatePoint(user.getId(), -paymoney, null,
									mid, buynum, 3);
							saveMagicLog(magic, user, buynum,
									MagicConstants.MAGIC_OPERATOR_BUY);
						}
					} else if (magic.getCredit() == 2) {
						credit = magicMessage.getMessage("cmsUser.prestige");
						if ((user.getPrestige() - paymoney) < 0) {
							// 威望不足
							message = magicMessage
									.getMessage("magic.buy.prestige.noenough");
						} else {
							message = magicMessage.getMessage(
									"magic.buy.success", paymoney, credit,
									buynum, magic.getName());
							userMng.updatePoint(user.getId(), null, -paymoney,
									mid, buynum, 3);
							saveMagicLog(magic, user, buynum,
									MagicConstants.MAGIC_OPERATOR_BUY);
						}
					}
				}
			}
			object.put("message", message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping("/magic/sellmagic.jspx")
	public void sellmagic(String mid, int operator, int num,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		JSONObject object = new JSONObject();
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		BbsMemberMagic user_magic;
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		Boolean pass;
		if (user == null) {
			pass = false;
		} else {
			pass = true;
		}
		try {
			object.put("pass", pass);
			object.put("magicName", magic.getName());
			int magicDiscount = 0;
			magicDiscount = magicConfig.getMagicDiscount();
			MagicMessage magicMessage = MagicMessage.create(request);
			user_magic = user.getMemberMagic(mid);
			if (operator == 0) {
				// 出售
				String sellMoneyCredit = "";
				int sellMoney = magic.getPrice() * magicDiscount / 100;
				int totalMoney = sellMoney * num;
				user = userMng.findById(user.getId());
				// 获取道具的购买方式-积分或者威望
				if (magic.getCredit() == 1) {
					if (user_magic != null) {
						// 用户拥有的数量大于输入的数量才可用出售道具
						if (user_magic.getNum() > num) {
							sellMoneyCredit = magicMessage
									.getMessage("cmsUser.point");
							userMng.updatePoint(user.getId(), totalMoney, null,
									mid, num, 0);
							object.put("magicMsg", magicMessage.getMessage(
									"magic.sell.success", num, magic.getName(),
									totalMoney, sellMoneyCredit));
							saveMagicLog(magic, user, num,
									MagicConstants.MAGIC_OPERATOR_SELL);
						} else {
							// 道具数量不够
							object.put("magicMsg", magicMessage
									.getMessage("magic.error.haslittlenum"));
						}
					} else {
						// 没有该道具
						object.put("magicMsg", magicMessage
								.getMessage("magic.error.hasnomagic"));
					}
				} else if (magic.getCredit() == 2) {
					if (user_magic != null) {
						if (user_magic.getNum() > num) {
							sellMoneyCredit = magicMessage
									.getMessage("cmsUser.prestige");
							userMng.updatePoint(user.getId(), null, totalMoney,
									mid, num, 0);
							saveMagicLog(magic, user, num,
									MagicConstants.MAGIC_OPERATOR_SELL);
						} else {
							// 道具数量不够
							object.put("magicMsg", magicMessage
									.getMessage("magic.error.haslittlenum"));
						}

					} else {
						// 没有该道具
						object.put("magicMsg", magicMessage
								.getMessage("magic.error.hasnomagic"));
					}

				}

			} else if (operator == 2) {
				// 丢弃
				if (user_magic != null) {
					if (user_magic.getNum() > num) {
						userMng.updatePoint(user.getId(), null, null, mid, num,
								2);
						object.put("magicMsg", magicMessage.getMessage(
								"magic.drop.success", num, magic.getName()));
						saveMagicLog(magic, user, num,
								MagicConstants.MAGIC_OPERATOR_DROP);
					} else {
						object.put("magicMsg", magicMessage
								.getMessage("magic.error.haslittlenum"));
					}
				} else {
					object.put("magicMsg", magicMessage
							.getMessage("magic.error.hasnomagic"));
				}

			}
			object.put("operator", operator);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}


	@RequestMapping("/magic/getmagicpage.jspx")
	public String getmagicpage(Integer pid, Integer ppid, String mid,
			String username, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		Boolean permiss = true;
		String magicMsg = "";
		BbsPost post = postMng.findById(ppid);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if (!magicConfig.getMagicSwitch()) {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MAGIC, TPL_NO_OPEN);
		}
		if (magicConfig.getMagicSwitch()) {
			MagicMessage magicMessage = MagicMessage.create(request);

			int permission = hasPermission(magic, user, ppid);
			if (permission == 1) {
				// 允许使用
				if (StringUtils.isNotBlank(mid)) {
					magicMsg = magicMessage.getMessage("magic." + mid);
				}
			} else if (permission == 0) {
				// 不允许使用
				magicMsg = magicMessage.getMessage("magic.use.hasnopermission");
				permiss = false;
			} else if (permission == -1) {
				// 不允许使用
				magicMsg = magicMessage
						.getMessage("magic.use.hasnobeusedpermission");
				permiss = false;
			}
		}
		model.addAttribute("magicName", magic.getName());
		model.addAttribute("pid", pid);
		model.addAttribute("ppid", ppid);
		model.addAttribute("mid", mid);
		if (username != null && StringUtils.isNotBlank(username)) {
			model.addAttribute("username", username);
		}
		model.addAttribute("magicswitch", magicConfig.getMagicSwitch());
		model.addAttribute("magicMsg", magicMsg);
		model.addAttribute("permiss", permiss);
		model.addAttribute("identifier", magic.getIdentifier());
		model.addAttribute("topicurl", post.getTopic().getUrl());
		model.addAttribute("post", post);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MAGIC, TPL_MAGIC_SHOW);
	}

	/**
	 * 
	 * @param pid
	 * @param mid
	 * @param color
	 * @param userid
	 * @param request
	 * @param response
	 * @param model
	 *            使用道具
	 */
	@RequestMapping("/magic/usemagic.jspx")
	public void usemagic(Integer pid, String mid, String color, Integer userid,
			String name, Integer ppid, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		CmsSite site = CmsUtils.getSite(request);
		JSONObject object = new JSONObject();
		BbsMagicConfig magicConfig = magicConfigMng.findById(site.getId());
		Boolean pass;
		BbsCommonMagic magic = magicMng.findByIdentifier(mid);
		if (user == null) {
			pass = false;
		} else {
			pass = true;
		}
		try {
			object.put("pass", pass);
			object.put("pid", pid);
			object.put("magicswitch", magicConfig.getMagicSwitch());
			MagicMessage magicMessage = MagicMessage.create(request);
			String magicMsg = "";
			String useMsg = "";
			String username = "";
			String ip = "";
			String money = "";
			String credit = "";
			BbsMemberMagic user_magic;
			if (StringUtils.isNotBlank(name)) {
				BbsUser usertemp;
				usertemp = userMng.findByUsername(name);
				if (usertemp != null) {
					userid = usertemp.getId();
				}
			}
			if (magicConfig.getMagicSwitch()) {
				if (StringUtils.isNotBlank(mid)) {
					user_magic = user.getMemberMagic(mid);
					int permission = hasPermission(magic, user, ppid);
					if (permission == 1) {
						// 允许使用
						if (StringUtils.isNotBlank(name)) {
							if (mid.equals(MagicConstants.MAGIC_SHOWIP)
									|| mid
											.equals(MagicConstants.MAGIC_CHECKONLINE)) {
								if (userMng.usernameNotExist(name)) {
									magicMsg = magicMessage
											.getMessage("magic.finduser.error");
								} else {
									// 用户没有该道具，防止直接输入地址刷道具
									if (user_magic == null
											|| user_magic.getNum() <= 0) {
										magicMsg = magicMessage
												.getMessage("magic.error.hasnomagic");
									} else {
										magicMsg = magicMessage
												.getMessage("magic." + mid
														+ ".success");
										useMsg = topicMng.useMagic(request,
												site.getId(), pid, mid, user
														.getId(), RequestUtils
														.getIpAddr(request),
												color, userid);
										saveMagicLog(
												magic,
												user,
												1,
												MagicConstants.MAGIC_OPERATOR_USE);
									}
								}
							}
						} else {
							if (mid.equals(MagicConstants.MAGIC_SHOWIP)
									|| mid
											.equals(MagicConstants.MAGIC_CHECKONLINE)) {
								magicMsg = magicMessage
										.getMessage("magic.nousername.error");
							} else {
								if (user_magic == null
										|| user_magic.getNum() <= 0) {
									magicMsg = magicMessage
											.getMessage("magic.error.hasnomagic");
								} else {
									magicMsg = magicMessage.getMessage("magic."
											+ mid + ".success");
									useMsg = topicMng.useMagic(request, site
											.getId(), pid, mid, user.getId(),
											RequestUtils.getIpAddr(request),
											color, userid);
									saveMagicLog(magic, user, 1,
											MagicConstants.MAGIC_OPERATOR_USE);
								}
							}
						}
					} else if (permission == 0) {
						// 不允许使用
						magicMsg = magicMessage
								.getMessage("magic.use.hasnopermission");
					} else if (permission == -1) {
						// 不允许使用
						magicMsg = magicMessage
								.getMessage("magic.use.hasnobeusedpermission");
					}
				}
				if (hasNoPermission(useMsg)) {
					object.put("magicMsg", magicMessage.getMessage(useMsg));
				} else {
					if (useMsg.contains(MagicConstants.MAGIC_NAMEPOST_SUCCESS)) {
						// 从manager返回的时候拼接了常量，这里去掉常量取得用户名 匿名
						username = useMsg
								.split(MagicConstants.MAGIC_NAMEPOST_SUCCESS)[1];
						object.put("magicMsg", magicMessage
								.getMessage(
										MagicConstants.MAGIC_NAMEPOST_SUCCESS,
										username));
					} else if (useMsg
							.contains(MagicConstants.MAGIC_CHECKONLINE_ONLINE)) {
						// 在线
						username = useMsg
								.split(MagicConstants.MAGIC_CHECKONLINE_ONLINE)[1];
						object.put("magicMsg", magicMessage.getMessage(
								MagicConstants.MAGIC_CHECKONLINE_ONLINE,
								username));
					} else if (useMsg
							.contains(MagicConstants.MAGIC_CHECKONLINE_OFFLINE)) {
						// 离线
						username = useMsg
								.split(MagicConstants.MAGIC_CHECKONLINE_OFFLINE)[1];
						object.put("magicMsg", magicMessage.getMessage(
								MagicConstants.MAGIC_CHECKONLINE_OFFLINE,
								username));
					} else if (useMsg
							.contains(MagicConstants.MAGIC_SHOWIP_SUCCESS)) {
						// 窥视
						username = useMsg
								.split(MagicConstants.MAGIC_SHOWIP_SUCCESS)[0];
						ip = useMsg.split(MagicConstants.MAGIC_SHOWIP_SUCCESS)[1];
						object.put("magicMsg", magicMessage.getMessage(
								MagicConstants.MAGIC_SHOWIP_SUCCESS, username,
								ip));
					} else if (useMsg
							.contains(MagicConstants.MAGIC_MONEY_SUCCESS)) {
						// 金钱卡
						credit = useMsg
								.split(MagicConstants.MAGIC_MONEY_SUCCESS)[0];
						money = useMsg
								.split(MagicConstants.MAGIC_MONEY_SUCCESS)[1];
						object.put("magicMsg", magicMessage.getMessage(
								MagicConstants.MAGIC_MONEY_SUCCESS, credit,
								money));
					} else {
						object.put("magicMsg", magicMsg);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	private void saveMagicLog(BbsCommonMagic magic, BbsUser user,
			Integer buynum, Byte Operate) {
		BbsMagicLog log = new BbsMagicLog();
		log.setLogTime(new Date());
		log.setMagic(magic);
		log.setOperator(Operate);
		log.setPrice(magic.getPrice());
		log.setNum(buynum);
		log.setUser(user);
		magicLogMng.save(log);
	}

	private Boolean hasNoPermission(String messageCode) {
		if (messageCode.equals(MagicConstants.MAGIC_OPEN_ERROR_NOIN_MODERATORS)
				|| messageCode.equals(MagicConstants.MAGIC_SOFEED_ERROR)) {
			return true;
		}
		return false;
	}

	private int hasPermission(BbsCommonMagic magic, BbsUser user, Integer pid) {
		Set<BbsUserGroup> userGroups = magic.getUseGroups();
		Set<BbsUserGroup> toUserGroups = magic.getToUseGroups();
		BbsPost post = null;
		BbsUserGroup postCreaterGroup = null;
		if (pid != null) {
			post = postMng.findById(pid);
			post.getCreater().getGroup();
		}
		// 道具没有勾选使用组，默认全部组用户可以使用
		if (userGroups == null || userGroups.size() == 0) {
			// 被使用为空则有权限使用该道具
			if (toUserGroups == null || toUserGroups.size() == 0) {
				return 1;
			} else {
				// 道具被的被使用组包含帖子的创建者所属组,pid存在则需要检查帖子的创建者组
				if (pid != null) {
					if (toUserGroups.contains(postCreaterGroup)) {
						return 1;
					} else {
						// 不允许被使用
						return -1;
					}
				} else {
					return 1;
				}

			}
		} else {
			// 道具使用组有勾选使用组，需要判断被使用组权限
			if (userGroups.contains(user.getGroup())) {
				if (toUserGroups == null || toUserGroups.size() == 0) {
					return 1;
				} else {
					// 道具被的被使用组包含帖子的创建者所属组
					if (pid != null) {
						if (toUserGroups.contains(postCreaterGroup)) {
							return 1;
						} else {
							// 不允许被使用
							return -1;
						}
					} else {
						return 1;
					}
				}
			} else {
				// 不允许使用
				return 0;
			}
		}
	}

	@Autowired
	private BbsCommonMagicMng magicMng;
	@Autowired
	private BbsTopicMng topicMng;
	@Autowired
	private BbsMagicConfigMng magicConfigMng;
	@Autowired
	private BbsUserMng userMng;
	@Autowired
	private BbsMagicLogMng magicLogMng;
	@Autowired
	private BbsPostMng postMng;
}
