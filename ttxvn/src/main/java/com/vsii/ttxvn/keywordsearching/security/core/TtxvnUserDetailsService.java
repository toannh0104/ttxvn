/*
 * FILENAME
 *     TtxvnUserDetailsService.java
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

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.enums.Status;
import com.vsii.ttxvn.keywordsearching.service.UserService;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * Integrates with Spring Security using our existing {@link UserService} by looking up a {@link TtxvnUser} and
 * converting it into a {@link UserDetails} so that Spring Security can do the username/password comparison for us.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Component
public class TtxvnUserDetailsService implements UserDetailsService, Serializable {
    private static final long serialVersionUID = -3567120098912327663L;
    private static final Log log = LogFactory.getLog(TtxvnUserDetailsService.class);
    
    /**
     * Lookup a {@link TtxvnUser} by the username representing the email address. Then, convert the
     * {@link TtxvnUser} into a {@link UserDetails} to conform to the {@link UserDetails} interface.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("-------------> username: " + username);
        TtxvnUser ttxvnUser = getUserService().findByUsername(username);
        log.debug("-------------> TtxvnUser is: " + (ttxvnUser != null ? "Not NULL" : "NULL"));
        if (ttxvnUser == null) {
            throw new UsernameNotFoundException("Invalid username.");
        }
        return new TtxvnUserDetails(ttxvnUser);
    }

    public UserService getUserService() {
        return ServiceResolver.findService(UserService.class);
    }
    
    /**
     * There are advantages to creating a class that extends {@link TtxvnUser}, our domain notion of a user, and
     * implements {@link UserDetails}, Spring Security's notion of a user.
     * <ul>
     * <li>First we can obtain all the custom information in the {@link TtxvnUser}</li>
     * <li>Second, we can use this service to integrate with Spring Security in other ways Remember-Me Authentication</li>
     * </ul>
     */
    private final class TtxvnUserDetails extends TtxvnUser implements UserDetails {
        private static final long serialVersionUID = 6673015415459108335L;
        
        private String account;
        private boolean active;
        
        TtxvnUserDetails(TtxvnUser ttxvnUser) {
            setId(ttxvnUser.getId());
            setEmail(ttxvnUser.getEmail());
            setFirstName(ttxvnUser.getFirstName());
            setLastName(ttxvnUser.getLastName());
            setUsername(ttxvnUser.getUsername());
            this.account = ttxvnUser.getUsername();
            setPassword(ttxvnUser.getPassword());
            this.active = (Status.ACTIVE == ttxvnUser.getStatus()) ? true : false;
            log.debug("----------- List Authorities --------------");
            for (GrantedAuthority ga : getAuthorities()) {
                log.debug(" + " + ga.getAuthority());
            }
            
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            String[] userPermissions = getUserService().findPermissions(this.getId());
            return TtxvnUserAuthorityUtils.createAuthorities(userPermissions);
        }
        
        // TODO: check later
        @Override
        public String getUsername() {
            return this.account;
        }

        @Override
        public boolean isAccountNonExpired() {
            return isActive();
        }

        @Override
        public boolean isAccountNonLocked() {
            return isActive();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return isActive();
        }

        @Override
        public boolean isEnabled() {
            return isActive();
        }

        public boolean isActive()
        {
            return active;
        }
    }
}