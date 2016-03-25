/*
 * FILENAME
 *     RunnableJob.java
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

package com.vsii.ttxvn.keywordsearching.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>
 * The class for defining an Runnable job.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
public abstract class RunnableJob implements Job, JobObject
{
	private String uniqueKey;

	public RunnableJob(String key)
	{
		uniqueKey = key;
	}

	public String getUniqueKey()
	{
		return uniqueKey;
	}

	protected abstract void execute();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		execute();
	}
}
