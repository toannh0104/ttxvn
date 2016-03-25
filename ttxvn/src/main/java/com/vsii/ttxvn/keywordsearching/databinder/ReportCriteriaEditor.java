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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsii.ttxvn.keywordsearching.domain.ReportCriteria;
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
public class ReportCriteriaEditor extends PropertyEditorSupport {

	/**
	 * {@inheritDoc}
	 *
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		final ObjectMapper mapper = new ObjectMapper();
		ReportCriteria reportCriteria = null;

		try {
			final JsonNode root = mapper.readTree(text);
			final String category = root.path(Constants.CATEGORY).asText();
			final String keyword = root.path(Constants.KEYWORD).asText();
			final String sourceUrl = root.path(Constants.SOURCE_URL).asText();
			final String startDateStr = root.path(Constants.START_DATE)
					.asText();
			final String endDateStr = root.path(Constants.END_DATE).asText();
			Date startDate;
			Date endDate;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			startDate = convertStringToDate(startDateStr, formatter);
			endDate = convertStringToDate(endDateStr, formatter);

			int page = 0;

			if ((root.path(Constants.PAGE) != null)
					&& (root.path(Constants.PAGE).textValue().trim().length() > 0)) {
				page = root.path(Constants.PAGE).asInt();
			}

			reportCriteria = new ReportCriteria(category, keyword, sourceUrl,
					startDate, endDate, page);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setValue(reportCriteria);
	}

	private Date convertStringToDate(String stringDate, SimpleDateFormat formatter) {
		if (StringUtils.isNotBlank(stringDate)) {
			try {
				return formatter.parse(stringDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

}
