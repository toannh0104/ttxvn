/*
 * FILENAME
 *     GroupServiceImpl.java
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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.domain.GroupItem;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.GroupRole;
import com.vsii.ttxvn.keywordsearching.entity.Role;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.entity.UserGroup;
import com.vsii.ttxvn.keywordsearching.enums.Status;
import com.vsii.ttxvn.keywordsearching.service.GroupService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;


/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author trungvd
 **/
@Service("groupService")
public class GroupServiceImpl extends GenericDaoImpl implements GroupService {
	private static final String GET_LIST_GROUP = "FROM Group";
	private static final String GET_LIST_GROUP_BY_NAME = "FROM Group g WHERE g.name = :name";
	private static final String GET_LIST_GROUP_BY_STATUS = "FROM Group g WHERE  c.status = :status";
	private static final String GET_LIST_GROUP_BY_NAME_AND_STATUS = "FROM Group g WHERE g.name LIKE :name AND g.status = :status";
	private static final String GET_LIST_GROUP_BY_NAME_FOR_SEARCH = "FROM Group g WHERE g.name LIKE :name";
	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getListGroupByUser(long userId, Status status) {
		String sql = "FROM Group c WHERE createdUserId = :p0 AND status = :p1";
		return getCurrentSession().createQuery(sql).setLong("p0", userId)
				.setParameter("p1", status).list();
	}

	@Override
	public long createGroup(String name, Status status, String description,
			long createdUserId) {
		Group group = new Group(name, createdUserId, status);
		group.setDescription(description);
		create(group);
		return group.getId();
	}

