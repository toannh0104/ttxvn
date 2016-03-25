/*
 * FILENAME
 *     LoginService.java
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

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.entity.User;

/**
 * <p>
 * The Login Service interface.
 * </p>
 * 
 * @version 1.0
 * @author ducvq
 **/
public interface LoginService extends GenericDao
{
    public User checkLogin(String username, String password);
}
