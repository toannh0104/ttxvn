/*
 * FILENAME
 *     KeywordServiceImpl.java
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.CategoryKeyword;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.enums.KeywordStatus;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnCache;


/**
 * <p>
 * The class for implementation of KeywordService interface
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Service("keywordService")
public class KeywordServiceImpl extends GenericDaoImpl implements KeywordService
{
	private TtxvnCache cacheUtil=new TtxvnCache();
	@SuppressWarnings("unchecked")
	@Override
	public List<Keyword> getListKeywordByNameStatusUserIdCategoryId(String name, long userId, long categoryId,
			KeywordStatus status)
	{
		Query query = null;
		if (!name.isEmpty())
		{
			return getKeywordByStatusNameUserIdCategoryId(name, userId, categoryId, status);
		}
		else if (categoryId <= 0)
		{
			LanguageCode languageCode = LanguageUtils.getCurrentLanguageCode();
			String sql = "SELECT distinct k FROM CategoryKeyword ck INNER JOIN ck.category c INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND k.status = :p2 AND c.langCode = :p3";
			query = getCurrentSession().createQuery(sql).setLong("p0", userId).setParameter("p2", status)
					.setParameter("p3", languageCode);
		}
		else
		{
			String sql = "SELECT k FROM CategoryKeyword ck INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND ck.categoryId = :p1 AND k.status = :p2";
			query = getCurrentSession().createQuery(sql).setLong("p0", userId).setLong("p1", categoryId)
					.setParameter("p2", status);
		}
		return (List<Keyword>) query.list();
	}

	@Override
	public Keyword getKeywordByNameUserIdCategoryId(String name, long userId, long categoryId)
	{
		if (name == null || name.isEmpty() || userId <= 0)
			return null;
		if (categoryId <= 0)
		{
			String sql = "SELECT k FROM CategoryKeyword ck INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND k.name = :p1";
			return (Keyword) getCurrentSession().createQuery(sql).setLong("p0", userId).setString("p1", name)
					.uniqueResult();
		}
		String sql = "SELECT k FROM CategoryKeyword ck INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND ck.categoryId = :p1 AND k.name = :p2";
		return (Keyword) getCurrentSession().createQuery(sql).setLong("p0", userId).setLong("p1", categoryId)
				.setString("p2", name).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Keyword> getKeywordByStatusNameUserIdCategoryId(String name, long userId, long categoryId,
			KeywordStatus status)
	{
		if (name == null || name.isEmpty() || userId <= 0)
			return null;
		if (categoryId > 0)
		{
			String sql = "SELECT k FROM CategoryKeyword ck INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND ck.categoryId = :p1 AND k.status = :p2 AND k.name = :p3";
			return getCurrentSession().createQuery(sql).setLong("p0", userId).setLong("p1", categoryId)
					.setParameter("p2", status).setString("p3", name).list();
		}
		String sql = "SELECT k FROM CategoryKeyword ck INNER JOIN ck.category c INNER JOIN ck.keyword k WHERE ck.userId = :p0 AND k.status = :p1 AND k.name = :p2 AND c.langCode = :p3";
		return getCurrentSession().createQuery(sql).setLong("p0", userId).setParameter("p1", status).setString("p2", name)
				.setParameter("p3", LanguageUtils.getCurrentLanguageCode()).list();
	}

	@Override
	public String createKeyword(String name, Date start, Date end, long userId, long categoryId)
	{
		Category category = findById(Category.class, categoryId);
		User user = findById(User.class, userId);
		if (category == null || user == null)
		{
			return "Co loi xay ra khi luu tu khoa";
		}
		Keyword keyword = create(new Keyword(name, start, end, KeywordStatus.TRACKING, userId));
		CategoryKeyword categoryKeyword = new CategoryKeyword(category.getName());
		categoryKeyword.setCategory(category);
		categoryKeyword.setKeyword(keyword);
		categoryKeyword.setUser(user);
		create(categoryKeyword);
		cacheUtil.removeCacheWhenUpdateKeyWord();
		return Constants.EMPTY_STRING;
	}

	@Override
	public String updateKeyword(Keyword keyword, long userId, long categoryId)
	{
		Category category = findById(Category.class, categoryId);
		User user = findById(User.class, userId);
		if (category == null || user == null)
			return "Co loi xay ra khi luu tu khoa";

		keyword.setLastModifiedUserId(userId);
		CategoryKeyword categoryKeyword = keyword.getCategoryKeyword();
		categoryKeyword.setCategory(category);
		categoryKeyword.setCategoryName(category.getName());
		if (KeywordStatus.END_TRACKING.equals(keyword.getStatus())
				&& keyword.getEndDate().after(Calendar.getInstance().getTime()))
			keyword.setStatus(KeywordStatus.TRACKING);
		update(keyword);
		update(categoryKeyword);
		cacheUtil.removeCacheWhenUpdateKeyWord();
		return Constants.EMPTY_STRING;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Keyword> getKeywordByCategory(long categoryId)
	{
		String sql = "SELECT ck.keyword FROM CategoryKeyword ck WHERE ck.categoryId = :p0";
		return getCurrentSession().createQuery(sql).setLong("p0", categoryId).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDistinctKeyword()
	{
		Criteria crit = (Criteria) getCurrentSession().
                createCriteria(Keyword.class).
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.setProjection( Projections.distinct( Projections.property( "name" ) ) );
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Keyword> getKeywordUnderMonitoring(long userId,LanguageCode language) {
		String sql= "SELECT k FROM CategoryKeyword ck JOIN ck.category c JOIN ck.keyword k WHERE k.status"
				+ " = :status AND ck.userId = :user AND c.langCode = :langCode";
		return getCurrentSession().createQuery(sql).setParameter("status", KeywordStatus.TRACKING).setLong("user", userId).setParameter("langCode", language).list();
	}

	@Override
	public List<Keyword> getKeywordUnderTitleNull(LanguageCode language) {
		String sql= "SELECT k FROM CategoryKeyword ck JOIN ck.category c JOIN ck.keyword k WHERE c.langCode = :langCode";
		return getCurrentSession().createQuery(sql).setParameter("langCode", language).list();
	}

	@Override
	public void deleteKeyword(Keyword Keyword) {
		// TODO Auto-generated method stub
		cacheUtil.removeCacheWhenUpdateKeyWord();
		delete(Keyword);
	}
}
