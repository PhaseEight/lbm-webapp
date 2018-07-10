package com.logbookmanager.web.faces;

import javax.faces.context.FacesContext;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class FacesContextBroker implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final FacesContextBroker INSTANCE = new FacesContextBroker();

    public FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}