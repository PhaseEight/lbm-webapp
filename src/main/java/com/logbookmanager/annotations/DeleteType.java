package com.logbookmanager.annotations;

/**
 * Marker annotation for entities, to indicate whether the entity is to be 
 * "removed" using a logical or physical delete
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteType {
	/** deletion type for an entity can be either physical or logical* */
	public DeletionType type();
}