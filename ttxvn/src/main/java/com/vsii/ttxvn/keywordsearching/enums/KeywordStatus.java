/*
 * FILENAME
 *     KeywordStatus.java
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
 * The class for purpose of storing keyword's status.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public enum KeywordStatus
{
    TRACKING(0, "ttxvn.keyworksearching.common.label.tracking"),
    PAUSE_TRACKING(1, "ttxvn.keyworksearching.common.label.pause.tracking"),
    END_TRACKING(2, "ttxvn.keyworksearching.common.label.end.tracking");

    private Integer value;
    private String messageCode;

    private KeywordStatus(Integer value, String messageCode)
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

    public static List<KeywordStatus> list()
    {
        return Arrays.asList(values());
    }

    public static KeywordStatus parse(Integer value)
    {
        for (KeywordStatus userStatus : list())
        {
            if (userStatus.getValue() == value)
            {
                return userStatus;
            }
        }

        return null;
    }
}
