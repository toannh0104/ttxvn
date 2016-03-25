/*
 * FILENAME
 *     SourceUrlItem.java
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

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author dungtt
 **/
public class SourceUrlItem
{

	private long sourceUrlId;
	private String url;
	private int frequency;
	private int deep;
	private int reliability;
	private String sourceTypeCode;
	private long categoryId;
	private long frequencyId;

	public long getSourceUrlId()
	{
		return sourceUrlId;
	}

	public void setSourceUrlId(long sourceUrlId)
	{
		this.sourceUrlId = sourceUrlId;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getFrequency()
	{
		return frequency;
	}

	public void setFrequency(int frequency)
	{
		this.frequency = frequency;
	}
	
	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	public int getReliability()
	{
		return reliability;
	}

	public void setReliability(int reliability)
	{
		this.reliability = reliability;
	}

	public String getSourceTypeCode()
	{
		return sourceTypeCode;
	}

	public void setSourceTypeCode(String sourceTypeCode)
	{
		this.sourceTypeCode = sourceTypeCode;
	}

	public long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(long categoryId)
	{
		this.categoryId = categoryId;
	}

	public long getFrequencyId()
	{
		return frequencyId;
	}

	public void setFrequencyId(long frequencyId)
	{
		this.frequencyId = frequencyId;
	}

}
