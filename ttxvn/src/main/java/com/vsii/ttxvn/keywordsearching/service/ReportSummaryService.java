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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageImpl;

import com.vsii.ttxvn.keywordsearching.domain.ReportCriteria;
import com.vsii.ttxvn.keywordsearching.domain.ReportItem;
import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.CategoryKeyword;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.service.impl.ReportSummaryServiceImpl;

/**
 * <p>
 * The class services searching news.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/

public interface ReportSummaryService
{	
	
	public PageImpl<ReportItem> queryReport(ReportCriteria criteria, TtxvnPageRequest pageRequest);
	
	public List<ReportItem> getListReportItem();
	
	public List<SolrDoc> report(Date from, Date to, LanguageCode langCode);
	
	public void init(Map map);
		
	public Map getMapKeywordNameByNumber(); 		

	
}
