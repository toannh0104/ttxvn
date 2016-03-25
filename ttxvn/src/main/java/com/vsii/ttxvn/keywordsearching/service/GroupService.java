/*
 * FILENAME
 *     GroupService.java
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

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.vsii.ttxvn.keywordsearching.dao.GenericDao;
import com.vsii.ttxvn.keywordsearching.domain.GroupItem;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.GroupRole;
import com.vsii.ttxvn.keywordsearching.entity.Role;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.entity.UserGroup;
import com.vsii.ttxvn.keywordsearching.enums.Status;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author trungvd
 **/
public interface GroupService extends GenericDao {
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
	 * @param name
	 * @param status
	 * @param description
	 * @param createdUserId
	 * @return
	 *
	 */
	public long createGroup(String name, Status status, String description,
			long createdUserId);

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
	 * @param userId
	 * @param status
	 * @return
	 *
	 */
	public List<Group> getListGroupByUser(long userId, Status status);

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
	 * @param groupId
	 * @return
	 *
	 */
	public PageImpl<TtxvnUser> getListUserByGroupId(long groupId, PageRequest request);

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
	 * @param groupId
	 * @return
	 *
	 */
	public List<User> getListUserNotInGroup(long groupId);

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
	 * @param groupId
	 * @param userID
	 * @return
	 *
	 */
	public UserGroup findUserGroup(long groupId, long userID);

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
	 * @param name
	 * @return
	 *
	 */
	public List<Group> getGroupByName(String name);

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
	 * @return
	 *
	 */
	public List<Group> getAllListGroup();

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
	 * @param group
	 * @return
	 *
	 */
	public List<User> getListUserByGroup(long groupid);

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
	 * @param group
	 * @param name
	 * @param status
	 * @param description
	 * @param modifiedUserId
	 * @return
	 *
	 */
	public String updateGroup(Group group, String name, Status status,
			String description);

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
	 * @param Name
	 * @param status
	 * @param pageRequest
	 * @return
	 *
	 */
	public PageImpl<GroupItem> getAllGroupByNameAndStatus(String Name,
			Status status, PageRequest pageRequest);
	public List<Role> getAllRole ();
	
	public List<Role> getListRoleInGroupByIdGroup(Long groupId);
	
	public List<Role> getListRoleNotInGroupByIdGroup(Long groupId);
	
	public GroupRole getGroupRole(Long groupId,Long roleId);
	
	public List<User> searchUserInGroup(long groupId, String name);
	public List<User> searchUserNotInGroup(long groupId, String name);
}
