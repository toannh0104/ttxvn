/*
 * FILENAME
 *     SearchNewsService.java
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

package com.vsii.ttxvn.keywordsearching.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class services searching news.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/

public interface SearchNewsService
{
	public PageImpl<SolrDoc> findNewspaper(SearchCriteria criteria, TtxvnPageRequest pageRequest);

	public List<SolrDoc> findFreshNews(Date from, Date to, LanguageCode langCode);

}
