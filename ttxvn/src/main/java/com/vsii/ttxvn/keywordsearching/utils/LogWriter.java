/*
 * FILENAME
 *     LogWriter.java
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

package com.vsii.ttxvn.keywordsearching.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import org.apache.velocity.texen.util.FileUtil;

/**
 * <p>
 * The class for purpose of.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class LogWriter
{
	private static final String FILE_PREFIX = ".log";
	private static final String INFO = "INFO: ";
	private static final String ERROR = "ERROR: ";

	private String fullPath;

	/**
	 * The default constructor with one instance variables.
	 * 
	 * @param fullPath
	 *            the fullPath
	 */
	public LogWriter(String fullPath)
	{
		this.fullPath = fullPath;
	}

	/**
	 * The method helps to get instance of LogWriter.
	 * 
	 * @param logFile
	 *            the logFile
	 * @return LogWriter
	 */
	public static LogWriter getInstance(final String logFileName)
	{
		StringBuffer fullPath = new StringBuffer();
		/*
		if (Constants.LINUX.equalsIgnoreCase(getOSName()))
		{
			fullPath.append(getTempDir()).append(File.separator);
		}
		else
		{
			fullPath.append(getTempDir());
		}*/

		fullPath.append(Constants.TTX_RESOURCES_FOLDER)
				.append(Constants.TTXVN_LOG).append(File.separator)
				.append(DateUtils.format(Calendar.getInstance().getTime(), DateUtils.DD_MM_YYYY))
				.append(Constants.UNDERSCORE).append(logFileName).append(FILE_PREFIX);

		return new LogWriter(fullPath.toString());
	}

	/**
	 * The method helps to log specific content.
	 * 
	 * @param content
	 *            the content
	 */
	private void log(String logType, String content)
	{
		BufferedWriter bufferedWriter = null;

		try
		{
			File file = new File(fullPath);
			// cause in general, a non existent file will be created by Java
			// only if the parent directory exists, so we must to check the
			// existence of parent directory first and create it if needed
			if (!file.getParentFile().exists() || !file.canWrite())
			{
				FileUtil.mkdir(file.getParentFile().toString());
				file.createNewFile();
			}
			OutputStreamWriter writer = new OutputStreamWriter(
	                  new FileOutputStream(file, true), "UTF-8");
			// construct the BufferedWriter object
			bufferedWriter = new BufferedWriter(writer);
			// write out the content to the file
			bufferedWriter.write(logType
					+ DateUtils.format(Calendar.getInstance().getTime(), DateUtils.DD_MM_YYYY_HH_MM_SS)
					+ Constants.SPACE + content);
			// write a new line to the file so the next time you write to the
			// file it does it on the next line
			bufferedWriter.newLine();
		}
		catch (FileNotFoundException nfx)
		{
			nfx.printStackTrace();
		}
		catch (IOException iox)
		{
			iox.printStackTrace();
		}
		finally
		{ // flushes and closes the stream
			try
			{
				if (bufferedWriter != null)
				{
					bufferedWriter.flush();
					bufferedWriter.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * The method helps to log specific content.
	 * 
	 * @param content
	 *            the content
	 */
	public void info(String content)
	{
		log(INFO, content);
	}

	/**
	 * The method helps to log specific content.
	 * 
	 * @param content
	 *            the content
	 */
	public void error(String content)
	{
		log(ERROR, content);
	}

	/**
	 * @return the temporary directory
	 * 
	 *         in Windows 7, the path is C:\Users\UserName\AppData\Local\Temp\
	 *         in Windows XP, the path is C:\Documents and
	 *         Settings\UserName\Local Settings\Temp\ You can change the TEMP
	 *         DIR by add System ENV TEMP
	 */
	public static String getTempDir()
	{
		return System.getProperty(Constants.TEMP_DIRECTORY);
	}
	
	/**
	 * @return the name of Operating System
	 */
	public static String getTtxResourceFolder()
	{
		return System.getProperty(Constants.OS_NAME);
	}

	/**
	 * @return the name of Operating System
	 */
	public static String getOSName()
	{
		return System.getProperty(Constants.OS_NAME);
	}
}
