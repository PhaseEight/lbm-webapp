package com.logbookmanager.domain.support;

import org.apache.commons.lang3.Validate;

import com.logbookmanager.util.StringValidator;

public class Title extends ValueObjectSupport<Title> {

	private String name;
	private String code;

	public Title(String code, String name) {
		Validate.isTrue(StringValidator.isUnderscoreAlphaNumericWithSpaces(code, 5));
		Validate.isTrue(StringValidator.isUnderscoreAlphaNumericWithSpaces(name, 5));
		this.setName(name);
		this.setCode(code);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setCode(String code) {
		this.code = code;
	}

	/* required by Hibernate */
	Title() {
		this.name = null;
		this.code = null;
	}

}
