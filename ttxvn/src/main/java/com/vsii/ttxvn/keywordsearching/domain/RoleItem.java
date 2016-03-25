/*
 * FILENAME
 *     RoleItem.java
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

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author sonta
 **/
public class RoleItem {
	String name;
	long id;
	public String getName() {
		return name;
	}
	public void setName(String inputName) {
		this.name = inputName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long inputId) {
		this.id = inputId;
	}
	

}
