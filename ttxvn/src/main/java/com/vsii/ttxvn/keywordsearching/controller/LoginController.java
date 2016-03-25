/*
 * FILENAME
 *     LoginController.java
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.service.EmailService;
import com.vsii.ttxvn.keywordsearching.service.LoginService;
import com.vsii.ttxvn.keywordsearching.service.UserService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.Md5;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author ducvq
 **/
@Controller
public class LoginController extends BaseController {

    private static final String REQUEST_PARAM_USERNAME = "username";
    private static final String REQUEST_PARAM_PASSWORD = "password";
    private static final String REQUEST_PARAM_EMAIL = "email";

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/login/form")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/errors/403")
    public ModelAndView error403() {
        return new ModelAndView("/errors/403");
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // SercurityContext.getInstance().setContextUser(null);
        session.setAttribute("userLogin", null);
        session.invalidate();

        return new ModelAndView("login");
    }

    /**
     * 
     * <p>
     * Add one-sentence summarising the method functionality here; this sentence
     * should only contain one full-stop.
     * </p>
     * <p>
     * Add detailed HTML description of method here, including the following,
     * where relevant:
     * <ul>
     * <li>Description of what the method does and how it is done.</li>
     * <li>Details on which error conditions may occur.</li>
     * </ul>
     * </p>
     *
     * @since TODO module version when it was introduced
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     *
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> checkLogin(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {

        Map<String, String> map = new HashMap<String, String>();
        String username = ServletRequestUtils.getStringParameter(request,
                REQUEST_PARAM_USERNAME, Constants.EMPTY_STRING);
        String password = ServletRequestUtils.getStringParameter(request,
                REQUEST_PARAM_PASSWORD, Constants.EMPTY_STRING);

        String message = Constants.EMPTY_STRING;

        try {
            String md5Password = Md5.encryptPassword(password);

            User user = getLoginService().checkLogin(username, md5Password);

            if (user == null) {
                message = "fail";
                map.put("message", message);

            } else {
                map.put("message", message);
                try {
                    request.getSession().setAttribute("userLogin", user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TtxvnUserContext.getCurrentUser();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return map;
    }
    
    @RequestMapping(value = "anonymous/recovery-password", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> recoveryPassword(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<String, String>();
        String email = ServletRequestUtils.getStringParameter(request,
                REQUEST_PARAM_EMAIL, Constants.EMPTY_STRING);
        User user = getUserService().findByEmail(email);

        if (user == null) {
            map.put("message", "fail");
        } else {
            String newPassword = getUserService().resetPassword(user);
            
            if (newPassword != null) {
                getEmailService().sendPassword(user.getUsername(), user.getEmail(), newPassword);
            } else {
                map.put("message", "fail");
            }   
        }
        
        return map;
    }

    private LoginService getLoginService() {
        return ServiceResolver.findService(LoginService.class);
    }
    
    private UserService getUserService() {
        return ServiceResolver.findService(UserService.class);
    }
    
    private EmailService getEmailService() {
        return ServiceResolver.findService(EmailService.class);
    }
}