package com.logbookmanager.spring.beans;

import com.logbookmanager.date.ValidateableSimpleDateFormat;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class ValidateableSimpleDateFormatBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    protected Class<ValidateableSimpleDateFormat> getBeanClass(Element element) {
        return ValidateableSimpleDateFormat.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        // this will never be null since the schema explicitly requires that a
        // value be supplied
        if (element == null || !element.hasAttributes() || !element.hasAttribute("pattern")) {
            throw new IllegalArgumentException("Element can not be null and must have a pattern attribute");
        }
        String pattern = element.getAttribute("pattern");
        bean.addConstructorArgValue(pattern);

        // this will never be null since the schema explicitly requires that a
        // value be supplied
        String validationPattern = element.getAttribute("validation");
        bean.addConstructorArgValue(validationPattern);

        // this however is an optional property
        String validate = element.getAttribute("validate");
        if (StringUtils.hasText(validate)) {
            bean.addConstructorArgValue(Boolean.valueOf(validate));
        }

        // this however is an optional property
        String lenient = element.getAttribute("lenient");
        if (StringUtils.hasText(lenient)) {
            bean.addPropertyValue("lenient", Boolean.valueOf(lenient));
        }
    }
}
