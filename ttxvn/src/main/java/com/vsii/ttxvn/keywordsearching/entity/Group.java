/*
 * FILENAME
 *     Group.java
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

import com.vsii.ttxvn.keywordsearching.enums.Status;

/**
 * <p>
 * The class for carrying the Group info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "groups")
public class Group extends BaseEntity
{
    private static final long serialVersionUID = 4794473322829683452L;

    @Column(name = "NAME", unique = true, nullable = false)
    @Size(max = 250)
    private String name;

    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    @Column(name = "STATUS", nullable = false)
    private Status status;

    @Column(name = "DESCRIPTION", length = 250)
    @Size(max = 1000)
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<UserGroup> userGroup;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private Set<GroupRole> groupRoles;

    /**
     * <p>
     * The default constructor without arguments.
     * </p>
     *
     **/
    protected Group()
    {
    }

    public Group(String inputName, Long inputCreatedUserId, Status inputStatus)
    {
        if (StringUtils.isBlank(inputName))
        {
            throw new IllegalArgumentException("The group's name cannot be blank");
        }

        if (inputStatus == null)
        {
            inputStatus = Status.INACTIVE;
        }

        if (inputCreatedUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be blank");
        }

        this.name = inputName;
        this.status = inputStatus;
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
	 * Getter for status.
	 * </p>
	 * 
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * <p>
	 * Setting value for status.
	 * </p>
	 * 
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
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
     * Getter for userGroup.
     * </p>
     * 
     * @return the userGroup
     */
    public Set<UserGroup> getUserGroup()
    {
        return userGroup;
    }

    /**
     * <p>
     * Setting value for userGroup.
     * </p>
     * 
     * @param userGroup
     *            the userGroup to set
     */
    public void setUserGroup(Set<UserGroup> userGroup)
    {
        this.userGroup = userGroup;
    }

    /**
     * <p>
     * Getter for groupRoles.
     * </p>
     * 
     * @return the groupRoles
     */
    public Set<GroupRole> getGroupRoles()
    {
        return groupRoles;
    }

    /**
     * <p>
     * Setting value for groupRoles.
     * </p>
     * 
     * @param groupRoles
     *            the groupRoles to set
     */
    public void setGroupRoles(Set<GroupRole> groupRoles)
    {
        this.groupRoles = groupRoles;
    }
}