/*
 * FILENAME
 *     Constants.java
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

package com.vsii.ttxvn.keywordsearching.utils;

/**
 * <p>
 * The class for purpose of hold application constants.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public final class Constants
{
	public static final String TEMP_DIRECTORY = "java.io.tmpdir";
	public static final String LINUX = "Linux";
	public static final String OS_NAME = "os.name";

	public static final String TTXVN_LOG = "TTXVN_LOG";
	public static final String TTXVN_NEWS_PREFIX = "TTXVN_News_";
	public static final String PDF_FILE_EXTENSION = ".pdf";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String OUTLINE_FILENAME = "outline;filename=\"";

	public static final String PAGE = "page";
	public static final String USERNAME = "username";
	public static final String CREATED_DATE = "createdDate";
	public static final String ID = "id";
	public static final String KEYWORD = "keyword";
	public static final String CATEGORY = "category";
	public static final String SOURCE_URL = "sourceUrl";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String URL = "url";
	public static final String SEARCH_KEYWORD = "searchKeyword";
	public static final String SEARCH_IN_TITLE_ONLY = "searchInTitleOnly";
	public static final String FIND_BY_ALL_USER_KEYWORD_UNDER_MONITORING = "findByAllUserKeywordUnderMonitoring";
	public static final String SEARCH_SOURCE_URL = "searchSourceUrl";

	public static final String SPACE = " ";
	public static final String MINUS_SIGN = "-";
	public static final String PLUS_SIGN = "+";
	public static final String ASTERISK = "*";
	public static final String UNDERSCORE = "_";
	public static final String EMPTY_STRING = "";
	public static final String NON_SPECIAL_CHAR_REGEX = "^[^!@#$%^&*()]+$";
	public static final String HYPERLINK_REGEX = "^(https?://)?([\\da-z\\.-]+).([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
	public static final String NOT_SEARCH_TITLE_TRUE= "^[^.,:/]+$";
	public static final String TTX_RESOURCES_FOLDER = "/ttxvn-resources/";
	public static final String ARIAL_FONT_PATH = "/ttxvn-resources/fonts/Arial Unicode MS.ttf";
	public static final String CRAWLING_SERVER_URI = "http://192.168.0.149:8080/ttxvn-ws/restful/crawling/crawl";

	public interface Paging
	{
		public static final int PAGE_SIZE = 10;
	}
}
