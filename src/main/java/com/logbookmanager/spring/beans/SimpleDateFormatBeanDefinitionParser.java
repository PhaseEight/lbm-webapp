package com.logbookmanager.spring.beans;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class SimpleDateFormatBeanDefinitionParser extends
		AbstractSingleBeanDefinitionParser {
	protected Class<SimpleDateFormat> getBeanClass(Element element) {
		return SimpleDateFormat.class;
	}

	protected void doParse(Element element, BeanDefinitionBuilder bean) {
		// this will never be null since the schema explicitly requires that a
		// value be supplied
		if (element == null || !element.hasAttributes()
				|| !element.hasAttribute("pattern")) {
			throw new IllegalArgumentException(
					"Element can not be null and must have a pattern attribute");
		}
		String pattern = element.getAttribute("pattern");
		bean.addConstructorArgValue(pattern);
		// this however is an optional property
		String lenient = element.getAttribute("lenient");
		if (StringUtils.hasText(lenient)) {
			bean.addPropertyValue("lenient", Boolean.valueOf(lenient));
		}
	}
}
