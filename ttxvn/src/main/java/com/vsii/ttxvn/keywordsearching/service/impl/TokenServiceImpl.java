/*
 * FILENAME
 *     CategoryServiceImpl.java
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

import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.entity.Token;
import com.vsii.ttxvn.keywordsearching.service.TokenService;

/**
 * <p>
 * The class for implementation of CategoryService interface.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("tokenService")
public class TokenServiceImpl extends GenericDaoImpl implements TokenService
{
    private static final String TOKEN_FOR_SERIES = "from Token where series = :series";
    private static final String REMOVE_TOKEN = "delete Token where username = :username";

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.TokenService#createNewToken(com.vsii.ttxvn.keywordsearching.entity.Token)
     */
    @Override
    public void createNewToken(Token token)
    {
        this.create(token);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.TokenService#updateToken(java.lang.String, java.lang.String,
     *      java.util.Date)
     */
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed)
    {
        Token existingToken = getTokenForSeries(series);
        existingToken.setTokenValue(tokenValue);
        existingToken.setDate(lastUsed);
        update(existingToken);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.TokenService#getTokenForSeries(java.lang.String)
     */
    @Override
    public Token getTokenForSeries(String series)
    {
        Query query = getCurrentSession().createQuery(TOKEN_FOR_SERIES);
        query.setString("series", series);

        return (Token) query.uniqueResult();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.TokenService#removeUserTokens(java.lang.String)
     */
    @Override
    public void removeUserTokens(String username)
    {
        Query query = getCurrentSession().createQuery(REMOVE_TOKEN);
        query.setString("username", username);
        query.executeUpdate();
    }

}
