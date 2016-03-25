/*
 * FILENAME
 *     SolrResponse.java
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

/**
 * <p>
 * The class for purpose of carry user info these presentations in View layer.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class SolrResponse
{
    private Long numFound;
    private Long start;
    List<SolrDoc> solrDocs;
	
    /**
	 * <p>
	 * Getter for numFound.
	 * </p>
	 * 
	 * @return the numFound
	 */
	public Long getNumFound() {
		return numFound;
	}
	/**
	 * <p>
	 * Setting value for numFound.
	 * </p>
	 * 
	 * @param numFound the numFound to set
	 */
	public void setNumFound(Long numFound) {
		this.numFound = numFound;
	}
	/**
	 * <p>
	 * Getter for start.
	 * </p>
	 * 
	 * @return the start
	 */
	public Long getStart() {
		return start;
	}
	/**
	 * <p>
	 * Setting value for start.
	 * </p>
	 * 
	 * @param start the start to set
	 */
	public void setStart(Long start) {
		this.start = start;
	}
	/**
	 * <p>
	 * Getter for solrDocs.
	 * </p>
	 * 
	 * @return the solrDocs
	 */
	public List<SolrDoc> getSolrDocs() {
		return solrDocs;
	}
	/**
	 * <p>
	 * Setting value for solrDocs.
	 * </p>
	 * 
	 * @param solrDocs the solrDocs to set
	 */
	public void setSolrDocs(List<SolrDoc> solrDocs) {
		this.solrDocs = solrDocs;
	}
    	
}
