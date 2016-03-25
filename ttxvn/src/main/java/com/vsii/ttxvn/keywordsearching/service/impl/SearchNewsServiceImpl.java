/*
 * FILENAME
 *     SearchNewsServiceImpl.java
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.httpclient.solr.SolrOxmClient;
import com.vsii.ttxvn.keywordsearching.service.SearchNewsService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.StringUtils;

/**
 * <p>
 * An implementation of {@code SearchNewsService}.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("searchNewsService")
public class SearchNewsServiceImpl implements SearchNewsService
{
    @Autowired
    SolrOxmClient<SolrDoc> solrOxmClient;

    /**
     * {@inheritDoc}
     * 
     * @see com.vsii.ttxvn.keywordsearching.service.SearchNewsService#findNewspaper(com.vsii.ttxvn.keywordsearching.domain.SearchCriteria,
     *      com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest)
     */
    @Override
    public PageImpl<SolrDoc> findNewspaper(SearchCriteria criteria, TtxvnPageRequest pageRequest)
    {
        final SolrResponse solrResponse = solrOxmClient.searchTitleContent(criteria, pageRequest.getFirstElement(), pageRequest.getPageSize());
        List<SolrDoc> solrDocs = new ArrayList<SolrDoc>();
        long totalElements = 0;

        if (solrResponse != null)
        {
            totalElements = solrResponse.getNumFound();
            solrDocs = solrResponse.getSolrDocs();

            if (CollectionUtils.isNotEmpty(solrDocs))
            {
                for (SolrDoc solrDoc : solrDocs)
                {
                    solrDoc.setAbbreviateTitle(solrDoc.getTitle());
                    solrDoc.setAbbreviateUrl(StringUtils.abbreviate(solrDoc.getUrl(), 30));
                    solrDoc.setAbbreviateContent(StringUtils.abbreviate(solrDoc.getContent(), 100));
                }
            }
        }

        return new PageImpl<SolrDoc>(solrDocs, pageRequest, totalElements);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SolrDoc> findFreshNews(Date from, Date to, LanguageCode langCode)
    {
    	List<SourceUrl> sources = getSourceUrlService().getListSourceUrlByLanguage(langCode);

        final SolrResponse response = solrOxmClient.findFreshNews(from, to, langCode, sources);
        
        if (response == null) {
            return Collections.EMPTY_LIST;
        }
        
        return response.getSolrDocs();
    }
    
    public SourceUrlService getSourceUrlService() {
		return (SourceUrlService) ServiceResolver
				.findService(SourceUrlService.class);
	}

}