/*
 * FILENAME
 *     SearchCriteriaEditor.java
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

package com.vsii.ttxvn.keywordsearching.databinder;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.utils.Constants;

/**
 * <p>
 * The utility class that helps to bind JSON request parameters to properties of
 * object SearchCriteria.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class SearchCriteriaEditor extends PropertyEditorSupport {

	/**
	 * {@inheritDoc}
	 *
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		final ObjectMapper mapper = new ObjectMapper();
		SearchCriteria searchCriteria = null;

		try {
			final JsonNode root = mapper.readTree(text);
			final String keyword = root.path(Constants.KEYWORD).asText();
			final String url = root.path(Constants.URL).asText();
			final boolean searchKeyword = root.path(Constants.SEARCH_KEYWORD).asBoolean();
			final boolean searchInTitleOnly = root.path(Constants.SEARCH_IN_TITLE_ONLY).asBoolean();
			final boolean findByAllUserKeywordUnderMonitoring = root.path(Constants.FIND_BY_ALL_USER_KEYWORD_UNDER_MONITORING).asBoolean();
			final boolean searchSourceUrl = root.path(Constants.SEARCH_SOURCE_URL).asBoolean();
			int page = 0;

			if ((root.path(Constants.PAGE) != null) && (root.path(Constants.PAGE).textValue().trim().length() > 0)) {
				page = root.path(Constants.PAGE).asInt();
			}
			
			searchCriteria = new SearchCriteria(keyword, url, searchKeyword, searchInTitleOnly, findByAllUserKeywordUnderMonitoring, searchSourceUrl, page);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setValue(searchCriteria);
	}

}
