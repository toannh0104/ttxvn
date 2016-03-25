/*
 * FILENAME
 *     TtxvnUserContext.java
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

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;

/**
 * <p>
 * A class that looks up the {@link TtxvnUser} using the Spring Security's {@link Authentication} by principal name.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class TtxvnUserContext
{
    private static final String ROLE_MANAGE_CATEGORY = "MANAGE_CATEGORY";
    private static final String ROLE_SYS_ADMIN_CATEGORY = "SYSTEM_ADMIN";

    public static TtxvnUser getCurrentUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null)
        {
            return null;
        }

        return (TtxvnUser) authentication.getPrincipal();
    }

    public static Long getCurrentUserId()
    {
        TtxvnUser currentUser = TtxvnUserContext.getCurrentUser();

        if (currentUser != null)
        {
            return currentUser.getId();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static boolean isUserInRoleManageCategory()
    {
        Collection<SimpleGrantedAuthority> authorities =
            (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();

        for (SimpleGrantedAuthority authority : authorities)
        {
            if (authority.getAuthority().equals(ROLE_MANAGE_CATEGORY))
            {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isUserInRoleSysAdmin()
    {
        Collection<SimpleGrantedAuthority> authorities =
            (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();
        
        for (SimpleGrantedAuthority authority : authorities)
        {
            if (authority.getAuthority().equals(ROLE_SYS_ADMIN_CATEGORY))
            {
                return true;
            }
        }
        
        return false;
    }
}
