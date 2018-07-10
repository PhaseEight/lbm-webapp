package com.logbookmanager.support;

import org.springframework.stereotype.Service;

import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.Map;

@Service("exceptionManager")
public class ExceptionManagerImpl implements ExceptionManager {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.logbookmanager.support.ExceptionManager#extractMessages(java.lang
     * .Exception, java.lang.Exception)
     */
    @Override
    public Map<String, String> extractMessages(Exception e, Exception root) {
        Map<String, String> out = new HashMap<String, String>();

        out.put("exc_message", e.getClass().toString() + ": " + e.getMessage());
        out.put("exc_details", formatStackTrace(e.getStackTrace()));
        out.put("root_message", root.getClass().toString() + ": " + root.getMessage());
        out.put("root_details", formatStackTrace(root.getStackTrace()));
        if (root instanceof BatchUpdateException) {
            out.put("batch_message", ((BatchUpdateException) root).getNextException().getClass().toString() + ": "
                    + ((BatchUpdateException) root).getNextException().getMessage());
        }

        return out;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.logbookmanager.support.ExceptionManager#formatStackTrace(java.lang
     * .StackTraceElement[])
     */
    @Override
    public String formatStackTrace(StackTraceElement[] elements) {
        String out = "";
        for (StackTraceElement ste : elements)
            out += ste.toString() + "<br/>";
        return out;
    }
}