/*
 * FILENAME
 *     TtxvnPageRequest.java
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

import org.springframework.data.domain.PageRequest;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class TtxvnPageRequest extends PageRequest {

	private static final long serialVersionUID = 6548429234935062147L;

	private long firstElement;

	/**
	 * Creates a new {@link TtxvnPageRequest}. Pages are zero indexed, thus
	 * providing 0 for {@code page} will return the first page.
	 * 
	 * @param page
	 *            zero-based page index.
	 * @param size
	 *            the size of the page to be returned.
	 */
	public TtxvnPageRequest(int page, int size) {
		super(page, size);
		firstElement = this.getPageNumber() * this.getPageSize();
	}

	/**
	 * <p>
	 * Getter for firstElement.
	 * </p>
	 * 
	 * @return the firstElement
	 */
	public long getFirstElement() {
		return firstElement;
	}

	/**
	 * <p>
	 * Setting value for firstElement.
	 * </p>
	 * 
	 * @param firstElement
	 *            the firstElement to set
	 */
	public void setFirstElement(long firstElement) {
		this.firstElement = firstElement;
	}

}
