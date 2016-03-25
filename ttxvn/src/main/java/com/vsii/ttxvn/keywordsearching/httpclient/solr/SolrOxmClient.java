/*
 * FILENAME
 *     SourceUrlController.java
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

package com.vsii.ttxvn.keywordsearching.httpclient.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.util.Assert;

import com.vsii.ttxvn.keywordsearching.domain.ReportCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.httpclient.HttpClientOxmTemplate;
import com.vsii.ttxvn.keywordsearching.httpclient.HttpClientTemplate;
import com.vsii.ttxvn.keywordsearching.httpclient.ResponseCallback;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.DateUtils;

/**
 * <p>
 * Specialized client for processing requests with Solr that are marshalled and
 * unmarshalled.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 */
public class SolrOxmClient<T> implements InitializingBean, DisposableBean {

	final Logger logger = LoggerFactory.getLogger(SolrOxmClient.class);

	public static final String SELECT = "select";
	public static final String UPDATE = "update";

	public static final String QUERY_PARAM = "q";
	public static final String QUERY_RESPONSE_WRITER = "wt";
	public static final String QUERY_OFFSET = "start";
	public static final String QUERY_MAX_ROW_RETURN = "rows";
	public static final String QUERY_FIELD_LIST = "fl";
	public static final String QUERY_SORT = "sort";
	public static final String QUERY_OR = "OR";
	public static final String QUERY_AND = "AND";
	public static final String QUERY_NOT = "NOT";
	public static final String BRACKET_OPEN = "(";
	public static final String BRACKET_CLOSE = ")";
	public static final String QUERY_FILTER = "fq";
	public static final String FIELD_LIST = "id url title content-en content-vi tstamp reliability lang-code";
	public static final String FIELD_LIST_ID_ONLY = "id";
	public static final String JSON_RESPONSE = "json";
	public static final String SORT_RELIABILITY_TSTAMP_DESC = "reliability desc, tstamp desc";
	public static final String NOT_IN = "-";

	protected static final String SELECT_URL_SUFFIX = "/select";
	protected static final String UPDATE_URL_SUFFIX = "/update";
	protected static final String DELETE_ELEMENT_NAME = "delete";
	protected static final String DELETE_ID_ELEMENT_NAME = "id";
	protected static final String DELETE_QUERY_ELEMENT_NAME = "query";
	protected static final String COMMIT_ELEMENT_NAME = "commit";
	protected static final String ROLLBACK_REQUEST = "<rollback/>";
	protected static final String OPTIMIZE_ELEMENT_NAME = "optimize";

	// public static List

	protected HttpClientOxmTemplate<List<T>> selectTemplate;
	protected HttpClientOxmTemplate<List<T>> selectTemplateVi;
	protected HttpClientOxmTemplate<List<T>> selectTemplateEn;

	protected HttpClientOxmTemplate<List<T>> updateTemplate;
	protected HttpClientOxmTemplate<List<T>> updateTemplateVi;
	protected HttpClientOxmTemplate<List<T>> updateTemplateEn;

	protected HttpClientTemplate postTemplate;
	protected HttpClientTemplate postTemplateVi;
	protected HttpClientTemplate postTemplateEn;

	protected String baseUrlVi;
	protected String baseUrlEn;
	protected Marshaller marshaller;
	protected Unmarshaller unmarshaller;

	protected Map<String, HttpClientOxmTemplate<List<T>>> mapSolrTemplateByDmlLangcode = new HashMap<String, HttpClientOxmTemplate<List<T>>>();

	/**
	 * <p>
	 * Initializes <code>HttpClientTemplate</code>s.
	 * </p>
	 * 
	 * <p>
	 * Implementation of <code>InitializingBean</code>.
	 * </p>
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(baseUrlVi);
		Assert.notNull(baseUrlEn);
		Assert.notNull(marshaller);
		Assert.notNull(unmarshaller);

		selectTemplate = new HttpClientOxmTemplate<List<T>>(getSelectUrlVi());
		selectTemplate.setMarshaller(marshaller);
		selectTemplate.setUnmarshaller(unmarshaller);
		// call init
		selectTemplate.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(SELECT, selectTemplate);

		selectTemplateVi = new HttpClientOxmTemplate<List<T>>(getSelectUrlVi());
		selectTemplateVi.setMarshaller(marshaller);
		selectTemplateVi.setUnmarshaller(unmarshaller);
		// call init
		selectTemplateVi.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(SELECT + "-" + LanguageCode.VI,
				selectTemplateVi);

		selectTemplateEn = new HttpClientOxmTemplate<List<T>>(getSelectUrlEn());
		selectTemplateEn.setMarshaller(marshaller);
		selectTemplateEn.setUnmarshaller(unmarshaller);
		// call init
		selectTemplateEn.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(SELECT + "-" + LanguageCode.EN,
				selectTemplateEn);

		updateTemplate = new HttpClientOxmTemplate<List<T>>(getUpdateUrlVi());
		updateTemplate.setMarshaller(marshaller);
		updateTemplate.setUnmarshaller(unmarshaller);
		// call init
		updateTemplate.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(UPDATE, updateTemplate);

		updateTemplateVi = new HttpClientOxmTemplate<List<T>>(getUpdateUrlVi());
		updateTemplateVi.setMarshaller(marshaller);
		updateTemplateVi.setUnmarshaller(unmarshaller);
		// call init
		updateTemplateVi.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(UPDATE + "-" + LanguageCode.VI,
				updateTemplateVi);

		updateTemplateEn = new HttpClientOxmTemplate<List<T>>(getUpdateUrlEn());
		updateTemplateEn.setMarshaller(marshaller);
		updateTemplateEn.setUnmarshaller(unmarshaller);
		// call init
		updateTemplateEn.afterPropertiesSet();
		mapSolrTemplateByDmlLangcode.put(UPDATE + "-" + LanguageCode.EN,
				updateTemplateEn);

		postTemplate = new HttpClientTemplate(getUpdateUrlVi(), true);
		postTemplateVi = new HttpClientTemplate(getUpdateUrlVi(), true);
		postTemplateEn = new HttpClientTemplate(getUpdateUrlEn(), true);
	}

	/**
	 * <p>
	 * Calls destroy on <code>HttpClientTemplate</code>.
	 * </p>
	 * 
	 * <p>
	 * Implementation of <code>DisposableBean</code>.
	 * </p>
	 */
	public void destroy() throws Exception {
		selectTemplate.destroy();
		selectTemplateVi.destroy();
		selectTemplateEn.destroy();
		updateTemplate.destroy();
		updateTemplateVi.destroy();
		updateTemplateEn.destroy();
		postTemplate.destroy();
		postTemplateVi.destroy();
		postTemplateEn.destroy();
	}

