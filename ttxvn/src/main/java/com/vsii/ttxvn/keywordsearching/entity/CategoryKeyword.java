/*
 * FILENAME
 *     CategoryKeyword.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * The class for carrying the relationship between the User/Category and
 * keywords.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "category_keyword")
public class CategoryKeyword extends BaseEntity
{
	private static final long serialVersionUID = -6818692307455991057L;

	@Column(name = "CATEGORY_NAME", length = 250, nullable = false)
	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	@Column(insertable = false, updatable = false)
	private Long categoryId;

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "keywordId")
	private Keyword keyword;
	
	@Column(insertable = false, updatable = false)
    private Long userId;
	
	@Column(insertable = false, updatable = false)
	private Long keywordId;

	/**
	 * <p>
	 * The default constructor without arguments.
	 * </p>
	 * 
	 **/
	protected CategoryKeyword()
	{
	}

	public CategoryKeyword(String inputCategoryName)
	{
		if (StringUtils.isBlank(inputCategoryName))
		{
			throw new IllegalArgumentException("The category name cannot be blank");
		}

		this.categoryName = inputCategoryName;
		this.createdDate = Calendar.getInstance().getTime();
	}

	/**
	 * <p>
	 * Getter for categoryName.
	 * </p>
	 * 
	 * @return the categoryName
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * <p>
	 * Setting value for categoryName.
	 * </p>
	 * 
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
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
		this.userId = user == null ? null : user.getId();
	}

	/**
	 * <p>
	 * Getter for category.
	 * </p>
	 * 
	 * @return the category
	 */
	public Category getCategory()
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
	public void setCategory(Category category)
	{
		this.category = category;
		this.categoryId = category == null ? null : category.getId();
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
	 * Getter for keyword.
	 * </p>
	 * 
	 * @return the keyword
	 */
	public Keyword getKeyword()
	{
		return keyword;
	}

	/**
	 * <p>
	 * Setting value for keyword.
	 * </p>
	 * 
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(Keyword keyword)
	{
		this.keyword = keyword;
		this.keywordId = keyword == null ? null : keyword.getId();
	}

	public Long getUserId()
	{
		return userId;
	}

	public Long getKeywordId()
	{
		return keywordId;
	}

	public Long getCategoryId()
	{
		return categoryId;
	}

}
