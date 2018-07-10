package com.logbookmanager.data.repository;

import com.logbookmanager.domain.model.security.Role;

public interface RoleRepository extends Repository<Role, Long> {

    /**
     * Gets roles information based on login name.
     *
     * @param rolename the current rolename
     * @return role populated role object
     */
    public Role findRole(String rolename);

}