	/**
	 * Search with XML response.
	 * 
	 * @param query
	 *            Search query.
	 */
	public List<T> searchWithXMLResponse(String query) {
		Map<String, String> hParams = new HashMap<String, String>();
		hParams.put(QUERY_PARAM, query);

		return search(hParams);
	}

	/**
	 * Search.
	 * 
	 * @param query
	 *            Search query.
	 * @param queryParams
	 *            <code>Map</code> of query parameters.
	 */
	public List<T> search(String query, Map<String, String> queryParams) {
		queryParams.put(QUERY_PARAM, query);

		return search(queryParams);
	}

	/**
	 * Search.
	 * 
	 * @param queryParams
	 *            <code>Map</code> of query parameters.
	 */
	public List<T> search(final Map<String, String> queryParams) {
		final List<T> results = new ArrayList<T>();

		selectTemplate.executeGetMethod(queryParams,
				new ResponseCallback<List<T>>() {
					public void doWithResponse(List<T> beans)
							throws IOException {
						results.addAll(beans);
					}
				});

		return results;
	}

	/**
	 * Search specific keyword in Title/Content and match URL request from user
	 * with JSON response.
	 * 
	 * @param keyword
	 *            the keyword.
	 * @param offset
	 *            the offset
	 * @param maxRow
	 *            the max return row
	 * @return the Solr Response
	 */
	public SolrResponse searchTitleContent(SearchCriteria criteria,
			long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();

		if ((criteria != null)) {
			StringBuffer query = new StringBuffer();
			String queryContentField = "content-vi"; // default will search in Vietnamese content
			String langCode = "vi";

			if (criteria.getLanguageCode() == LanguageCode.EN) {
				queryContentField = "content-en";
				langCode = "en";
			}

			if (!criteria.isFindByAllUserKeywordUnderMonitoring()) {
				if ((StringUtils.isNoneBlank(criteria.getKeyword()) || StringUtils.isNoneBlank(criteria.getUrl()))) {
					if (!criteria.isSearchInTitleOnly()) {
							if (criteria.getKeyword() == "" && criteria.getUrl() != "") {
								String escapeUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(criteria.getUrl());
								query.append(BRACKET_OPEN)
										.append("lang-code:\"")
										.append(langCode.toUpperCase())
										.append("\"").append(Constants.SPACE)
										.append(QUERY_OR)
										.append(Constants.SPACE)
										.append("lang-code:\"")
										.append(langCode).append("\"")
										.append(BRACKET_CLOSE)
										.append(Constants.SPACE)
										.append(QUERY_AND)
										.append(Constants.SPACE)
										.append("url:")
										.append(BRACKET_OPEN);
								query.append("*").append(escapeUrl).append("*");
								query.append(BRACKET_CLOSE)
										// NOT IN http://getUrl.com/
										.append(Constants.SPACE)
										.append(QUERY_NOT)
										.append(Constants.SPACE)
										.append("url:")
										.append(BRACKET_OPEN)
										.append("\"")
										.append("http://")
										.append(escapeUrl)
										.append("/")
										.append("\"")
										// NOT IN http://getUrl.com
										.append("\"")
										.append("http://")
										.append(escapeUrl)
										.append("\"")
										// NOT IN http://www.getUrl.com/
										.append("\"").append("http://www.")
										.append(escapeUrl)
										.append("/")
										.append("\"")
										// NOT IN http://www.getUrl.com
										.append("\"").append("http://www.")
										.append(escapeUrl).append("\"")
										.append(BRACKET_CLOSE);
							}
							else if (criteria.getUrl() == "" && criteria.getKeyword() != "")
							{
								return SolrResponseSearchKeyword(criteria, langCode, queryContentField, offset, maxRow);
							} 
							else if (criteria.getUrl() != "" && criteria.getKeyword() != "")
							{
								return SolrResponseSearchKeywordUrl(criteria, "url", langCode, queryContentField, offset, maxRow);
							}
					} else {
						return SolrResponseSearchTitle(criteria, langCode, offset, maxRow);
					}
				} else {
					if (!StringUtils.isNoneBlank(criteria.getKeyword()))
					{
						query = new StringBuffer();
						query.append(BRACKET_OPEN).append("title").append(":")
								.append(BRACKET_OPEN);
						if (!criteria.getKeywords().isEmpty() && criteria.getKeywords().size() > 0) {
							for (String nameKeyword : criteria.getKeywords()) {
								query.append("\"").append(nameKeyword)
										.append("\"");

							}
							query.append(BRACKET_CLOSE).append(BRACKET_CLOSE)
									.append(Constants.SPACE).append(QUERY_AND)
									.append(Constants.SPACE)
									.append(BRACKET_OPEN)
									.append("lang-code:\"")
									.append(langCode.toUpperCase())
									.append("\"").append(Constants.SPACE)
									.append(QUERY_OR).append(Constants.SPACE)
									.append("lang-code:\"").append(langCode)
									.append("\"").append(BRACKET_CLOSE);
						} else {
							return null;
						}
					}
				}
			} else {
				query = new StringBuffer();
				StringBuffer queryTitle = new StringBuffer();
				
				query.append(BRACKET_OPEN).append(BRACKET_OPEN).append(queryContentField).append(":").append(BRACKET_OPEN);
				
				if (CollectionUtils.isNotEmpty(criteria.getKeywordByUser())) {
					for (String nameKeyword : criteria.getKeywordByUser()) {
						query.append("\"").append(nameKeyword).append("\"");
						queryTitle.append("\"").append(nameKeyword)
								.append("\"");
					}
					query.append(BRACKET_CLOSE).append(Constants.SPACE)
							.append(QUERY_OR).append(Constants.SPACE)
							.append("title").append(":").append(BRACKET_OPEN)
							.append(queryTitle);
					query.append(BRACKET_CLOSE).append(BRACKET_CLOSE)
							.append(Constants.SPACE).append(QUERY_NOT)
							.append(Constants.SPACE).append("url:")
							.append(BRACKET_OPEN);
					for (String nameUrl : criteria.getUrlByKeyword()) {
						String escapeUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(nameUrl);
						query.append("\"").append("http://")
								.append(escapeUrl)
								.append("/")
								.append("\"")
								// NOT IN http://getUrl.com
								.append("\"").append("http://")
								.append(escapeUrl)
								.append("\"")
								// NOT IN http://www.getUrl.com/
								.append("\"").append("http://www.")
								.append(escapeUrl).append("/").append("\"")
								// NOT IN http://www.getUrl.com
								.append("\"").append("http://www.")
								.append(escapeUrl).append("\"");
					}
					query.append(BRACKET_CLOSE).append(BRACKET_CLOSE)
							.append(Constants.SPACE).append(QUERY_AND)
							.append(Constants.SPACE).append(BRACKET_OPEN)
							.append("lang-code:\"")
							.append(langCode.toUpperCase()).append("\"")
							.append(Constants.SPACE).append(QUERY_OR)
							.append(Constants.SPACE).append("lang-code:\"")
							.append(langCode).append("\"")
							.append(BRACKET_CLOSE);
				} else {
					return null;
				}
			}

			logger.debug("--------------> query: " + query);
			queryParams.put(QUERY_PARAM, query.toString());
			queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
			queryParams.put(QUERY_OFFSET, String.valueOf(offset));
			queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
			queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
			queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
			return searchBySolr(queryParams, criteria.getLanguageCode()
					.getCode().toUpperCase());
		}

		return null;

	}

