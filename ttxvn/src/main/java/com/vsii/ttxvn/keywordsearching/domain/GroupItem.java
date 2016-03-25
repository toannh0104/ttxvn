/*
 * FILENAME
 *     GroupItem.java
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

import com.vsii.ttxvn.keywordsearching.enums.Status;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of list group in jsp
 * </p>
 * 
 * @version 1.0
 * @author sonta
 **/
public class GroupItem {
	private Long id;
	
	private String name;
	private Status status;
	private String description;
	private String namestatus;
	public String getNamestatus() {
		return namestatus;
	}
	public void setNamestatus(String namestatus) {
		this.namestatus = namestatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String inputName) {
		this.name = inputName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status inputStatus) {
		this.status = inputStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String inputDescription) {
		this.description = inputDescription;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long inputid) {
		this.id = inputid;
	}
	
}
