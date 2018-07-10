package com.logbookmanager.service;

import com.logbookmanager.data.repository.RoleRepository;
import com.logbookmanager.domain.model.security.Role;
import com.logbookmanager.service.support.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Implementation of RoleService interface. </p>
 *
 * <p>
 * <a href="RoleServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Transactional(readOnly = true)
@Service("roleService")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RoleServiceImpl extends GenericService<Role, Long> implements RoleService {

    private static final long serialVersionUID = 1L;

    @Inject
    @Qualifier("roleRepository")
    RoleRepository roleRepository = null;

    @Inject
    public RoleServiceImpl(@Qualifier("roleRepository") RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    public Role findRole(String rolename) {
        Role exampleInstance = new Role();
        exampleInstance.setName(rolename);
        return roleRepository.findOneByExample(exampleInstance);
    }

}