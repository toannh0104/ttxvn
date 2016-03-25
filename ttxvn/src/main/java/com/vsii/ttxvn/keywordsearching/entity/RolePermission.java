/*
 * FILENAME
 *     RolePermission.java
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
@Table(name = "role_permission")
public class RolePermission extends BaseEntity
{
    private static final long serialVersionUID = 7453945525598143722L;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID", nullable = false)
    private Permission permission;

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
    protected RolePermission()
    {
    }

    public RolePermission(Role inputRole, Permission inputPermission, Long inputCreatedUserId)
    {
        if (inputRole == null)
        {
            throw new IllegalArgumentException("The role cannot be empty");
        }

        if (inputPermission == null)
        {
            throw new IllegalArgumentException("The permission cannot be empty");
        }

        if (inputCreatedUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be empty");
        }

        this.role = inputRole;
        this.permission = inputPermission;
        this.createdUserId = inputCreatedUserId;
        this.createdDate = Calendar.getInstance().getTime();
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
     * Getter for permission.
     * </p>
     * 
     * @return the permission
     */
    public Permission getPermission()
    {
        return permission;
    }

    /**
     * <p>
     * Setting value for permission.
     * </p>
     * 
     * @param permission
     *            the permission to set
     */
    public void setPermission(Permission permission)
    {
        this.permission = permission;
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
