/*
 * FILENAME
 *     FreshNewsRestController.java
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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.JobTracker;
import com.vsii.ttxvn.keywordsearching.entity.UserNotification;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;
import com.vsii.ttxvn.keywordsearching.service.SearchNewsService;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of get Fresh news and Notification.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Controller
@RequestMapping("/news")
public class FreshNewsRestController extends BaseController
{
    /*
	private static boolean hasNotification = false;
	private static Date lastFetchTime = null;
     */
	@RequestMapping("/getNews")
	@ResponseBody
	public Map<String, String> getFreshNews(HttpServletResponse response)
	{
		Map<String, String> responseData = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		// find news on time[current - 12h, current]
		cal.add(Calendar.HOUR_OF_DAY, -19);
		List<SolrDoc> docs = getSearchNewsService().findFreshNews(cal.getTime(), null,
				LanguageUtils.getCurrentLanguageCode());
		for (SolrDoc doc : docs)
		{
			responseData.put(doc.getUrl(), doc.getTitle()); 
		}
		return responseData;
	}

	@RequestMapping("/getNotification")
	@ResponseBody
	public Map<String, String> getNotification(HttpServletResponse response)
	{
		Map<String, String> responseData = new HashMap<String, String>();
		UserNotification notification = getFetchFrequencyService().findById(UserNotification.class, TtxvnUserContext.getCurrentUserId());
		boolean hasNotification = false;
	    Date lastFetchTime = null;
	    
        if (notification != null) {
            hasNotification = notification.isHasNotification();
        }

        JobTracker jobTracker = getFetchFrequencyService().findJobTrackerByName(FetchFrequency.class.getName());
        
        if (jobTracker != null) {
            lastFetchTime = jobTracker.getLastFired();
        }
        
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -120);
		
		if (hasNotification)
		{
			if (lastFetchTime != null)
			{
				cal.setTime(lastFetchTime);
				cal.add(Calendar.HOUR_OF_DAY, -7);
			}
			responseData.put("isNotify", "true");
		} else {
		    responseData.put("isNotify", "false");
		}

		List<SolrDoc> docs = getSearchNewsService().findFreshNews(cal.getTime(), null, LanguageUtils.getCurrentLanguageCode());
		
		for (SolrDoc doc : docs)
		{
			responseData.put(doc.getUrl(), doc.getTitle());
		}
		
		return responseData;
	}

	@RequestMapping(value = "/remove-notification", method = RequestMethod.GET)
	public void removeNotification(HttpServletRequest request)
	{
		UserNotification notification = getFetchFrequencyService().findById(UserNotification.class, TtxvnUserContext.getCurrentUserId());
		
		if (notification != null)
		{
			notification.setHasNotification(false);
			getFetchFrequencyService().update(notification);
		}
	}

	private SearchNewsService getSearchNewsService()
	{
		return ServiceResolver.findService(SearchNewsService.class);
	}
	/*
	private void checkNotificationStatus()
	{
		UserNotification notification = getFetchFrequencyService().findById(UserNotification.class, TtxvnUserContext.getCurrentUserId());
		
		if (notification != null) {
		    hasNotification = notification.isHasNotification();
		}

		JobTracker jobTracker = getFetchFrequencyService().findJobTrackerByName(FetchFrequency.class.getName());
		
		if (jobTracker != null) {
		    lastFetchTime = jobTracker.getLastFired();
		}
	}
	*/
	public FetchFrequencyService getFetchFrequencyService() {
	    return ServiceResolver.findService(FetchFrequencyService.class);
	}
}
