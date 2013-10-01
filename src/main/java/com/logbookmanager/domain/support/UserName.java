package com.logbookmanager.domain.support;

import org.apache.commons.lang3.Validate;

import com.logbookmanager.util.StringValidator;

public class UserName extends ValueObjectSupport<UserName> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	
	/*required by Hibernate */
	UserName(){
		this.value = null;
	}

	public UserName(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		Validate.isTrue(StringValidator.isUnderscoreAlphaNumericWithoutSpaces(value, 8),"error.username.invalid");
		this.value = value;
	}

}
