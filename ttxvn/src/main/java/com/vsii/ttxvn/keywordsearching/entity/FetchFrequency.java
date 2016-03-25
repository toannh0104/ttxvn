/*
 * FILENAME
 *     FetchFrequency.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.vsii.ttxvn.keywordsearching.schedule.JobObject;

/**
 * <p>
 * The class for carrying the Fetch Frequency info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "fetch_frequency")
public class FetchFrequency extends BaseEntity implements JobObject
{
	private static final long serialVersionUID = 970937443085373349L;

	@Column(name = "NAME_CODE", unique = true, nullable = false)
	@Size(max = 250)
	private String nameCode;

	@Column(name = "CREATED_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "CREATED_USER_ID", nullable = false)
	private Long createdUserId;

	@Column(name = "FREQUENCY", nullable = false)
	private Integer frequency;

	@OneToMany(mappedBy = "fetchFrequency", fetch = FetchType.LAZY)
	private Set<SourceUrl> sourceUrls;

	/**
	 * <p>
	 * The default constructor without arguments.
	 * </p>
	 * 
	 **/
	protected FetchFrequency()
	{
	}

	public FetchFrequency(String inputName, Long createdUserId, Integer frequency)
	{
		if (StringUtils.isBlank(inputName))
		{
			throw new IllegalArgumentException("The frequency's name cannot be blank");
		}

		if (createdUserId == null)
		{
			throw new IllegalArgumentException("The created user cannot be blank");
		}

		if (frequency == null)
		{
			throw new IllegalArgumentException("The frequency cannot be blank");
		}

		this.nameCode = inputName;
		this.createdDate = Calendar.getInstance().getTime();
		this.createdUserId = createdUserId;
		this.frequency = frequency;
	}

	/**
	 * <p>
	 * Getter for nameCode.
	 * </p>
	 * 
	 * @return the nameCode
	 */
	public String getNameCode()
	{
		return nameCode;
	}

	/**
	 * <p>
	 * Setting value for nameCode.
	 * </p>
	 * 
	 * @param nameCode
	 *            the nameCode to set
	 */
	public void setNameCode(String nameCode)
	{
		this.nameCode = nameCode;
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
	 * Getter for frequency.
	 * </p>
	 * 
	 * @return the frequency
	 */
	public Integer getFrequency()
	{
		return frequency;
	}

	/**
	 * <p>
	 * Setting value for frequency.
	 * </p>
	 * 
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(Integer frequency)
	{
		this.frequency = frequency;
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
