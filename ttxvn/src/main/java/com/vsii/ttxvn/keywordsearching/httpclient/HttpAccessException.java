/*
 * FILENAME
 *     HttpAccessException.java
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

package com.vsii.ttxvn.keywordsearching.httpclient;

import org.springframework.core.NestedRuntimeException;

/**
 * <p>
 * The HTTP runtime exception for any HTTP error.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class HttpAccessException extends NestedRuntimeException {

	private static final long serialVersionUID = 3144858033479971939L;

	protected int statusCode = -1;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            Error message.
	 */
	public HttpAccessException(String msg) {
		super(msg);
	}

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            Error message.
	 * @param statusCode
	 *            HTTP status code.
	 */
	public HttpAccessException(String msg, int statusCode) {
		this(msg);
		this.statusCode = statusCode;
	}

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            Error message.
	 * @param cause
	 *            Cause of error.
	 */
	public HttpAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            Error message.
	 * @param cause
	 *            Cause of error.
	 * @param statusCode
	 *            HTTP status code.
	 */
	public HttpAccessException(String msg, Throwable cause, int statusCode) {
		this(msg, cause);
		this.statusCode = statusCode;
	}

}
