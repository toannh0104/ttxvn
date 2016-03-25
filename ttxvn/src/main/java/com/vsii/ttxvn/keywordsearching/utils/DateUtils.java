/*
 * FILENAME
 *     DateUtils.java
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

package com.vsii.ttxvn.keywordsearching.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * The utility for manipulate the Date.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class DateUtils
{
	static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String ISO_8601_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static String format(Date date, String pattern)
	{
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	public static Date convertStringToDate(String date, String pattern)
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try
		{
			return format.parse(date);
		}
		catch (ParseException e)
		{
			logger.error(e.getMessage());
		}
		return null;
	}

	public static String getCurrentDate()
	{
		final SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		return dateFormat.format(new Date());
	}

	public static String getISODate(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat(ISO_8601_DATE_TIME);
		dateFormat.setTimeZone(TimeZone.getDefault());
		Calendar calendar = Calendar.getInstance();
		if (date != null)
			calendar.setTime(date);
		return dateFormat.format(calendar.getTime());
	}
}
