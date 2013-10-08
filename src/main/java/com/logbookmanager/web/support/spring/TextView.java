package com.logbookmanager.web.support.spring;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class TextView extends AbstractUrlBasedView {
	private String body;

	protected TextView(String url) {
		super(url);
		// TODO: Load the body from the File using views
	}

	public TextView() {
		super();
	}

	public void setResponseBody(String body) {
		this.body = body;
	}

	public String getContentType() {
		return "text/plain; charset=UTF-8";
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType(getContentType());
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();

		if (session != null) {
			Enumeration<String> sessionAttributeNames = session.getAttributeNames();
			writer.write("<<Session Attributes>>");
			while (sessionAttributeNames.hasMoreElements()) {
				String name = sessionAttributeNames.nextElement();
				writer.write("attributeName:[");
				writer.write(session.getAttribute(name).toString());
				writer.write("]");
			}
			writer.write("<<Session Attributes>>");
		}
		writer.write("\n\n\n\n");
		Enumeration<String> requestAttributeNames = request.getAttributeNames();
		writer.write("<<Request Attributes>>");
		while (requestAttributeNames.hasMoreElements()) {
			String name = requestAttributeNames.nextElement();
			writer.write("attributeName:[");
			writer.write(request.getAttribute(name).toString());
			writer.write("]");
		}
		writer.write("<<Request Attributes>>");

		if (body != null) {
			writer.write("<<Body>>");
			writer.write(body);
			writer.write("<</Body>>");
		}

	}
}