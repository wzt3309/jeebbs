package com.jeecms.common.security.rememberme;

/**
 * @author Luke Taylor
 * @version $Id: CookieTheftException.java,v 1.1 2011/12/26 03:47:58 Administrator Exp $
 */
@SuppressWarnings("serial")
public class CookieTheftException extends RememberMeAuthenticationException {
	public CookieTheftException(String message) {
		super(message);
	}
}
