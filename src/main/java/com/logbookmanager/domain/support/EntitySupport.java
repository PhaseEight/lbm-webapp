package com.logbookmanager.domain.support;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

import com.logbookmanager.support.BaseObject;

/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() *
 * <p>
 * <a href="BaseObject.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a> updated
 *         by Peter
 * 
 */
@MappedSuperclass
public abstract class EntitySupport<T extends Entity<T, ID>, ID extends Serializable> extends BaseObject<T> implements
		Entity<T, ID>, Serializable {

	private static final long serialVersionUID = 1912839123L;

	protected Long version = new Long(0);

	protected ID id = null;

	protected Timestamp lastUpdateTimeStamp;

	protected Boolean active = null;
	protected Boolean deleted = null;
	protected Boolean verified = null;

	protected void copy(final EntitySupport<T, ID> source) {
		this.id = source.id;
		this.version = source.version;
		this.lastUpdateTimeStamp = source.lastUpdateTimeStamp;
	}

	protected static boolean getBooleanValue(Boolean value) {
		return (value == null) ? null : Boolean.valueOf(String.valueOf(value));
	}

	@Override
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public ID getId() {
		return id;
	}

	public void setId(final ID id) {
		this.id = id;
	}

	@Override
	@Transient
	@XmlTransient
	public boolean isIdSet() {
		return id != null;
	}

	@Version
	@Column(name = "version", unique = false, nullable = false)
	public Long getVersion() {
		return new Long(this.version.longValue());
	}

	public void setVersion(Long version) {
		this.version = Long.valueOf(version.longValue());
	}

	@Column(insertable = false, name = "lastUpdateTimeStamp")
	public Timestamp getlastUpdateTimeStamp() {
		if (this.lastUpdateTimeStamp != null) {
			return new Timestamp(this.lastUpdateTimeStamp.getTime());
		}
		return null;
	}

	public void setlastUpdateTimeStamp(Timestamp lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	public void setActive(Boolean active) {
		if (active == null) {
			this.active = Boolean.TRUE;
		}
		this.active = Boolean.valueOf(active.booleanValue());
	}

	public void setDeleted(Boolean deleted) {
		if (deleted == null) {
			this.deleted = Boolean.TRUE;
		}
		this.deleted = Boolean.valueOf(deleted.booleanValue());
	}

	@Column(name = "active", unique = false, nullable = false, columnDefinition = "BOOLEAN default true")
	public Boolean isActive() {
		if (active == null) {
			return Boolean.TRUE;
		}
		return Boolean.valueOf(this.active.booleanValue());
	}

	@Column(name = "deleted", unique = false, nullable = false, columnDefinition = "BOOLEAN default false")
	public Boolean isDeleted() {
		if (deleted == null) {
			return Boolean.FALSE;
		}
		return Boolean.valueOf(this.deleted.booleanValue());
	}

	@Column(name = "verified", unique = false, nullable = false, columnDefinition = "BOOLEAN default false")
	public Boolean isVerified() {
		if (verified == null) {
			return Boolean.FALSE;
		}
		return Boolean.valueOf(this.verified.booleanValue());
	}

	public Boolean getActive() {
		return isActive();
	}

	public Boolean getDeleted() {
		return isDeleted();
	}

	public Boolean getVerified() {
		return isVerified();
	}

	@Override
	/**
	 * TODO: natural-id must be tested for objects that have a natural id.
	 */
	public boolean sameIdentityAs(T other) {
		return other != null && this.getId().equals(other.getId());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntitySupport)) {
			return false;
		}
		final EntitySupport<?, ?> other = (EntitySupport<?, ?>) obj;
		// if id is different
		if (this.id != null && other.id != null) {
			if (this.id != other.id) {
				return false;
			}
		}
		// or if version is different
		if (this.version != null && other.version != null) {
			if (this.version.longValue() != other.version.longValue()) {
				return false;
			}
		}
		// or if last update date is different
		if (this.lastUpdateTimeStamp != null && other.lastUpdateTimeStamp != null) {
			if (this.lastUpdateTimeStamp.getTime() != other.lastUpdateTimeStamp.getTime()) {
				return false;
			}
		}

		return true;
	}

}
