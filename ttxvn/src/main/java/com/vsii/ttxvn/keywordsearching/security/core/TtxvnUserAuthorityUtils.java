/*
 * FILENAME
 *     TtxvnUserAuthorityUtils.java
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

package com.vsii.ttxvn.keywordsearching.security.core;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;

/**
 * <p>
 * A utility class used for creating the {@link GrantedAuthority}'s given a {@link TtxvnUser}. In a real solution this
 * would be looked up in the existing system, but for simplicity our original system had no notion of authorities.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public final class TtxvnUserAuthorityUtils
{
    private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList(new String[] {
        "ROLE_ADMIN", "ROLE_REPORTER", "ROLE_USER"
    });
    private static final List<GrantedAuthority> REPORTER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER",
        "ROLE_REPORTER");
    private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

    private static final List<GrantedAuthority> ANONYMOUS_ROLES = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");
    
    public static Collection<? extends GrantedAuthority> createAuthorities(TtxvnUser ttxvnUser)
    {
        String username = ttxvnUser.getUsername();
        
        if (username.startsWith("admin"))
        {
            return ADMIN_ROLES;
        }
        else if (username.startsWith("reporter"))
        {
            return REPORTER_ROLES;
        }

        return USER_ROLES;
    }

    public static Collection<? extends GrantedAuthority> createAuthorities(String[] userPermissions)
    {
        if (ArrayUtils.isNotEmpty(userPermissions)) {
            return AuthorityUtils.createAuthorityList(userPermissions);
        }
        
        return ANONYMOUS_ROLES;
    }
    
    private TtxvnUserAuthorityUtils()
    {
    }
}
