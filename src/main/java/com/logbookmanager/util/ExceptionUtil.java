package com.logbookmanager.util;

/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//TODO: use this to create an advice for ObjectDeleted type exceptions
public class ExceptionUtil {

	protected static final Logger log = LoggerFactory.getLogger(ExceptionUtil.class.getName());

	public static Throwable retrieveCause(Throwable t, Class<?>[] searchForAny)
			throws Exception {
		// protect from a null
		if (t == null) {
			return null;
		}
		// first check if the throwable is one of the classes to searchFor
		for (Class<?> searchFor : searchForAny) {
			if (t.getClass().equals(searchFor)) {
				log.info("found cause: " + searchFor);
				return t;
			}
		}

		// now check if any of the causes are one of the classes to searchFor
		while (t.getCause() != null) {
			for (Class<?> searchFor : searchForAny) {
				if (searchFor.equals(t.getCause())) {
					return t.getCause();
				}
			}
		}

		return null;
	}

	public static Throwable retrieveCause(Throwable t, Class<?> searchFor)
			throws Exception {
		Throwable e2 = t.getCause();
		while (e2 != null) {
			if (e2.getClass().equals(searchFor)) {
				log.info("found cause: " + searchFor);
				return e2;
			}
			e2 = e2.getCause();
		}
		return null;
	}

}
