/*
 * FILENAME
 *     BaseController.java
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

package com.vsii.ttxvn.keywordsearching.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vsii.ttxvn.keywordsearching.utils.LogWriter;

/**
 * <p>
 * The class BaseController.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class BaseController
{
	private static final String TTXVN_KEYWORD_SEARCHING = "TTXVN";
    
	protected static LogWriter LOG_WRITER;
    
    protected Log log = LogFactory.getLog(getClass());
    
    static {
    	LOG_WRITER = LogWriter.getInstance(TTXVN_KEYWORD_SEARCHING);
    }
}
