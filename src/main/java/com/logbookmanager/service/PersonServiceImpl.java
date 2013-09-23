package com.logbookmanager.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.logbookmanager.data.repository.PersonRepository;
import com.logbookmanager.data.repository.Repository;
import com.logbookmanager.domain.model.person.Person;
import com.logbookmanager.service.support.GenericService;

/**
 * Implementation of PersonService interface. </p>
 * 
 * <p>
 * <a href="PersonServiceImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
@Service("personService")
public class PersonServiceImpl extends GenericService<Person, Long> implements
		PersonService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@Qualifier("personRepository")
	PersonRepository personRepository;

	public PersonServiceImpl(Repository<Person, Long> personRepository) {
		super(personRepository);
	}

	@Override
	@Secured("ROLE_ADMIN")
	public List<Person> findAll() {
		return super.findAll();
	}
	

}
