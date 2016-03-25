/*
 * FILENAME
 *     ResponseStatus.java
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
public class ResponseStatus {
	private String status;
	private String message;

	public ResponseStatus() {
		super();
	}

	public ResponseStatus(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}



	/**
	 * <p>
	 * Getter for status.
	 * </p>
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <p>
	 * Setting value for status.
	 * </p>
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * <p>
	 * Getter for message.
	 * </p>
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * <p>
	 * Setting value for message.
	 * </p>
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
