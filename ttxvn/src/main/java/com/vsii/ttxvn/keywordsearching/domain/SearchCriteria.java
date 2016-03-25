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

import java.util.List;

import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class for purpose of holding search criteria info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class SearchCriteria {
	private String keyword;
	private String url;
	private int page;
	private int[] selectNewsIds;
	private LanguageCode languageCode;
	private boolean searchKeyword;
	private boolean searchInTitleOnly;
	private boolean findByAllUserKeywordUnderMonitoring;
	private boolean searchSourceUrl;
	private List<String> keywordByUser;
	private List<String> keywords;
	private List<String> urlByKeyword;

	public SearchCriteria() {
		super();
	}

	public SearchCriteria(String keyword, String url) {
		super();
		this.keyword = keyword;
		this.url = url;
	}

	public SearchCriteria(String keyword, String url, int page) {
		super();
		this.keyword = keyword;
		this.url = url;
		this.page = page;
	}

	public SearchCriteria(String keyword, String url, boolean searchKeyword,
			int page) {
		super();
		this.keyword = keyword;
		this.url = url;
		this.searchKeyword = searchKeyword;
		this.page = page;
	}

	public SearchCriteria(String keyword, String url, boolean searchKeyword,
			boolean searchInTitleOnly,
			boolean findByAllUserKeywordUnderMonitoring,
			boolean searchSourceUrl, int page) {
		super();
		this.keyword = keyword;
		this.url = url;
		this.page = page;
		this.searchKeyword = searchKeyword;
		this.searchInTitleOnly = searchInTitleOnly;
		this.findByAllUserKeywordUnderMonitoring = findByAllUserKeywordUnderMonitoring;
		this.searchSourceUrl = searchSourceUrl;
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
	 * Getter for url.
	 * </p>
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * <p>
	 * Setting value for url.
	 * </p>
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * <p>
	 * Getter for selectNewsIds.
	 * </p>
	 * 
	 * @return the selectNewsIds
	 */
	public int[] getSelectNewsIds() {
		return selectNewsIds;
	}

	/**
	 * <p>
	 * Setting value for selectNewsIds.
	 * </p>
	 * 
	 * @param selectNewsIds
	 *            the selectNewsIds to set
	 */
	public void setSelectNewsIds(int[] selectNewsIds) {
		this.selectNewsIds = selectNewsIds;
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
	 * Getter for searchKeyword.
	 * </p>
	 * 
	 * @return the searchKeyword
	 */
	public boolean isSearchKeyword() {
		return searchKeyword;
	}

	/**
	 * <p>
	 * Setting value for searchKeyword.
	 * </p>
	 * 
	 * @param searchKeyword
	 *            the searchKeyword to set
	 */
	public void setSearchKeyword(boolean searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * <p>
	 * Getter for searchInTitleOnly.
	 * </p>
	 * 
	 * @return the searchInTitleOnly
	 */
	public boolean isSearchInTitleOnly() {
		return searchInTitleOnly;
	}

	/**
	 * <p>
	 * Setting value for searchInTitleOnly.
	 * </p>
	 * 
	 * @param searchInTitleOnly
	 *            the searchInTitleOnly to set
	 */
	public void setSearchInTitleOnly(boolean searchInTitleOnly) {
		this.searchInTitleOnly = searchInTitleOnly;
	}

	/**
	 * <p>
	 * Getter for findByAllUserKeywordUnderMonitoring.
	 * </p>
	 * 
	 * @return the findByAllUserKeywordUnderMonitoring
	 */
	public boolean isFindByAllUserKeywordUnderMonitoring() {
		return findByAllUserKeywordUnderMonitoring;
	}

	/**
	 * <p>
	 * Setting value for findByAllUserKeywordUnderMonitoring.
	 * </p>
	 * 
	 * @param findByAllUserKeywordUnderMonitoring
	 *            the findByAllUserKeywordUnderMonitoring to set
	 */
	public void setFindByAllUserKeywordUnderMonitoring(
			boolean findByAllUserKeywordUnderMonitoring) {
		this.findByAllUserKeywordUnderMonitoring = findByAllUserKeywordUnderMonitoring;
	}

	public boolean isSearchSourceUrl() {
		return searchSourceUrl;
	}

	public void setSearchSourceUrl(boolean searchSourceUrl) {
		this.searchSourceUrl = searchSourceUrl;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getKeywordByUser() {
		return keywordByUser;
	}

	public void setKeywordByUser(List<String> keywordByUser) {
		this.keywordByUser = keywordByUser;
	}

	public List<String> getUrlByKeyword() {
		return urlByKeyword;
	}

	public void setUrlByKeyword(List<String> urlByKeyword) {
		this.urlByKeyword = urlByKeyword;
	}
	
	
}
