/*
 * FILENAME
 *     KeywordStatusJob.java
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

import java.util.Calendar;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.SchedulerException;

import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.enums.KeywordStatus;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author dungtt
 **/
@DisallowConcurrentExecution
public class KeywordStatusJob extends RunnableJob
{

	public KeywordStatusJob()
	{
		super(KeywordStatusJob.class.getName());
	}

	@Override
	protected void execute()
	{
		KeywordService service = ServiceResolver.findService(KeywordService.class);
		List<Keyword> keywords = service.findAll(Keyword.class);
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		for (Keyword keyword : keywords)
		{
			if (keyword.getEndDate().before(now.getTime()))
			{
				keyword.setStatus(KeywordStatus.END_TRACKING);
				service.update(keyword);
			}
		}
	}

	public void createJob() throws SchedulerException
	{
		KeywordStatusJob job = new KeywordStatusJob();
		if (ScheduleService.getScheduler().getJobGroupNames().contains(KeywordStatusJob.class.getName()))
			return;
		String cronExpression = "0 0 0 * * ?";
		ScheduleService.schedule(job, cronExpression, null, null, false);
	}
}
