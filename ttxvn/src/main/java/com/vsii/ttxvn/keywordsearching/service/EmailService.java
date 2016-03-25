/*
 * FILENAME
 *     EmailService.java
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

package com.vsii.ttxvn.keywordsearching.service;

import java.util.Map;

import org.springframework.mail.SimpleMailMessage;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public interface EmailService
{
    /**
     * Sends e-mail using Velocity template for the body and the properties passed in as Velocity variables.
     *
     * @param mailMessage
     *            the e-mail message to be sent, except for the body.
     * @param templateLocation
     *            the location of e-mail template name in classpath.
     * @param model
     *            variables to use when processing the template.
     */
    public void send(SimpleMailMessage mailMessage, final String templateLocation, String encoding,
        final Map<String, Object> model);

    public void sendPassword(final String toAccount, final String toEmail, final String newPassword);
}
