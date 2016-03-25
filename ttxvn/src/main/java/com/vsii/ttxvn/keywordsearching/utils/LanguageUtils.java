/*
 * FILENAME
 *     LanguageUtils.java
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

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class LanguageUtils
{
	public static LanguageCode getCurrentLanguageCode()
	{
		Locale locate = LocaleContextHolder.getLocale();

		if (locate != null)
		{
			return LanguageCode.parse(locate.getLanguage());
		}

		return null;
	}
}
