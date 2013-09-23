package com.logbookmanager.spring.beans;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class DateNamespaceHandler extends NamespaceHandlerSupport {
	public void init() {
		registerBeanDefinitionParser("dateformat",
				new SimpleDateFormatBeanDefinitionParser());

		registerBeanDefinitionParser("vdateformat",
				new ValidateableSimpleDateFormatBeanDefinitionParser());

	}
}