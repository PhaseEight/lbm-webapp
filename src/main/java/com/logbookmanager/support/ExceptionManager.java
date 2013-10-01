package com.logbookmanager.support;

import java.util.Map;

public interface ExceptionManager {

	public abstract Map<String, String> extractMessages(Exception e, Exception root);

	public abstract String formatStackTrace(StackTraceElement[] elements);

}