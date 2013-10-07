package com.logbookmanager.data.support.hibernate;

/**
 * protected Logger log;
 * this.log = LoggerFactory.getLogger(getClass());
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NaturalId;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.NaturalIdentifier;
import org.hibernate.criterion.Restrictions;
import org.jodah.typetools.TypeResolver;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.logbookmanager.annotations.DeleteType;
import com.logbookmanager.annotations.DeletionType;
import com.logbookmanager.data.repository.Repository;
import com.logbookmanager.support.BaseObject;

/**
 * 
 * @author Peter Neil
 */
public class HibernateRepository<T extends BaseObject<T>, ID extends Serializable> implements Repository<T, ID> {

	protected ID id;

	protected final Logger log;
	
	private Class<T> typeClass;
	private Class<ID> typeIdClass;
	private String cacheRegion;

	@Autowired(required = true)
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public HibernateRepository() {
		Class<?>[] typeArguments = TypeResolver.resolveArguments(getClass(), HibernateRepository.class);
		if (typeArguments != null) {
			this.typeClass = (Class<T>) typeArguments[0];
			this.typeIdClass = (Class<ID>) typeArguments[1];
			this.log = LoggerFactory.getLogger(typeClass);
			this.cacheRegion = typeClass.getCanonicalName();
		} else {
			log = LoggerFactory.getLogger(HibernateRepository.class.getName());
		}
	}
	
	@SuppressWarnings("unchecked")
	public HibernateRepository(Class<T> typeClass, Class<ID> typeIdClass) {
		Class<?>[] typeArguments = TypeResolver.resolveArguments(getClass(), HibernateRepository.class);
		if (typeArguments != null) {
			this.typeClass = (Class<T>) typeArguments[0];
			this.typeIdClass = (Class<ID>) typeArguments[1];
			this.cacheRegion = this.typeClass.getCanonicalName();
			this.log = LoggerFactory.getLogger(this.typeClass);
		}
		else {
			this.log = LoggerFactory.getLogger(getClass());
		}
	}

	public Class<T> getType() {
		return typeClass;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

	public boolean isIdSet() {
		return id != null;
	}

	public Class<T> getPersistentClass() {
		return this.typeClass;
	}

	public Class<ID> getIdClass() {
		return this.typeIdClass;
	}

	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T result;
		result = (T) this.sessionFactory.getCurrentSession().get(getPersistentClass(), id);
		return result;
	}

	public List<T> findAll() {
		return findByCriteria(false);
	}

	public List<T> findByExample(T exampleInstance) {
		Criterion crit = Example.create(exampleInstance);
		return findByCriteria(false, crit);
	}

	public T findUniqueByExample(T exampleInstance) {
		Criterion crit = Example.create(exampleInstance);
		T result = (T) findUniqueByCriteria(false, crit);
		return (T) result;
	}

	/**
	 * 
	 * natural-id must be mapped in hibernate We will use annotations to look
	 * for the NaturalId Annotation
	 * 
	 */
	public T findByNaturalId(T exampleInstance) {
		// default natural-id to id
		NaturalIdentifier naturalIdRestriction = loadNaturalIdentifierRestriction(exampleInstance);
		T result = null;
		Criterion exampleCriterion = Example.create(exampleInstance);
		List<T> resultList = findByCriteria(false, exampleCriterion, naturalIdRestriction);
		if (resultList.size() >= 1) {
			result = resultList.get(0);
		}
		return result;
	}

	/**
	 * Restrictions.naturalId().set("natural-id-property-name",
	 * "natural-id-property-value")
	 */
	public T findByNaturalId(T exampleInstance, Criterion naturalIdRestriction) {
		Criterion exampleCriterion = Example.create(exampleInstance);
		T result = (T) findUniqueByCriteria(false, exampleCriterion, naturalIdRestriction);
		return result;
	}

