/*
 * FILENAME
 *     InitialFetchFrequenceScheduleJob.java
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

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vsii.ttxvn.keywordsearching.entity.FetchFrequency;
import com.vsii.ttxvn.keywordsearching.entity.JobTracker;
import com.vsii.ttxvn.keywordsearching.service.FetchFrequencyService;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * The class for creating first job to schedule FetchFrequency jobs.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Transactional
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class InitialFetchFrequenceScheduleJob extends RunnableJob
{
	final Logger logger = LoggerFactory.getLogger(InitialFetchFrequenceScheduleJob.class);

	public InitialFetchFrequenceScheduleJob()
	{
		super(InitialFetchFrequenceScheduleJob.class.getName());
	}

	@Override
	protected void execute()
	{
		FetchFrequencyService service = ServiceResolver.findService(FetchFrequencyService.class);
		JobTracker tracker = new JobTracker(FetchFrequency.class.getName());
		tracker.setDone(false);
		service.create(tracker);
		List<FetchFrequency> frequencies = service.findAll(FetchFrequency.class);
		
		for (FetchFrequency frequency : frequencies)
		{
			int time = frequency.getFrequency().intValue();
			ScheduleService.schedule(frequency, time);
		}
	}

	public void createJob() throws SchedulerException
	{
		InitialFetchFrequenceScheduleJob job = new InitialFetchFrequenceScheduleJob();
		if (ScheduleService.getScheduler().getJobGroupNames().contains(FetchFrequency.class.getName()))
			return;
		ScheduleService.schedule(job);
	}
}