	Query query = null;

	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<TtxvnUser> getListUserByGroupId(long groupId, PageRequest pageRequest) {
		int firstResult = pageRequest.getPageNumber() * pageRequest.getPageSize();
		int totalElements = 0;
		int pageSize = pageRequest.getPageSize();
		String sql = "SELECT ck.user FROM UserGroup ck WHERE ck.group.id = :p0";
		List<User> users = new ArrayList<User>();
		
		totalElements = getCurrentSession().createQuery(sql).setLong("p0", groupId).list().size();
		users = (List<User>) getCurrentSession().createQuery(sql).setLong("p0", groupId).setFirstResult(firstResult).setMaxResults(pageSize).list();
		
		List<TtxvnUser> items = new ArrayList<TtxvnUser>();
		for (User user : users) {
			TtxvnUser item = new TtxvnUser();
			item.setFirstName(user.getFirstName());
			item.setMiddleName(user.getMiddleName());
			item.setLastName(user.getLastName());
			item.setUsername(user.getUsername());
			item.setEmail(user.getEmail());
			item.setPhone(user.getPhone());
			item.setId(user.getId());
			items.add(item);
		}
		return new PageImpl<TtxvnUser>(items, pageRequest, totalElements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListUserNotInGroup(long groupId) {
		String sql = "SELECT u FROM UserGroup ck RIGHT JOIN ck.user u "
				+ "WHERE ck.group.id is Null OR ck.group.id != :p0 "
				+ "AND u.id NOT IN (SELECT ck.user.id FROM ck WHERE ck.group.id = :p1)";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId)
				.setLong("p1", groupId);
		return (List<User>) query.list();
	}

	@Override
	public UserGroup findUserGroup(long groupId, long userId) {
		String sql = "SELECT ck FROM UserGroup ck WHERE ck.group.id = :p0 AND ck.user.id= :p1";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId)
				.setLong("p1", userId);
		return (UserGroup) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getGroupByName(String name) {
		// TODO Auto-generated method stub
		List<Group> listGroups = getCurrentSession()
				.createQuery(GET_LIST_GROUP_BY_NAME).setString("name", name)
				.list();
		return listGroups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getAllListGroup() {
		return getCurrentSession().createQuery(GET_LIST_GROUP).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListUserByGroup(long groupid) {
		String sql = "SELECT ck.user FROM UserGroup ck WHERE ck.group.id = :group";
		return getCurrentSession().createQuery(sql)
				.setParameter("group", groupid).list();
	}

	@Override
	public String updateGroup(Group group, String name, Status status,
			String description) {
		if (name == null || name.isEmpty() || status == null
				|| description == null || description.isEmpty())
			return "Co loi xay ra khi luu group";
		group.setName(name);
		group.setDescription(description);
		group.setStatus(status);

		update(group);
		return Constants.EMPTY_STRING;

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<GroupItem> getAllGroupByNameAndStatus(String name,
			Status status, PageRequest pageRequest) {
		List<Group> groups = new ArrayList<Group>();
		int firstResult = pageRequest.getPageNumber()
				* pageRequest.getPageSize();
		int totalElements = 0;
		int pageSize = pageRequest.getPageSize();
		if ((null == name || name.isEmpty()) && null == status) {
			totalElements = getCurrentSession().createQuery(GET_LIST_GROUP)
					.list().size();
			groups = getCurrentSession().createQuery(GET_LIST_GROUP)
					.setFirstResult(firstResult).setMaxResults(pageSize).list();
		} else if (null == status) {
			totalElements = getCurrentSession()
					.createQuery(GET_LIST_GROUP_BY_NAME_FOR_SEARCH)
					.setString("name", "%" + name + "%").list().size();
			groups = getCurrentSession()
					.createQuery(GET_LIST_GROUP_BY_NAME_FOR_SEARCH)
					.setString("name", "%" + name + "%")
					.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (null == name) {
			totalElements = getCurrentSession()
					.createQuery(GET_LIST_GROUP_BY_STATUS)
					.setParameter("status", status).list().size();
			groups = getCurrentSession().createQuery(GET_LIST_GROUP_BY_STATUS)
					.setParameter("status", status).setFirstResult(firstResult)
					.setMaxResults(pageSize).list();
		}

		else {
			totalElements = getCurrentSession()
					.createQuery(GET_LIST_GROUP_BY_NAME_AND_STATUS)
					.setString("name", "%" + name + "%")
					.setParameter("status", status).list().size();
			groups = getCurrentSession()
					.createQuery(GET_LIST_GROUP_BY_NAME_AND_STATUS)
					.setString("name", "%" + name + "%")
					.setParameter("status", status).setFirstResult(firstResult)
					.setMaxResults(pageSize).list();
		}

		List<GroupItem> items = new ArrayList<GroupItem>();
		for (Group group : groups) {
			GroupItem item = new GroupItem();
			item.setId(group.getId());
			item.setName(group.getName());
			item.setStatus(group.getStatus());
			item.setDescription(group.getDescription());

			items.add(item);
		}
		return new PageImpl<GroupItem>(items, pageRequest, totalElements);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole() {
		String sql ="From Role";
		query=getCurrentSession().createQuery(sql);
		return (List<Role>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getListRoleInGroupByIdGroup(Long groupId) {
		String sql = "SELECT ck.role FROM GroupRole ck WHERE ck.group.id = :p0";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId);
		return (List<Role>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getListRoleNotInGroupByIdGroup(Long groupId) {
		String sql = "SELECT r FROM Role r "
				+ "WHERE r NOT IN (SELECT ck.role FROM GroupRole ck WHERE ck.group.id = :p1)";
		query = getCurrentSession().createQuery(sql)
				.setLong("p1", groupId);
		return (List<Role>) query.list();

	}

	@Override
	public GroupRole getGroupRole(Long groupId, Long roleId) {
		String sql = "SELECT ck FROM GroupRole ck WHERE ck.group.id = :p0 AND ck.role.id= :p1";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId)
				.setLong("p1", roleId);
		return (GroupRole) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUserInGroup(long groupId, String name) {
		String sql = "SELECT ck.user FROM UserGroup ck WHERE ck.group.id = :p0 AND ck.user.username LIKE :name";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId).setString("name", "%" + name + "%");
		return (List<User>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUserNotInGroup(long groupId, String name) {
		String sql = "FROM User u WHERE u.username LIKE :name AND u.id NOT IN (SELECT ck.user.id FROM UserGroup ck WHERE ck.group.id = :p0)";
		query = getCurrentSession().createQuery(sql).setLong("p0", groupId).setString("name", "%" + name + "%");
		return (List<User>) query.list();
	}
}
