/*
 * FILENAME
 *     UserServiceImpl.java
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vsii.ttxvn.keywordsearching.dao.impl.GenericDaoImpl;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.GroupRole;
import com.vsii.ttxvn.keywordsearching.entity.Permission;
import com.vsii.ttxvn.keywordsearching.entity.Role;
import com.vsii.ttxvn.keywordsearching.entity.RolePermission;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.entity.UserGroup;
import com.vsii.ttxvn.keywordsearching.entity.UserNotification;
import com.vsii.ttxvn.keywordsearching.enums.Gender;
import com.vsii.ttxvn.keywordsearching.enums.Status;
import com.vsii.ttxvn.keywordsearching.httpclient.solr.SolrOxmClient;
import com.vsii.ttxvn.keywordsearching.service.UserService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.Md5;
import com.vsii.ttxvn.keywordsearching.utils.StringPasswordReset;

/**
 * <p>
 * An implementation of UserService.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Service("userService")
public class UserServiceImpl extends GenericDaoImpl implements UserService
{
	private static final String GET_ALL_USER = "FROM User ";
	private static final String GET_USER_LIKE_EMAIL = "FROM User u WHERE email LIKE :email";
	private static final String GET_USER_LIKE_USERNAME = "FROM User u WHERE username LIKE :username";
	private static final String GET_USER_LIKE_USERNAME_EMAIL =
			"FROM User u WHERE username LIKE :username AND email LIKE :email";
	private static final String CHECK_OLD_PASSWORD =
			"FROM User u WHERE u.username = :username AND u.password = :oldPassword";

	private static final String GET_USER_LIKE_GROUP =
			"SELECT u FROM UserGroup ug INNER JOIN ug.user u INNER JOIN ug.group g WHERE g.name LIKE :groupname";
	private static final String GET_USER_LIKE_USERNAME_GROUP =
			"SELECT u FROM UserGroup ug INNER JOIN ug.user u INNER JOIN ug.group g WHERE u.username LIKE :username AND g.name LIKE :groupname";
	private static final String GET_USER_LIKE_GROUP_EMAIL =
			"SELECT u FROM UserGroup ug INNER JOIN ug.user u INNER JOIN ug.group g WHERE g.name LIKE :groupname AND u.email LIKE :email";
	private static final String GET_USER_LIKE_USERNAME_GROUP_EMAIL =
			"SELECT u FROM UserGroup ug INNER JOIN ug.user u INNER JOIN ug.group g WHERE u.username LIKE :username AND g.name LIKE :groupname AND u.email LIKE :email";

	private static final String RECOVERY_PASSWORD = "FROM User u WHERE u.email = :email";

	@Autowired
	SolrOxmClient<User> solrOxmClient;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser()
	{
		return getCurrentSession().createQuery(GET_ALL_USER).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageImpl<TtxvnUser> getAllUserByNameGroupEmail(long userId, String username, String groupByName,
			String email, PageRequest pageRequest)
	{
		List<User> users = new ArrayList<User>();

		int firstResult = pageRequest.getPageNumber() * pageRequest.getPageSize();
		int totalElements = 0;
		int pageSize = pageRequest.getPageSize();

		if (username.isEmpty() && email.isEmpty() && groupByName.isEmpty())
		{
			totalElements = getCurrentSession().createQuery(GET_ALL_USER).list().size();
			users =
					getCurrentSession().createQuery(GET_ALL_USER).setFirstResult(firstResult).setMaxResults(pageSize)
							.list();
		}

		else if (username.isEmpty() && groupByName.isEmpty() && !email.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_EMAIL).setString("email", "%" + email + "%").list()
							.size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_EMAIL).setString("email", "%" + email + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (email.isEmpty() && groupByName.isEmpty() && !username.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME).setString("username", "%" + username + "%")
							.list().size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME).setString("username", "%" + username + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (email.isEmpty() && username.isEmpty() && !groupByName.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_GROUP)
							.setString("groupname", "%" + groupByName + "%")
							.list().size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_GROUP)
							.setString("groupname", "%" + groupByName + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (!email.isEmpty() && !username.isEmpty() && groupByName.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_EMAIL)
							.setString("username", "%" + username + "%").setString("email", "%" + email + "%").list()
							.size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_EMAIL)
							.setString("username", "%" + username + "%").setString("email", "%" + email + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (email.isEmpty() && !username.isEmpty() && !groupByName.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_GROUP)
							.setString("username", "%" + username + "%")
							.setString("groupname", "%" + groupByName + "%").list()
							.size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_GROUP)
							.setString("username", "%" + username + "%")
							.setString("groupname", "%" + groupByName + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else if (!email.isEmpty() && username.isEmpty() && !groupByName.isEmpty())
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_GROUP_EMAIL)
							.setString("groupname", "%" + groupByName + "%").setString("email", "%" + email + "%")
							.list()
							.size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_GROUP_EMAIL)
							.setString("groupname", "%" + groupByName + "%").setString("email", "%" + email + "%")
							.setFirstResult(firstResult).setMaxResults(pageSize).list();
		}

		else
		{
			totalElements =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_GROUP_EMAIL)
							.setString("username", "%" + username + "%")
							.setString("groupname", "%" + groupByName + "%")
							.setString("email", "%" + email + "%").list().size();
			users =
					getCurrentSession().createQuery(GET_USER_LIKE_USERNAME_GROUP_EMAIL)
							.setString("username", "%" + username + "%")
							.setString("groupname", "%" + groupByName + "%")
							.setString("email", "%" + email + "%").setFirstResult(firstResult).setMaxResults(pageSize)
							.list();
		}

		List<TtxvnUser> user_items = new ArrayList<TtxvnUser>();

		for (User user : users)
		{

			TtxvnUser item = new TtxvnUser();
			item.setFullname(user.getLastName() + " " + user.getMiddleName() + " " + user.getFirstName());
			item.setUsername(user.getUsername());
			item.setEmail(user.getEmail());
			item.setId(user.getId());

			Set<UserGroup> groups = user.getUserGroup();
			StringBuilder groupName = new StringBuilder();
			for (UserGroup group : groups)
			{
				if (groupName.lastIndexOf(groupByName) == (groups.size() - 1))
				{
					groupName.append(group.getGroup().getName());
				}
				else
				{
					groupName.append(group.getGroup().getName()).append(", ");
				}
			}

			item.setGroup(groupName.toString());
			user_items.add(item);
		}
		return new PageImpl<TtxvnUser>(user_items, pageRequest, totalElements);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.UserService#checkOldPassword(java.lang.String)
	 */
	@Override
	public User checkOldPassword(String username, String oldPassword)
	{
		if ((null == oldPassword || oldPassword.isEmpty()) && (null == username || username.isEmpty()))
			return null;
		return (User) getCurrentSession().createQuery(CHECK_OLD_PASSWORD).setString("username", username)
				.setString("oldPassword", oldPassword).uniqueResult();
	}

	@Override
	public String updateNewPassword(User user, String newPassword)
	{
		if (null == newPassword || newPassword.isEmpty())
			return null;
		user.setPassword(newPassword);;
		update(user);
		return Constants.EMPTY_STRING;
	}

	@Override
	public User getUserByName(String name)
	{
		if (name == null || name.isEmpty())
			return null;
		String sql = "FROM User u WHERE u.username = :p1";
		return (User) getCurrentSession().createQuery(sql).setString("p1", name).uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.UserService#findByUsername(java.lang.String)
	 */
	@Override
	public TtxvnUser findByUsername(String username)
	{
		User user = getUserByName(username);

		if (user != null)
		{
			TtxvnUser ttxvnUser = new TtxvnUser();
			BeanUtils.copyProperties(user, ttxvnUser);
			return ttxvnUser;
		}

		return null;
	}

	/**
	 * {@inheritDoc} check Username and Email from database
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.LoginService#recoveryPassword(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User findByEmail(String email)
	{
		if (null == email || email.isEmpty())
		{
			return null;
		}

		return (User) getCurrentSession().createQuery(RECOVERY_PASSWORD).setString("email", email).uniqueResult();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.vsii.ttxvn.keywordsearching.service.UserService#findPermissions(java.lang.Long)
	 */
	@Override
	public String[] findPermissions(Long userId)
	{
		User user = findById(User.class, userId);

		if (user == null)
		{
			throw new IllegalArgumentException("Invalid userId");
		}

		Set<Group> groups = new HashSet<Group>();

		if (user.getUserGroup() != null)
		{
			for (UserGroup userGroup : user.getUserGroup())
			{
				groups.add(userGroup.getGroup());
			}
		}

		Set<GroupRole> groupRoles = new HashSet<GroupRole>();

		for (Group group : groups)
		{
			groupRoles.addAll(group.getGroupRoles());
		}

		Set<Role> roles = new HashSet<Role>();

		for (GroupRole groupRole : groupRoles)
		{
			roles.add(groupRole.getRole());
		}

		log.debug("-----------> user per:");
		for (Role group : roles)
			log.debug("+ " + group.getName());

		Set<RolePermission> rolePermissions = new HashSet<RolePermission>();

		for (Role role : roles)
		{
			rolePermissions.addAll(role.getRolePermissions());
		}

		Set<Permission> permissions = new HashSet<Permission>();

		for (RolePermission rolePermission : rolePermissions)
		{
			permissions.add(rolePermission.getPermission());
		}

		log.debug("-----------> user per:");
		for (Permission group : permissions)
			log.debug("+ " + group.getName());

		String[] userPermissions = new String[permissions.size()];
		int index = 0;

		for (Permission permission : permissions)
		{
			userPermissions[index++] = permission.getName();
		}

		return userPermissions;
	}

	@Override
	public User getUserByEmail(String email)
	{
		if (email == null || email.isEmpty())
			return null;
		String sql = "FROM User u WHERE  u.email = :p1";
		return (User) getCurrentSession().createQuery(sql).setString("p1", email).uniqueResult();
	}

	@Override
	public String updateUser(User user, String firstName, String middleName, String lastname, Date birthDay,
			Gender gender, String phoneNumber, String province, String district, String address, long userIdCreate,
			Status status)
	{
		user.setLastModifiedUserId(userIdCreate);
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastname);
		user.setBithday(birthDay);
		user.setGender(gender);
		user.setPhone(phoneNumber);
		user.setProvince(province);
		user.setDistrict(district);
		user.setAddress(address);
		user.setStatus(status);
		user.setLastModified(new Date());
		update(user);
		return Constants.EMPTY_STRING;
	}

	@Override
	public String createUser(String name, String password, String firstName, String middleName, String lastname,
			Date birthDay, Gender gender, String phoneNumber, String email, String province, String district,
			String address, long userIdCreate, Status status)
	{
		User user = new User(name, password, firstName, middleName, lastname, birthDay, gender, phoneNumber, email,
						province, district, address, userIdCreate, status);
		user.setLastModifiedUserId(userIdCreate);
		User savedUser = create(user);
		UserNotification notification = new UserNotification(savedUser.getId());
		notification.setHasNotification(true);
		create(notification);
		return Constants.EMPTY_STRING;
	}

	@Override
	public User getUserById(long id)
	{
		String sql = "FROM User u WHERE u.id = :p1";
		return (User) getCurrentSession().createQuery(sql).setLong("p1", id).uniqueResult();
	}

	Query query = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getListGroupByUserId(long id)
	{
		String sql = "SELECT ck.group FROM UserGroup ck WHERE ck.user.id = :p0";
		query = getCurrentSession().createQuery(sql).setLong("p0", id);
		return (List<Group>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getListGroupNotContainUserId(long id)
	{
		String sql =
				"select g from Group g where g NOT IN(  " + "select gr from Group gr inner join gr.userGroup ug "
						+ "where ug.user.id=:p0)";
		query = getCurrentSession().createQuery(sql).setLong("p0", id);
		return (List<Group>) query.list();
	}

	@Override
	public String resetPassword(User user)
	{
		String newPassword =
				new StringPasswordReset(StringPasswordReset.STR_NUMBER + StringPasswordReset.STR_LOWER_CASE).next(10);
		try
		{
			String md5NewPassword = Md5.encryptPassword(newPassword);
			if (null == md5NewPassword || md5NewPassword.isEmpty())
				return null;
			user.setPassword(md5NewPassword);
			update(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

		return newPassword;
	}
}
