/*
 * FILENAME
 *     Md5.java
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author ducvq
 **/
public class Md5
{
    private static final String MESSAGE_DIGEST = "MD5";
    private static final int MAX_LENGTH = 32;
    private static final String ENCRYPT_KEYWORD = "0";
    private static final int SIGNUM = 1;
    private static final int STRING_LENGTH = 16;

    public static String encryptPassword(String password) throws Exception
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(MESSAGE_DIGEST);
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(SIGNUM, messageDigest);
            String encryptPass = number.toString(STRING_LENGTH);

            while (encryptPass.length() < MAX_LENGTH)
            {
                encryptPass = ENCRYPT_KEYWORD + encryptPass;
            }

            return encryptPass;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Exception(e);
        }

    }
}
