/*
 * FILENAME
 *     TtxvnUser.java
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

package com.vsii.ttxvn.keywordsearching.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.vsii.ttxvn.keywordsearching.enums.Status;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class TtxvnUser implements Serializable
{
    private static final long serialVersionUID = -5425204171716334887L;
    
    private Long id;
    private String username;
    private String password;
    private String fullname;
	private String firstName;
    private String middleName;
    private String lastName;
    private Date bithday;
    private String gender;
    private String phone;
    private String email;
    private String province;
    private String district;
    private String address;
    private Date createdDate;
    private Long createdUserId;
    private Status status;
    private Date lastModified;
    private Long lastModifiedUserId;
    private String group;

	/**
     * <p>
     * Getter for id.
     * </p>
     * 
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * <p>
     * Setting value for id.
     * </p>
     * 
     * @param id
     *            the id to set
     */
    public void setId(Long id)
    {
        this.id = id;
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
     * Getter for password.
     * </p>
     * 
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * <p>
     * Setting value for password.
     * </p>
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	
    /**
     * <p>
     * Getter for firstName.
     * </p>
     * 
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * <p>
     * Setting value for firstName.
     * </p>
     * 
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * <p>
     * Getter for middleName.
     * </p>
     * 
     * @return the middleName
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * <p>
     * Setting value for middleName.
     * </p>
     * 
     * @param middleName
     *            the middleName to set
     */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /**
     * <p>
     * Getter for lastName.
     * </p>
     * 
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * <p>
     * Setting value for lastName.
     * </p>
     * 
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * <p>
     * Getter for bithday.
     * </p>
     * 
     * @return the bithday
     */
    public Date getBithday()
    {
        return bithday;
    }

    /**
     * <p>
     * Setting value for bithday.
     * </p>
     * 
     * @param bithday
     *            the bithday to set
     */
    public void setBithday(Date bithday)
    {
        this.bithday = bithday;
    }


    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
     * <p>
     * Getter for phone.
     * </p>
     * 
     * @return the phone
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * <p>
     * Setting value for phone.
     * </p>
     * 
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * <p>
     * Getter for email.
     * </p>
     * 
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * <p>
     * Setting value for email.
     * </p>
     * 
     * @param email
     *            the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * <p>
     * Getter for province.
     * </p>
     * 
     * @return the province
     */
    public String getProvince()
    {
        return province;
    }

    /**
     * <p>
     * Setting value for province.
     * </p>
     * 
     * @param province
     *            the province to set
     */
    public void setProvince(String province)
    {
        this.province = province;
    }

    /**
     * <p>
     * Getter for district.
     * </p>
     * 
     * @return the district
     */
    public String getDistrict()
    {
        return district;
    }

    /**
     * <p>
     * Setting value for district.
     * </p>
     * 
     * @param district
     *            the district to set
     */
    public void setDistrict(String district)
    {
        this.district = district;
    }

    /**
     * <p>
     * Getter for address.
     * </p>
     * 
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * <p>
     * Setting value for address.
     * </p>
     * 
     * @param address
     *            the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
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
     * Setting value for createdDate.
     * </p>
     * 
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
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
    public Status getStatus()
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
    public void setStatus(Status status)
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
    
    public String getGroup() {
  		return group;
  	}

  	public void setGroup(String group) {
  		this.group = group;
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
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
