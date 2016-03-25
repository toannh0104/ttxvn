/*
 * FILENAME
 *     SearchCriteria.java
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

package com.vsii.ttxvn.keywordsearching.domain;

import java.util.Date;

import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class for purpose of holding search criteria info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class ReportCriteria {
	private String category;
	private String keyword;
	private String sourceUrl;
	private Date startDate;
	private Date endDate;
	private LanguageCode languageCode;
	private int page; 
	
	public ReportCriteria() {
		super();
	}

	public ReportCriteria(String category, String keyword, String sourceUrl,
			Date startDate, Date endDate, int page) {
		super();
		this.category = category;
		this.keyword = keyword;
		this.sourceUrl = sourceUrl;
		this.startDate = startDate;
		this.endDate = endDate;
		this.page = page;
	}



	/**
	 * <p>
	 * Getter for category.
	 * </p>
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * <p>
	 * Setting value for category.
	 * </p>
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * <p>
	 * Getter for keyword.
	 * </p>
	 * 
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * <p>
	 * Setting value for keyword.
	 * </p>
	 * 
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * <p>
	 * Getter for sourceUrl.
	 * </p>
	 * 
	 * @return the sourceUrl
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}

	/**
	 * <p>
	 * Setting value for sourceUrl.
	 * </p>
	 * 
	 * @param sourceUrl
	 *            the sourceUrl to set
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/**
	 * <p>
	 * Getter for startDate.
	 * </p>
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * <p>
	 * Setting value for startDate.
	 * </p>
	 * 
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * <p>
	 * Getter for endDate.
	 * </p>
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * <p>
	 * Setting value for endDate.
	 * </p>
	 * 
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * <p>
	 * Getter for languageCode.
	 * </p>
	 * 
	 * @return the languageCode
	 */
	public LanguageCode getLanguageCode() {
		return languageCode;
	}

	/**
	 * <p>
	 * Setting value for languageCode.
	 * </p>
	 * 
	 * @param languageCode
	 *            the languageCode to set
	 */
	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * <p>
	 * Getter for page.
	 * </p>
	 * 
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * <p>
	 * Setting value for page.
	 * </p>
	 * 
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	
	
}
