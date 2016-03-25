/*
 * FILENAME
 *     SourceUrl.java
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

package com.vsii.ttxvn.keywordsearching.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.vsii.ttxvn.keywordsearching.enums.CrawlingStatus;
import com.vsii.ttxvn.keywordsearching.enums.FetchDeep;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;
import com.vsii.ttxvn.keywordsearching.enums.SourceUrlType;

/**
 * <p>
 * The class for carrying the SourceUrl info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "source_url")
public class SourceUrl extends BaseEntity
{
	private static final long serialVersionUID = -7313992005288859013L;

	@Column(name = "URL", length = 1000, nullable = false)
	private String url;

	@Column(name = "DOMAIN", length = 250, nullable = false)
	private String domain;
	
	@Column(name = "LANG_CODE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private LanguageCode langCode;

	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "CREATED_USER_ID", nullable = false)
	private Long createdUserId;

	@Column(name = "RELIABILITY")
	private Integer reliability;

	@Column(name = "SOURCE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private SourceUrlType sourceType;

	@Column(name = "CRAWLING_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private CrawlingStatus crawlingStatus;

	@Column(name = "LAST_MODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;

	@Column(name = "LAST_MODIFIED_USER_ID")
	private Long lastModifiedUserId;

	@Column(name = "FETCH_DEEP")
	@Enumerated(EnumType.ORDINAL)
	private FetchDeep fetchDeep;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FETCH_FREQUENCY_ID")
	private FetchFrequency fetchFrequency;

	@Column(name = "LAST_FETCHED")
	private Date lastFetched;


	/**
	 * <p>
	 * The default constructor without arguments.
	 * </p>
	 * 
	 **/
	protected SourceUrl()
	{
		this.createdDate = Calendar.getInstance().getTime();
	}

	public SourceUrl(String inputUrl, LanguageCode inputLangCode, Long inputCreatedUserId, Integer inputReliability,
			SourceUrlType inputSourceType, Category inputCategory, FetchFrequency inputFetchFrequency, FetchDeep inputFetchDeep)
	{
		if (StringUtils.isBlank(inputUrl))
		{
			throw new IllegalArgumentException("The URL cannot be blank");
		}

		if (inputLangCode == null)
		{
			throw new IllegalArgumentException("The language cannot be blank");
		}

		if (inputCreatedUserId == null)
		{
			throw new IllegalArgumentException("The created user cannot be blank");
		}

		if (inputReliability == null)
		{
			throw new IllegalArgumentException("The reliability cannot be blank");
		}

		if (inputSourceType == null)
		{
			throw new IllegalArgumentException("The source type cannot be blank");
		}

		if (inputCategory == null)
		{
			throw new IllegalArgumentException("The category cannot be blank");
		}

		if (inputFetchFrequency == null)
		{
			throw new IllegalArgumentException("The fetch frequency cannot be blank");
		}
		
		if (inputFetchDeep == null)
		{
			throw new IllegalArgumentException("The deep cannot be blank");
		}

		this.url = inputUrl;
		this.langCode = inputLangCode;
		this.createdDate = Calendar.getInstance().getTime();
		this.createdUserId = inputCreatedUserId;
		this.reliability = inputReliability;
		this.sourceType = inputSourceType;
		this.category = inputCategory;
		this.fetchFrequency = inputFetchFrequency;
		this.fetchDeep = inputFetchDeep;
	}

	/**
	 * <p>
	 * Getter for url.
	 * </p>
	 * 
	 * @return the url
	 */
	public String getUrl()
	{
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
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * <p>
	 * Getter for domain.
	 * </p>
	 * 
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * <p>
	 * Setting value for domain.
	 * </p>
	 * 
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * <p>
	 * Getter for langCode.
	 * </p>
	 * 
	 * @return the langCode
	 */
	public LanguageCode getLangCode()
	{
		return langCode;
	}

	/**
	 * <p>
	 * Setting value for langCode.
	 * </p>
	 * 
	 * @param langCode
	 *            the langCode to set
	 */
	public void setLangCode(LanguageCode langCode)
	{
		this.langCode = langCode;
	}

	/**
	 * <p>
	 * Getter for createdUserId.
	 * </p>
	 * 
	 * @return the createdUserId
	 */
	public Long getCreatedUserId()
	{
		return createdUserId;
	}

	/**
	 * <p>
	 * Setting value for createdUserId.
	 * </p>
	 * 
	 * @param createdUserId
	 *            the createdUserId to set
	 */
	public void setCreatedUserId(Long createdUserId)
	{
		this.createdUserId = createdUserId;
	}

	/**
	 * <p>
	 * Getter for reliability.
	 * </p>
	 * 
	 * @return the reliability
	 */
	public Integer getReliability()
	{
		return reliability;
	}

	/**
	 * <p>
	 * Setting value for reliability.
	 * </p>
	 * 
	 * @param reliability
	 *            the reliability to set
	 */
	public void setReliability(Integer reliability)
	{
		this.reliability = reliability;
	}

	/**
	 * <p>
	 * Getter for sourceType.
	 * </p>
	 * 
	 * @return the sourceType
	 */
	public SourceUrlType getSourceType()
	{
		return sourceType;
	}

	/**
	 * <p>
	 * Setting value for sourceType.
	 * </p>
	 * 
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(SourceUrlType sourceType)
	{
		this.sourceType = sourceType;
	}

	/**
     * <p>
     * Getter for fetchDeep.
     * </p>
     * 
     * @return the fetchDeep
     */
    public FetchDeep getFetchDeep()
    {
        return fetchDeep;
    }

    /**
     * <p>
     * Setting value for fetchDeep.
     * </p>
     * 
     * @param fetchDeep the fetchDeep to set
     */
    public void setFetchDeep(FetchDeep fetchDeep)
    {
        this.fetchDeep = fetchDeep;
    }

    /**	
	 * <p>
	 * Getter for crawlingStatus.
	 * </p>
	 * 
	 * @return the crawlingStatus
	 */
	public CrawlingStatus getCrawlingStatus()
	{
		return crawlingStatus;
	}

	/**
	 * <p>
	 * Setting value for crawlingStatus.
	 * </p>
	 * 
	 * @param crawlingStatus
	 *            the crawlingStatus to set
	 */
	public void setCrawlingStatus(CrawlingStatus crawlingStatus)
	{
		this.crawlingStatus = crawlingStatus;
	}

	/**
	 * <p>
	 * Getter for lastModified.
	 * </p>
	 * 
	 * @return the lastModified
	 */
	public Date getLastModified()
	{
		return lastModified;
	}

	/**
	 * <p>
	 * Setting value for lastModified.
	 * </p>
	 * 
	 * @param lastModified
	 *            the lastModified to set
	 */
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	/**
	 * <p>
	 * Getter for lastModifiedUserId.
	 * </p>
	 * 
	 * @return the lastModifiedUserId
	 */
	public Long getLastModifiedUserId()
	{
		return lastModifiedUserId;
	}

	/**
	 * <p>
	 * Setting value for lastModifiedUserId.
	 * </p>
	 * 
	 * @param lastModifiedUserId
	 *            the lastModifiedUserId to set
	 */
	public void setLastModifiedUserId(Long lastModifiedUserId)
	{
		this.lastModifiedUserId = lastModifiedUserId;
	}

	/**
	 * <p>
	 * Getter for category.
	 * </p>
	 * 
	 * @return the category
	 */
	public Category getCategory()
	{
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
	public void setCategory(Category category)
	{
		this.category = category;
	}

	/**
	 * <p>
	 * Getter for fetchFrequency.
	 * </p>
	 * 
	 * @return the fetchFrequency
	 */
	public FetchFrequency getFetchFrequency()
	{
		return fetchFrequency;
	}

	/**
	 * <p>
	 * Setting value for fetchFrequency.
	 * </p>
	 * 
	 * @param fetchFrequency
	 *            the fetchFrequency to set
	 */
	public void setFetchFrequency(FetchFrequency fetchFrequency)
	{
		this.fetchFrequency = fetchFrequency;
	}

	public Date getLastFetched()
	{
		return lastFetched;
	}

	public void setLastFetched(Date lastFetched)
	{
		this.lastFetched = lastFetched;
	}

}
