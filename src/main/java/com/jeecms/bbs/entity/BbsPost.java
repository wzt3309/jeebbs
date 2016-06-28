package com.jeecms.bbs.entity;

import static com.jeecms.bbs.web.FrontUtils.replaceSensitivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.jeecms.bbs.entity.base.BaseBbsPost;
import com.jeecms.bbs.web.StrUtils;
import com.jeecms.core.bbcode.BbcodeHandler;

public class BbsPost extends BaseBbsPost {
	private static final long serialVersionUID = 1L;
	/**
	 * 正常状态
	 */
	public static final short NORMAL = 0;
	/**
	 * 屏蔽状态
	 */
	public static final short SHIELD = -1;
	/**
	 * 帖子的锚点
	 */
	public static final String ANCHOR = "#pid";

	/**
	 * URL地址
	 * 
	 * @return
	 */
	public String getUrl() {
		Integer index = getIndexCount();
		if (index == 1) {
			// 第一个帖子和主题url相同
			return getTopic().getUrl();
		} else {
			StringBuilder buff = getTopic().getUrlPerfix();
			Integer pageSize = getConfig().getPostCountPerPage();
			int pageNo = (index - 1) / pageSize;
			if (pageNo > 0) {
				buff.append('_').append(pageNo + 1);
			}
			buff.append(getSite().getDynamicSuffix()).append(ANCHOR).append(
					getId());
			return buff.toString();
		}
	}

	public String getRedirectUrl() {
		String path = getTopic().getForum().getPath();
		String url = "/" + path + "/" + getTopic().getId()
				+ getSite().getDynamicSuffix();
		return url;
	}

	/**
	 * 是否楼主
	 * 
	 * @return
	 */
	public boolean isFirst() {
		return getTopic().getFirstPost().equals(this);
	}

	public boolean isShield() {
		if (getStatus() == SHIELD) {
			return true;
		}
		return false;
	}

	/**
	 * 获得标题
	 * 
	 * @return
	 */
	public String getTitle() {
		BbsPostText text = getPostText();
		if (text == null) {
			return null;
		} else {
			return replaceSensitivity(text.getContent());
		}
	}

	/**
	 * 获得内容
	 * 
	 * @return
	 */
	public String getContent() {
		BbsPostText text = getPostText();
		if (text == null) {
			return null;
		} else {
			return replaceSensitivity(text.getContent());
		}
	}
	
	/**
	 * 获得转换后的内容
	 * 
	 * @return
	 */
	public String getContentHtml() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()
							+ "[/attachment]";
					if (t.getPicture()) {
						String newcontent = "[img]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/img]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					} else {
						String newcontent = "[url=" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "]"
								+ t.getFileName() + "[/url]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					}
				}
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							"[quote]" + str + "[/quote]");
				}
			}
			return BbcodeHandler.toHtml(s);
		}
	}

	/**
	 * 获取引用内容
	 * 
	 * @return
	 */
	public String getQuoteContent() {
		String s = getContent();
		if (getHidden()) {
			List<String> list = getHideContent(s);
			if (list != null) {
				for (String str : list) {
					String newcontent = "[color=red]此处是被引用的隐藏帖[/color]";
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							newcontent);
				}
			}
		}
		if (haveQuote(s)) {
			s = s.substring(s.lastIndexOf("[/quote]") + 8);
		}
		return s;
	}

	/**
	 * 分离隐藏内容
	 * 
	 * @param s
	 * 
	 * @return
	 */
	private List<String> getHideContent(String content) {
		String ems = "\\[hide\\]([\\s\\S]*?)\\[/hide\\]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			String url = matcher.group(1);
			list.add(url);
		}
		return list;
	}

	private boolean haveQuote(String content) {
		String ems = "\\[quote]([\\s\\S]*)\\[/quote\\]";
		Matcher matcher = Pattern.compile(ems).matcher(content);
		while (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 未回复的隐藏内容
	 * 
	 * @return
	 */
	public String getHideContent() {
		String s = getContent();
		if (StringUtils.isBlank(s)) {
			return "";
		} else {
			if (getAffix()) {
				Set<Attachment> att = getAttachments();
				for (Attachment t : att) {
					String oldcontent = "[attachment]" + t.getId()
							+ "[/attachment]";
					if (t.getPicture()) {
						String newcontent = "[img]" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "[/img]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					} else {
						String newcontent = "[url=" + getSite().getUrl()
								+ t.getFilePath().substring(1) + "]"
								+ t.getFileName() + "[/url]";
						s = StrUtils.replace(s, oldcontent, newcontent);
					}
				}
			}
			if (getHidden()) {
				List<String> list = getHideContent(s);
				for (String str : list) {
					s = StrUtils.replace(s, "[hide]" + str + "[/hide]",
							"[quote]这是隐藏内容.需要回复才能浏览[/quote]");
				}
			}
			return BbcodeHandler.toHtml(s);
		}
	}

	/**
	 * 覆盖父类同名方法。增加反向引用
	 */
	public void setPostText(BbsPostText text) {
		if (text != null) {
			text.setPost(this);
		}
		super.setPostText(text);
	}
	
	/* [CONSTRUCTOR MARKER BEGIN] */
	public BbsPost () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsPost (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsPost (
		java.lang.Integer id,
		com.jeecms.core.entity.CmsSite site,
		com.jeecms.bbs.entity.BbsConfig config,
		com.jeecms.bbs.entity.BbsTopic topic,
		com.jeecms.bbs.entity.BbsUser creater,
		java.lang.String title,
		java.util.Date createTime,
		java.lang.String posterIp,
		java.lang.Integer editCount,
		java.lang.Integer indexCount,
		java.lang.Short status,
		java.lang.Boolean affix,
		java.lang.Boolean hidden) {

		super (
			id,
			site,
			config,
			topic,
			creater,
			title,
			createTime,
			posterIp,
			editCount,
			indexCount,
			status,
			affix,
			hidden);
	}

	/* [CONSTRUCTOR MARKER END] */

}