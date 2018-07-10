package com.logbookmanager.domain.support;

import com.logbookmanager.util.StringValidator;
import org.apache.commons.lang3.Validate;

public class Title extends ValueObjectSupport<Title> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String code;

    public Title(String code, String name) {
        Validate.isTrue(StringValidator.isUnderscoreAlphaNumericWithSpaces(code, 5));
        Validate.isTrue(StringValidator.isUnderscoreAlphaNumericWithSpaces(name, 5));
        this.setName(name);
        this.setCode(code);
    }

    /* required by Hibernate */
    Title() {
        this.name = null;
        this.code = null;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

}
