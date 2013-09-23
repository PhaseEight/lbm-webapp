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

public interface RoleService extends LBMService<Role, Long> {
	public Role findRole(String rolename);
}
