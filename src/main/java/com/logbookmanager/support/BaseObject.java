package com.logbookmanager.support;

import java.io.Serializable;

/**
 * Base class for Model objects. Child objects must implement toString(),
 * equals(), hashCode();
 *
 * <p>
 * <a href="BaseObject.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public abstract class BaseObject<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

}
