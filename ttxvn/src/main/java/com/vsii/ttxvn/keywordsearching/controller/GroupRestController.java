/*
 * FILENAME
 *     GroupRestController.java
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.domain.GroupItem;
import com.vsii.ttxvn.keywordsearching.domain.RoleItem;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.GroupRole;
import com.vsii.ttxvn.keywordsearching.entity.Role;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.entity.UserGroup;
import com.vsii.ttxvn.keywordsearching.enums.Status;
import com.vsii.ttxvn.keywordsearching.enums.TtxvnRole;
import com.vsii.ttxvn.keywordsearching.service.GroupService;
import com.vsii.ttxvn.keywordsearching.service.UserService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * The controller for Group requests.
 * 
 * @version 1.0
 * @author trungvd
 **/

@Controller
@RequestMapping("/manage/group")
public class GroupRestController extends BaseController {
	private static final String REQUEST_PARAM_DELETEONEUSERID = "deleteOneuserId";
	private static final String REQUEST_PARAM_USERSELECTED = "userSelected";
	private static final String REQUEST_PARAM_USER_DELETE_SELECTED="userdeleteSelected";
	private static final String REQUEST_PARAM_DELETEDUSER = "deletedUser";
	private static final String REQUEST_PARAM_GROUPID = "groupId";
	private static final String REQUEST_PARAM_ROLESELECTED = "roleSelected";
	private static final String REQUEST_PARAM_ADDROLESELECTED = "addRoleSelected";
	private static final String REQUEST_PARAM_LEFTVALUES = "leftValues";
	private static final String REQUEST_PARAM_SEARCHNAME = "searchName";
	private static final String PARAM_GROUP_NAME = "name";
	private static final String PARAM_GROUP_STATUS = "status";
	private static final String PARAM_GROUP_DESCRIPTION = "description";
	private static final String PARAM_GROUP_GROUPID = "groupId";
	private static final int MAX_LENGTH = 250;
	private static final int MAX_DESCRIPTION_LENGTH = 1000;
	private static final String REQUEST_PARAM_DELETEGROUP = "groupIds";
	@Autowired
	private MessageSource messageSource;
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewHompage(HttpServletRequest request) {
		return new ModelAndView("groups/group");
	}
	/**
	 * Getting all users in a group
	 *
	 * @since TODO module version when it was introduced
	 * @param request
	 * @param response
	 * @return
	 **/
	@RequestMapping(value = "/loadUserIn", method = RequestMethod.GET)
	@ResponseBody
	public TtxvnPageWrapper<TtxvnUser> findUserByGroup(
			HttpServletRequest request, HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		int page = ServletRequestUtils.getIntParameter(request, Constants.PAGE,
				1);
		String orderBy = "createDate";
		Sort sort = new Sort(Sort.Direction.ASC, orderBy);
		PageRequest pageRequest = new PageRequest(page - 1, 5, sort);
		TtxvnPageWrapper<TtxvnUser> pageWrapper = new TtxvnPageWrapper<TtxvnUser>(
				getGroupService().getListUserByGroupId(groupId, pageRequest),
				"/findURLByPage", 5);
		return pageWrapper;
	}
	@RequestMapping(value = "/searchNameInGroup", method = RequestMethod.GET)
	@ResponseBody
	public List<TtxvnUser> searchNameInGroup(HttpServletRequest request,
			HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		String searchName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_SEARCHNAME, Constants.EMPTY_STRING);
		List<User> users = getGroupService().searchUserInGroup(groupId,
				searchName);
		List<TtxvnUser> items = new ArrayList<TtxvnUser>();
		for (User user : users) {
			TtxvnUser item = new TtxvnUser();
			item.setUsername(user.getUsername());
			item.setId(user.getId());
			items.add(item);
		}
		return items;
	}
	@RequestMapping(value = "/searchNameNotInGroup", method = RequestMethod.GET)
	@ResponseBody
	public List<TtxvnUser> searchNameNotInGroup(HttpServletRequest request,
			HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		String searchName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_SEARCHNAME, Constants.EMPTY_STRING);
		List<User> users = getGroupService().searchUserNotInGroup(groupId,
				searchName);
		List<TtxvnUser> items = new ArrayList<TtxvnUser>();
		for (User user : users) {
			TtxvnUser item = new TtxvnUser();
			item.setUsername(user.getUsername());
			item.setId(user.getId());
			items.add(item);
		}
		return items;
	}
	/**
	 * Deleting single user with button delete in each row, not concern with
	 * check box
	 *
	 * @since TODO module version when it was introduced
	 * @param request
	 * @param response
	 * @return
	 **/
	@RequestMapping(value = "/deleteOneUserGroup", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> deleteUserGroup(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		long deleteUserId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_DELETEONEUSERID, -1);

		UserGroup userGroup = getGroupService().findUserGroup(groupId,
				deleteUserId);
		if (userGroup != null) {
			getGroupService().delete(userGroup);
		}
		return map;
	}
	/**
	 * Getting all users not involved in group
	 *
	 * @since TODO module version when it was introduced
	 * @param request
	 * @param response
	 * @return
	 **/
	@RequestMapping(value = "/loadUserNot", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findUserNotByGroup(HttpServletRequest request,
			HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		List<User> users = getGroupService().getListUserNotInGroup(groupId);
		Map<String, String> values = new HashMap<String, String>();
		for (User user : users) {
			values.put(user.getId().toString(), user.getUsername());
		}
		return values;
	}
	/**
	 * Adding group
	 *
	 * @since TODO module version when it was introduced
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 **/
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> createGroup(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		long groupId = 0;
		Map<String, String> map = new HashMap<String, String>();
		// long createdUserId = ServletRequestUtils.getLongParameter(request,
		// "createdUserId", -1);
		// long createdUserId = 1;
		long createdUserId = TtxvnUserContext.getCurrentUser().getId();
		Status status = null;
		String name = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_NAME, Constants.EMPTY_STRING);
		String compare = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_STATUS, Constants.EMPTY_STRING);
		String description = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_DESCRIPTION, Constants.EMPTY_STRING);
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_ROLESELECTED);
		if (compare.equals("active"))
			status = Status.ACTIVE;
		else
			status = Status.INACTIVE;
		String message = Constants.EMPTY_STRING;
		if (StringUtils.isBlank(name) || StringUtils.isBlank(description))
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.required", null,
					LocaleContextHolder.getLocale());
		else if (getGroupService().getGroupByName(name).isEmpty() == false)
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		else if (name.length() > MAX_LENGTH
				|| !name.matches(Constants.NON_SPECIAL_CHAR_REGEX))
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.invalid", null,
					LocaleContextHolder.getLocale());
		else if (description.length() > MAX_DESCRIPTION_LENGTH)
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.invalid.decription", null,
					LocaleContextHolder.getLocale());
		if (message.isEmpty()) {
			groupId = getGroupService().createGroup(name, status, description,
					createdUserId);
		}
		if (parameter != null) {
			List<String> listParameter = Arrays.asList(parameter);

			for (String e : listParameter) {
				long roleSelect = Long.parseLong(e);

				Role role = getGroupService().findById(Role.class, roleSelect);
				Group group = getGroupService().findById(Group.class, groupId);
				GroupRole groupRole = getGroupService().getGroupRole(groupId,
						roleSelect);
				if (groupRole != null)
					continue;
				else {
					if (role != null && group != null) {
						GroupRole roleGroup = new GroupRole(group, role,
								createdUserId);
						getGroupService().create(roleGroup);
					}
				}
			}
		}
		map.put("message", message);
		return map;
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
	 *
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	@Transactional
	public Map<String, String> deleteGroup(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		long groupId = ServletRequestUtils.getLongParameter(request,
				PARAM_GROUP_GROUPID, -1);
		Group group = getGroupService().findById(Group.class, groupId);
		String msg = Constants.EMPTY_STRING;
		if (!getGroupService().getListUserByGroup(groupId).isEmpty())
			msg = this.messageSource.getMessage(
					"homepage.group.delete.validate.error", null,
					LocaleContextHolder.getLocale());
		else {
			getGroupService().delete(Group.class, groupId);
		}
		map.put("message", msg);
		return map;
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
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteAll(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_DELETEGROUP);
		String msg = Constants.EMPTY_STRING;
		List<String> listParameter = Arrays.asList(parameter);
		for (String temp : listParameter) {
			long groupId = Long.parseLong(temp);
			Group group = getGroupService().findById(Group.class, groupId);
			if (!getGroupService().getListUserByGroup(groupId).isEmpty()) {
				msg = this.messageSource.getMessage(
						"homepage.group.delete.validate.error", null,
						LocaleContextHolder.getLocale());
				msg += " " + group.getName();
				break;
			} else {
				getGroupService().delete(Group.class, groupId);

			}
		}
		map.put("message", msg);
		return map;
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
	@RequestMapping(value = "/group-details")
	@ResponseBody
	public GroupItem loadModifiedGroup(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		GroupItem item = new GroupItem();
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		Group group = getGroupService().findById(Group.class, groupId);
		item.setId(group.getId());
		item.setName(group.getName());
		item.setDescription(group.getDescription());
		item.setStatus(group.getStatus());
		return item;
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
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String, String> updateGroup(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		long groupId = ServletRequestUtils.getLongParameter(request,
				PARAM_GROUP_GROUPID, -1);
		Status status = null;
		String name = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_NAME, Constants.EMPTY_STRING);
		String compare = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_STATUS, Constants.EMPTY_STRING);
		String description = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_DESCRIPTION, Constants.EMPTY_STRING);
		if (compare.equals("active"))
			status = Status.ACTIVE;
		else
			status = Status.INACTIVE;
		Group group = getGroupService().findById(Group.class, groupId);
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_ADDROLESELECTED);
		String[] parameterLeft = request
				.getParameterValues(REQUEST_PARAM_LEFTVALUES);
		// long createdUserId = ServletRequestUtils.getLongParameter(request,
		// REQUEST_PARAM_USER, -1);
		long createdUserId = TtxvnUserContext.getCurrentUser().getId();
		String message = Constants.EMPTY_STRING;
		if (name.isEmpty() || description.isEmpty())
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.required", null,
					LocaleContextHolder.getLocale());
		else if ((group == null || !name.equals(group.getName()))
				&& getGroupService().getGroupByName(name).isEmpty() == false)
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		else if (name.length() > MAX_LENGTH
				|| !name.matches(Constants.NON_SPECIAL_CHAR_REGEX))
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.invalid", null,
					LocaleContextHolder.getLocale());
		else if (description.length() > MAX_DESCRIPTION_LENGTH)
			message = this.messageSource.getMessage(
					"homepage.group.add.validate.invalid.decription", null,
					LocaleContextHolder.getLocale());
		if (message.isEmpty()) {
			getGroupService().updateGroup(group, name, status, description);
		}
		if (parameter != null) {
			List<String> listParameter = Arrays.asList(parameter);
			for (String e : listParameter) {
				long roleSelect = Long.parseLong(e);
				Role role = getGroupService().findById(Role.class, roleSelect);
				// Group group = getGroupService().findById(Group.class,
				// groupId);
				GroupRole groupRole = getGroupService().getGroupRole(groupId,
						roleSelect);
				if (groupRole == null) {
					if (role != null && group != null) {
						GroupRole roleGroup = new GroupRole(group, role,
								createdUserId);
						getGroupService().create(roleGroup);
					}
				}
			}
		}
		if (parameterLeft != null) {
			List<String> listParameterLeft = Arrays.asList(parameterLeft);
			for (String e : listParameterLeft) {
				long roleSelect = Long.parseLong(e);
				Role role = getGroupService().findById(Role.class, roleSelect);
				GroupRole groupRole = getGroupService().getGroupRole(groupId,
						roleSelect);
				if (groupRole != null) {
					if (role != null && group != null) {
						getGroupService().delete(groupRole);
					}
				}
			}
		}
		map.put("message", message);
		return map;
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
	 *
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public TtxvnPageWrapper<GroupItem> findURLByPage(
			HttpServletRequest request, HttpServletResponse response) {
		Status status = null;
		int page = ServletRequestUtils.getIntParameter(request, Constants.PAGE,
				1);
		String name = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_NAME, Constants.EMPTY_STRING);
		String compare = ServletRequestUtils.getStringParameter(request,
				PARAM_GROUP_STATUS, Constants.EMPTY_STRING);
		if (compare.equals("1"))
			status = Status.ACTIVE;
		else if (compare.equals("2"))
			status = Status.INACTIVE;
		String orderBy = "createDate";
		Sort sort = new Sort(Sort.Direction.ASC, orderBy);
		PageRequest pageRequest = new PageRequest(page - 1, 10, sort);
		TtxvnPageWrapper<GroupItem> pageWrapper = new TtxvnPageWrapper<GroupItem>(
				getGroupService().getAllGroupByNameAndStatus(name, status,
						pageRequest), "/findGroupByNameAndStatus", 5);
		return pageWrapper;
	}
	@RequestMapping(value = "/addUserToGroup", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> addUserToGroup(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_USERSELECTED);
		String[] parameterdelete=request.getParameterValues(REQUEST_PARAM_USER_DELETE_SELECTED);
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		long createdUserId = TtxvnUserContext.getCurrentUser().getId();
		if (parameter != null) {
			List<String> listParameter = Arrays.asList(parameter);
			for (String e : listParameter) {
				long userSelect = Long.parseLong(e);

				User user = getGroupService().findById(User.class, userSelect);
				Group group = getGroupService().findById(Group.class, groupId);
				UserGroup userGroup = getGroupService().findUserGroup(groupId,
						userSelect);
				if (userGroup == null)
				{
					if (user != null && group != null) {
						UserGroup userGroup1 = new UserGroup(user, group,
								createdUserId);
						getGroupService().create(userGroup1);
					}
				}
			}
		}
		if(parameterdelete!=null){
			List<String> listParameterDelete = Arrays.asList(parameterdelete);
			for (String e : listParameterDelete) {
				long userSelect = Long.parseLong(e);
				User user = getGroupService().findById(User.class, userSelect);
				Group group = getGroupService().findById(Group.class, groupId);
				UserGroup userGroup = getGroupService().findUserGroup(groupId,
						userSelect);
				if (userGroup != null)
				{
					if (user != null && group != null) {
						getGroupService().delete(userGroup);
					}
				}
			}
		}
		return map;
	}
	@RequestMapping(value = "/deleteMultipleUserGroup", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteMultipleUserGroups(
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_DELETEDUSER);
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);

		if (parameter != null) {
			List<String> listParameter = Arrays.asList(parameter);
			for (String e : listParameter) {
				long deleteUser = Long.parseLong(e);
				UserGroup userGroup = getGroupService().findUserGroup(groupId,
						deleteUser);
				if (userGroup != null) {
					getGroupService().delete(userGroup);
				}
			}
		}
		return map;
	}
	@RequestMapping(value = "/loadAllRole", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadAllRole(HttpServletRequest request,
			HttpServletResponse response) {
		List<Role> roles = getGroupService().getAllRole();
		Map<String, String> values = new HashMap<String, String>();
		for (Role role : roles) {
			String temp = (this.messageSource.getMessage(
					TtxvnRole.parse(role.getId()).getMessageCode(), null,
					LocaleContextHolder.getLocale()));
			values.put(role.getId().toString(), temp);
		}
		return values;
	}
	@RequestMapping(value = "/loadRoleNotIn", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleItem> loadRoleNot(HttpServletRequest request,
			HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		List<Role> roles = getGroupService().getListRoleNotInGroupByIdGroup(
				groupId);
		List<RoleItem> items = new ArrayList<RoleItem>();
		for (Role role : roles) {
			RoleItem item = new RoleItem();
			item.setName(this.messageSource.getMessage(
					TtxvnRole.parse(role.getId()).getMessageCode(), null,
					LocaleContextHolder.getLocale()));
			item.setId(role.getId());
			items.add(item);
		}
		return items;
	}
	@RequestMapping(value = "/loadRoleIn", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleItem> loadRoleIn(HttpServletRequest request,
			HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_GROUPID, -1);
		List<Role> roles = getGroupService().getListRoleInGroupByIdGroup(
				groupId);
		List<RoleItem> items = new ArrayList<RoleItem>();
		for (Role role : roles) {
			RoleItem item = new RoleItem();
			item.setName(this.messageSource.getMessage(
					TtxvnRole.parse(role.getId()).getMessageCode(), null,
					LocaleContextHolder.getLocale()));
			item.setId(role.getId());
			items.add(item);
		}
		return items;
	}
	
	@RequestMapping(value = "/loadUserAllUserInGroup", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadUserAllUserInGroup(
			HttpServletRequest request, HttpServletResponse response) {
		long groupId = ServletRequestUtils.getLongParameter(request,
		REQUEST_PARAM_GROUPID, -1);
		List<User> users = getGroupService().getListUserByGroup(groupId);
		Map<String, String> values = new HashMap<String, String>();
		for (User user : users) {
			values.put(user.getId().toString(), user.getUsername());
		}
		return values;
		
	}
	
	private GroupService getGroupService() {
		return ServiceResolver.findService(GroupService.class);
	}
	public UserService getUserService() {
		return (UserService) ServiceResolver.findService(UserService.class);
	}
}
