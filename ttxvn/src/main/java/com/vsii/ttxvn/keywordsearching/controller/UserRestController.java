/*
 * FILENAME
 *     UserRestController.java
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsii.ttxvn.keywordsearching.entity.User;
import com.vsii.ttxvn.keywordsearching.enums.Gender;
import com.vsii.ttxvn.keywordsearching.enums.Status;
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
@RequestMapping("/manage/restuser")
public class UserRestController extends BaseController{
	private static final String REQUEST_PARAM_USER_ID = "userId";
	private static final String REQUEST_PARAM_NAME = "userName";
	private static final String REQUEST_PARAM_PASSWORD = "userPassword";
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
	private static final String IS_NUMBER = "^\\d+$";
	
	private static final String REQUEST_PARAM_OLD_PASSWORD = "oldPassword";
	private static final String REQUEST_PARAM_NEW_PASSWORD = "newPassword";
	private static final String REQUEST_PARAM_CONFIRM_NEW_PASSWORD = "confirmNewPassword";
	private static final String REQUEST_PARAM_ID_CHANGE_PASSWORD = "idchangepassword";
	private static final String REQUEST_PARAM_USERNAME_CHANGE_PASSWORD = "username";
	private static final int MAX_LENGTH = 250;
	private static final int MIN_LENGTH = 6;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/loadUserEdit", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> loadUser(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		long id = ServletRequestUtils.getLongParameter(request,
				REQUEST_PARAM_USER_ID, -1);
	
		User user = getRestUserService().findById(User.class, id);

		if (user != null) {
			map.put(REQUEST_PARAM_USER_ID, user.getId().toString());
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
		long userId = ServletRequestUtils.getLongParameter(request,
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

		if (StringUtils.isBlank(lastname)) {
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
		} else if (StringUtils.isNotBlank(middleName) && !middleName.matches(Constants.NON_SPECIAL_CHAR_REGEX)) {
			message = this.messageSource.getMessage(
					"usermanagement.user.middlename.validate", null,
					LocaleContextHolder.getLocale());
		} else if (StringUtils.isNotBlank(middleName) && middleName.length() > MAX_LENGTH) {
			message = this.messageSource.getMessage(
					"usermanagement.user.middlename.maxlength", null,
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
		}
		else if (StringUtils.isBlank(birthOfDate)) {
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
			User user = getRestUserService().findById(User.class, userId);

			Gender gender = null;

			if (sex.equals("MALE"))
				gender = Gender.MALE;
			else
				gender = Gender.FEMALE;
			if (message.isEmpty()) {
				getRestUserService().updateUser(user, firstName, middleName,
						lastname, df.parse(birthOfDate), gender, phoneNumber,
						province, district, address, userIdCreate,
						Status.ACTIVE);
			}
		}

		map.put("message", message);
		return map;
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

				User userCheckOldPassword = getRestUserService().checkOldPassword(
						username, md5OldPassword);
				User updatePassword = getRestUserService().findById(User.class,
						idchangepassword);

				if (userCheckOldPassword == null) {
					message = this.messageSource.getMessage(
							"user.keyword.old.password.validate.invalid", null,
							LocaleContextHolder.getLocale());
					map.put("message", message);
				} else {
					if (md5NewPassword.equals(md5ConfirmNewPassword)) {
						String changePassword = getRestUserService()
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
	
	public UserService getRestUserService() {
		return (UserService) ServiceResolver.findService(UserService.class);
	}
}
