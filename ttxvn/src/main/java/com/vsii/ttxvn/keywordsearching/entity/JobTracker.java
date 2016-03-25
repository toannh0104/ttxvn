/*
 * FILENAME
 *     JobTracker.java
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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Entity
@Table(name = "job_tracker")
public class JobTracker extends BaseEntity
{
	private static final long serialVersionUID = -2023361151952425958L;

	@Column(name = "DONE")
	private boolean done = false;

	@Column(name = "LAST_FIRED", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastFired;

	@Column(name = "JOB_NAME")
	private String jobName;

	/**
	 * 
	 * <p>
	 * Default constructor with no param.
	 * </p>
	 * 
	 */
	protected JobTracker()
	{
	}

	/**
	 * 
	 * <p>
	 * Default constructor with param.
	 * </p>
	 * 
	 */
	public JobTracker(String name)
	{
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Job name must not be null");
		jobName = name;
	}

    /**
     * <p>
     * Getter for done.
     * </p>
     * 
     * @return the done
     */
    public boolean isDone()
    {
        return done;
    }

    /**
     * <p>
     * Setting value for done.
     * </p>
     * 
     * @param done the done to set
     */
    public void setDone(boolean done)
    {
        this.done = done;
    }

    /**
     * <p>
     * Getter for lastFired.
     * </p>
     * 
     * @return the lastFired
     */
    public Date getLastFired()
    {
        return lastFired;
    }

    /**
     * <p>
     * Setting value for lastFired.
     * </p>
     * 
     * @param lastFired the lastFired to set
     */
    public void setLastFired(Date lastFired)
    {
        this.lastFired = lastFired;
    }

    /**
     * <p>
     * Getter for jobName.
     * </p>
     * 
     * @return the jobName
     */
    public String getJobName()
    {
        return jobName;
    }

    /**
     * <p>
     * Setting value for jobName.
     * </p>
     * 
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

}
