/*
 * FILENAME
 *     LoginServiceImpl.java
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

package com.vsii.ttxvn.keywordsearching.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.service.LoginService;

/**
 * <p>
 * The class for implementation of LoginService interface.
 * </p>
 * 
 * @version 1.0
 * @author ducvq
 **/
@Service("loginService")
@Component
public class LoginServiceImpl extends GenericDaoImpl implements LoginService
{

    private static final String CHECK_USER_LOGIN =
        "FROM User u WHERE u.username = :username AND u.password = :password";

    /**
     * {@inheritDoc} check Username and Password from database
     *
     * @see com.vsii.ttxvn.keywordsearching.service.LoginService#checkLogin(java.lang.String, java.lang.String)
     */

    @Override
    public User checkLogin(String username, String password)
    {
        if ((null == username || username.isEmpty()) && (null == password || password.isEmpty()))
            return null;
        return (User) getCurrentSession().createQuery(CHECK_USER_LOGIN).setString("username", username)
            .setString("password", password).uniqueResult();
    }

}
