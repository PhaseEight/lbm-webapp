package com.logbookmanager.service.support;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface DefaultService<T, ID extends Serializable> extends Serializable {

	public T findById(ID id);

	public List<T> findAll();

	public List<T> findByExample(T exampleInstance);

	public T findOneByExample(T exampleInstance);

	public T findByNaturalId(T exampleInstance);

	public T findByNaturalId(T exampleInstance, Criterion naturalIdRestriction);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 
	 * @param entity
	 *            The entity in the session which should be deleted
	 * @return the transient version of the entity
	 */
	public T delete(T entity);

	/**
	 * 
	 * @param id
	 *            The id of the entity to be removed
	 * @return the transient version of the entity
	 */
	public T delete(ID id);

}
