package com.logbookmanager.web.util;

import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

/**
 * Use this bean to execute JavaScript on client side.
 */
public class PrimeFacesUtil {

	/**
	 * Tells the client to update the search results region with the passed
	 * text.
	 */
	public static void updateSearchResultsRegion(String text) {
		if (RequestContext.getCurrentInstance() != null) {
			RequestContext.getCurrentInstance().execute(
					"APP.updateSearchResultsRegion(\"" + text + "\")");
		}
	}

	public static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	public static void forceClose() {
		RequestContext.getCurrentInstance().execute("APP.menu.forceClose()");
	}
}
