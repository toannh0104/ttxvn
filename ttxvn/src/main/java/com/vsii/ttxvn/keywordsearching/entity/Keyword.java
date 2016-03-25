/*
 * FILENAME
 *     Keyword.java
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

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.vsii.ttxvn.keywordsearching.enums.KeywordStatus;

/**
 * <p>
 * The class for carrying the Keyword info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "keyword")
public class Keyword extends BaseEntity
{
    private static final long serialVersionUID = 7235667193825290558L;

    @Column(name = "NAME", length = 250, nullable = false)
    private String name;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private KeywordStatus status;

    @Column(name = "LAST_MODIFIED")
    @Temporal(TemporalType.DATE)
    private Date lastModified;

    @Column(name = "LAST_MODIFIED_USER_ID")
    private Long lastModifiedUserId;

    @OneToOne(mappedBy = "keyword", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private CategoryKeyword categoryKeyword;

    /**
     * <p>
     * The default constructor without arguments.
     * </p>
     *
     **/
    protected Keyword()
    {
    }

    public Keyword(String inputName, Date inputStartDate, Date inputEndDate, KeywordStatus inputStatus,
        Long inputLastModifiedUserId)
    {
        if (StringUtils.isBlank(inputName))
        {
            throw new IllegalArgumentException("The keyword's name cannot be blank");
        }

        if (inputStartDate == null)
        {
            throw new IllegalArgumentException("The start date cannot be blank");
        }

        if (inputEndDate == null)
        {
            throw new IllegalArgumentException("The end date cannot be blank");
        }

        if (inputStatus == null)
        {
            throw new IllegalArgumentException("The status cannot be blank");
        }

        if (inputLastModifiedUserId == null)
        {
            throw new IllegalArgumentException("The last modified user cannot be blank");
        }

        this.name = inputName;
        this.createdDate = Calendar.getInstance().getTime();
        this.startDate = inputStartDate;
        this.endDate = inputEndDate;
        this.status = inputStatus;
        this.lastModifiedUserId = inputLastModifiedUserId;
    }

    /**
     * <p>
     * Getter for name.
     * </p>
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>
     * Setting value for name.
     * </p>
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * <p>
     * Getter for startDate.
     * </p>
     * 
     * @return the startDate
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * <p>
     * Setting value for startDate.
     * </p>
     * 
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * <p>
     * Getter for endDate.
     * </p>
     * 
     * @return the endDate
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * <p>
     * Setting value for endDate.
     * </p>
     * 
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * <p>
     * Getter for status.
     * </p>
     * 
     * @return the status
     */
    public KeywordStatus getStatus()
    {
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
    public void setStatus(KeywordStatus status)
    {
        this.status = status;
    }

    /**
     * <p>
     * Getter for lastModified.
     * </p>
     * 
     * @return the lastModified
     */
    public Date getLastModified()
    {
        return lastModified;
    }

    /**
     * <p>
     * Setting value for lastModified.
     * </p>
     * 
     * @param lastModified
     *            the lastModified to set
     */
    public void setLastModified(Date lastModified)
    {
        this.lastModified = lastModified;
    }

    /**
     * <p>
     * Getter for lastModifiedUserId.
     * </p>
     * 
     * @return the lastModifiedUserId
     */
    public Long getLastModifiedUserId()
    {
        return lastModifiedUserId;
    }

    /**
     * <p>
     * Setting value for lastModifiedUserId.
     * </p>
     * 
     * @param lastModifiedUserId
     *            the lastModifiedUserId to set
     */
    public void setLastModifiedUserId(Long lastModifiedUserId)
    {
        this.lastModifiedUserId = lastModifiedUserId;
    }

    /**
     * <p>
     * Getter for categoryKeyword.
     * </p>
     * 
     * @return the categoryKeyword
     */
    public CategoryKeyword getCategoryKeyword()
    {
        return categoryKeyword;
    }

    /**
     * <p>
     * Setting value for categoryKeyword.
     * </p>
     * 
     * @param categoryKeyword
     *            the categoryKeyword to set
     */
    public void setCategoryKeyword(CategoryKeyword categoryKeyword)
    {
        this.categoryKeyword = categoryKeyword;
    }

    /**
     * <p>
     * Getter for createdDate.
     * </p>
     * 
     * @return the createdDate
     */
    public Date getCreatedDate()
    {
        return createdDate;
    }

}
