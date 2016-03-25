/*
 * FILENAME
 *     FetchFrequencyService.java
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

import java.util.List;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.CrawlingStatus;

/**
 * <p>
 * The class services for Fetch Frequency.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public interface FetchFrequencyService extends GenericDao
{
	public List<FetchFrequency> updateFetchFrequencies(long[] fetchFrequencyIds, int[] frequencies) throws Exception;

	public List<SourceUrl> getListUrlByFrequency(FetchFrequency frequance);
	
	public boolean isCrawledUrl(String url);
	
	public CrawlingStatus getCrawlingStatus(boolean alive, String url);
}
