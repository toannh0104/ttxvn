/*
 * FILENAME
 *     FetchFrequencyController.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * The class for purpose of handling all request about Fetch Frequency.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/manage/fetchFrequency")
public class FetchFrequencyController extends BaseController
{
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "")
	public ModelAndView viewFrequency()
	{
		return new ModelAndView("system-config/frequencyConfig");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateSourceUrl(HttpServletRequest request, HttpServletResponse response)
	{
		String[] fetchFrequencyIds = request.getParameterValues("ids[]");
		String[] frequencies = request.getParameterValues("frequencies[]");

		long[] ids = new long[fetchFrequencyIds.length];
		int[] values = new int[frequencies.length];
		String msg = Constants.EMPTY_STRING;
		for (int i = 0; i < frequencies.length; i++)
		{
			int frequency = -1;
			try
			{
				frequency = Integer.parseInt(frequencies[i]);
			}
			catch (NumberFormatException e)
			{
				frequency = -1;
			}
			if (frequency < 0)
				msg = this.messageSource.getMessage("ttxvn.keywordsearching.systemConfig.frequency.add.invalid1", null,
						LocaleContextHolder.getLocale());
			else if (frequency < 15 || frequency > 3600)
				msg = this.messageSource.getMessage("ttxvn.keywordsearching.systemConfig.frequency.add.invalid2", null,
						LocaleContextHolder.getLocale());
			else
			{
				ids[i] = Long.parseLong(fetchFrequencyIds[i]);
				values[i] = frequency;
			}
		}

		if (msg.isEmpty())
			try
			{
				getFetchFrequencyService().updateFetchFrequencies(ids, values);
				msg = this.messageSource.getMessage("ttxvn.keywordsearching.systemConfig.frequency.add.success", null,
						LocaleContextHolder.getLocale());
			}
			catch (Exception e)
			{
				msg = this.messageSource.getMessage("ttxvn.keywordsearching.systemConfig.frequency.add.failed", null,
						LocaleContextHolder.getLocale());
			}

		Map<String, String> message = new HashMap<String, String>();
		message.put("message", msg);
		return message;
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public Map<String, Integer> getAllFrequency(HttpServletResponse response)
	{
		List<FetchFrequency> frequencies = getFetchFrequencyService().findAll(FetchFrequency.class);
		Map<String, Integer> responseData = new HashMap<String, Integer>();
		for (FetchFrequency frequency : frequencies)
			responseData.put(frequency.getId().toString(), frequency.getFrequency());
		return responseData;
	}

	/**
	 * <p>
	 * Getter for fetchFrequencyService.
	 * </p>
	 * 
	 * @return the fetchFrequencyService
	 */
	public FetchFrequencyService getFetchFrequencyService()
	{
		return (FetchFrequencyService) ServiceResolver.findService(FetchFrequencyService.class);
	}

}
