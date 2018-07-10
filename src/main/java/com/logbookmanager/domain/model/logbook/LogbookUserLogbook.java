package com.logbookmanager.domain.model.logbook;

import com.logbookmanager.domain.support.EntitySupport;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A logbook user logbook is linked to the loggable activity in which a logbook
 * user participates e.g. scuba diving
 * <p>
 * LogbookUserLogbook
 */
public class LogbookUserLogbook extends EntitySupport<LogbookUserLogbook, Long> implements java.io.Serializable {
    private static final long serialVersionUID = 912839123L;

    // Fields

    private Logbook logbook;

    private LogbookUser logbookUser;

    private String name;

    // Constructors

    /**
     * default constructor required by Hibernate
     */
    LogbookUserLogbook() {
    }

    /**
     *
     */
    public Logbook getLogbook() {
        return this.logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
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
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof LogbookUserLogbook)) {
            return false;
        }
        LogbookUserLogbook rhs = (LogbookUserLogbook) object;
        return new EqualsBuilder().append(this.logbook.getId(), rhs.logbook.getId()).append(this.id, rhs.id)
                .append(this.logbookUser.getId(), rhs.logbookUser.getId()).append(this.name, rhs.name)
                .append(this.version, rhs.version).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-94869041, 2116460323).append((logbook == null) ? 5 : this.logbook.getId())
                .append(this.id).append((logbookUser == null) ? 5 : this.logbookUser.getId()).append(this.name)
                .append(this.version).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("version", this.version)
                .append("LogbookUserId", (logbookUser == null) ? "[null]" : this.logbookUser.getId())
                .append("logbookId", (logbook == null) ? "[null]" : this.logbook.getId()).append("id", this.id)
                .toString();
    }

}