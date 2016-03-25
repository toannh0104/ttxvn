/*
 * FILENAME
 *     Token.java
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

package com.vsii.ttxvn.keywordsearching.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 * <p>
 * The class for carrying the Remember Me Token info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "persistent_logins")
public class Token extends BaseEntity
{
    private static final long serialVersionUID = 6729640999801385505L;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "SERIES")
    private String series;

    @Column(name = "token")
    private String tokenValue;
    
    @Column(name = "LAST_USED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Token()
    {
        super();
    }

    public Token(PersistentRememberMeToken persistentRememberMeToken)
    {
        this.username = persistentRememberMeToken.getUsername();
        this.series = persistentRememberMeToken.getSeries();
        this.date = persistentRememberMeToken.getDate();
        this.tokenValue = persistentRememberMeToken.getTokenValue();
    }

    /**
     * <p>
     * Getter for series.
     * </p>
     * 
     * @return the series
     */
    public String getSeries()
    {
        return series;
    }

    /**
     * <p>
     * Setting value for series.
     * </p>
     * 
     * @param series
     *            the series to set
     */
    public void setSeries(String series)
    {
        this.series = series;
    }

    /**
     * <p>
     * Getter for username.
     * </p>
     * 
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * <p>
     * Setting value for username.
     * </p>
     * 
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * <p>
     * Getter for tokenValue.
     * </p>
     * 
     * @return the tokenValue
     */
    public String getTokenValue()
    {
        return tokenValue;
    }

    /**
     * <p>
     * Setting value for tokenValue.
     * </p>
     * 
     * @param tokenValue
     *            the tokenValue to set
     */
    public void setTokenValue(String tokenValue)
    {
        this.tokenValue = tokenValue;
    }

    /**
     * <p>
     * Getter for date.
     * </p>
     * 
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * <p>
     * Setting value for date.
     * </p>
     * 
     * @param date
     *            the date to set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

}
