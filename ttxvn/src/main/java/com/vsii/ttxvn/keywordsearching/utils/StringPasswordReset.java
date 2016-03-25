/*
 * FILENAME
 *     StringPasswordReset.java
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
 * @author ducvq
 **/
public class StringPasswordReset {
	public final static int STR_NUMBER = 1;
	public final static int STR_UPPER_CASE = 2;
	public final static int STR_LOWER_CASE = 4;

	private final static String NUMBERS = "0123456789";
	private final static String ALPHABE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String getPassword = new String("");

	public StringPasswordReset(int mode) {
		if ((mode & STR_NUMBER) != 0) {
			getPassword += NUMBERS;
		}
		if ((mode & STR_UPPER_CASE) != 0) {
			getPassword += ALPHABE;
		}
		if ((mode & STR_LOWER_CASE) != 0 || getPassword.isEmpty()) {
			getPassword += ALPHABE.toLowerCase();
		}
	}

	public String next(int length) {
		String string = new String();
		final int max = getPassword.length();
		for (int i = 0; i < length; i++) {
			string += getPassword.charAt(((int) (Math.random() * 1000)) % max);
		}
		return string;
	}
}
