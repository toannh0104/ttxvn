/*
 * FILENAME
 *     SolrDoc.java
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

/**
 * <p>
 * The class for purpose of carry user info these presentations in View layer.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class SolrDoc {
	private String id;
	private String url;
	private String title;
	private String content; // for display in views
	private String contentSolr; // mapping with original content in Solr
	private String langCode;
	private String abbreviateUrl;
	private String abbreviateTitle;
	private String abbreviateContent;

	/**
	 * <p>
	 * Getter for id.
	 * </p>
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Setting value for id.
	 * </p>
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Getter for title.
	 * </p>
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <p>
	 * Setting value for title.
	 * </p>
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * <p>
	 * Getter for content.
	 * </p>
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <p>
	 * Setting value for content.
	 * </p>
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
     * <p>
     * Getter for contentSolr.
     * </p>
     * 
     * @return the contentSolr
     */
    public String getContentSolr()
    {
        return contentSolr;
    }

    /**
     * <p>
     * Setting value for contentSolr.
     * </p>
     * 
     * @param contentSolr the contentSolr to set
     */
    public void setContentSolr(String contentSolr)
    {
        this.contentSolr = contentSolr;
    }

    /**
	 * <p>
	 * Getter for abbreviateUrl.
	 * </p>
	 * 
	 * @return the abbreviateUrl
	 */
	public String getAbbreviateUrl() {
		return abbreviateUrl;
	}

	/**
	 * <p>
	 * Setting value for abbreviateUrl.
	 * </p>
	 * 
	 * @param abbreviateUrl
	 *            the abbreviateUrl to set
	 */
	public void setAbbreviateUrl(String abbreviateUrl) {
		this.abbreviateUrl = abbreviateUrl;
	}

	/**
	 * <p>
	 * Getter for abbreviateTitle.
	 * </p>
	 * 
	 * @return the abbreviateTitle
	 */
	public String getAbbreviateTitle() {
		return abbreviateTitle;
	}

	/**
	 * <p>
	 * Setting value for abbreviateTitle.
	 * </p>
	 * 
	 * @param abbreviateTitle
	 *            the abbreviateTitle to set
	 */
	public void setAbbreviateTitle(String abbreviateTitle) {
		this.abbreviateTitle = abbreviateTitle;
	}

	/**
	 * <p>
	 * Getter for abbreviateContent.
	 * </p>
	 * 
	 * @return the abbreviateContent
	 */
	public String getAbbreviateContent() {
		return abbreviateContent;
	}

	/**
	 * <p>
	 * Setting value for abbreviateContent.
	 * </p>
	 * 
	 * @param abbreviateContent
	 *            the abbreviateContent to set
	 */
	public void setAbbreviateContent(String abbreviateContent) {
		this.abbreviateContent = abbreviateContent;
	}

	/**
	 * <p>
	 * Getter for langCode.
	 * </p>
	 * 
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * <p>
	 * Setting value for langCode.
	 * </p>
	 * 
	 * @param langCode the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolrDoc other = (SolrDoc) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	

}