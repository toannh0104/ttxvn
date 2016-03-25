/*
 * FILENAME
 *     Permission.java
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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * The class for carrying the Group info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity
{
    private static final long serialVersionUID = 1568925542258523273L;

    @Column(name = "NAME", unique = true, nullable = false)
    @Size(max = 250)
    private String name;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "DESCRIPTION", length = 250)
    @Size(max = 250)
    private String description;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.REMOVE)
    private Set<RolePermission> rolePermissions;

    /**
     * <p>
     * The default constructor without arguments.
     * </p>
     *
     **/
    protected Permission()
    {
    }

    public Permission(String inputName, Long inputCreatedUserId)
    {
        if (StringUtils.isBlank(inputName))
        {
            throw new IllegalArgumentException("The group's name cannot be empty");
        }

        if (createdUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be empty");
        }

        this.name = inputName;
        this.createdUserId = inputCreatedUserId;
        this.createdDate = Calendar.getInstance().getTime();
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

    /**
     * <p>
     * Getter for description.
     * </p>
     * 
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * <p>
     * Setting value for description.
     * </p>
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * <p>
     * Getter for rolePermissions.
     * </p>
     * 
     * @return the rolePermissions
     */
    public Set<RolePermission> getRolePermissions()
    {
        return rolePermissions;
    }

    /**
     * <p>
     * Setting value for rolePermissions.
     * </p>
     * 
     * @param rolePermissions
     *            the rolePermissions to set
     */
    public void setRolePermissions(Set<RolePermission> rolePermissions)
    {
        this.rolePermissions = rolePermissions;
    }

}
