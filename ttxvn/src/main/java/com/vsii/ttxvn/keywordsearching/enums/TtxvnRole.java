/*
 * FILENAME
 *     TtxvnRole.java
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
 * The class for purpose of storing TTXVN Roles.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public enum TtxvnRole
{
	ANONYMOUS(0,"ttxvn.keywordsearching.role.anonymous"),
	USER(1, "ttxvn.keywordsearching.role.user"),
	MANAGE_USER(2, "ttxvn.keywordsearching.role.manage.user"),
    MANAGE_CATEGORY(3, "ttxvn.keywordsearching.role.manage.category"),
    MANAGE_SOURCE_URL(4, "ttxvn.keywordsearching.role.manage.source.url"),
    REPORTER(5, "ttxvn.keywordsearching.role.reporter"),
    SYSTEM_ADMIN(6, "ttxvn.keywordsearching.role.system.admin");

	private long value;
	private String messageCode;

	private TtxvnRole(long value, String messageCode)
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
	public long getValue()
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

	public static List<TtxvnRole> list()
	{
		return Arrays.asList(values());
	}

	public static TtxvnRole parse(long value)
	{
		for (TtxvnRole sut : list())
		{
			if (sut.getValue() == value)
			{
				return sut;
			}
		}

		return null;
	}
}
