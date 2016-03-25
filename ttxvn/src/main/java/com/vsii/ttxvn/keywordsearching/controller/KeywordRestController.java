/*
 * FILENAME
 *     KeywordRestController.java
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.enums.KeywordStatus;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.service.ReportSummaryService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.DateUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The controller for Keyword requests.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Controller
@RequestMapping("/keyword")
public class KeywordRestController extends BaseController
{
	private static final String REQUEST_PARAM_KEYWORD = "keyword";
	private static final String REQUEST_PARAM_KEYWORDID = "keywordId";
	private static final String REQUEST_PARAM_USER = "user";
	private static final String REQUEST_PARAM_CATEGORY = "category";
	private static final String REQUEST_PARAM_START = "start";
	private static final String REQUEST_PARAM_END = "end";
	private static final String RESPONSE_KEYWORD_FOLLOW = "follow";
	private static final String RESPONSE_KEYWORD_PAUSE = "pause";
	private static final String RESPONSE_KEYWORD_STOP = "stop";
	private static final int MAX_LENGTH = 250;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/loadKeyword", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Map<String, String>> findKeywordByStatus(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		String name = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_KEYWORD, Constants.EMPTY_STRING);
		long userId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_USER, -1);
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null)
		{
			userId = currentUser.getId();
		}
		long categoryId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORY, -1);
		List<Keyword> follow_keywords = getKeywordService().getListKeywordByNameStatusUserIdCategoryId(name, userId,
				categoryId, KeywordStatus.TRACKING);
		List<Keyword> pause_keywords = getKeywordService().getListKeywordByNameStatusUserIdCategoryId(name, userId,
				categoryId, KeywordStatus.PAUSE_TRACKING);
		List<Keyword> stop_keywords = getKeywordService().getListKeywordByNameStatusUserIdCategoryId(name, userId,
				categoryId, KeywordStatus.END_TRACKING);
		Map<String, Map<String, String>> keywords = new HashMap<String, Map<String, String>>();
		keywords.put(RESPONSE_KEYWORD_FOLLOW, createKeywordMap(follow_keywords));
		keywords.put(RESPONSE_KEYWORD_PAUSE, createKeywordMap(pause_keywords));
		keywords.put(RESPONSE_KEYWORD_STOP, createKeywordMap(stop_keywords));
		return keywords;
	}

	@RequestMapping(value = "/createKeyword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> createKeyword(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		String name = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_KEYWORD, Constants.EMPTY_STRING);
		String startDate = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_START, Constants.EMPTY_STRING);
		String endDate = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_END, Constants.EMPTY_STRING);
		long categoryId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORY, -1);
		long userId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_USER, -1);
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		String username = Constants.EMPTY_STRING;
		if (currentUser != null)
		{
			userId = currentUser.getId();
			username = currentUser.getUsername();
		}
		Map<String, String> map = new HashMap<String, String>();
		String message = Constants.EMPTY_STRING;
		name = name.trim();
		Date start = DateUtils.convertStringToDate(startDate, DateUtils.DD_MM_YYYY);
		Date end = DateUtils.convertStringToDate(endDate, DateUtils.DD_MM_YYYY);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -1);
			
		if (name.isEmpty())
			message = this.messageSource.getMessage("homepage.keyword.add.validate.required2", null,
					LocaleContextHolder.getLocale());
		else if (startDate.isEmpty() || endDate.isEmpty())
			message = this.messageSource.getMessage("homepage.keyword.add.validate.required", null,
					LocaleContextHolder.getLocale());
		else if (getKeywordService().getKeywordByNameUserIdCategoryId(name, userId, categoryId) != null)
			message = this.messageSource.getMessage("homepage.keyword.add.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		else if (name.length() > MAX_LENGTH || !name.matches(Constants.NON_SPECIAL_CHAR_REGEX))
			message = this.messageSource.getMessage("homepage.keyword.add.validate.invalid", null,
					LocaleContextHolder.getLocale());
		else if (start == null || end == null || !start.after(now.getTime()) || start.after(end))
			message = this.messageSource.getMessage("homepage.keyword.add.validate.date", null,
					LocaleContextHolder.getLocale());
		if (message.isEmpty())
		{
			message = getKeywordService().createKeyword(name, start, end, userId, categoryId);
			LOG_WRITER.info(username + " CREATED Keyword name " + name);
		}
		map.put("message", message);
		
		return map;
	}
	
	@RequestMapping(value = "/updateKeyword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateKeyword(HttpServletRequest request, HttpServletResponse response){
		long keywordId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_KEYWORDID, -1);
		String name = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_KEYWORD, Constants.EMPTY_STRING);
		String startDate = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_START, Constants.EMPTY_STRING);
		String endDate = ServletRequestUtils.getStringParameter(request, REQUEST_PARAM_END, Constants.EMPTY_STRING);
		long categoryId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_CATEGORY, -1);
		long userId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_USER, -1);
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		String username = Constants.EMPTY_STRING;
		if (currentUser != null)
		{
			userId = currentUser.getId();
			username = currentUser.getUsername();
		}
		Map<String, String> map = new HashMap<String, String>();
		String message = Constants.EMPTY_STRING;
		String regex = Constants.NON_SPECIAL_CHAR_REGEX;
		name = name.trim();
		Date start = DateUtils.convertStringToDate(startDate, DateUtils.DD_MM_YYYY);
		Date end = DateUtils.convertStringToDate(endDate, DateUtils.DD_MM_YYYY);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -1);
		Keyword keyword = getKeywordService().findById(Keyword.class, keywordId);
		if (name.isEmpty())
			message = this.messageSource.getMessage("homepage.keyword.add.validate.required2", null,
					LocaleContextHolder.getLocale());
		else if (startDate.isEmpty() || endDate.isEmpty())
			message = this.messageSource.getMessage("homepage.keyword.add.validate.required", null,
					LocaleContextHolder.getLocale());
		else if ((keyword == null || !name.equals(keyword.getName()))
				&& getKeywordService().getKeywordByNameUserIdCategoryId(name, userId, categoryId) != null)
			message = this.messageSource.getMessage("homepage.keyword.add.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		else if (name.length() > MAX_LENGTH || !name.matches(regex))
			message = this.messageSource.getMessage("homepage.keyword.add.validate.invalid", null,
					LocaleContextHolder.getLocale());
		else if (start == null || end == null || (keyword == null && !start.after(now.getTime())) || start.after(end))
			message = this.messageSource.getMessage("homepage.keyword.add.validate.date", null,
					LocaleContextHolder.getLocale());
		if (message.isEmpty())
		{
			if (!keyword.getName().equals(name))
				LOG_WRITER.info(username + " UPDATED Keyword changed Name: from [" + keyword.getName() + "] to ["
						+ name + "]");
			if (!keyword.getStartDate().equals(start))
				LOG_WRITER.info(username + " UPDATED Keyword changed Start Date: from ["
						+ keyword.getStartDate().toString() + "] to [" + start.toString() + "]");
			if (!keyword.getEndDate().equals(end))
				LOG_WRITER.info(username + " UPDATED Keyword changed End Date: from ["
						+ keyword.getEndDate().toString() + "] to [" + end.toString() + "]");
			if (!keyword.getCategoryKeyword().getCategoryId().equals(categoryId))
			{
				Category category = getKeywordService().findById(Category.class, categoryId);
				LOG_WRITER.info(username + " UPDATED Keyword changed Category: from ["
						+ keyword.getCategoryKeyword().getCategoryName() + "] to [" + category.getName() + "]");
			}
			keyword.setName(name);
			keyword.setStartDate(start);
			keyword.setEndDate(end);
			keyword.setLastModified(new Date());
			message = getKeywordService().updateKeyword(keyword, userId, categoryId);
		}
		map.put("message", message);
		return map;
	}

	@RequestMapping(value = "/deleteKeyword")
	public void deleteKeyword(HttpServletRequest request, HttpServletResponse response)
	{
		String username = Constants.EMPTY_STRING;
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null)
			username = currentUser.getUsername();
		long keywordId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_KEYWORD, -1);
		Keyword keyword = getKeywordService().findById(Keyword.class, keywordId);
		LOG_WRITER.info(username + " DELETED Keyword " + keyword.getName());
		getKeywordService().deleteKeyword(keyword);
	
	}

	@RequestMapping(value = "/keywordStatus")
	public void changeKeywordStatus(HttpServletRequest request, HttpServletResponse response)
	{
		String username = Constants.EMPTY_STRING;
		TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();
		if (currentUser != null)
			username = currentUser.getUsername();
		long keywordId = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_KEYWORD, -1);
		Keyword keyword = getKeywordService().findById(Keyword.class, keywordId);
		if (keyword != null)
		{
			if (KeywordStatus.TRACKING.equals(keyword.getStatus()))
			{
				keyword.setStatus(KeywordStatus.PAUSE_TRACKING);
				LOG_WRITER.info(username + " UPDATED Keyword " + keyword.getName() + " changed Status: from ["
						+ KeywordStatus.TRACKING
						+ "] to ["
						+ KeywordStatus.PAUSE_TRACKING + "]");
			}
			else
			{
				keyword.setStatus(KeywordStatus.TRACKING);
				LOG_WRITER.info(username + " UPDATED Keyword " + keyword.getName() + " changed Status: from ["
						+ KeywordStatus.PAUSE_TRACKING
						+ "] to [" + KeywordStatus.TRACKING + "]");
			}
			getKeywordService().update(keyword);
		}
	}

	@RequestMapping(value = "/loadKeywordDetail", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadKeywordDetails(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, String> map = new HashMap<String, String>();
		long id = ServletRequestUtils.getLongParameter(request, REQUEST_PARAM_KEYWORD, -1);
		Keyword keyword = getKeywordService().findById(Keyword.class, id);
		if (keyword != null)
		{
			map.put(REQUEST_PARAM_START, DateUtils.format(keyword.getStartDate(), DateUtils.DD_MM_YYYY));
			map.put(REQUEST_PARAM_END, DateUtils.format(keyword.getEndDate(), DateUtils.DD_MM_YYYY));
			map.put(REQUEST_PARAM_CATEGORY, keyword.getCategoryKeyword().getCategoryId().toString());
			map.put(REQUEST_PARAM_KEYWORD, keyword.getName());
			map.put(REQUEST_PARAM_KEYWORDID, keyword.getId().toString());
		}
		return map;
	}

	private KeywordService getKeywordService()
	{
		return ServiceResolver.findService(KeywordService.class);
	}
	public ReportSummaryService getReportService() {
	     return ServiceResolver.findService(ReportSummaryService.class);
	}
	private Map<String, String> createKeywordMap(List<Keyword> keywords)
	{
		Map<String, String> map = new HashMap<String, String>();
		for (Keyword keyword : keywords)
			map.put(keyword.getId().toString(), keyword.getName());
		return map;
	}

}
