/*
 * FILENAME
 *     SourceUrlServiceImpl.java
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

package com.vsii.ttxvn.keywordsearching.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.domain.SourceUrlItem;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;

/**
 * <p>
 * An implementation of Source Url Service.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("sourceUrlService")
public class SourceUrlServiceImpl extends GenericDaoImpl implements SourceUrlService
{
	@Autowired
	private MessageSource messageSource;

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.SourceUrlService#create(com.vsii.ttxvn.keywordsearching.entity.SourceUrl,
	 *      java.lang.Long, java.lang.Long)
	 */
	@Override
	public SourceUrl create(SourceUrl sourceUrl, Long categoryId, Long fetchFrequencyId)
	{
		sourceUrl.setCategory(this.findById(Category.class, categoryId));
		sourceUrl.setFetchFrequency(this.findById(FetchFrequency.class, fetchFrequencyId));
		sourceUrl.setLastModified(new Date());

		return super.create(sourceUrl);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.SourceUrlService#update(com.vsii.ttxvn.keywordsearching.entity.SourceUrl,
	 *      java.lang.Long, java.lang.Long)
	 */
	@Override
	public SourceUrl update(SourceUrl sourceUrl, Long fetchFrequencyId)
	{
		sourceUrl.setLastModified(new Date());
		sourceUrl.setFetchFrequency(this.findById(FetchFrequency.class, fetchFrequencyId));

		return super.update(sourceUrl);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.SourceUrlService#findByCagegoryUrl(java.lang.Long,
	 *      java.lang.String, int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SourceUrl> findByCagegoryUrl(Long categoryId, String url, int page, int pageSize, String orderBy)
	{
		final int firstResult = (page - 1) * pageSize;
		Criteria criteria =
				this.getCurrentSession().createCriteria(SourceUrl.class).setFirstResult(firstResult)
						.setMaxResults(pageSize).addOrder(Order.asc(orderBy));

		criteria.add(Restrictions.and(Restrictions.eq("category", findById(Category.class, categoryId)),
				Restrictions.ilike("url", "%" + url + "%", MatchMode.ANYWHERE)));

		return (List<SourceUrl>) criteria.list();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.SourceUrlService#countByCategoryIdSourceUrl(java.lang.Long,
	 *      java.lang.String)
	 */
	@Override
	public long countByCategoryIdSourceUrl(Long categoryId, String url)
	{
		Criteria criteria = this.getCurrentSession().createCriteria(SourceUrl.class);

		criteria.add(Restrictions.and(Restrictions.eq("category", findById(Category.class, categoryId)),
				Restrictions.ilike("url", "%" + url + "%", MatchMode.ANYWHERE)));
		criteria.setProjection(Projections.rowCount());

		return (long) criteria.uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.SourceUrlService#getAllSourceUrl(java.lang.Long,
	 *      java.lang.String, org.springframework.data.domain.PageRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<SourceUrl> getAllSourceUrl(Long categoryId, String url, PageRequest pageRequest)
	{
		final int firstResult = pageRequest.getPageNumber() * pageRequest.getPageSize();
		final long totalElements = this.countByCategoryIdSourceUrl(categoryId, url);

		log.debug("----> totalElements = " + totalElements);

		Criteria criteria =
				this.getCurrentSession().createCriteria(SourceUrl.class).setFirstResult(firstResult)
						.setMaxResults(pageRequest.getPageSize());

		criteria.add(Restrictions.and(Restrictions.eq("category", findById(Category.class, categoryId)),
				Restrictions.ilike("url", "%" + url + "%", MatchMode.ANYWHERE)));

		return new PageImpl<SourceUrl>(criteria.list(), pageRequest, totalElements);
	}

	@Override
	public SourceUrl findByCategory(Long categoryId, String url)
	{
		if (categoryId == null || Strings.isNullOrEmpty(url))
			return null;
		String sql = "FROM SourceUrl s WHERE s.category.id = :p0 AND s.url = :p1";
		return (SourceUrl) getCurrentSession().createQuery(sql).setLong("p0", categoryId).setString("p1", url)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<SourceUrlItem> getAllSourceUrlByCategoryLanguage(long categoryId, LanguageCode language,
			String url, PageRequest pageRequest)
	{
		List<SourceUrl> urls = new ArrayList<SourceUrl>();
		int firstResult = pageRequest.getPageNumber() * pageRequest.getPageSize();
		int totalElements = 0;
		int pageSize = pageRequest.getPageSize();
		if (categoryId <= 0)
		{
			if (url.isEmpty())
			{
				String sql = "FROM SourceUrl s WHERE langCode = :p0";
				totalElements = getCurrentSession().createQuery(sql).setParameter("p0", language).list().size();
				urls = getCurrentSession().createQuery(sql).setParameter("p0", language).setFirstResult(firstResult)
						.setMaxResults(pageSize).list();
			}
			else
			{
				String sql = "FROM SourceUrl s WHERE langCode = :p0 AND url like :p1";
				totalElements = getCurrentSession().createQuery(sql).setParameter("p0", language).setString("p1", "%"+url+"%")
						.list().size();
				urls = getCurrentSession().createQuery(sql).setParameter("p0", language).setString("p1","%"+url+"%")
						.setFirstResult(firstResult).setMaxResults(pageSize).list();
			}
		}
		else
		{
			if (url.isEmpty())
			{
				String sql = "FROM SourceUrl s WHERE category.id = :p0 AND langCode = :p1";
				totalElements = getCurrentSession().createQuery(sql).setLong("p0", categoryId)
						.setParameter("p1", language).list().size();
				urls = getCurrentSession().createQuery(sql).setLong("p0", categoryId).setParameter("p1", language)
						.setFirstResult(firstResult).setMaxResults(pageSize).list();
			}
			else
			{
				String sql = "FROM SourceUrl s WHERE category.id = :p0 AND langCode = :p1 AND url = :p2";
				totalElements = getCurrentSession().createQuery(sql).setLong("p0", categoryId)
						.setParameter("p1", language).setString("p2", url).list().size();
				urls = getCurrentSession().createQuery(sql).setLong("p0", categoryId).setParameter("p1", language)
						.setString("p2", url).setFirstResult(firstResult).setMaxResults(pageSize).list();
			}
		}
		List<SourceUrlItem> items = new ArrayList<SourceUrlItem>();
		for (SourceUrl sourceUrl : urls)
		{
			SourceUrlItem item = new SourceUrlItem();
			item.setSourceUrlId(sourceUrl.getId());
			item.setUrl(sourceUrl.getUrl());
			item.setSourceTypeCode(sourceUrl.getSourceType().getMessageCode());
			item.setFrequency(sourceUrl.getFetchFrequency().getFrequency());
			item.setDeep(sourceUrl.getFetchDeep().getValue());
			item.setReliability(sourceUrl.getReliability());
			item.setCategoryId(sourceUrl.getCategory().getId());
			item.setFrequencyId(sourceUrl.getFetchFrequency().getId());
			items.add(item);
		}
		return new PageImpl<SourceUrlItem>(items, pageRequest, totalElements);
	}

	@Override
	public SourceUrl findByUrl(String url)
	{
		if (Strings.isNullOrEmpty(url))
			return null;
		String sql = "FROM SourceUrl s WHERE s.url = :p0";
		return (SourceUrl) getCurrentSession().createQuery(sql).setString("p0", url)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SourceUrl> getListSourceUrlByLanguage(LanguageCode code)
	{
		String sql = "FROM SourceUrl su WHERE langCode = :p1";
		return getCurrentSession().createQuery(sql).setParameter("p1", code).list();
	}

}
