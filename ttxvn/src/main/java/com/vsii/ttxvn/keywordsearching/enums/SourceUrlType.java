/*
 * FILENAME
 *     SourceUrlType.java
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

package com.vsii.ttxvn.keywordsearching.enums;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * The class for purpose of storing Source URL Type.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public enum SourceUrlType
{
	NEWSPAPER(0, "ttxvn.keywordsearching.sourceUrlType.newspaper"),
	SOCIAL_NETWORK(1, "ttxvn.keywordsearching.sourceUrlType.socialNetwork"),
	OTHER(2, "ttxvn.keywordsearching.sourceUrlType.other"),
	FORUM(3, "ttxvn.keywordsearching.sourceUrlType.forum");

	private Integer value;
	private String messageCode;

	private SourceUrlType(Integer value, String messageCode)
	{
		this.value = value;
		this.messageCode = messageCode;
	}

	/**
	 * <p>
	 * Getter for value.
	 * </p>
	 * 
	 * @return the value
	 */
	public Integer getValue()
	{
		return value;
	}

	/**
	 * <p>
	 * Getter for messageCode.
	 * </p>
	 * 
	 * @return the messageCode
	 */
	public String getMessageCode()
	{
		return messageCode;
	}

	public static List<SourceUrlType> list()
	{
		return Arrays.asList(values());
	}

	public static SourceUrlType parse(Integer value)
	{
		for (SourceUrlType sut : list())
		{
			if (sut.getValue() == value)
			{
				return sut;
			}
		}

		return null;
	}
}
