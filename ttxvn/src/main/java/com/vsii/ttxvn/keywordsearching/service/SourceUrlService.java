/*
 * FILENAME
 *     SourceUrlService.java
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

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.domain.SourceUrlItem;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class services for Source URL.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/

public interface SourceUrlService extends GenericDao
{
	public SourceUrl create(SourceUrl sourceUrl, Long categoryId, Long fetchFrequencyId);

	public SourceUrl update(SourceUrl sourceUrl, Long fetchFrequencyId);

	public List<SourceUrl> findByCagegoryUrl(Long categoryId, String url, int page, int pageSize, String orderBy);

	public long countByCategoryIdSourceUrl(Long categoryId, String url);

	public PageImpl<SourceUrl> getAllSourceUrl(Long categoryId, String url, PageRequest pageRequest);

	public SourceUrl findByCategory(Long categoryId, String url);

	public PageImpl<SourceUrlItem> getAllSourceUrlByCategoryLanguage(long categoryId, LanguageCode language,
			String url, PageRequest pageRequest);

	public SourceUrl findByUrl(String url);
	
	public List<SourceUrl> getListSourceUrlByLanguage(LanguageCode code);
}
