/*
 * FILENAME
 *     GroupRole.java
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
 * The class for carrying the relationship between the Group and Group's Roles.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "group_role")
public class GroupRole extends BaseEntity
{
    private static final long serialVersionUID = -4711856532030681021L;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

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
    protected GroupRole()
    {
    }

    public GroupRole(Group inputGroup, Role inputRole, Long inputCreatedUserId)
    {
        if (inputGroup == null)
        {
            throw new IllegalArgumentException("The group cannot be empty");
        }

        if (inputRole == null)
        {
            throw new IllegalArgumentException("The role cannot be empty");
        }

        if (inputCreatedUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be empty");
        }

        this.group = inputGroup;
        this.role = inputRole;
        this.createdUserId = inputCreatedUserId;
        this.createdDate = Calendar.getInstance().getTime();
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
     * Getter for role.
     * </p>
     * 
     * @return the role
     */
    public Role getRole()
    {
        return role;
    }

    /**
     * <p>
     * Setting value for role.
     * </p>
     * 
     * @param role
     *            the role to set
     */
    public void setRole(Role role)
    {
        this.role = role;
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
