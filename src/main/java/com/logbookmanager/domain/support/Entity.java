package com.logbookmanager.domain.support;

import java.io.Serializable;

public interface Entity<T, ID extends Serializable> {

	/**
	 * @return the business key
	 */
	ID getId();

	/**
	 * Sets the primary key
	 */
	void setId(ID id);

	boolean isIdSet();

	public boolean sameIdentityAs(T other);
	
}