/*
 * FILENAME
 *     FetchFrequencyServiceImpl.java
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

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.CrawlingStatus;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.httpclient.solr.SolrOxmClient;
import com.vsii.ttxvn.keywordsearching.schedule.ScheduleService;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;

/**
 * <p>
 * An implementation of Fetch Frequency Service.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("fetchFrequencyService")
public class FetchFrequencyServiceImpl extends GenericDaoImpl implements FetchFrequencyService
{
    private static final Log logger = LogFactory.getLog(FetchFrequencyServiceImpl.class);
    
    @Autowired
    SolrOxmClient<SolrDoc> solrOxmClient;
    
	/**
	 * {@inheritDoc}
	 * 
	 * @throws Exception
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService#updateFetchFrequencies(long[],
	 *      int[])
	 */
	@Override
	public List<FetchFrequency> updateFetchFrequencies(long[] fetchFrequencyIds, int[] frequencies) throws Exception
	{
		List<FetchFrequency> fetchFrequencies = this.findByIds(FetchFrequency.class, fetchFrequencyIds);

		if (CollectionUtils.isNotEmpty(fetchFrequencies) && (fetchFrequencies.size() == frequencies.length))
		{
			FetchFrequency fetch;

			for (int i = 0; i < fetchFrequencies.size(); i++)
			{
				fetch = fetchFrequencies.get(i);
				int oldFrequency = fetch.getFrequency();
				fetch.setFrequency(frequencies[i]);
				if (oldFrequency != frequencies[i])
				{
					ScheduleService.delete(fetch);
					ScheduleService.schedule(fetch, frequencies[i]);
				}

			}
		}

		return this.update(fetchFrequencies);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SourceUrl> getListUrlByFrequency(FetchFrequency frequence)
	{
		String query = "FROM SourceUrl u WHERE u.fetchFrequency = :p0";

		return getCurrentSession().createQuery(query).setEntity("p0", frequence).list();
	}

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService#isCrawledUrl(java.lang.String)
     */
    @Override
    public boolean isCrawledUrl(String url)
    {
        SolrResponse solrResponse = solrOxmClient.searchIDByUrl(url, 0, 10, LanguageCode.VI);
        
        if (solrResponse != null && solrResponse.getNumFound() > 0)
        {
            logger.debug("------> [VI] Docs IDs related with {" + url + "} num found: " + solrResponse.getNumFound());
            return true;
        }
        
        solrResponse = solrOxmClient.searchIDByUrl(url, 0, 10, LanguageCode.EN);
        
        if (solrResponse != null && solrResponse.getNumFound() > 0)
        {
            logger.debug("------> [EN] Docs IDs related with {" + url + "} num found: " + solrResponse.getNumFound());
            return true;
        }
        
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService#getCrawlingStatus(boolean, java.lang.String)
     */
    @Override
    public CrawlingStatus getCrawlingStatus(boolean alive, String url)
    {
        boolean isCrawledUrl = isCrawledUrl(url);
        
        if (isCrawledUrl && alive) {
            return CrawlingStatus.CRAWLED;
        } else if (isCrawledUrl && !alive) {
            return CrawlingStatus.UN_REACHABLE;
        } else {
            return CrawlingStatus.NOT_CRAWLING;
        }
    }

}
