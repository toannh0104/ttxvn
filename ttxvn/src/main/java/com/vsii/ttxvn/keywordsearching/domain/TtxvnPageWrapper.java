/*
 * FILENAME
 *     TtxvnPageWrapper.java
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

package com.vsii.ttxvn.keywordsearching.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageImpl;

/**
 * <p>
 * The class for purpose of wrapper pagination info.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class TtxvnPageWrapper<T>
{
	private static final Log logger = LogFactory.getLog(TtxvnPageWrapper.class);

	public static final int MAX_PAGE_ITEM_DISPLAY = 5;
	public static final int ITEM_PER_PAGE = 7;

	private List<TtxvnPageItem> pageItems;
	private List<T> content;
	private int selectedPage;
	private String url;
	private int pageSize;
	private int totalPages;
	private boolean firstPage;
	private boolean lastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	private int displayedPage = MAX_PAGE_ITEM_DISPLAY;

	public TtxvnPageWrapper(PageImpl<T> page, String url, int pageAmount)
	{
		this.url = url;
		this.content = page.getContent();
		this.pageSize = page.getSize();
		this.selectedPage = page.getNumber() + 1; // start from 1 to match
													// page.page
		this.totalPages = page.getTotalPages();
		this.firstPage = page.isFirst();
		this.lastPage = (totalPages == selectedPage);
		this.hasPreviousPage = page.hasPrevious();
		this.hasNextPage = page.hasNext();
		this.pageItems = new ArrayList<TtxvnPageItem>();

		if (pageAmount > 0)
			displayedPage = pageAmount;

		int start;
		int size;

		if (page.getTotalPages() <= displayedPage)
		{
			start = 1;
			size = page.getTotalPages();
		}
		else
		{
			if (selectedPage <= displayedPage - displayedPage
					/ 2)
			{
				start = 1;
				size = displayedPage;
			}
			else if (selectedPage >= page.getTotalPages()
					- displayedPage / 2)
			{
				start = page.getTotalPages() - displayedPage + 1;
				size = displayedPage;
			}
			else
			{
				start = selectedPage - displayedPage / 2;
				size = displayedPage;
			}
		}

		for (int i = 0; i < size; i++)
		{
			pageItems.add(new TtxvnPageItem(start + i, (start + i) == selectedPage));
		}

		logger.debug("-------> url: " + this.url);
		logger.debug("-------> pageSize: " + this.pageSize);
		logger.debug("-------> selectedPage: " + this.selectedPage);
		logger.debug("-------> totalPages: " + this.totalPages);
		logger.debug("-------> firstPage: " + this.firstPage);
		logger.debug("-------> lastPage: " + this.isLastPage());
		logger.debug("-------> hasPreviousPage: " + this.hasPreviousPage);
		logger.debug("-------> hasNextPage: " + this.hasNextPage);
		logger.debug("-------> pageItems.size(): " + this.pageItems.size());
	}

	/**
	 * <p>
	 * Getter for url.
	 * </p>
	 * 
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * <p>
	 * Getter for pageItems.
	 * </p>
	 * 
	 * @return the pageItems
	 */
	public List<TtxvnPageItem> getPageItems()
	{
		return pageItems;
	}

	/**
	 * <p>
	 * Getter for selectedPage.
	 * </p>
	 * 
	 * @return the selectedPage
	 */
	public int getSelectedPage()
	{
		return selectedPage;
	}

	/**
	 * <p>
	 * Getter for content.
	 * </p>
	 * 
	 * @return the content
	 */
	public List<T> getContent()
	{
		return content;
	}

	/**
	 * <p>
	 * Setting value for content.
	 * </p>
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(List<T> content)
	{
		this.content = content;
	}

	/**
	 * <p>
	 * Getter for size.
	 * </p>
	 * 
	 * @return the size
	 */
	public int getSize()
	{
		return pageSize;
	}

	/**
	 * <p>
	 * Getter for totalPages.
	 * </p>
	 * 
	 * @return the totalPages
	 */
	public int getTotalPages()
	{
		return totalPages;
	}

	/**
	 * <p>
	 * Getter for firstPage.
	 * </p>
	 * 
	 * @return the firstPage
	 */
	public boolean isFirstPage()
	{
		return firstPage;
	}

	/**
	 * <p>
	 * Getter for lastPage.
	 * </p>
	 * 
	 * @return the lastPage
	 */
	public boolean isLastPage()
	{
		return lastPage;
	}

	/**
	 * <p>
	 * Getter for hasPreviousPage.
	 * </p>
	 * 
	 * @return the hasPreviousPage
	 */
	public boolean isHasPreviousPage()
	{
		return hasPreviousPage;
	}

	/**
	 * <p>
	 * Getter for hasNextPage.
	 * </p>
	 * 
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage()
	{
		return hasNextPage;
	}

}
