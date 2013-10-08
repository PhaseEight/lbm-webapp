package com.logbookmanager.web.util;

/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

/**
 * RequestUtil utility class Good ol' copy-n-paste from <a
 * href="http://www.javaworld.com/javaworld/jw-02-2002/ssl/utilityclass.txt">
 * http://www.javaworld.com/javaworld/jw-02-2002/ssl/utilityclass.txt</a>
 */
public class RequestUtil {

	private transient static Logger log = LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * Creates query String from request body parameters
	 */
	public static String getRequestParameters(HttpServletRequest aRequest) {
		// set the ALGORIGTHM as defined for the application
		// ALGORITHM = (String) aRequest.getAttribute(Constants.ENC_ALGORITHM);
		Map<?, ?> m = aRequest.getParameterMap();

		return createQueryStringFromMap(m, "&").toString();
	}

	/**
	 * Appends new key and value pair to query string
	 * 
	 * @param key
	 *            parameter name
	 * @param value
	 *            value of parameter
	 * @param queryString
	 *            existing query string
	 * @param ampersand
	 *            string to use for ampersand (e.g. "&" or "&amp;")
	 * 
	 * @return query string (with no leading "?")
	 */
	private static StringBuffer append(Object key, Object value, StringBuffer queryString, String ampersand) {
		if (queryString.length() > 0) {
			queryString.append(ampersand);
		}

		try {
			queryString.append(URLEncoder.encode(key.toString(), "UTF-8"));
			queryString.append("=");
			queryString.append(URLEncoder.encode(value.toString(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// won't happen since we're hard-coding UTF-8
		}
		return queryString;
	}

	/**
	 * Builds a query string from a given map of parameters
	 * 
	 * @param m
	 *            A map of parameters
	 * @param ampersand
	 *            String to use for ampersands (e.g. "&" or "&amp;" )
	 * 
	 * @return query string (with no leading "?")
	 */
	public static StringBuffer createQueryStringFromMap(Map<?, ?> m, String ampersand) {
		StringBuffer aReturn = new StringBuffer("");
		Set<?> anEntryS = m.entrySet();
		Iterator<?> aEntryI = anEntryS.iterator();

		while (aEntryI.hasNext()) {
			Map.Entry<?, ?> aEntry = (Map.Entry<?, ?>) aEntryI.next();
			Object o = aEntry.getValue();

			if (o == null) {
				append((String) aEntry.getKey(), "", aReturn, ampersand);
			} else if (o instanceof String) {
				append(aEntry.getKey(), o, aReturn, ampersand);
			} else if (o instanceof String[]) {
				String[] aValues = (String[]) o;

				for (int i = 0; i < aValues.length; i++) {
					append(aEntry.getKey(), aValues[i], aReturn, ampersand);
				}
			} else {
				append(aEntry.getKey(), o, aReturn, ampersand);
			}
		}

		return aReturn;
	}

	/**
	 * Convenience method to set a cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String path) {
		if (log.isDebugEnabled()) {
			log.debug("Setting cookie '" + name + "' on path '" + path + "'");
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath(path);
		cookie.setMaxAge(3600 * 24 * 30); // 30 days

		response.addCookie(cookie);
	}

	/**
	 * Convenience method to get a cookie by name
	 * 
	 * @param request
	 *            the current request
	 * @param name
	 *            the name of the cookie to find
	 * 
	 * @return the cookie (if found), null if not found
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = null;
		if (request instanceof SecurityContextHolderAwareRequestWrapper) {
			SecurityContextHolderAwareRequestWrapper req = (SecurityContextHolderAwareRequestWrapper) request;
			cookies = req.getCookies();
		} else {
			cookies = request.getCookies();
		}
		Cookie returnCookie = null;

		if (cookies == null) {
			return returnCookie;
		}

		for (int i = 0; i < cookies.length; i++) {
			Cookie thisCookie = cookies[i];

			if (thisCookie.getName().equals(name)) {
				// cookies with no value do me no good!
				if (!thisCookie.getValue().equals("")) {
					returnCookie = thisCookie;

					break;
				}
			}
		}

		return returnCookie;
	}

	/**
	 * Convenience method for deleting a cookie by name
	 * 
	 * @param response
	 *            the current web response
	 * @param cookie
	 *            the cookie to delete
	 * @param path
	 *            the path on which the cookie was set (i.e. /ulm)
	 */
	public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) {
		if (cookie != null) {
			// Delete the cookie by setting its maximum age to zero
			cookie.setMaxAge(0);
			cookie.setPath(path);
			response.addCookie(cookie);
		}
	}

	/**
	 * Convenience method to get the application's URL based on request
	 * variables.
	 */
	public static String getAppURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		int port = request.getServerPort();
		if (port < 0) {
			port = 80; // Work around java.net.URL bug
		}
		String scheme = request.getScheme();
		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());
		if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(request.getContextPath());
		return url.toString();
	}
}
