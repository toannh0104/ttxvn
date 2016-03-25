/*
 * FILENAME
 *     DaoServiceHelper.java
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

package com.vsii.ttxvn.keywordsearching.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.entity.BaseEntity;
import com.vsii.ttxvn.keywordsearching.entity.JobTracker;

/**
 * <p>
 * An implementation of {@link GenericDao} interface.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Repository("genericDao")
public class GenericDaoImpl implements GenericDao
{
	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#save(com.vsii.ttxvn.keywordsearching.entity.BaseEntity)
	 */
	@Override
	public <E extends BaseEntity> E create(E entity)
	{
		this.getCurrentSession().save(entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#update(com.vsii.ttxvn.keywordsearching.entity.BaseEntity)
	 */
	@Override
	public <E extends BaseEntity> E update(E entity)
	{
		this.getCurrentSession().update(entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.dao.GenericDao#update(java.util.List)
	 */
	@Override
	public <E extends BaseEntity> List<E> update(List<E> entities)
	{
		if (CollectionUtils.isNotEmpty(entities))
		{
			/*
			 * Transaction transaction =
			 * this.getCurrentSession().beginTransaction(); transaction.begin();
			 */

			for (E e : entities)
			{
				this.getCurrentSession().update(e);
			}

			/*
			 * transaction.commit(); this.getCurrentSession().close();
			 */

			return entities;
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#delete(com.vsii.ttxvn.keywordsearching.entity.BaseEntity)
	 */
	@Override
	public <E extends BaseEntity> void delete(E entity)
	{
		if ((entity != null) && entity.getId() != null)
		{
			this.getCurrentSession().delete(entity);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#delete(java.lang.Long)
	 */
	@Override
	public <E extends BaseEntity> void delete(Class<E> clazz, Long id)
	{
		E entity = this.findById(clazz, id);

		if ((entity != null) && entity.getId() != null)
		{
			this.delete(entity);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#findById(java.lang.Class,
	 *      java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends BaseEntity> E findById(Class<E> clazz, Long id)
	{
		return (E) this.getCurrentSession().get(clazz, id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.dao.GenericDao#findByIds(java.lang.Class,
	 *      java.lang.Long[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends BaseEntity> List<E> findByIds(Class<E> clazz, long[] ids)
	{
		if (ArrayUtils.isNotEmpty(ids))
		{
			Long[] buffer = new Long[ids.length];
			int index = 0;
			for (long id : ids)
			{
				buffer[index++] = new Long(id);
			}

			Criteria criteria = this.getCurrentSession().createCriteria(clazz).add(Restrictions.in("id", buffer));
			return (List<E>) criteria.list();
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends BaseEntity> List<E> findAll(Class<E> clazz)
	{
		Query query = this.getCurrentSession().createQuery("FROM " + clazz.getName());
		return (List<E>) query.list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#exist(java.lang.Class,
	 *      java.lang.Long)
	 */
	@Override
	public <E extends BaseEntity> boolean exist(Class<E> clazz, Long id)
	{
		return (this.findById(clazz, id) != null);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#countAll(java.lang.Class)
	 */
	@Override
	public <E extends BaseEntity> long countAll(Class<E> clazz)
	{
		List<E> entities = this.findAll(clazz);
		return (entities != null ? entities.size() : 0);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#findAll(java.lang.Class,
	 *      int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends BaseEntity> List<E> findAll(Class<E> clazz, int firstResult, int maxResult, String orderBy)
	{
		String sqlQuery = "FROM " + clazz.getName();

		if (orderBy != null)
		{
			sqlQuery += " ORDER BY ?";
		}

		final Query query = this.getCurrentSession().createQuery(sqlQuery);

		query.setString(0, orderBy);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		return (List<E>) query.list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.dao.GenericDao#findByPage(java.lang.Class,
	 *      int, int, java.lang.String)
	 */
	@Override
	public <E extends BaseEntity> List<E> findByPage(Class<E> clazz, int page, int pageSize, String orderBy)
	{
		final int firstResult = page * pageSize - pageSize;
		return (List<E>) this.findAll(clazz, firstResult, pageSize, orderBy);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.DaoService#getSessionFactory()
	 */
	@Override
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}

	protected Session getCurrentSession()
	{
		try
		{
			return this.getSessionFactory().getCurrentSession();
		}
		catch (Exception e)
		{
			return this.getSessionFactory().openSession();
		}
	}

	@SuppressWarnings("unchecked")
	final <E extends BaseEntity> Class<E> getClass(final E entity)
	{
		return (Class<E>) (entity instanceof HibernateProxy ? entity.getClass().getSuperclass() : entity.getClass());
	}

	@Override
	public JobTracker findJobTrackerByName(String name)
	{
		if (name == null || name.isEmpty())
			return null;

		String query = "FROM JobTracker t WHERE t.jobName = :p0";
		return (JobTracker) getCurrentSession().createQuery(query).setString("p0", name).uniqueResult();
	}
}
