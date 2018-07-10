package com.logbookmanager.data.repository;

import com.logbookmanager.support.BaseObject;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Each Repository represents 1 Data object
 * <p>
 * The data objects will be used by Domain Objects and a Data Repository will be used by a Domain Repository.
 */
public interface Repository<T extends BaseObject<T> & Serializable, ID extends Serializable> {


    T findById(ID id);

    List<T> findAll();

    List<T> findByExample(T exampleInstance);

    T findOneByExample(T exampleInstance);

    T findByNaturalId(T exampleInstance);

    T findByNaturalId(T exampleInstance, Criterion naturalIdRestriction);

    T makePersistent(T entity);

    /**
     * @param entity The entity in the session which should be removed from the
     *               datastore
     * @return the transient version of the entity
     */
    T delete(T entity);

    /**
     * @param id The id of the entity to be removed
     * @return the transient version of the entity
     */
    T delete(ID id);

}
