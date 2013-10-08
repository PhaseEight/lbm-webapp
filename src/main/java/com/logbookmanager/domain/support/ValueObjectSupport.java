package com.logbookmanager.domain.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.logbookmanager.support.BaseObject;

public class ValueObjectSupport<T> extends BaseObject<T> implements ValueObject<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, false);
	}

	@Override
	public boolean sameValueAs(T other) {
		return EqualsBuilder.reflectionEquals(this, other, false);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof ValueObject)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		T other = (T) object;

		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(1, 3, this, false);
	}

}
