/*
 * FILENAME
 *     CategoryService.java
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

package com.vsii.ttxvn.keywordsearching.service;

import java.util.Date;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.entity.Token;

/**
 * <p>
 * The Category Service interface.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public interface TokenService extends GenericDao
{
    public void createNewToken(final Token token);
    
    public void updateToken(final String series, final String tokenValue, final Date lastUsed);
    
    public Token getTokenForSeries(final String series);
    
    public void removeUserTokens(final String username);
}
