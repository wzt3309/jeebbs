package com.jeecms.common.web.springmvc;

import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * magic信息
 * 
 * 可以通过MessageSource实现国际化。
 * 
 * @author liufang
 * 
 */
public abstract class MagicMessage {
	/**
	 * email正则表达式
	 */
	public static final Pattern EMAIL_PATTERN = Pattern
			.compile("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+$");
	/**
	 * username正则表达式
	 */
	public static final Pattern USERNAME_PATTERN = Pattern
			.compile("^[0-9a-zA-Z\\u4e00-\\u9fa5\\.\\-@_]+$");

	/**
	 * 通过HttpServletRequest创建MagicMessage
	 * 
	 * @param request
	 *            从request中获得MessageSource和Locale，如果存在的话。
	 */
	public MagicMessage(HttpServletRequest request) {
		WebApplicationContext webApplicationContext = RequestContextUtils
				.getWebApplicationContext(request);
		if (webApplicationContext != null) {
			LocaleResolver localeResolver = RequestContextUtils
					.getLocaleResolver(request);
			Locale locale;
			if (localeResolver != null) {
				locale = localeResolver.resolveLocale(request);
				this.messageSource = webApplicationContext;
				this.locale = locale;
			}
		}
	}

	public MagicMessage() {
	}

	/**
	 * 构造器
	 * 
	 * @param messageSource
	 * @param locale
	 */
	public MagicMessage(MessageSource messageSource, Locale locale) {
		this.messageSource = messageSource;
		this.locale = locale;
	}

	public String getMessage(String code, Object... args) {
		if (messageSource == null) {
			throw new IllegalStateException("MessageSource cannot be null.");
		}
		return messageSource.getMessage(code, args, locale);
	}

	private MessageSource messageSource;
	private Locale locale;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 获得本地化信息
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * 设置本地化信息
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
