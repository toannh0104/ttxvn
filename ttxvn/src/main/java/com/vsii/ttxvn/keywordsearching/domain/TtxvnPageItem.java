/*
 * FILENAME
 *     TtxvnPageItem.java
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
 * The class for purpose of holding info of Page Item which displays pagination.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class TtxvnPageItem {
	private int pageNumber;
	private boolean selected;
	
	public TtxvnPageItem(int pageNumber, boolean selected) {
		super();
		this.pageNumber = pageNumber;
		this.selected = selected;
	}

	/**
	 * <p>
	 * Getter for pageNumber.
	 * </p>
	 * 
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * <p>
	 * Setting value for pageNumber.
	 * </p>
	 * 
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * <p>
	 * Getter for selected.
	 * </p>
	 * 
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * <p>
	 * Setting value for selected.
	 * </p>
	 * 
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
