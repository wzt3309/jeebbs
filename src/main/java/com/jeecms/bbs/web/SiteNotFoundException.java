package com.jeecms.bbs.web;

@SuppressWarnings("serial")
public class SiteNotFoundException extends RuntimeException {
	private String domain;

	public SiteNotFoundException(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return this.domain;
	}
}
