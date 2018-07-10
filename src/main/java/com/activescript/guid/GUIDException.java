/*
 * GUIDException.java
 *
 * Created on 29 August 2001, 09:44
 */

package com.activescript.guid;

/**
 * @author WoodcockS
 * @version 1.0
 */
public class GUIDException extends java.lang.Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new <code>GUIDException</code> without detail mailMessage.
     */
    public GUIDException() {
        // Default
    }

    /**
     * Constructs an <code>GUIDException</code> with the specified detail
     * mailMessage.
     *
     * @param msg the detail mailMessage.
     */
    public GUIDException(String msg) {
        super(msg);
    }
}
