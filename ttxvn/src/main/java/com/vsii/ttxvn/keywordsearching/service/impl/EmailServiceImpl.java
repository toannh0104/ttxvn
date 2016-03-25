/*
 * FILENAME
 *     EmailServiceImpl.java
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

package com.vsii.ttxvn.keywordsearching.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.vsii.ttxvn.keywordsearching.service.EmailService;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("emailService")
public class EmailServiceImpl implements EmailService
{
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private static final String RECOVER_PASSWORD_TEMPLATE_LOCATION = "/recoverPassword.vm";
    private static final String UTF8_ENCODING = "UTF-8";

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.service.EmailService#send(org.springframework.mail.SimpleMailMessage,
     *      java.lang.String, java.lang.String, java.util.Map)
     */
    @Override
    public void send(final SimpleMailMessage mailMessage, final String templateLocation, final String encoding,
        final Map<String, Object> model)
    {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailMessage.getTo());
                message.setFrom(mailMessage.getFrom());
                message.setSubject(mailMessage.getSubject());

                String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, encoding, model);

                logger.debug("body: ", body);

                message.setText(body, true);
            }
        };

        mailSender.send(preparator);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vsii.ttxvn.keywordsearching.mail.EmailSender#sendPassword(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void sendPassword(String toAccount, String toEmail, String newPassword)
    {
        this.simpleMailMessage.setTo(toEmail);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username", toAccount);
        model.put("password", newPassword);

        send(this.simpleMailMessage, RECOVER_PASSWORD_TEMPLATE_LOCATION, UTF8_ENCODING, model);
    }
}