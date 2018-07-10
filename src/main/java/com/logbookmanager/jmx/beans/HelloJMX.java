package com.logbookmanager.jmx.beans;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "Spring-beans:name=HelloSpringJMX")
public class HelloJMX {

    String message = null;

    @ManagedAttribute(description = "get the message")
    public String getMessage() {

        return this.message;
    }

    @ManagedAttribute(description = "set the message")
    public void setMessage(String Message) {

        this.message = Message;

    }
}