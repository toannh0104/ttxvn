/*
 * FILENAME
 *     UserController.java
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnUser;
import com.vsii.ttxvn.keywordsearching.entity.Group;
import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.entity.UserGroup;
import com.vsii.ttxvn.keywordsearching.enums.Gender;
import com.vsii.ttxvn.keywordsearching.enums.Status;
import com.vsii.ttxvn.keywordsearching.service.GroupService;
import com.vsii.ttxvn.keywordsearching.service.UserService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.Md5;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The class for purpose of handling all request about User.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {

	private static final String REQUEST_PARAM_OLD_PASSWORD = "oldPassword";
	private static final String REQUEST_PARAM_NEW_PASSWORD = "newPassword";
	private static final String REQUEST_PARAM_CONFIRM_NEW_PASSWORD = "confirmNewPassword";
	private static final String REQUEST_PARAM_ID_CHANGE_PASSWORD = "idchangepassword";
	private static final String REQUEST_PARAM_USERNAME_CHANGE_PASSWORD = "username";
	private static final String REQUEST_PARAM_USER_ID = "userID";
	private static final String REQUEST_PARAM_DELETE_LIST_USER = "deleteListUser";
	private static final String REQUEST_PARAM_DELETE_USER_NAME = "userName";
	private static final String REQUEST_PARAM_MODIFIED_USER_ID = "modifiedUserId";
	private static final String REQUEST_PARAM_NAME = "userName";
	private static final String REQUEST_PARAM_PASSWORD = "userPassword";
	private static final String REQUEST_PARAM_CONFIRM_PASSWORD = "userConfirmPassword";
	private static final String REQUEST_PARAM_EMAIL = "userEmail";
	private static final String REQUEST_PARAM_LASTNAME = "userLastname";
	private static final String REQUEST_PARAM_MIDDNAME = "userMiddleName";
	private static final String REQUEST_PARAM_FIRSTNAME = "userFirstName";
	private static final String REQUEST_PARAM_BIRTH_OF_DATE = "userBirthOfDate";
	private static final String REQUEST_PARAM_SEX = "userSex";
	private static final String REQUEST_PARAM_PHONENUMBER = "userPhoneNumber";
	private static final String REQUEST_PARAM_ADDRESS = "userAddress";
	private static final String REQUEST_PARAM_DISTRICT = "userDistrict";
	private static final String REQUEST_PARAM_PROVINCE = "userCity";
	private static final String REQUEST_PARAM_USERSELECTEDID = "userSelectedId";
	private static final String REQUEST_PARAM_GROUPSELECTED = "groupSelected";
	private static final String REQUEST_PARAM_LEFTVALUES = "leftValues";
	private static final int MAX_LENGTH = 250;
	private static final int MIN_LENGTH = 6;
	private static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static final String VALIDATE_SPECIAL = ".*[^a-zA-Z0-9\\-\\_].*";
	private static final String IS_NUMBER = "^\\d+$";

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewlistUser() {
		ModelAndView model = new ModelAndView("user-management/userManagement");
		return model;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUserById(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		long id = Long.parseLong(userId);
		getUserService().delete(User.class, id);
		List<User> listUser = getUserService().getAllUser();
		ModelAndView model = new ModelAndView("user-management/userManagement");
		model.addObject("listUser", listUser);
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createUserPage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("index");
		return model;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult bindingResult) {
//		log.info("-- User Info --");
//		log.info(user.toString());

		if (bindingResult.hasErrors()) {

			return "createUser";
		}

		this.getUserService().create(user);

		return "redirect:/list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateUserPage(@PathVariable Long id) {
		final User user = this.getUserService().findById(User.class, id);

		return new ModelAndView("updateUser", "user", user);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateUser(@PathVariable Long id,
			@ModelAttribute("user") User user) {
//		log.info("-- User Info --");
//		log.info(user.toString());
		this.getUserService().update(user);

		return "redirect:../list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable Long id) {
		this.getUserService().delete(User.class, id);

		return "redirect:../list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listUser() {
		return "listUser";
	}

	@RequestMapping(value = "/ajax/findByPage")
	public String findByPage(Model model, HttpServletRequest request) {
		final int page = ServletRequestUtils.getIntParameter(request,
				Constants.PAGE, 1);
		final Long count = this.getUserService().countAll(User.class);
		int pageSize = 2;

		if (count > 0) {
			List<User> users = this.getUserService().findByPage(User.class,
					page, Constants.Paging.PAGE_SIZE, Constants.CREATED_DATE);

//			if (users != null)
//				log.info("--> users.size() = " + users.size());
			model.addAttribute("users", users);
			// TODO: implements pagination
			model.addAttribute("pageNav", null);
			model.addAttribute("subtractor", (page * pageSize - pageSize));
		}

		return "ajax/usersLoader";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> checkLogin(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Map<String, String> map = new HashMap<String, String>();

		String username = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_USERNAME_CHANGE_PASSWORD, Constants.EMPTY_STRING);
		String oldPassword = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_OLD_PASSWORD, Constants.EMPTY_STRING);
		String newPassword = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_NEW_PASSWORD, Constants.EMPTY_STRING);
		String confirmNewPassword = ServletRequestUtils.getStringParameter(
				request, REQUEST_PARAM_CONFIRM_NEW_PASSWORD,
				Constants.EMPTY_STRING);
		long idchangepassword = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_ID_CHANGE_PASSWORD, -1);

		String message = Constants.EMPTY_STRING;

		if (StringUtils.isBlank(oldPassword)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.old.password.blank", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);
		} else if (oldPassword.length() < MIN_LENGTH
				|| oldPassword.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"user.keyword.password.validate.required", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);
		} else if (StringUtils.isBlank(newPassword)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.new.password.blank", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);

		} else if (newPassword.length() < MIN_LENGTH
				|| newPassword.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"user.keyword.password.validate.required", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);

		} else if (StringUtils.isBlank(confirmNewPassword)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.confirm.password.blank", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);
		} else if (confirmNewPassword.length() < MIN_LENGTH
				|| confirmNewPassword.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"user.keyword.password.validate.required", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);
		} else if (newPassword.equals(oldPassword)) {
			message = this.messageSource.getMessage(
					"user.keyword.change.password.validate.duplicate", null,
					LocaleContextHolder.getLocale());
			map.put("message", message);
		} else {
			try {
				// Md5 encrypt = new Md5();
				String md5OldPassword = Md5.encryptPassword(oldPassword);
				String md5NewPassword = Md5.encryptPassword(newPassword);
				String md5ConfirmNewPassword = Md5
						.encryptPassword(confirmNewPassword);

				User userCheckOldPassword = getUserService().checkOldPassword(
						username, md5OldPassword);
				User updatePassword = getUserService().findById(User.class,
						idchangepassword);

				if (userCheckOldPassword == null) {
					message = this.messageSource.getMessage(
							"user.keyword.old.password.validate.invalid", null,
							LocaleContextHolder.getLocale());
					map.put("message", message);
				} else {
					if (md5NewPassword.equals(md5ConfirmNewPassword)) {
						String changePassword = getUserService()
								.updateNewPassword(updatePassword,
										md5NewPassword);
						if (changePassword == null) {
							message = this.messageSource.getMessage(
									"user.keyword.validate.required", null,
									LocaleContextHolder.getLocale());
							map.put("message", message);
						} else {
							map.put("message", message);
						}
					} else {
						message = this.messageSource.getMessage(
								"user.keyword.new.password.validate.duplicate",
								null, LocaleContextHolder.getLocale());
						map.put("message", message);
					}

				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return map;
	}

	@RequestMapping(value = "/findUserByPage")
	@ResponseBody
	public TtxvnPageWrapper<TtxvnUser> findURLByPage(
			HttpServletRequest request, HttpServletResponse response) {
		int page = ServletRequestUtils.getIntParameter(request, Constants.PAGE,
				1);
		long userId = ServletRequestUtils.getLongParameter(request, "user", 1);
		String username = ServletRequestUtils.getStringParameter(request,
				"username", Constants.EMPTY_STRING);
		String groupByName = ServletRequestUtils.getStringParameter(request,
				"groupByName", Constants.EMPTY_STRING);
		String email = ServletRequestUtils.getStringParameter(request, "email",
				Constants.EMPTY_STRING);

		String orderBy = "username";
		Sort sort = new Sort(Sort.Direction.ASC, orderBy);
		PageRequest pageRequest = new PageRequest(page - 1, 10, sort);

//		log.info("--------> userId: " + userId);
//		log.info("--------> selected page: " + page);
//		log.info("--------> username: " + username);
//		log.info("--------> groupByName: " + groupByName);
//		log.info("--------> email: " + email);

		TtxvnPageWrapper<TtxvnUser> pageWrapper = new TtxvnPageWrapper<TtxvnUser>(
				getUserService().getAllUserByNameGroupEmail(userId, username,
						groupByName, email, pageRequest), "/findUserByPage", 5);

//		log.info("---> getTotalPages = " + pageWrapper.getTotalPages());
//		log.info("---> getSelectedPage() = " + pageWrapper.getSelectedPage());

		return pageWrapper;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> createUser(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			ParseException {
		Map<String, String> map = new HashMap<String, String>();

		long userIdCreate = TtxvnUserContext.getCurrentUser().getId();
		long userID = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USER_ID, -1);
		String name = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_NAME, Constants.EMPTY_STRING);
		String password = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_PASSWORD, Constants.EMPTY_STRING);
		String confirmPassword = ServletRequestUtils
				.getStringParameter(request, REQUEST_PARAM_CONFIRM_PASSWORD,
						Constants.EMPTY_STRING);
		String email = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_EMAIL, Constants.EMPTY_STRING);
		String lastname = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_LASTNAME, Constants.EMPTY_STRING);
		String middleName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_MIDDNAME, Constants.EMPTY_STRING);
		String firstName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_FIRSTNAME, Constants.EMPTY_STRING);
		String birthOfDate = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_BIRTH_OF_DATE, Constants.EMPTY_STRING);
		String sex = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_SEX, Constants.EMPTY_STRING);
		String phoneNumber = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_PHONENUMBER, Constants.EMPTY_STRING);
		String address = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_ADDRESS, Constants.EMPTY_STRING);
		String district = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_DISTRICT, Constants.EMPTY_STRING);
		String province = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_PROVINCE, Constants.EMPTY_STRING);

		String message = Constants.EMPTY_STRING;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Gender gender = null;

		if (sex.equals("male"))
			gender = Gender.MALE;
		else
			gender = Gender.FEMALE;
		
		if (StringUtils.isBlank(name)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.username.blank", null,
					LocaleContextHolder.getLocale());
		} else if (name.matches(VALIDATE_SPECIAL) == true) {
			message = this.messageSource.getMessage(
					"usermanagement.user.addusername.validate", null,
					LocaleContextHolder.getLocale());
		} else if (name.length() > MAX_LENGTH || name.length() < MIN_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.name.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (getUserService().getUserByName(name) != null) {
			message = this.messageSource.getMessage(
					"usermanagement.user.addusername.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(password)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.password.blank", null,
					LocaleContextHolder.getLocale());
		} else if (password.length() < MIN_LENGTH
				|| password.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.password.validate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(confirmPassword)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.confirmpassword.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!password.equals(confirmPassword)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.confirmpassword.validate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(email)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.email.blank", null,
					LocaleContextHolder.getLocale());
		} else if (email.matches(EMAIL_REGEX) == false)
			message = this.messageSource.getMessage(
					"usermanagement.user.email.validate", null,
					LocaleContextHolder.getLocale());
		else if (email.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.email.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (getUserService().getUserByEmail(email) != null) {
			message = this.messageSource.getMessage(
					"usermanagement.user.addemail.validate.duplicate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(firstName)) {
            message = this.messageSource.getMessage(
                "usermanagement.user.lastname.blank", null,
                LocaleContextHolder.getLocale());
        } else if (!firstName.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.firstname.validate", null,
                    LocaleContextHolder.getLocale());
        } else if (firstName.length() > MAX_LENGTH) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.lastname.maxlength", null,
                    LocaleContextHolder.getLocale());
        } else if (StringUtils.isNotBlank(middleName) && !middleName.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
            message = this.messageSource.getMessage(
                "usermanagement.user.middlename.validate", null,
                LocaleContextHolder.getLocale());
        } else if (StringUtils.isNotBlank(middleName) && middleName.length() > MAX_LENGTH) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.middlename.maxlength", null,
                    LocaleContextHolder.getLocale());
        } else if (StringUtils.isBlank(lastname)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.firstname.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!lastname.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.lastname.validate", null,
					LocaleContextHolder.getLocale());
		} else if (lastname.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.firstname.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(birthOfDate)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.birthofdate.blank", null,
					LocaleContextHolder.getLocale());
		} else if (df.parse(birthOfDate).compareTo(new Date()) == 1) {
			message = this.messageSource.getMessage(
					"usermanagement.user.date.validate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(phoneNumber)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.blank", null,
					LocaleContextHolder.getLocale());
		} else if (phoneNumber.matches(IS_NUMBER) == false)
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.validate", null,
					LocaleContextHolder.getLocale());
		else if (phoneNumber.length() > MAX_LENGTH)
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.maxlength", null,
					LocaleContextHolder.getLocale());
		else if (StringUtils.isBlank(address)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.address.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!address.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.validate", null,
					LocaleContextHolder.getLocale());
		} else if (address.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(district)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!district.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.validate", null,
					LocaleContextHolder.getLocale());
		} else if (district.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(province)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!province.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.validate", null,
					LocaleContextHolder.getLocale());
		} else if (province.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (message.isEmpty()) {
			try {
                String passwordMD5 = Md5.encryptPassword(password);
                
                getUserService().createUser(name, passwordMD5, firstName, middleName, lastname, df.parse(birthOfDate),
                    gender, phoneNumber, email, province, district, address, userIdCreate, Status.ACTIVE);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}

		map.put("message", message);
		return map;
	}

	@RequestMapping(value = "/loadUserEdit", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		long id = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_MODIFIED_USER_ID, -1);
		
		User user = getUserService().findById(User.class, id);

		if (user != null) {
			map.put(REQUEST_PARAM_MODIFIED_USER_ID, user.getId().toString());
			map.put(REQUEST_PARAM_NAME, user.getUsername());
			map.put(REQUEST_PARAM_PASSWORD, user.getPassword());
			map.put(REQUEST_PARAM_EMAIL, user.getEmail());
			map.put(REQUEST_PARAM_FIRSTNAME, user.getFirstName());
			map.put(REQUEST_PARAM_MIDDNAME, user.getMiddleName());
			map.put(REQUEST_PARAM_LASTNAME, user.getLastName());
			map.put(REQUEST_PARAM_BIRTH_OF_DATE, new SimpleDateFormat(
					"dd-MM-yyyy").format(user.getBithday()));
			map.put(REQUEST_PARAM_SEX, user.getGender().toString());
			map.put(REQUEST_PARAM_PHONENUMBER, user.getPhone());
			map.put(REQUEST_PARAM_ADDRESS, user.getAddress());
			map.put(REQUEST_PARAM_DISTRICT, user.getDistrict());
			map.put(REQUEST_PARAM_PROVINCE, user.getProvince());

		}
		return map;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateUser(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			ParseException {
		Map<String, String> map = new HashMap<String, String>();
		long userIdCreate = TtxvnUserContext.getCurrentUser().getId();
		long userID = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USER_ID, -1);
		String lastname = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_LASTNAME, Constants.EMPTY_STRING);
		String middleName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_MIDDNAME, Constants.EMPTY_STRING);
		String firstName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_FIRSTNAME, Constants.EMPTY_STRING);
		String birthOfDate = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_BIRTH_OF_DATE, Constants.EMPTY_STRING);
		String sex = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_SEX, Constants.EMPTY_STRING);
		String phoneNumber = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_PHONENUMBER, Constants.EMPTY_STRING);
		String address = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_ADDRESS, Constants.EMPTY_STRING);
		String district = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_DISTRICT, Constants.EMPTY_STRING);
		String province = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_PROVINCE, Constants.EMPTY_STRING);
		String message = Constants.EMPTY_STRING;

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		if (StringUtils.isBlank(firstName)) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.lastname.blank", null,
                    LocaleContextHolder.getLocale());
        } else if (!firstName.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.firstname.validate", null,
                    LocaleContextHolder.getLocale());
        } else if (firstName.length() > MAX_LENGTH) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.lastname.maxlength", null,
                    LocaleContextHolder.getLocale());
        } else if (StringUtils.isNotBlank(middleName) && !middleName.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
            message = this.messageSource.getMessage(
                "usermanagement.user.middlename.validate", null,
                LocaleContextHolder.getLocale());
        } else if (StringUtils.isNotBlank(middleName) && (middleName.length() > MAX_LENGTH)) {
            message = this.messageSource.getMessage(
                    "usermanagement.user.middlename.maxlength", null,
                    LocaleContextHolder.getLocale());
        } else if (StringUtils.isBlank(lastname)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.firstname.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!lastname.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.lastname.validate", null,
					LocaleContextHolder.getLocale());
		} else if (lastname.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.firstname.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(birthOfDate)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.birthofdate.blank", null,
					LocaleContextHolder.getLocale());
		} else if (df.parse(birthOfDate).compareTo(new Date()) == 1) {
			message = this.messageSource.getMessage(
					"usermanagement.user.date.validate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(phoneNumber)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.blank", null,
					LocaleContextHolder.getLocale());
		} else if (phoneNumber.matches(IS_NUMBER) == false)
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.validate", null,
					LocaleContextHolder.getLocale());
		else if (phoneNumber.length() > MAX_LENGTH)
			message = this.messageSource.getMessage(
					"usermanagement.user.phonenumber.maxlength", null,
					LocaleContextHolder.getLocale());
		else if (StringUtils.isBlank(address)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.address.blank", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(district)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!district.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.validate", null,
					LocaleContextHolder.getLocale());
		} else if (district.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.district.maxlength", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isBlank(province)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.blank", null,
					LocaleContextHolder.getLocale());
		} else if (!province.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.validate", null,
					LocaleContextHolder.getLocale());
		} else if (province.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.province.maxlength", null,
					LocaleContextHolder.getLocale());
		} else {
			User user = getUserService().findById(User.class, userID);

			Gender gender = null;

			if (sex.equals("MALE"))
				gender = Gender.MALE;
			else
				gender = Gender.FEMALE;
			if (message.isEmpty()) {
				getUserService().updateUser(user, firstName, middleName,
						lastname, df.parse(birthOfDate), gender, phoneNumber,
						province, district, address, userIdCreate,
						Status.ACTIVE);
			}
		}

		map.put("message", message);
		return map;
	}

	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public Map<String, String> deleteUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		long userIdCreate = TtxvnUserContext.getCurrentUser().getId();

		String userName = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_DELETE_USER_NAME, Constants.EMPTY_STRING);
		User user = getUserService().getUserByName(userName);
		String msg = Constants.EMPTY_STRING;
		if (user == null)
			msg = this.messageSource.getMessage(
					"usermanagement.user.delete.validate", null,
					LocaleContextHolder.getLocale());
		else if (user.getId() == userIdCreate) {
			msg = this.messageSource.getMessage(
					"usermanagement.user.deleteIdLogin.validate", null,
					LocaleContextHolder.getLocale());
		} else
			getUserService().delete(User.class, user.getId());
		map.put("message", msg);
		return map;
	}

	@RequestMapping(value = "/deleteListUser")
	@ResponseBody
	public Map<String, String> deleteListUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		String deleteListUser = ServletRequestUtils.getStringParameter(request,
				REQUEST_PARAM_DELETE_LIST_USER, Constants.EMPTY_STRING);
		long userIdCreate = TtxvnUserContext.getCurrentUser().getId();

		String[] listUser = deleteListUser.split(",");
		User user = new User();
		for (int i = 0; i < listUser.length; i++) {
			user = getUserService().getUserByName(listUser[i]);
			String msg = Constants.EMPTY_STRING;
			if (user.getId() == userIdCreate) {
				msg = this.messageSource.getMessage(
						"usermanagement.user.deleteIdLogin.validate", null,
						LocaleContextHolder.getLocale());
			} else
				getUserService().delete(User.class, user.getId());
			map.put("message", msg);
		}

		return map;
	}

	@RequestMapping(value = "/loadUserSelected", method = RequestMethod.GET)
	@ResponseBody
	public List<TtxvnUser> loadUserSelected(HttpServletRequest request,
			HttpServletResponse response) {
		long userSelectedId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USERSELECTEDID, -1);
		User user = getUserService().getUserById(userSelectedId);
		List<TtxvnUser> items = new ArrayList<TtxvnUser>();
		TtxvnUser item = new TtxvnUser();
		item.setFirstName(user.getFirstName());
		item.setMiddleName(user.getMiddleName());
		item.setLastName(user.getLastName());
		// item.setUserName(user.getUsername());
		item.setUsername(user.getUsername());
		item.setGender(this.messageSource.getMessage(user.getGender()
				.getMessageCode(), null, LocaleContextHolder.getLocale()));
		item.setEmail(user.getEmail());
		item.setPhone(user.getPhone());
		item.setId(user.getId());
		items.add(item);
		return items;
	}

	@RequestMapping(value = "/loadGroupIn", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findGroupByUser(HttpServletRequest request,
			HttpServletResponse response) {
		long userSelectedId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USERSELECTEDID, -1);

		List<Group> groups = getUserService().getListGroupByUserId(
				userSelectedId);
		Map<String, String> values = new HashMap<String, String>();
		for (Group group : groups) {
			values.put(group.getId().toString(), group.getName());
		}
		return values;
	}

	@RequestMapping(value = "/loadGroupOut", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadGroupOut(HttpServletRequest request,
			HttpServletResponse response) {
		long userSelectedId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USERSELECTEDID, -1);

		List<Group> groups = getUserService().getListGroupNotContainUserId(
				userSelectedId);
		Map<String, String> values = new HashMap<String, String>();
		for (Group group : groups) {
			values.put(group.getId().toString(), group.getName());
		}
		return values;
	}

	@RequestMapping(value = "/addGroupToUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addUserToGroup(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		String[] parameter = request
				.getParameterValues(REQUEST_PARAM_GROUPSELECTED);
		long userSelectedId = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USERSELECTEDID, -1);

		long userId = TtxvnUserContext.getCurrentUser().getId();
		String[] parameterLeft = request
				.getParameterValues(REQUEST_PARAM_LEFTVALUES);

		if (parameter != null) {
			List<String> listParameter = Arrays.asList(parameter);
			for (String e : listParameter) {
				long groupSelect = Long.parseLong(e);
				User user = getUserService().findById(User.class,
						userSelectedId);
				Group group = getUserService().findById(Group.class,
						groupSelect);
				UserGroup userGroup = getGroupService().findUserGroup(
						groupSelect, userSelectedId);
				if (userGroup == null) {
					if (user != null && group != null) {
						UserGroup userGroup1 = new UserGroup(user, group,
								userId);
						getGroupService().create(userGroup1);
					}
				}
			}
		}
		if (parameterLeft != null) {
			List<String> listParameterLeft = Arrays.asList(parameterLeft);
			for (String e : listParameterLeft) {
				long groupSelect = Long.parseLong(e);
				User user = getUserService().findById(User.class,
						userSelectedId);
				Group group = getUserService().findById(Group.class,
						groupSelect);
				UserGroup userGroup = getGroupService().findUserGroup(
						groupSelect, userSelectedId);
				if (userGroup != null) {
					if (user != null && group != null) {
						getGroupService().delete(userGroup);
					}
				}
			}
		}

		return map;
	}

	
	@RequestMapping(value = "/permissions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String[] checkLogin(@PathVariable("id") Long id) {
	    log.debug("-----------> id: " + id);
	    return getUserService().findPermissions(id);
	}
	
	/**
	 * <p>
	 * Getter for userService.
	 * </p>
	 * 
	 * @return the userService
	 */
	public UserService getUserService() {
		return (UserService) ServiceResolver.findService(UserService.class);
	}

	private GroupService getGroupService() {
		return ServiceResolver.findService(GroupService.class);
	}

}
