package com.logbookmanager.domain.model.logbook;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.logbookmanager.domain.support.EntitySupport;

/**
 * LogbookUserLogbookDetailId 
 */
public class LogbookUserLogbookDetailId extends
		EntitySupport<LogbookUserLogbookDetailId, Long> implements
		java.io.Serializable {
	private static final long serialVersionUID = 912839123L;

	// Fields

	private LogbookUser logbookUser;

	private Detail detail;

	private String name;

	private String displayValue;

	// Constructors

	/** default constructor required by Hibernate */
	LogbookUserLogbookDetailId() {
	}

	/**
	 * 
	 */
	public LogbookUser getLogbookUser() {
		return this.logbookUser;
	}

	public void setLogbookUser(LogbookUser logbookUser) {
		this.logbookUser = logbookUser;
	}

	/**
	 * 
	 */
	public Detail getDetail() {
		return this.detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	/**
	 * 
	 */
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public String getDisplayValue() {
		return this.displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LogbookUserLogbookDetailId))
			return false;
		LogbookUserLogbookDetailId castOther = (LogbookUserLogbookDetailId) other;

		return (this.getLogbookUser() == castOther.getLogbookUser())
				|| (this.getLogbookUser() != null && castOther.getLogbookUser() != null && this
						.getLogbookUser().equals(castOther.getLogbookUser()))
				&& (this.getDetail() == castOther.getDetail())
				|| (this.getDetail() != null && castOther.getDetail() != null && this
						.getDetail().equals(castOther.getDetail()))
				&& (this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this
						.getId().equals(castOther.getId()))
				&& (this.getName() == castOther.getName())
				|| (this.getName() != null && castOther.getName() != null && this
						.getName().equals(castOther.getName()))
				&& (this.getDisplayValue() == castOther.getDisplayValue())
				|| (this.getDisplayValue() != null
						&& castOther.getDisplayValue() != null && this
						.getDisplayValue().equals(castOther.getDisplayValue()))
				&& (this.getVersion() == castOther.getVersion())
				|| (this.getVersion() != null && castOther.getVersion() != null && this
						.getVersion().equals(castOther.getVersion()));
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + ((this.logbookUser==null)?13:this.getLogbookUser().hashCode());
		result = 37 * result + ((this.detail==null)?13:this.getDetail().hashCode());
		result = 37 * result + ((this.id==null)?13:this.getId().hashCode());
		result = 37 * result + ((this.name==null)?13:this.name.hashCode());
		result = 37 * result + ((this.displayValue==null)?13:this.displayValue.hashCode());
		result = 37 * result
				+ (this.version == null ? 0 : this.version.hashCode());
		return result;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE, false);
	}

}