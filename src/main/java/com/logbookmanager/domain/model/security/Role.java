package com.logbookmanager.domain.model.security;

import com.logbookmanager.domain.support.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to represent available roles in the database. </p>
 *
 * <p>
 * <a href="Role.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
public class Role extends EntitySupport<com.logbookmanager.domain.model.security.Role, Long> implements Serializable {
    private static final long serialVersionUID = 3690197650654049848L;

    @NaturalId
    private String name;

    private String description;

    // registeredUsers with this role
    private Set<com.logbookmanager.domain.model.security.RegisteredUser> registeredUsers = new HashSet<com.logbookmanager.domain.model.security.RegisteredUser>();

    public Role() {
        super();
    }

    /**
     * provide a natural id contructor
     *
     * @param name
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * constructor with id
     */
    public Role(Long id) {
        this.id = id;
    }

    /**
     * Returns the name.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description.
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the registeredUsers. This inverse relation causes
     * exceptions :-( hibernate.set table="sec_user_role"
     * cascade="save-update" lazy="false" inverse="true"
     * hibernate.collection-key column="role_name"
     * hibernate.collection-many-to-many
     * class="com.logbookmanager.domain.model.User" column="username"
     */
    public Set<RegisteredUser> getUsers() {
        return registeredUsers;
    }

    /**
     * @param registeredUsers The registeredUsers to set.
     */
    public void setUsers(Set<RegisteredUser> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    @Override
    public boolean equals(Object other) {
        return (this == other || (other instanceof Role && hashCode() == other.hashCode()));
    }

    @Override
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    /**
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("name", this.name)
                .append("description", this.description).toString();
    }

}
