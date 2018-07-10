package com.logbookmanager.domain.support;

import org.apache.commons.lang3.Validate;

import java.net.MalformedURLException;
import java.net.URL;

public class WebSite {

    URL url;

    WebSite() {
    }

    @org.hibernate.validator.constraints.URL
    public WebSite(String url) throws MalformedURLException {
        this(new URL(url));
    }

    public WebSite(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @org.hibernate.validator.constraints.URL
    public void setUrl(String url) throws MalformedURLException {
        Validate.notBlank(url);
        setUrl(new URL(url));
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
