/*
 * FILENAME
 *     User.java
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

import com.vsii.ttxvn.keywordsearching.enums.Gender;
import com.vsii.ttxvn.keywordsearching.enums.Status;

/**
 * <p>
 * The class for carrying the User info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "user")
public class User extends BaseEntity
{
    private static final long serialVersionUID = 356302914380439126L;

    @Column(name = "USERNAME", unique = true, nullable = false)
    @Size(max = 250)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    @Size(min = 6, max = 250)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    @Size(max = 250)
    private String firstName;

    @Column(name = "MIDDLE_NAME", nullable = true)
    @Size(max = 250)
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    @Size(max = 250)
    private String lastName;

    @Column(name = "BITHDAY", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date bithday;

    @Column(name = "GENDER", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "PHONE", nullable = false)
    @Size(max = 250)
    private String phone;

    @Email
    @Column(name = "EMAIL", unique = true, nullable = false)
    @Size(max = 250)
    private String email;

    @Column(name = "PROVINCE", nullable = false)
    @Size(max = 250)
    private String province;

    @Column(name = "DISTRICT", nullable = false)
    @Size(max = 250)
    private String district;

    @Column(name = "ADDRESS", nullable = false)
    @Size(max = 250)
    private String address;

    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column(name = "CREATED_USER_ID", nullable = false)
    private Long createdUserId;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "LAST_MODIFIED")
    @Temporal(TemporalType.DATE)
    private Date lastModified;

    @Column(name = "LAST_MODIFIED_USER_ID")
    private Long lastModifiedUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<UserGroup> userGroup;

    @OneToMany(mappedBy = "user")
    private Set<Category> category;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<CategoryKeyword> categoryKeywords;

    /**
     * <p>
     * The default constructor without arguments.
     * </p>
     *
     **/
    
    public User(String inputEmail, String inputPassword)
    {
        if (StringUtils.isBlank(inputPassword))
        {
            throw new IllegalArgumentException("The password cannot be blank");
        }

        if (StringUtils.isBlank(inputEmail))
        {
            throw new IllegalArgumentException("The email cannot be blank");
        }

        this.email = inputEmail;
        this.password = inputPassword;
    }

    public User(String inputUsername, String inputPassword, String inputFirstName, String inputMiddleName,
        String inputLastName, Date inputBithday, Gender inputGender, String inputPhone, String inputEmail,
        String inputProvince, String inputDistrict, String inputAddress, Long inputCreatedUserId, Status inputStatus)
    {
        if (StringUtils.isBlank(inputUsername))
        {
            throw new IllegalArgumentException("The username cannot be blank");
        }

        if (StringUtils.isBlank(inputPassword))
        {
            throw new IllegalArgumentException("The password cannot be blank");
        }

        if (StringUtils.isBlank(inputFirstName))
        {

            throw new IllegalArgumentException("The first name cannot be blank");
        }

        if (StringUtils.isBlank(inputLastName))
        {
            throw new IllegalArgumentException("The last name cannot be blank");
        }

        if (inputBithday == null)
        {
            throw new IllegalArgumentException("The birthday cannot be blank");
        }

        if (inputGender == null)
        {
            throw new IllegalArgumentException("The gender cannot be blank");
        }

        if (StringUtils.isBlank(inputPhone))
        {
            throw new IllegalArgumentException("The phone cannot be blank");
        }

        if (StringUtils.isBlank(inputEmail))
        {
            throw new IllegalArgumentException("The email cannot be blank");
        }

        if (StringUtils.isBlank(inputProvince))
        {
            throw new IllegalArgumentException("The province cannot be blank");
        }

        if (StringUtils.isBlank(inputDistrict))
        {
            throw new IllegalArgumentException("The district cannot be blank");
        }

        if (StringUtils.isBlank(inputAddress))
        {
            throw new IllegalArgumentException("The address cannot be blank");
        }

        if (inputCreatedUserId == null)
        {
            throw new IllegalArgumentException("The created user cannot be blank");
        }

        if (inputStatus == null)
        {
            throw new IllegalArgumentException("The status cannot be blank");
        }

        this.username = inputUsername;
        this.password = inputPassword;
        this.firstName = inputFirstName;
        this.middleName = inputMiddleName;
        this.lastName = inputLastName;
        this.bithday = inputBithday;
        this.gender = inputGender;
        this.phone = inputPhone;
        this.email = inputEmail;
        this.province = inputProvince;
        this.district = inputDistrict;
        this.address = inputAddress;
        this.createdDate = Calendar.getInstance().getTime();
        this.createdUserId = inputCreatedUserId;
        this.status = inputStatus;
    }

    public User() {
		
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

    /**
     * <p>
     * Getter for gender.
     * </p>
     * 
     * @return the gender
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * <p>
     * Setting value for gender.
     * </p>
     * 
     * @param gender
     *            the gender to set
     */
    public void setGender(Gender gender)
    {
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

    /**
     * <p>
     * Setting value for lastModified.
     * </p>
     * 
     * @param lastModified the lastModified to set
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
     * @param lastModifiedUserId the lastModifiedUserId to set
     */
    public void setLastModifiedUserId(Long lastModifiedUserId)
    {
        this.lastModifiedUserId = lastModifiedUserId;
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
     * Getter for category.
     * </p>
     * 
     * @return the category
     */
    public Set<Category> getCategory()
    {
        return category;
    }

    /**
     * <p>
     * Setting value for category.
     * </p>
     * 
     * @param category
     *            the category to set
     */
    public void setCategory(Set<Category> category)
    {
        this.category = category;
    }

    /**
     * <p>
     * Getter for categoryKeywords.
     * </p>
     * 
     * @return the categoryKeywords
     */
    public Set<CategoryKeyword> getCategoryKeywords()
    {
        return categoryKeywords;
    }

    /**
     * <p>
     * Setting value for categoryKeywords.
     * </p>
     * 
     * @param categoryKeywords
     *            the categoryKeywords to set
     */
    public void setCategoryKeywords(Set<CategoryKeyword> categoryKeywords)
    {
        this.categoryKeywords = categoryKeywords;
    }

}
