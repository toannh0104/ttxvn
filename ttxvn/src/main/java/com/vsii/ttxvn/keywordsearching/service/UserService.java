/*
 * FILENAME
 *     UserService.java
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

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.enums.Gender;
import com.vsii.ttxvn.keywordsearching.enums.Status;

/**
 * <p>
 * The class services for User.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/

public interface UserService extends GenericDao
{
    public List<User> getAllUser();

    public PageImpl<TtxvnUser> getAllUserByNameGroupEmail(long userId, String username, String groupByName,
        String email, PageRequest pageRequest);

    public User checkOldPassword(String username, String oldPassword);

    public String updateNewPassword(User user, String newPassword);

    public User getUserByEmail(String email);

    public User getUserByName(String name);

    public TtxvnUser findByUsername(String username);
    
    public User findByEmail(String email);
    
    public String[] findPermissions(Long userId);
    
    public String createUser(String name, String password, String firstName, String middleName, String lastname,
        Date birthDay, Gender gender, String phoneNumber, String email, String province, String district,
        String address, long userIdCreate, Status status);

    public String updateUser(User user, String firstName, String middleName, String lastname, Date birthDay,
        Gender gender, String phoneNumber, String province, String district, String address, long userIdCreate,
        Status status);

    public User getUserById(long id);

    public List<Group> getListGroupByUserId(long id);

    public List<Group> getListGroupNotContainUserId(long id);
    
    public String resetPassword(User user);
}
