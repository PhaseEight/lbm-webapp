package com.logbookmanager.web.authentication;

import com.logbookmanager.util.ResourcesUtil;
import com.logbookmanager.web.faces.FacesContextBroker;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class IdleMonitorController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Default logout monitor time is 1000 to make sure that the user sets a
     * valid timeout.
     */
    private long permittedIdleTimeMillis = 1000;
    private long autologoutIdleTimeMillis = 60000;

    public IdleMonitorController(int permittedIdleTimeSeconds, int autologoutIdleTimeSeconds) {
        setPermittedIdleTimeMillis(permittedIdleTimeSeconds * 1000);
        setAutologoutIdleTimeMillis(autologoutIdleTimeSeconds * 1000);
    }

    public void welcomeListener() {
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, ResourcesUtil.getInstance().getProperty(
                        "idle.timeout.welcome.back", "Welcome Back"), ResourcesUtil.getInstance().getProperty(
                        "idle.timeout.continue", "Continue")));
    }

    public void logoutListener() throws Exception {
        ExternalContext ec = new FacesContextBroker().getContext().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        String baseUrl = ec.getApplicationContextPath() + request.getServletPath();
        ec.redirect(baseUrl + "/logout");
    }

    public long getPermittedIdleTimeMillis() {
        return permittedIdleTimeMillis;
    }

    public void setPermittedIdleTimeMillis(long permittedIdleTimeMillis) {
        this.permittedIdleTimeMillis = permittedIdleTimeMillis;
    }

    public long getPermittedIdleTimeSeconds() {
        return permittedIdleTimeMillis / 1000;
    }

    public long getAutologoutIdleTimeMillis() {
        return autologoutIdleTimeMillis;
    }

    public void setAutologoutIdleTimeMillis(long autologoutIdleTimeMillis) {
        this.autologoutIdleTimeMillis = autologoutIdleTimeMillis;
    }

    public long getAutologoutIdleTimeSeconds() {
        return autologoutIdleTimeMillis / 1000;
    }
}
