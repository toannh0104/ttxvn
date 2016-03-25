/*
 * FILENAME
 *     DaoService.java
 *
 * FILE LOCATION
 *     $Source$
 *
 * VERSION
 *     $Id$
 *         @version       $Revision$
 *         Check-Out Tag: $Name$
 *         Locked By:     $Lockers$
 *
 * FORMATTING NOTES
 *     * Lines should be limited to 78 characters.
 *     * Files should contain no tabs.
 *     * Indent code using four-character increments.
 *
 * COPYRIGHT
 *     Copyright (C) 2005 vietsoftware international Inc. All rights reserved.
 *     This software is the confidential and proprietary information of
 *     VSII ("Confidential Information"). You shall not
 *     disclose such Confidential Information and shall use it only in
 *     accordance with the terms of the license agreement you entered into
 *     with VSII.
 */

package com.vsii.ttxvn.keywordsearching.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.vsii.ttxvn.keywordsearching.entity.BaseEntity;
import com.vsii.ttxvn.keywordsearching.entity.JobTracker;

/**
 * <p>
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs. Extend
 * this interface if you want type safe (no casting necessary) DAO's for your
 * domain objects.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public interface GenericDao
{
	/**
	 * <p>
	 * Create new an entity of type E.
	 * </p>
	 * <p>
	 * The implementation should merely create the entity to database without
	 * any other business logic/check. It is recommended to have a dedicated
	 * method to take care of other business logic if any.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param entity
	 *            the entity to be saved
	 * @return saved instance of the entity
	 */
	<E extends BaseEntity> E create(E entity);

	/**
	 * <p>
	 * Update an entity of type E.
	 * </p>
	 * <p>
	 * The implementation should merely save the entity to database without any
	 * other business logic/check. It is recommended to have a dedicated method
	 * to take care of other business logic if any.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param entity
	 *            the entity to be saved
	 * @return updated instance of the entity
	 */
	<E extends BaseEntity> E update(E entity);

	/**
	 * <p>
	 * Update list entities of type E.
	 * </p>
	 * <p>
	 * The implementation should merely save the entity to database without any
	 * other business logic/check. It is recommended to have a dedicated method
	 * to take care of other business logic if any.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param entities
	 *            list entities to be saved
	 * @return updated instance of entities
	 */
	<E extends BaseEntity> List<E> update(List<E> entities);

	/**
	 * <p>
	 * Delete an entity of type E.
	 * </p>
	 * <p>
	 * The implementation should merely delete the entity off database without
	 * any other business logic/check. It is recommended to have a dedicated
	 * method to take care of other business logic if any.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param entity
	 *            the entity to be deleted
	 */
	<E extends BaseEntity> void delete(E entity);

	/**
	 * <p>
	 * Delete an entity of type E by given id.
	 * </p>
	 * <p>
	 * The implementation should merely delete the entity off database without
	 * any other business logic/check. It is recommended to have a dedicated
	 * method to take care of other business logic if any.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param entity
	 *            the entity to be deleted
	 * @param id
	 *            unique identifier of the entity
	 */
	<E extends BaseEntity> void delete(Class<E> clazz, Long id);

	/**
	 * <p>
	 * Find an entity of type E by a given id.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param clazz
	 *            class of the entity to get
	 * @param id
	 *            unique identifier of the entity
	 * @return the found entity, null if none matches the specified id
	 */
	<E extends BaseEntity> E findById(Class<E> clazz, Long id);

	/**
	 * <p>
	 * Find all entities of type E by a given list ids.
	 * </p>
	 * 
	 * @param <E>
	 *            Entity type
	 * @param clazz
	 *            class of the entity to get
	 * @param ids
	 *            list of unique identifier of entities
	 * @return the found entities, null if none id in list ids
	 */
	<E extends BaseEntity> List<E> findByIds(Class<E> clazz, long[] ids);

	/**
	 * <p>
	 * Find all entities of type E.
	 * </p>
	 * 
	 * @param clazz
	 *            class of the entity to get
	 * @return a list of found entities
	 */
	<E extends BaseEntity> List<E> findAll(Class<E> clazz);

	/**
	 * <p>
	 * Check the existence of type E by a given id.
	 * </p>
	 * 
	 * @param clazz
	 *            class of the entity
	 * @param id
	 *            unique identifier of the entity
	 * @return the existence
	 */
	<E extends BaseEntity> boolean exist(Class<E> clazz, Long id);

	/**
	 * <p>
	 * Count all entities of type E.
	 * </p>
	 * 
	 * @param clazz
	 *            class of the entity
	 * @return total entities
	 */
	<E extends BaseEntity> long countAll(Class<E> clazz);

	/**
	 * <p>
	 * find all entities of type E by criteria.
	 * </p>
	 * 
	 * @param clazz
	 *            class of the entity
	 * @param firstResult
	 * @param maxResult
	 *            number of entities return
	 * @param orderBy
	 *            a given field of entity to order
	 * @return list of found entities
	 */
	<E extends BaseEntity> List<E> findAll(Class<E> clazz, int firstResult, int maxResult, String orderBy);

	<E extends BaseEntity> List<E> findByPage(Class<E> clazz, int page, int pageSize, String orderBy);

	JobTracker findJobTrackerByName(String name);

	public SessionFactory getSessionFactory();

}
