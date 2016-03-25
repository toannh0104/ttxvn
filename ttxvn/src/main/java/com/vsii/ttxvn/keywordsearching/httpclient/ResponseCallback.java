/*
 * FILENAME
 *     ResponseCallback.java
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

import java.io.IOException;

/**
 * <p>
 * The class uses to process a response.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public interface ResponseCallback<T> {

	/**
	 * Called by {@link HttpClientTemplate#executeGetMethod}.
	 */
	public void doWithResponse(T response) throws IOException;

}
