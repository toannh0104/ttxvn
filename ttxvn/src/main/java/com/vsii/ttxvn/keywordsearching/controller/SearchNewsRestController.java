/*
 * FILENAME
 *     SearchNewsRestController.java
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vsii.ttxvn.keywordsearching.databinder.SearchCriteriaEditor;
import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.service.SearchNewsService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The class for handling user searching news request.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/search")
public class SearchNewsRestController extends BaseController
{
	@RequestMapping(value = "/findByKeywordUrl", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public TtxvnPageWrapper<SolrDoc> findByKeywordUrl(@RequestParam("searchCriteria") SearchCriteria searchCriteria,
			HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException
	{
		TtxvnPageWrapper<SolrDoc> pageWrapper = null;
        
		if (searchCriteria != null)
		{
			log.debug("--> search all paper has: {keyword: " + searchCriteria.getKeyword() + ", url: "
					+ searchCriteria.getUrl() + ", page: " + searchCriteria.getPage() + "}");
			long userId = TtxvnUserContext.getCurrentUser().getId();
			int page = searchCriteria.getPage() - 1;

			if (page < 0)
			{
				page = 0;
			}

			searchCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
			
			if (searchCriteria.isFindByAllUserKeywordUnderMonitoring())
			{
				List<Keyword> keywordbyUser = getKeywordService().getKeywordUnderMonitoring(userId, searchCriteria.getLanguageCode());
				List<String> keywordNames = new ArrayList<String>();
				
				for(Keyword keyword : keywordbyUser)
				{
					keywordNames.add(keyword.getName());
				}
				
				searchCriteria.setKeywordByUser(keywordNames);
			}
			
			if ((!searchCriteria.isSearchInTitleOnly() && StringUtils.isBlank(searchCriteria.getKeyword())))
			{
				List<SourceUrl> sourceUrls = getSourceUrlService().getListSourceUrlByLanguage(searchCriteria.getLanguageCode());
				List<String> urlNames = new ArrayList<String>();
				for(SourceUrl url : sourceUrls)
				{
					urlNames.add(url.getUrl());
				}
				searchCriteria.setUrlByKeyword(urlNames);
			}
			
			if (searchCriteria.isSearchInTitleOnly()&& !StringUtils.isNoneBlank(searchCriteria.getKeyword()))
			{
				List<Keyword> keywords=getKeywordService().getKeywordUnderTitleNull(searchCriteria.getLanguageCode());
				List<String> keywordNames2 = new ArrayList<String>();
				
				for(Keyword keyword : keywords) 
				{
					keywordNames2.add(keyword.getName());
				}
				searchCriteria.setKeywords(keywordNames2);
			}
			
			if(searchCriteria.getKeyword().matches(Constants.NOT_SEARCH_TITLE_TRUE) || searchCriteria.getKeyword() == "")
			{
				final TtxvnPageRequest pageRequest = new TtxvnPageRequest(page, TtxvnPageWrapper.ITEM_PER_PAGE);
				final PageImpl<SolrDoc> solrPage = getSearchNewsService().findNewspaper(searchCriteria, pageRequest);
				pageWrapper = new TtxvnPageWrapper<SolrDoc>(solrPage, "", 0);
			}
			else
			{
				pageWrapper=null;
			}
		}
		
		return pageWrapper;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(SearchCriteria.class, new SearchCriteriaEditor());
	}

	/**
	 * <p>
	 * Getter for searchNewsService.
	 * </p>
	 * 
	 * @return the searchNewsService
	 */
	public SearchNewsService getSearchNewsService()
	{
		return ServiceResolver.findService(SearchNewsService.class);
	}
    public KeywordService getKeywordService()
	{
		return ServiceResolver.findService(KeywordService.class);
	}
    
    public SourceUrlService getSourceUrlService() {
    	return ServiceResolver.findService(SourceUrlService.class);
    }
}
