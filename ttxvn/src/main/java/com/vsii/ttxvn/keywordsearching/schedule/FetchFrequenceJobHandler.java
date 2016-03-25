/*
 * FILENAME
 *     FetchFrequenceJobHandler.java
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

package com.vsii.ttxvn.keywordsearching.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestOperations;

import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.domain.SourceUrlVO;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.JobTracker;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.entity.UserNotification;
import com.vsii.ttxvn.keywordsearching.httpclient.solr.SolrOxmClient;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.DateUtils;
import com.vsii.ttxvn.keywordsearching.utils.PropertiesResolver;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.StringUtils;

/**
 * <p>
 * The class for handling FetchFrequence job.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@DisallowConcurrentExecution
public class FetchFrequenceJobHandler extends JobHandler
{
	private static String CRAWLING_WEB_SERVICE_URI = PropertiesResolver.getProperty("com.vsii.ttxvn.crawling.webservice.crawling.crawl.url");
	private SolrOxmClient<SolrDoc> oxmClient;
	private Date lastFetched;
	
	@Override
	public boolean canHandleJob(Class<? extends JobObject> job)
	{
		return job != null && FetchFrequency.class.isAssignableFrom(job);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		LOG_WRITER.info("<----- Start calling webservice for crawling data ----->");
		lastFetched = context.getPreviousFireTime();
		
		JobTracker jobTracker = getFetchFrequencyService().findJobTrackerByName(FetchFrequency.class.getName());
		
		if (jobTracker != null)
		{
			jobTracker.setDone(false);
			jobTracker = getFetchFrequencyService().update(jobTracker);
		}

		try
		{
			SchedulerContext schedulerContext = context.getScheduler().getContext();
			ApplicationContext context2 = (ApplicationContext) schedulerContext.get("applicationContext");
			oxmClient = (SolrOxmClient<SolrDoc>) context2.getBean(SolrOxmClient.class);
		}
		catch (SchedulerException e1)
		{
			logger.error(e1.getMessage());
		}
		
		long id = Long.parseLong(context.getJobDetail().getKey().getName());
		
		LOG_WRITER.info("<----- PhuNV ----->");
		LOG_WRITER.info("pre fired time: " + context.getPreviousFireTime());
		logger.debug("---------------------> pre fired time: " + context.getPreviousFireTime());
		
		handleJob((Class<? extends JobObject>) this.getClass(), id);
		
		if (jobTracker != null)
		{
		    logger.debug("---------------------> scheduled time: " + context.getScheduledFireTime());
		    logger.debug("---------------------> next fired time: " + context.getNextFireTime());
		    logger.debug("---------------------> pre fired time: " + context.getPreviousFireTime());
		    logger.debug("---------------------> pre fired time2: " + lastFetched);
		    logger.debug("---------------------> fired time: " + context.getFireTime());
//			jobTracker.setLastFired(context.getPreviousFireTime());
			jobTracker.setLastFired(lastFetched);
			jobTracker.setDone(true);
			getFetchFrequencyService().update(jobTracker);
		}

		List<UserNotification> notifications = getFetchFrequencyService().findAll(UserNotification.class);
		
		for (UserNotification notification : notifications)
		{
			notification.setHasNotification(true);
			getFetchFrequencyService().update(notification);
		}
		LOG_WRITER.info("<----- Finish crawling data ----->");
	}

	@Override
	public void handleJob(Class<? extends JobObject> job, long jobId)
	{
		FetchFrequency frequency = getFetchFrequencyService().findById(FetchFrequency.class, jobId);

		if (frequency != null)
		{
			try
			{
				List<SourceUrl> urls = getFetchFrequencyService().getListUrlByFrequency(frequency);
				requestCrawling(urls);
			}
			catch (Exception e)
			{
				logger.error(e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
    private void requestCrawling(List<SourceUrl> sourceUrls)
	{
	    if (sourceUrls == null || sourceUrls.size() == 0) {
	        return;
	    }
	    
	    try
        {
	        List<SourceUrlVO> sourceUrlsToFetch = new ArrayList<SourceUrlVO>();
	        SourceUrlVO sourceUrlVO;
	        
	        for (SourceUrl url : sourceUrls)
	        {
	            sourceUrlVO = new SourceUrlVO();
	            sourceUrlVO.setId(url.getId());
	            sourceUrlVO.setUrl(url.getUrl());
	            sourceUrlVO.setDomain(url.getDomain());
	            sourceUrlVO.setReliability(url.getReliability());
	            sourceUrlVO.setLangCode(url.getCategory().getLangCode());
	            sourceUrlVO.setFetchDeep(url.getFetchDeep());
	            sourceUrlVO.setCrawlingStatus(url.getCrawlingStatus());
	            sourceUrlsToFetch.add(sourceUrlVO);
	        }
	        
	        List<LinkedHashMap<String, Object>> responses = getRestOperations().postForObject(CRAWLING_WEB_SERVICE_URI, sourceUrlsToFetch, List.class);
	        SourceUrl sourceUrl;
	        long sourceUrlId;
	        boolean alive;
	        
	        for (LinkedHashMap<String, Object> map : responses)
	        {
	            try
                {
	                sourceUrlId = Long.parseLong(map.get("id").toString());
	                sourceUrl = getSourceUrlService().findById(SourceUrl.class, sourceUrlId);
	                sourceUrl.setLastFetched(new Date());
	                alive = (boolean) map.get("alive");
	                sourceUrl.setCrawlingStatus(getFetchFrequencyService().getCrawlingStatus(alive, sourceUrl.getUrl()));
	                getFetchFrequencyService().update(sourceUrl);
	                // write info to .log file
	                writeLog(sourceUrl);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
	        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	private void writeLog(SourceUrl url)
	{
		String from = "*";
		if (lastFetched != null)
			from = DateUtils.getISODate(lastFetched);
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(SolrOxmClient.QUERY_PARAM, "url:\"" + StringUtils.escapeSorlQueryChars(url.getUrl()) + "\"");
		queryParams.put(SolrOxmClient.QUERY_RESPONSE_WRITER, SolrOxmClient.JSON_RESPONSE);
		queryParams.put(SolrOxmClient.QUERY_FIELD_LIST, "url");
		queryParams.put(SolrOxmClient.QUERY_FILTER, "tstamp:[" + from + " TO *]");
		SolrResponse response = oxmClient.searchBySolr(queryParams, url.getCategory().getLangCode().getCode());
		LOG_WRITER.info("Link [" + url.getUrl() + "] found " + response.getNumFound() + " fresh news");
	}

	public FetchFrequencyService getFetchFrequencyService()
	{
		return ServiceResolver.findService(FetchFrequencyService.class);
	}
	
	public SourceUrlService getSourceUrlService()
    {
        return ServiceResolver.findService(SourceUrlService.class);
    }
	
	public RestOperations getRestOperations() {
        return ServiceResolver.findService(RestOperations.class);
    }
    
}
