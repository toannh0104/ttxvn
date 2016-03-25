/*
 * FILENAME
 *     UserNotification.java
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

package com.vsii.ttxvn.keywordsearching.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for purpose of tracking notification for each user.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Entity
@Table(name = "user_notification")
public class UserNotification extends BaseEntity
{
	private static final long serialVersionUID = 7032600080630950310L;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "HAS_NOTIFICATION")
	private boolean hasNotification = true;

	public UserNotification(Long userId)
	{
		if (userId == null)
			throw new IllegalArgumentException("UserId must not be null");
		this.userId = userId;
	}

	protected UserNotification()
	{
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public boolean isHasNotification()
	{
		return hasNotification;
	}

	public void setHasNotification(boolean hasNotification)
	{
		this.hasNotification = hasNotification;
	}

}
