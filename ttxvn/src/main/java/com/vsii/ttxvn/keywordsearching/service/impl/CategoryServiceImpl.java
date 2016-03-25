/*
 * FILENAME
 *     CategoryServiceImpl.java
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.service.CategoryService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for implementation of CategoryService interface.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Service("categoryService")
public class CategoryServiceImpl extends GenericDaoImpl implements CategoryService
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getListCategoryByLanguage(LanguageCode code)
	{
		String sql = "FROM Category c WHERE langCode = :p1";
		return getCurrentSession().createQuery(sql).setParameter("p1", code).list();
	}

	@Override
	public Category getCategoryByName(String name, LanguageCode code)
	{
		if (name == null || name.isEmpty())
			return null;
		String sql = "FROM Category c WHERE c.name = :p1 AND langCode = :p2";
		return (Category) getCurrentSession().createQuery(sql).setString("p1", name)
				.setParameter("p2", code).uniqueResult();
	}

	@Override
	public String createCategory(String name, long userId, LanguageCode language)
	{
		if (userId <= 0 || name == null || name.isEmpty())
			return "Co loi xay ra khi luu chu de";
		Category category = new Category(name, language, userId, true);
		category.setLastModifiedUserId(userId);
		create(category);
		return Constants.EMPTY_STRING;
	}

	@Override
	public String updateCategory(Category category, String name, long userId)
	{
		if (userId <= 0 || name == null || name.isEmpty())
			return "Co loi xay ra khi luu chu de";
		category.setName(name);
		category.setLastModifiedUserId(userId);
		category.setLastModified(new Date());
		update(category);
		return Constants.EMPTY_STRING;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SourceUrl> getSourceUrlByCategory(Category category)
	{
		if (category == null)
			return Collections.EMPTY_LIST;
		String sql = "FROM SourceUrl WHERE category = :category";
		return getCurrentSession().createQuery(sql).setParameter("category", category).list();
	}

	@Override
	public List<SourceUrl> getSourceUrlByCategoryLanguageCode(
			Category category, LanguageCode language) {
		// TODO Auto-generated method stub
		if (category == null)
			return Collections.EMPTY_LIST;
		String sql = "FROM SourceUrl WHERE category = :category AND langCode = :langcode";
		return getCurrentSession().createQuery(sql).setParameter("category", category).setParameter("langcode", language).list();
	}
}
