package com.logbookmanager.domain.support;

import com.logbookmanager.util.StringValidator;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public class EmailAddress extends ValueObjectSupport<EmailAddress> {

    private static final long serialVersionUID = 1L;

    @Email(regexp = StringValidator.EMAIL_PATTERN, message = "{Email.com.logbookmanager.domain.support.EmailAddress.address.message}")
    private String address;

    EmailAddress() {
    }

    public EmailAddress(String address) {
        Validate.notEmpty(address);
        setAddress(address);
    }

    public @NotEmpty
    String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, false).toString();
    }

}
