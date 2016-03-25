/*
 * FILENAME
 *     CategoryRestController.java
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

package com.vsii.ttxvn.keywordsearching.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.service.CategoryService;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The controller for Category requests.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Controller
@RequestMapping("/category")
public class CategoryRestController extends BaseController
{
	private static final String REQUEST_PARAM_USER = "user";
	private static final String REQUEST_PARAM_CATEGORY = "category";
	private static final String REQUEST_PARAM_CATEGORYID = "categoryId";
	private static final int MAX_LENGTH = 250;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/loadCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findCategoriesByUser(HttpServletRequest request, HttpServletResponse response)
	{
		List<Category> categories = getCategoryService().getListCategoryByLanguage(
				LanguageUtils.getCurrentLanguageCode());
		Map<String, String> values = new HashMap<String, String>();
		for (Category category : categories)
		{
			values.put(category.getId().toString(), category.getName());
		}
		return values;
	}

	@RequestMapping(value = "/createCategory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> createCategory(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		Map<String, String> map = new HashMap<String, String>();
		long categoryId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORYID, -1);
		long userId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_USER, -1);
		String userName = null;
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null)
		{
			userId = currentUser.getId();
			userName=currentUser.getUsername();
		}
		String name = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_CATEGORY, Constants.EMPTY_STRING);
		Category category = getCategoryService().findById(Category.class, categoryId);
		String message = Constants.EMPTY_STRING;
		if (name.isEmpty())
			message = this.messageSource.getMessage("homepage.category.add.validate.required", null,
					LocaleContextHolder.getLocale());
		else if ((category == null || !name.equals(category.getName()))
				&& getCategoryService().getCategoryByName(name, LanguageUtils.getCurrentLanguageCode()) != null)
			message = this.messageSource.getMessage("homepage.category.add.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		else if (name.length() > MAX_LENGTH || !name.matches(Constants.NON_SPECIAL_CHAR_REGEX))
			message = this.messageSource.getMessage("homepage.category.add.validate.invalid", null,
					LocaleContextHolder.getLocale());
		if (message.isEmpty())
		{
			if (category == null)
			{
				getCategoryService().createCategory(name, userId, LanguageUtils.getCurrentLanguageCode());
				LOG_WRITER.info( userName+" CREATED Category name: " + name);
			}
			else
			{
				LOG_WRITER.info(userName+" UPDATED Category changed Name from [" + category.getName() + "] to ["
						+ name + "]");
				getCategoryService().updateCategory(category, name, userId);
			}
		}
		map.put("message", message);
		return map;
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> updateCategory(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, String> map = new HashMap<String, String>();
		long id = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORYID, -1);
		Category category = getCategoryService().findById(Category.class, id);
		if (category != null)
		{
			map.put(REQUEST_PARAM_CATEGORY, category.getName());
			map.put(REQUEST_PARAM_CATEGORYID, category.getId().toString());
		}
		return map;
	}

	@RequestMapping(value = "/deleteCategory")
	@ResponseBody
	@Transactional
	public Map<String, String> deleteCategory(HttpServletRequest request, HttpServletResponse response)
	{
		String username = Constants.EMPTY_STRING;
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null)
			username = currentUser.getUsername();
		Map<String, String> map = new HashMap<String, String>();
		long categoryId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORYID, -1);
		Category category = getCategoryService().findById(Category.class, categoryId);
		String msg = Constants.EMPTY_STRING;
		if (!getCategoryService().getSourceUrlByCategory(category).isEmpty()
				|| !getKeywordService().getKeywordByCategory(categoryId).isEmpty())
			msg = this.messageSource.getMessage("homepage.category.delete.validate", null,
					LocaleContextHolder.getLocale());
		else
		{
			LOG_WRITER.info(username + " DELETED Category " + category.getName());
			getCategoryService().delete(Category.class, categoryId);
		}
		map.put("message", msg);
		return map;
	}

	private CategoryService getCategoryService()
	{
		return ServiceResolver.findService(CategoryService.class);
	}

	private KeywordService getKeywordService()
	{
		return ServiceResolver.findService(KeywordService.class);
	}
}
