/*
 * FILENAME
 *     HomepageController.java
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

package com.vsii.ttxvn.keywordsearching.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The Controller for Hompage requests.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Controller
public class HomepageController extends BaseController
{
    @RequestMapping("/home")
    public ModelAndView viewHompage()
    {
        ModelAndView modelAndView = new ModelAndView("homepage");
        modelAndView.addObject("showCreateCategory", TtxvnUserContext.isUserInRoleManageCategory());
        return modelAndView;
    }

    @RequestMapping("/")
    public String welcome()
    {
        return "redirect:/home";
    }
}
