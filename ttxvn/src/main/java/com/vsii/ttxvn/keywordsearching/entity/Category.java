/*
 * FILENAME
 *     Category.java
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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class for carrying the Category info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "category")
public class Category extends BaseEntity
{
	private static final long serialVersionUID = -4810829510066460069L;

	@Column(name = "NAME", length = 250, nullable = false)
	private String name;

	@Column(name = "LANG_CODE")
	private LanguageCode langCode;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "CREATED_USER_ID")
	private Long createdUserId;

	@Column(name = "IS_GLOBAL")
	private Boolean global;

	@Column(name = "LAST_MODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;

	@Column(name = "LAST_MODIFIED_USER_ID")
	private Long lastModifiedUserId;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<SourceUrl> sourceUrls = new HashSet<SourceUrl>();

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	/**
	 * <p>
	 * The default constructor without arguments.
	 * </p>
	 * 
	 **/
	protected Category()
	{
	}

	public Category(String inputName, LanguageCode inputLangCode, Long inputCreatedUserId, Boolean inputGlobal)
	{
		if (StringUtils.isBlank(inputName))
		{
			throw new IllegalArgumentException("The group's name cannot be blank");
		}

		if (inputLangCode == null)
		{
			throw new IllegalArgumentException("The language cannot be blank");
		}

		if (inputCreatedUserId == null)
		{
			throw new IllegalArgumentException("The created user cannot be blank");
		}

		this.name = inputName;
		this.langCode = inputLangCode;
		this.createdDate = Calendar.getInstance().getTime();
		this.createdUserId = inputCreatedUserId;
		this.global = inputGlobal;
		this.lastModified = Calendar.getInstance().getTime();
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
	 * Getter for langCode.
	 * </p>
	 * 
	 * @return the langCode
	 */
	public LanguageCode getLangCode()
	{
		return langCode;
	}

	/**
	 * <p>
	 * Setting value for langCode.
	 * </p>
	 * 
	 * @param langCode
	 *            the langCode to set
	 */
	public void setLangCode(LanguageCode langCode)
	{
		this.langCode = langCode;
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
	 * Getter for global.
	 * </p>
	 * 
	 * @return the global
	 */
	public Boolean getGlobal()
	{
		return global;
	}

	/**
	 * <p>
	 * Setting value for global.
	 * </p>
	 * 
	 * @param global
	 *            the global to set
	 */
	public void setGlobal(Boolean global)
	{
		this.global = global;
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
	 * Getter for sourceUrls.
	 * </p>
	 * 
	 * @return the sourceUrls
	 */
	public Set<SourceUrl> getSourceUrls()
	{
		return sourceUrls;
	}

	/**
	 * <p>
	 * Setting value for sourceUrls.
	 * </p>
	 * 
	 * @param sourceUrls
	 *            the sourceUrls to set
	 */
	public void setSourceUrls(Set<SourceUrl> sourceUrls)
	{
		this.sourceUrls = sourceUrls;
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

}
