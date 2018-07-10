package com.logbookmanager.domain.support;

import org.apache.commons.lang3.Validate;

public class Password extends ValueObjectSupport<Password> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String value;
    private String comparison;

    /* required by Hibernate */
    Password() {
    }

    public Password(String value, String comparison) {
        Validate.notBlank(value, "error.password.blank");
        Validate.notBlank(comparison, "error.password.comparison.blank");
        setValue(value);
        setComparison(comparison);
    }

    protected String getValue() {
        return value;
    }

    protected void setValue(String value) {
        this.value = value;
    }

    protected String getComparison() {
        return comparison;
    }

    protected void setComparison(String comparison) {
        this.comparison = comparison;
    }

}