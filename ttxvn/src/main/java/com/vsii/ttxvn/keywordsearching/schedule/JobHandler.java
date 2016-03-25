/*
 * FILENAME
 *     JobHandler.java
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vsii.ttxvn.keywordsearching.utils.LogWriter;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * The class for declare JobHandler for a JobObject.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
public abstract class JobHandler implements Job
{
	private static final String TTXVN_KEYWORD_SEARCHING = "TTXVN_Schedule";

	protected static LogWriter LOG_WRITER;

	protected Log logger = LogFactory.getLog(getClass());

	static
	{
		LOG_WRITER = LogWriter.getInstance(TTXVN_KEYWORD_SEARCHING);
	}

	public abstract boolean canHandleJob(Class<? extends JobObject> job);

	public abstract void handleJob(Class<? extends JobObject> job, long jobId);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		String className = context.getJobDetail().getKey().getGroup();
		long id = Long.parseLong(context.getJobDetail().getKey().getName());
		
		try
		{
			handleJob((Class<? extends JobObject>) Class.forName(className), id);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
