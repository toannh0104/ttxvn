/*
 * FILENAME
 *     UserGroup.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>
 * The class for carrying the Group info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "user_group")
public class UserGroup extends BaseEntity
{
    private static final long serialVersionUID = 7061594838845403799L;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    /**
     * <p>
     * The default constructor without arguments.
     * </p>
     *
     **/
    protected UserGroup()
    {
    }

    public UserGroup(User inputUser, Group inputGroup, Long inputCreatedUserId)
    {
        if (inputUser == null)
        {
            throw new IllegalArgumentException("The user cannot be empty");
        }

        if (inputGroup == null)
        {
            throw new IllegalArgumentException("The group cannot be empty");
        }

        if (inputCreatedUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be empty");
        }

        this.user = inputUser;
        this.group = inputGroup;
        this.createdUserId = inputCreatedUserId;
        this.createdDate = Calendar.getInstance().getTime();
    }

    /**
     * <p>
     * Getter for user.
     * </p>
     * 
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * <p>
     * Setting value for user.
     * </p>
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * <p>
     * Getter for group.
     * </p>
     * 
     * @return the group
     */
    public Group getGroup()
    {
        return group;
    }

    /**
     * <p>
     * Setting value for group.
     * </p>
     * 
     * @param group
     *            the group to set
     */
    public void setGroup(Group group)
    {
        this.group = group;
    }

    /**
     * <p>
     * Getter for createdUserId.
     * </p>
     * 
     * @return the createdUserId
     */
    public Long getCreatedUserId()
    {
        return createdUserId;
    }

    /**
     * <p>
     * Setting value for createdUserId.
     * </p>
     * 
     * @param createdUserId
     *            the createdUserId to set
     */
    public void setCreatedUserId(Long createdUserId)
    {
        this.createdUserId = createdUserId;
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
