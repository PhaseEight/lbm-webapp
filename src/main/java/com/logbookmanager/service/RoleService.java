/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="RoleService.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
package com.logbookmanager.service;

import com.logbookmanager.domain.model.security.Role;
import com.logbookmanager.service.support.DefaultService;

public interface RoleService extends DefaultService<Role, Long> {
    public Role findRole(String rolename);
}
