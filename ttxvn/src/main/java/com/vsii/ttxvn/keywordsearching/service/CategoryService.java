/*
 * FILENAME
 *     CategoryService.java
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
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The Category Service interface.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
public interface CategoryService extends GenericDao
{
	public List<Category> getListCategoryByLanguage(LanguageCode language);

	public Category getCategoryByName(String name, LanguageCode language);

	public String createCategory(String name, long userId, LanguageCode language);

	public String updateCategory(Category category, String name, long userId);

	public List<SourceUrl> getSourceUrlByCategory(Category category);
	
	public List<SourceUrl> getSourceUrlByCategoryLanguageCode(Category category, LanguageCode language);
	
}