	public T makePersistent(T entity) {
		setEntityAttribute(entity, "lastUpdateTimeStamp", new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	private void setEntityAttribute(T entity, String attributeName, Object value) {

		BeanWrapper bw = new BeanWrapperImpl(entity);
		bw.setPropertyValue(attributeName, value);

	}

	public T delete(T entity) {
		DeletionType delType = getDeleteType(entity);
		switch (delType) {
		case LogicalDelete:
			logicalDelete(entity);
			break;
		case PhysicalDelete:
			physicalDelete(entity);
			break;
		}
		return entity;
	}

	public T delete(ID id) {
		T o = findById(id);
		delete(o);
		return o;
	}

	public T logicalDelete(final T entity) {
		setEntityAttribute(entity, "lastUpdateTimeStamp", new java.sql.Timestamp(Calendar.getInstance()
				.getTimeInMillis()));
		setEntityAttribute(entity, "deleted", new Boolean(true));
		setEntityAttribute(entity, "active", new Boolean(false));
		sessionFactory.getCurrentSession().update(entity);
		return entity;
	}

	public T logicalDelete(ID id) {
		T o = findById(id);
		return logicalDelete(o);
	}

	public void physicalDelete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	protected List<T> findByCriteria(final boolean unique, final Class<T> entityType, final Criterion... criterion) {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(entityType);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (unique) {
			crit.uniqueResult();
		}
		@SuppressWarnings("unchecked")
		final List<T> result = Collections.checkedList(crit.list(), typeClass);
		return result;
	}

	/**
	 * Use this inside subclasses as a convenience method.
	 */

	protected List<T> findByCriteria(final boolean cacheable, final Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (cacheable) {
			crit.setCacheable(cacheable);
		}
		@SuppressWarnings("unchecked")
		final List<T> checkedList = Collections.checkedList(crit.list(), typeClass);
		return checkedList;
	}

	protected T findUniqueByCriteria(final boolean cacheable, final Criterion... criterion) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (cacheable) {
			crit.setCacheable(cacheable);
		}
		@SuppressWarnings("unchecked")
		T result = (T) crit.uniqueResult();
		return result;
	}

	private NaturalIdentifier loadNaturalIdentifierRestriction(T exampleInstance) {
		final Field[] fields = exampleInstance.getClass().getDeclaredFields();
		Map<String, Object> naturalId = new TreeMap<String, Object>();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof NaturalId) {
					// we have the annotation on this field, so lets use it to
					// set the value
					String fieldName = field.getName();
					String fieldAccessor = "getId";
					try {
						final String uc = (fieldName.substring(0, 1)).toUpperCase();
						fieldAccessor = "get" + uc + fieldName.substring(1, fieldName.length());
						Method m = exampleInstance.getClass().getMethod(fieldAccessor, new Class[0]);
						Object fieldValue = m.invoke(exampleInstance, new Object[0]);
						// store the fieldname and value to be
						naturalId.put(fieldName, fieldValue);
					} catch (NoSuchMethodException e) {
						this.log.error("No accessor '" + fieldAccessor + "' found for field: " + fieldName);
					} catch (InvocationTargetException e) {
						this.log.error("InvocationTargetException: unable to execute method on target: "
								+ exampleInstance, e);
					} catch (IllegalAccessException e) {
						this.log.error(
								"IllegalAccessException: unable to execute method on target: " + exampleInstance, e);
					}
				}
			}
		}

		NaturalIdentifier naturalIdRestriction = Restrictions.naturalId();
		// create the restrictions
		for (Map.Entry<String, Object> field : naturalId.entrySet()) {
			naturalIdRestriction.set(field.getKey(), field.getValue());
		}
		return naturalIdRestriction;
	}

	/**
	 * 
	 * Locate the Annotation marker which is used to identify the typeClass of
	 * delete for an Entity. if there is a DeleteType Marker the Type will
	 * either by Physical or Logical. When the DeleteType.type is Logical there
	 * MUST be a DELETED attribute to the entity. All Entities are able to have
	 * 
	 * DELETED - deleted does not mean IS_ACTIVE - not active does not mean
	 * deleted, but to be deleted the entity will be inactive
	 * 
	 * DeleteType will default to Physical as this does not depend on correctly
	 * structured entities
	 * 
	 * At build time APT will be used to check and add the appropriate attribute
	 * to the entity and will also ensure the correct value is present in that
	 * entity's hbm mapping file
	 */
	private DeletionType getDeleteType(T o) {
		Annotation[] annotations = o.getClass().getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof DeleteType) {
				DeleteType anno = (DeleteType) annotation;
				return anno.type();
			}
		}
		return DeletionType.PhysicalDelete;
	}

	/**
	 * Set hints for 2d level cache.
	 */
	protected void setCacheHints(TypedQuery<?> typedQuery) {
		typedQuery.setHint("org.hibernate.cacheable", true);
		typedQuery.setHint("org.hibernate.cacheRegion", cacheRegion);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
