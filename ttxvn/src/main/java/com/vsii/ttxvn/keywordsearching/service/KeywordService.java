/*
 * FILENAME
 *     KeywordService.java
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

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.enums.KeywordStatus;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The Interface for Keyword service.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
public interface KeywordService extends GenericDao
{
	public List<Keyword> getListKeywordByNameStatusUserIdCategoryId(String name, long userId, long categoryId,
			KeywordStatus status);

	public Keyword getKeywordByNameUserIdCategoryId(String name, long userId, long categoryId);

	public List<Keyword> getKeywordByStatusNameUserIdCategoryId(String name, long userId, long categoryId,
			KeywordStatus status);

	public String createKeyword(String name, Date start, Date end, long userId, long categoryId);

	public String updateKeyword(Keyword keyword, long userId, long categoryId);

	public List<Keyword> getKeywordByCategory(long categoryId);
	
	public List<Keyword> getKeywordUnderMonitoring(long userId,LanguageCode language);
	
	public List<Keyword> getKeywordUnderTitleNull(LanguageCode language);
	
	public void deleteKeyword(Keyword keyword);

	List<String> getDistinctKeyword();
}
