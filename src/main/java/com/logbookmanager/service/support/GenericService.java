package com.logbookmanager.service.support;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.criterion.Criterion;
/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logbookmanager.data.repository.Repository;
import com.logbookmanager.domain.support.EntitySupport;
import com.logbookmanager.service.LBMService;
import com.logbookmanager.util.ResourcesUtil;

/**
 * @author <a HREF="mailto:peter.neil@logbookmanager.com">Peter Neil</a>
 */
abstract public class GenericService<T extends EntitySupport<T, ID>, ID extends Serializable> implements
		LBMService<T, ID> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Logger log;

	@Inject
	private ResourcesUtil resourcesUtil = null;

	/**
	 */
	protected Repository<T, ID> repository = null;

	// required by cglib to create a proxy around the object we are using
	protected GenericService() {
		super();
	}

	@Inject
	public GenericService(Repository<T, ID> repository) {
		this.log = LoggerFactory.getLogger(getClass());
		this.repository = repository;
	}

	/**
	 * @see com.logbookmanager.domain.model.service.Manager#getObject(java.lang.Class,
	 *      java.io.Serializable)
	 */
	public T findById(ID id) {
		return repository.findById(id);
	}

	/**
	 * @see com.logbookmanager.domain.model.service.Manager#getObjects(java.lang.Class)
	 */
	public List<T> findAll() {
		return repository.findAll();
	}

	public List<T> findByExample(T exampleInstance) {
		return repository.findByExample(exampleInstance);
	}

	public T findByNaturalId(T exampleInstance) {
		return repository.findByNaturalId(exampleInstance);
	}

	public T findByNaturalId(T exampleInstance, Criterion naturalIdRestriction) {
		return repository.findByNaturalId(exampleInstance, naturalIdRestriction);
	}

	public T findUniqueByExample(T exampleInstance) {
		return repository.findUniqueByExample(exampleInstance);
	}

	/**
	 * @see com.logbookmanager.domain.model.service.Manager#saveObject(java.lang.Object)
	 */
	public T save(T o) {
		return repository.makePersistent(o);
	}

	public T delete(T entity) {
		return repository.delete(entity);
	}

	public T delete(ID id) {
		return repository.delete(id);
	}

	public ResourcesUtil getResourcesUtil() {
		return resourcesUtil;
	}

	public void setResourcesUtil(ResourcesUtil resourcesUtil) {
		this.resourcesUtil = resourcesUtil;
	}

}
