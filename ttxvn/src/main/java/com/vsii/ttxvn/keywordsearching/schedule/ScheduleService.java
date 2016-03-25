/*
 * FILENAME
 *     ScheduleService.java
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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.utils.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vsii.ttxvn.keywordsearching.entity.BaseEntity;

//
// IMPORTS
// NOTE: Import specific classes without using wildcards.
//

/**
 * <p>
 * Service for Scheduling.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Transactional(rollbackFor = Exception.class)
public class ScheduleService
{
	static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

	private static Scheduler scheduler;
	private static Set<JobHandler> jobHandlers = new HashSet<JobHandler>();
	private static Map<Class<? extends JobObject>, JobHandler> handlers = new HashMap<Class<? extends JobObject>, JobHandler>();

	public ScheduleService(Scheduler schedulerFactory, List<JobHandler> handlers) throws SchedulerException
	{
		if (schedulerFactory == null)
		{
			throw new IllegalArgumentException("A Quartz scheduler factory is required for the service to function.");
		}
		scheduler = schedulerFactory;// .getScheduler();
		scheduler.start();

		if (handlers != null)
			jobHandlers.addAll(handlers);
	}

	/**
	 * 
	 * <p>
	 * Schedule a job at specified date.
	 * </p>
	 * 
	 * @param jobDetail
	 *            the JobObject
	 * @param startAt
	 *            date start
	 * @throws SchedulerException
	 *             if some thing went wrong
	 * 
	 */
	public static void schedule(JobObject jobDetail, Date startAt) throws SchedulerException
	{
		JobKey key = createJobKey(jobDetail);
		Trigger trigger = TriggerBuilder.newTrigger().forJob(key).withIdentity(createTriggerKey(jobDetail))
				.startAt(startAt).build();
		internalSchedule(jobDetail, trigger, key);
	}

	/**
	 * 
	 * <p>
	 * Schedule a Job execute after created.
	 * </p>
	 * 
	 * @param jobDetail
	 *            the JobObject
	 * @throws SchedulerException
	 *             if some thing went wrong
	 * 
	 */
	public static void schedule(JobObject jobDetail) throws SchedulerException
	{
		JobKey key = createJobKey(jobDetail);
		Trigger trigger = TriggerBuilder.newTrigger().forJob(key).withIdentity(createTriggerKey(jobDetail)).startNow()
				.build();
		internalSchedule(jobDetail, trigger, key);
	}

	/**
	 * 
	 * <p>
	 * schedule a Job with cron expression and date start, stop.
	 * </p>
	 * 
	 * @param job
	 *            the JobObject
	 * @param cronExpression
	 *            cron expression
	 * @param startAt
	 *            date start
	 * @param stopAt
	 *            date stop
	 * @throws SchedulerException
	 *             if something went wrong
	 * 
	 */
	public static void schedule(final JobObject job, final String cronExpression, final Date startAt,
			final Date stopAt, boolean startNow) throws SchedulerException
	{
		JobKey key = createJobKey(job);
		TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger().forJob(key)
				.withIdentity(createTriggerKey(job)).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
		if (startAt == null && startNow)
			triggerBuilder.startNow();
		if (stopAt != null && startAt != null && stopAt.before(startAt))
			throw new SchedulerException("Stop time must not be before start time"); //$NON-NLS-1$
		if (startAt != null)
			triggerBuilder.startAt(startAt);
		if (stopAt != null)
		{
			if (Calendar.getInstance().getTime().after(stopAt))
				throw new SchedulerException("Stop time must be in the future"); //$NON-NLS-1$
			triggerBuilder.endAt(stopAt);
		}
		CronTrigger trigger = triggerBuilder.build();
		internalSchedule(job, trigger, key);
	}

	public static void schedule(final JobObject job, final int minute)
	{
		JobKey key = createJobKey(job);
		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity(createTriggerKey(job)).forJob(key)
				.startNow().withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(minute)).build();
		internalSchedule(job, simpleTrigger, key);
	}

	@SuppressWarnings("unchecked")
	private static void internalSchedule(final JobObject job, final Trigger trigger, JobKey key)
	{
		try
		{
			if (scheduler.checkExists(trigger.getKey()))
				scheduler.rescheduleJob(trigger.getKey(), trigger);
			else
			{
				JobDetail jobDetail;
				if ((scheduler.checkExists(key)) && ((jobDetail = scheduler.getJobDetail(key)) != null))
				{
					scheduler.rescheduleJob(trigger.getKey(), trigger);
				}
				else
				{
					if (job instanceof BaseEntity)
						jobDetail = JobBuilder.newJob(getJobHandler(job).getClass()).withIdentity(key).build();
					else
						jobDetail = JobBuilder.newJob((Class<? extends Job>) job.getClass()).withIdentity(key).build();
					scheduler.scheduleJob(jobDetail, trigger);
				}
			}
		}
		catch (SchedulerException e)
		{
			logger.error(e.getMessage());
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}

	public static void unschedule(final JobObject job) throws SchedulerException
	{
		JobKey key = createJobKey(job);
		try
		{
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(key);
			for (Trigger trigger : triggers)
				scheduler.unscheduleJob(trigger.getKey());
		}
		catch (SchedulerException e)
		{
			logger.error(e.getMessage());
		}
	}

	public static void delete(final JobObject job) throws SchedulerException
	{
		JobKey key = createJobKey(job);
		try
		{
			scheduler.deleteJob(key);
		}
		catch (SchedulerException e)
		{
			logger.error(e.getMessage());
		}
	}

	private static JobHandler getJobHandler(JobObject job)
	{
		JobHandler jobHandler = handlers.get(job.getClass());
		if (jobHandler != null)
			return jobHandler;

		for (JobHandler handler : jobHandlers)
		{
			if (handler.canHandleJob(job.getClass()))
			{
				handlers.put(job.getClass(), handler);
				jobHandler = handler;
			}
		}
		if (jobHandler == null)
			throw new IllegalArgumentException("Cannot find Job handler for" + job.getClass().getName());
		return jobHandler;
	}

	private static JobKey createJobKey(JobObject job)
	{
		return new JobKey(getJobId(job), getJobClass(job));
	}

	private static String getJobId(JobObject job)
	{
		return (job instanceof BaseEntity) ? ((BaseEntity) job).getId().toString() : ((RunnableJob) job).getUniqueKey();
	}

	private static String getJobClass(JobObject job)
	{
		return (job instanceof BaseEntity) ? ((BaseEntity) job).getTarget().getClass().getName() : job.getClass()
				.getName();
	}

	private static TriggerKey createTriggerKey(JobObject job)
	{
		String jobGroup = getJobClass(job);
		String triggerName = Key.createUniqueName(jobGroup) + getJobId(job);
		return new TriggerKey(triggerName, jobGroup);
	}

	public static Scheduler getScheduler()
	{
		return scheduler;
	}

}
