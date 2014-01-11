package com.logbookmanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * ResourcesUtil allows you to retrieve localized resources for the locale
 * present in the current thread of execution. It can be used from both Spring
 * beans (simple dependency injection) and from non spring beans. In the later
 * case, you obtain the reference thanks to the static method
 * ResourcesUtil.getInstance()
 */
public class ResourcesUtil {

	private static ResourcesUtil instance;
	private MessageSource messageSource;

	@Autowired
	public ResourcesUtil(@Qualifier("messageSource") MessageSource messageSource) {
		this.messageSource = messageSource;
		instance = this;
	}

	/**
	 * Call it from non spring aware code to obtain a reference to the
	 * ResourcesUtil singleton instance.
	 */
	public static ResourcesUtil getInstance() {
		return instance;
	}

	/**
	 * Return the MessageSource that backs this ResourcesUtil.
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * Return the property value for the contextual locale. If no value is
	 * found, the passed key is returned.
	 */
	public String getProperty(String key, Object... args) {
		if (key == null) {
			return "";
		}

		return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
	}

	/**
	 * Return the property value for the contextual locale. If no value is
	 * found, the passed key is returned.
	 */
	public String getProperty(String key, String defaultMessage, Object... args) {
		if (key == null) {
			return "";
		}

		return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
	}

	/**
	 * Same as getProperty() but use the count to choose the proper key. Used
	 * when the message varies depending on the context. For example: 'there is
	 * no result' vs 'there is one result' vs 'there are n results'
	 * 
	 * @param key
	 *            the base key
	 */
	public String getPluralableProperty(String key, int count) {
		if (key == null) {
			return "";
		}

		switch (count) {
		case 0:
			return getProperty(key + "_0");
		case 1:
			return getProperty(key + "_1");
		default:
			return getProperty(key + "_n", count);
		}
	}
}