	private SolrResponse searchIncorrectTitleContent(String title,
			String[] subStrings, String language, long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();
		StringBuffer query = new StringBuffer();
		query.append(BRACKET_OPEN).append("lang-code:\"")
				.append(language.toUpperCase()).append("\"")
				.append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(language).append("\"").append(BRACKET_CLOSE)
				.append(Constants.SPACE).append(QUERY_AND)
				.append(Constants.SPACE).append(BRACKET_OPEN).append("title:")
				.append(BRACKET_OPEN);
		for (String subString : subStrings) {
			query.append("\"").append(subString).append("\"");
		}
		query.append(BRACKET_CLOSE).append(Constants.SPACE).append(NOT_IN)
				.append("title:\"").append(title).append("\"")
				.append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		return searchBySolr(queryParams, language.toUpperCase());
	}

	private SolrResponse searchIncorrectKeyword(String keyword,
			String[] subKeyWords, String language, String queryContentField,
			long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();
		StringBuffer query = new StringBuffer();
		StringBuffer keywords = new StringBuffer();
		for (String subKeyWord : subKeyWords) {
			keywords.append("\"").append(subKeyWord).append("\"");
		}
		query.append(BRACKET_OPEN).append(BRACKET_OPEN).append("title:")
				.append(BRACKET_OPEN).append(keywords).append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE).append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append(BRACKET_OPEN)
				.append(queryContentField).append(":").append(BRACKET_OPEN)
				.append(keywords).append(BRACKET_CLOSE).append(BRACKET_CLOSE)
				.append(NOT_IN).append("title:\"").append(keyword).append("\"")
				.append(Constants.SPACE).append(NOT_IN)
				.append(queryContentField).append(":\"").append(keyword)
				.append("\"").append(BRACKET_CLOSE).append(Constants.SPACE)
				.append(QUERY_AND).append(Constants.SPACE).append(BRACKET_OPEN)
				.append("lang-code:\"").append(language.toUpperCase())
				.append("\"").append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(language).append("\"").append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		return searchBySolr(queryParams, language.toUpperCase());
	}

	private SolrResponse searchIncorrectKeywordUrl(String keyword,
			String[] subKeyWords, String url, String queryContentField,
			String queryUrlField, String language, long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();
		StringBuffer query = new StringBuffer();
		StringBuffer keywords = new StringBuffer();
		String escapeUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(url);
		for (String subKeyWord : subKeyWords) {
			keywords.append("\"").append(subKeyWord).append("\"");
		}
		query.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append("title:")
				.append(BRACKET_OPEN)
				.append(keywords)
				.append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE)
				.append(Constants.SPACE)
				.append(QUERY_OR)
				.append(Constants.SPACE)
				.append(BRACKET_OPEN)
				.append(queryContentField)
				.append(":")
				.append(BRACKET_OPEN)
				.append(keywords)
				.append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE)
				.append(NOT_IN)
				.append("title:\"")
				.append(keyword)
				.append("\"")
				.append(Constants.SPACE)
				.append(NOT_IN)
				.append(queryContentField)
				.append(":\"")
				.append(keyword)
				.append("\"")
				.append(BRACKET_CLOSE)
				.append(Constants.SPACE)
				.append(QUERY_AND)
				.append(Constants.SPACE)
				.append(queryUrlField)
				.append(":")
				.append(BRACKET_OPEN)
				.append("*")
				.append(escapeUrl)
				.append("*")
				.append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE)
				.append(QUERY_NOT)
				.append(Constants.SPACE)
				.append(queryUrlField)
				.append(":")
				.append(BRACKET_OPEN)
				.append("\"")
				.append("http://")
				.append(escapeUrl)
				.append("/")
				.append("\"")
				// NOT IN http://getUrl.com
				.append("\"")
				.append("http://")
				.append(escapeUrl)
				.append("\"")
				// NOT IN http://www.getUrl.com/
				.append("\"")
				.append("http://www.")
				.append(escapeUrl)
				.append("/")
				.append("\"")
				// NOT IN http://www.getUrl.com
				.append("\"").append("http://www.").append(escapeUrl).append("\"")
				.append(BRACKET_CLOSE).append(BRACKET_CLOSE)
				.append(Constants.SPACE).append(QUERY_AND)
				.append(Constants.SPACE).append(BRACKET_OPEN)
				.append("lang-code:\"").append(language.toUpperCase())
				.append("\"").append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(language).append("\"").append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		return searchBySolr(queryParams, language.toUpperCase());
	}

	public SolrResponse searchForReporting(ReportCriteria criteria,
			long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();

		if ((criteria != null)
				&& (StringUtils.isNoneBlank(criteria.getKeyword()) || StringUtils
						.isNoneBlank(criteria.getSourceUrl()))) {
			StringBuffer query = new StringBuffer();
			String queryContentField = "content-vi"; // default will search in Vietnamese content
			String langCode = "vi";
			if (criteria.getLanguageCode() == LanguageCode.EN) {
				queryContentField = "content-en";
				langCode = "en";
			}
			String escapeUrl =  com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(criteria.getSourceUrl());
			query.append(BRACKET_OPEN)
					.append(BRACKET_OPEN)
					.append(BRACKET_OPEN)
					.append("title:\"")
					.append(criteria.getKeyword())
					.append("\"")
					.append(Constants.SPACE)
					.append(QUERY_OR)
					.append(Constants.SPACE)
					.append(queryContentField)
					.append(":\"")
					.append(criteria.getKeyword())
					.append("\"")
					.append(Constants.SPACE)
					.append(QUERY_OR)
					.append(Constants.SPACE)
					.append("content")
					.append(":\"")
					.append(criteria.getKeyword())
					.append("\"")
					.append(BRACKET_CLOSE)
					.append(Constants.SPACE)
					.append(QUERY_AND)
					.append(Constants.SPACE)
					.append("url")
					.append(":")
					.append(BRACKET_OPEN)
					.append("*")
					.append(escapeUrl)
					.append("*")
					.append(BRACKET_CLOSE)
					.append(BRACKET_CLOSE)
					.append(Constants.SPACE)
					.append(QUERY_NOT)
					.append(Constants.SPACE)
					.append("url")
					.append(":")
					.append(BRACKET_OPEN)
					.append("\"")
					.append("http://")
					.append(escapeUrl)
					.append("/")
					.append("\"")
					// NOT IN http://getUrl.com
					.append("\"")
					.append("http://")
					.append(escapeUrl)
					.append("\"")
					// NOT IN http://www.getUrl.com/
					.append("\"")
					.append("http://www.")
					.append(escapeUrl)
					.append("/")
					.append("\"")
					// NOT IN http://www.getUrl.com
					.append("\"").append("http://www.")
					.append(escapeUrl).append("\"")
					.append(BRACKET_CLOSE).append(BRACKET_CLOSE)
					.append(Constants.SPACE).append(QUERY_AND)
					.append(Constants.SPACE).append(BRACKET_OPEN)
					.append("lang-code:\"").append(langCode.toUpperCase())
					.append("\"").append(Constants.SPACE).append(QUERY_OR)
					.append(Constants.SPACE).append("lang-code:\"")
					.append(langCode).append("\"").append(BRACKET_CLOSE);
			logger.debug("--------------> query: " + query);

			StringBuilder dateRange = new StringBuilder("tstamp:[");
			String date = "*";
			if (criteria.getStartDate() != null)
				date = DateUtils.getISODate(criteria.getStartDate());
			dateRange.append(date).append(" TO ");
			date = "*";
			if (criteria.getEndDate() != null)
				date = DateUtils.getISODate(new Date(criteria.getEndDate()
						.getTime() + (24 * 60 * 60 * 1000)));
			dateRange.append(date).append("]");

			queryParams.put(QUERY_PARAM, query.toString());
			queryParams.put(SolrOxmClient.QUERY_FILTER, dateRange.toString());
			queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
			queryParams.put(QUERY_MAX_ROW_RETURN,
					String.valueOf(Integer.MAX_VALUE));
			queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
			queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);

			return searchBySolr(queryParams, criteria.getLanguageCode()
					.getCode().toUpperCase());

		}

		return null;
	}

	/**
	 * Search.
	 * 
	 * @param queryParams
	 *            <code>Map</code> of query parameters.
	 */
	public SolrResponse searchBySolr(final Map<String, String> queryParams,
			String langCode) {
		final SolrResponse solrResponse = new SolrResponse();

		mapSolrTemplateByDmlLangcode.get(SELECT + "-" + langCode)
				.executeGetMethod(queryParams,
						new ResponseCallback<SolrResponse>() {
							public void doWithResponse(SolrResponse bean)
									throws IOException {
								solrResponse.setNumFound(bean.getNumFound());
								solrResponse.setStart(bean.getStart());
								solrResponse.setSolrDocs(bean.getSolrDocs());
							}
						});

		return solrResponse;
	}

	/**
	 * Updates a list of beans and automatically commits the updates.
	 * 
	 * @param beans
	 *            List of beans to create or update.
	 */
	public void update(List<T> beans) {
		update(beans, true);
	}

	/**
	 * Updates a list of beans.
	 * 
	 * @param beans
	 *            List of beans to create or update.
	 * @param commit
	 *            Whether or not to commit the request.
	 */
	public void update(List<T> beans, boolean commit) {
		updateTemplate.executePostMethod(beans);

		if (commit) {
			commit();
		}
	}

	/**
	 * Deletes a record based on an id and automatically commits the delete.
	 * 
	 * @param id
	 *            ID to delete.
	 */
	public void deleteById(String id) {
		deleteById(id, true);
	}

	/**
	 * Deletes a record based on an id.
	 * 
	 * @param id
	 *            ID to delete.
	 * @param commit
	 *            Whether or not to commit the request.
	 */
	public void deleteById(String id, boolean commit) {
		delete(getEnclosingElement(DELETE_ID_ELEMENT_NAME, id), commit);
	}

	/**
	 * Deletes an id and automatically commits the delete.
	 * 
	 * @param query
	 *            ID to delete.
	 */
	public void deleteByQuery(String query) {
		deleteByQuery(query, true);
	}

	/**
	 * Deletes an id and automatically commits the delete.
	 * 
	 * @param query
	 *            ID to delete.
	 * @param commit
	 *            Whether or not to commit the request.
	 */
	public void deleteByQuery(String query, boolean commit) {
		delete(getEnclosingElement(DELETE_QUERY_ELEMENT_NAME, query), commit);
	}

	/**
	 * Deletes.
	 * 
	 * @param query
	 *            Query should either be an <i>id</i> element or <i>query</i>
	 *            element.
	 * @param commit
	 *            Whether or not to commit the request.
	 */
	protected void delete(String query, boolean commit) {
		String request = getEnclosingElement(DELETE_ELEMENT_NAME, query);

		postTemplate.executePostMethod(request);

		if (commit) {
			commit();
		}

		logger.info("Processed delete.  request='{}' commit={}", request,
				commit);
	}

	/**
	 * Commits pending transactions.
	 */
	public void commit() {
		commit(null);
	}

	/**
	 * Commits pending transactions using specified attributes.
	 */
	public void commit(SolrRequestAttributes attrs) {
		String request = getElementWithAttributes(COMMIT_ELEMENT_NAME, attrs);

		postTemplate.executePostMethod(request);

		logger.debug("Processed commit.  request={}", request);
	}

	/**
	 * Rollback pending transactions.
	 */
	public void rollback() {
		postTemplate.executePostMethod(ROLLBACK_REQUEST);

		logger.info("Processed rollback.");
	}

	/**
	 * Optimize.
	 */
	public void optimize() {
		optimize(null);
	}

	/**
	 * Optimize using specified attributes.
	 */
	public void optimize(SolrRequestAttributes attrs) {
		String request = getElementWithAttributes(OPTIMIZE_ELEMENT_NAME, attrs);

		postTemplate.executePostMethod(request);

		logger.debug("Processed optimize.  request={}", request);
	}

	/**
	 * Gets an element enclosing a value.
	 */
	private String getEnclosingElement(String elementName, String value) {
		StringBuilder result = new StringBuilder();

		result.append("<" + elementName + ">");
		result.append(value);
		result.append("</" + elementName + ">");

		return result.toString();
	}

	/**
	 * Gets an element with attributes.
	 */
	private String getElementWithAttributes(String elementName,
			SolrRequestAttributes attrs) {
		StringBuilder result = new StringBuilder();

		result.append("<");
		result.append(elementName);

		if (attrs != null) {
			if (attrs.getMaxSegments() != null) {
				result.append(" maxSegments=\"" + attrs.getMaxSegments() + "\"");
			}

			if (attrs.getWaitFlush() != null) {
				result.append(" waitFlush=\"" + attrs.getWaitFlush() + "\"");
			}

			if (attrs.getWaitSearcher() != null) {
				result.append(" waitSearcher=\"" + attrs.getWaitSearcher()
						+ "\"");
			}
		}

		result.append("/>");

		return result.toString();
	}

	/**
	 * Gets base Solr url (ex: http://localhost:8983/solr).
	 */
	public String getBaseUrlVi() {
		return baseUrlVi;
	}

	/**
	 * Sets base Solr url (ex: http://localhost:8983/solr).
	 */
	public void setBaseUrlVi(String baseUrl) {
		this.baseUrlVi = baseUrl;
	}

	/**
	 * <p>
	 * Getter for baseUrlEn.
	 * </p>
	 * 
	 * @return the baseUrlEn
	 */
	public String getBaseUrlEn() {
		return baseUrlEn;
	}

	/**
	 * <p>
	 * Setting value for baseUrlEn.
	 * </p>
	 * 
	 * @param baseUrlEn
	 *            the baseUrlEn to set
	 */
	public void setBaseUrlEn(String baseUrlEn) {
		this.baseUrlEn = baseUrlEn;
	}

	/**
	 * Gets Solr select url.
	 */
	protected String getSelectUrlVi() {
		return baseUrlVi + SELECT_URL_SUFFIX;
	}

	/**
	 * Gets Solr update url.
	 */
	protected String getUpdateUrlVi() {
		return baseUrlVi + UPDATE_URL_SUFFIX;
	}

	/**
	 * Gets Solr select url.
	 */
	protected String getSelectUrlEn() {
		return baseUrlEn + SELECT_URL_SUFFIX;
	}

	/**
	 * Gets Solr update url.
	 */
	protected String getUpdateUrlEn() {
		return baseUrlEn + UPDATE_URL_SUFFIX;
	}

	/**
	 * Gets marshaller.
	 */
	public Marshaller getMarshaller() {
		return marshaller;
	}

	/**
	 * Sets marshaller.
	 */
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * Gets unmarshaller.
	 */
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	/**
	 * Sets unmarshaller.
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	private SolrResponse SolrResponseSearchTitle(SearchCriteria criteria,
			String langCode, long offset, int maxRow) {

		Map<String, String> queryParams = new HashMap<String, String>();
		String[] subStrings = criteria.getKeyword().split(" ");
		StringBuffer query = new StringBuffer();

		query.append(BRACKET_OPEN).append("title:\"")
				.append(criteria.getKeyword()).append("\"")
				.append(BRACKET_CLOSE).append(Constants.SPACE)
				.append(QUERY_AND).append(Constants.SPACE).append(BRACKET_OPEN)
				.append("lang-code:\"").append(langCode.toUpperCase())
				.append("\"").append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(langCode).append("\"");
		
		query.append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		
		SolrResponse solrResponeCorrect = searchBySolr(queryParams, criteria.getLanguageCode().getCode().toUpperCase());
		SolrResponse solrResponeTotal = new SolrResponse();
		solrResponeTotal.setStart(0l);
		List<SolrDoc> solrDocs = solrResponeCorrect.getSolrDocs();
		
		solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound());
		
		if (subStrings.length > 1)
		{
			int maxRow2 = (maxRow - solrResponeCorrect.getSolrDocs().size());
			long offset2 = 0;
			
			if (offset >= solrResponeCorrect.getNumFound())
			{
				offset2 = (offset - solrResponeCorrect.getNumFound());
			}
			
			SolrResponse solrResponeIncorrect = searchIncorrectTitleContent(criteria.getKeyword(), subStrings, langCode, offset2, maxRow2);
			
			List<SolrDoc> solrDocsIncorrect = solrResponeIncorrect.getSolrDocs();
			
			solrDocs.addAll(solrDocsIncorrect);
			solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound() + solrResponeIncorrect.getNumFound());
		}
		
		solrResponeTotal.setSolrDocs(solrDocs);

		return solrResponeTotal;
	}

	private SolrResponse SolrResponseSearchKeyword(SearchCriteria criteria, String langCode, String queryContentField, long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();
		String[] subStrings = criteria.getKeyword().split(" ");
		StringBuffer query = new StringBuffer();
		
		query.append(BRACKET_OPEN).append("title:\"")
				.append(criteria.getKeyword()).append("\"")
				.append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append(queryContentField)
				.append(":\"").append(criteria.getKeyword()).append("\"")
				.append(BRACKET_CLOSE).append(Constants.SPACE)
				.append(QUERY_AND).append(Constants.SPACE).append(BRACKET_OPEN)
				.append("lang-code:\"").append(langCode.toUpperCase())
				.append("\"").append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(langCode).append("\"").append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		
		SolrResponse solrResponeCorrect = searchBySolr(queryParams, criteria.getLanguageCode().getCode().toUpperCase());
		SolrResponse solrResponeTotal = new SolrResponse();
		solrResponeTotal.setStart(0l);
		List<SolrDoc> solrDocs = solrResponeCorrect.getSolrDocs();
		
		solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound());
		
		if (subStrings.length > 1)
		{
			int maxRow2 = (maxRow - solrResponeCorrect.getSolrDocs().size());
			long offset2 = 0;
			
			if (offset >= solrResponeCorrect.getNumFound())
			{
				offset2 = (offset - solrResponeCorrect.getNumFound());
			}
			
			SolrResponse solrResponeIncorrect = searchIncorrectKeyword(criteria.getKeyword(), subStrings, langCode,
					queryContentField, offset2, maxRow2);
			List<SolrDoc> solrDocsIncorrect = solrResponeIncorrect.getSolrDocs();
			solrDocs.addAll(solrDocsIncorrect);
			solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound() + solrResponeIncorrect.getNumFound());
		}
		
		solrResponeTotal.setSolrDocs(solrDocs);
		
		return solrResponeTotal;
	}

	private SolrResponse SolrResponseSearchKeywordUrl(SearchCriteria criteria, String queryUrlField, 
	    String langCode, String queryContentField, long offset, int maxRow) {
		Map<String, String> queryParams = new HashMap<String, String>();
		String[] subStrings = criteria.getKeyword().split(" ");
		StringBuffer query = new StringBuffer();
		String escapeUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(criteria.getUrl());
		query.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append(BRACKET_OPEN)
				.append("title:\"")
				.append(criteria.getKeyword())
				.append("\"")
				.append(Constants.SPACE)
				.append(QUERY_OR)
				.append(Constants.SPACE)
				.append(queryContentField)
				.append(":\"")
				.append(criteria.getKeyword())
				.append("\"")
				.append(BRACKET_CLOSE)
				.append(Constants.SPACE)
				.append(QUERY_AND)
				.append(Constants.SPACE)
				.append(queryUrlField)
				.append(":")
				.append(BRACKET_OPEN)
				.append("*")
				.append(escapeUrl)
				.append("*")
				.append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE)
				.append(BRACKET_CLOSE)
				.append(Constants.SPACE)
				.append(QUERY_NOT)
				.append(Constants.SPACE)
				.append(queryUrlField)
				.append(":")
				.append(BRACKET_OPEN)
				.append("\"")
				.append("http://")
				.append(escapeUrl)
				.append("/")
				.append("\"")
				// NOT IN http://getUrl.com
				.append("\"")
				.append("http://")
				.append(escapeUrl)
				.append("\"")
				// NOT IN http://www.getUrl.com/
				.append("\"")
				.append("http://www.")
				.append(escapeUrl)
				.append("/")
				.append("\"")
				// NOT IN http://www.getUrl.com
				.append("\"").append("http://www.").append(escapeUrl)
				.append("\"").append(BRACKET_CLOSE).append(BRACKET_CLOSE)
				.append(Constants.SPACE).append(QUERY_AND)
				.append(Constants.SPACE).append(BRACKET_OPEN)
				.append("lang-code:\"").append(langCode.toUpperCase())
				.append("\"").append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(langCode).append("\"").append(BRACKET_CLOSE);
		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
		queryParams.put(QUERY_OFFSET, String.valueOf(offset));
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
		queryParams.put(QUERY_FIELD_LIST, FIELD_LIST);
		queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
		SolrResponse solrResponeCorrect = searchBySolr(queryParams, criteria
				.getLanguageCode().getCode().toUpperCase());
		SolrResponse solrResponeTotal = new SolrResponse();
		solrResponeTotal.setStart(0l);
		List<SolrDoc> solrDocs = solrResponeCorrect.getSolrDocs();
		solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound());
		if (subStrings.length > 1) {
			int maxRow2 = (maxRow - solrResponeCorrect.getSolrDocs().size());
			long offset2 = 0;
			if (offset >= solrResponeCorrect.getNumFound()) {
				offset2 = (offset - solrResponeCorrect.getNumFound());
			}
			SolrResponse solrResponeIncorrect = searchIncorrectKeywordUrl(
					criteria.getKeyword(), subStrings, criteria.getUrl(),
					queryContentField, queryUrlField, langCode, offset2,
					maxRow2);
			List<SolrDoc> solrDocsIncorrect = solrResponeIncorrect
					.getSolrDocs();
			solrDocs = solrResponeCorrect.getSolrDocs();
			solrDocs.addAll(solrDocsIncorrect);
			solrResponeTotal.setNumFound(solrResponeCorrect.getNumFound()
					+ solrResponeIncorrect.getNumFound());
		}
		solrResponeTotal.setSolrDocs(solrDocs);
		return solrResponeTotal;
	}

	public SolrResponse findFreshNews(Date from, Date to, LanguageCode langCode, List<SourceUrl> sources) {
		Map<String, String> queryParams = new HashMap<String, String>();
		StringBuffer query = new StringBuffer();
		
		query.append(BRACKET_OPEN).append(BRACKET_OPEN).append(Constants.SPACE)
				.append("lang-code:\"")
				.append(langCode.getCode().toLowerCase()).append("\"")
				.append(Constants.SPACE).append(QUERY_OR)
				.append(Constants.SPACE).append("lang-code:\"")
				.append(langCode.getCode()).append("\"").append(BRACKET_CLOSE)
				.append(Constants.SPACE).append(QUERY_NOT)
				.append(Constants.SPACE).append("url").append(":")
				.append(BRACKET_OPEN);
		
		for (SourceUrl url : sources) {
			String escapeUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(url.getUrl());
			query.append("\"").append("http://")
					.append(escapeUrl)
					.append("/")
					.append("\"")
					// NOT IN http://getUrl.com
					.append("\"").append("http://")
					.append(escapeUrl)
					.append("\"")
					// NOT IN http://www.getUrl.com/
					.append("\"").append("http://www.").append(escapeUrl)
					.append("/").append("\"")
					// NOT IN http://www.getUrl.com
					.append("\"").append("http://www.").append(escapeUrl)
					.append("\"");
		}
		
		query.append(BRACKET_CLOSE).append(BRACKET_CLOSE);

		logger.debug("--------------> query: " + query);
		queryParams.put(QUERY_PARAM, query.toString());

		StringBuilder dateRange = new StringBuilder("tstamp:[");
		String date = "*";
		
		if (from != null)
			date = DateUtils.getISODate(from);
		
		dateRange.append(date).append(" TO ");
		
		date = "*";
		
		if (to != null)
			date = DateUtils.getISODate(to);
		
		dateRange.append(date).append("]");
		logger.debug("--------------> dateRange: " + dateRange);
		queryParams.put(SolrOxmClient.QUERY_FILTER, dateRange.toString());
		queryParams.put(QUERY_RESPONSE_WRITER, SolrOxmClient.JSON_RESPONSE);
		queryParams.put(QUERY_FIELD_LIST, "url title lang-code");
		queryParams.put(SolrOxmClient.QUERY_SORT, "tstamp desc");
		queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(100));

		return searchBySolr(queryParams, langCode.getCode().toUpperCase());
	}
	
	/**
     * get all ID of Docs that match specific URL with JSON response.
     * 
     * @param url
     *            the url.
     * @param offset
     *            the offset
     * @param maxRow
     *            the max return row
     * @param languageCode 
     *            the languageCode 
     * @return the SolrResponse
     */
    public SolrResponse searchIDByUrl(String url, long offset, int maxRow, LanguageCode languageCode) {
        Map<String, String> queryParams = new HashMap<String, String>();
        
        if (StringUtils.isNotBlank(url)) {
            StringBuffer query = new StringBuffer();
            String escapseUrl = com.vsii.ttxvn.keywordsearching.utils.StringUtils.escapeSorlQueryChars(url);
            
            query.append("url:")
            .append(BRACKET_OPEN);
            query.append("*").append(escapseUrl).append("*");
            query.append(BRACKET_CLOSE)
            // NOT IN http://getUrl.com/
            .append(Constants.SPACE)
            .append(QUERY_NOT)
            .append(Constants.SPACE)
            .append("url:")
            .append(BRACKET_OPEN)
            .append("\"").append("http://").append(escapseUrl).append("/").append("\"")
            // NOT IN http://getUrl.com
            .append("\"").append("http://").append(escapseUrl).append("\"")
            // NOT IN http://www.getUrl.com/
            .append("\"").append("http://www.").append(escapseUrl).append("/").append("\"")
            // NOT IN http://www.getUrl.com
            .append("\"").append("http://www.").append(escapseUrl).append("\"")
            .append(BRACKET_CLOSE);
    
            logger.debug("--------------> query: " + query);
            queryParams.put(QUERY_PARAM, query.toString());
            queryParams.put(QUERY_RESPONSE_WRITER, JSON_RESPONSE);
            queryParams.put(QUERY_OFFSET, String.valueOf(offset));
            queryParams.put(QUERY_MAX_ROW_RETURN, String.valueOf(maxRow));
            queryParams.put(QUERY_FIELD_LIST, FIELD_LIST_ID_ONLY);
            queryParams.put(QUERY_SORT, SORT_RELIABILITY_TSTAMP_DESC);
            return searchBySolr(queryParams, languageCode.getCode().toUpperCase());
        }

        return null;

    }
}
