/*
 * FILENAME
 *     SolrRequestAttributes.java
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

package com.vsii.ttxvn.keywordsearching.httpclient;


/**
 * <p>
 * The class for optional attributes for a commit or optimize request.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class SolrRequestAttributes {

    private Integer maxSegments = null;
    private Boolean waitFlush = null;
    private Boolean waitSearcher = null;
   
    /**
     * Gets max segments.
     * Used to optimize down to at most this number of segments.
     * Defaults to '1'.
     */
    public Integer getMaxSegments() {
        return maxSegments;
    }
    
    /**
     * Sets max segments.
     * Used to optimize down to at most this number of segments.
     * Defaults to '1'.
     */
    public void setMaxSegments(Integer maxSegments) {
        this.maxSegments = maxSegments;
    }
    
    /**
     * Gets whether or not to wait flush.
     * Will block until index changes are flushed to disk.
     * Defaults to <code>true</code>.
     */
    public Boolean getWaitFlush() {
        return waitFlush;
    }

    /**
     * Sets whether or not to wait flush.
     * Will block until index changes are flushed to disk.
     * Defaults to <code>true</code>.
     */
    public void setWaitFlush(Boolean waitFlush) {
        this.waitFlush = waitFlush;
    }

    /**
     * Gets whether or not to wait for the searcher.
     * Will block until a new searcher is opened and registered as the main query searcher, making the changes visible.
     * Defaults to <code>true</code>.
     */
    public Boolean getWaitSearcher() {
        return waitSearcher;
    }

    /**
     * Sets whether or not to wait for the searcher.
     * Will block until a new searcher is opened and registered as the main query searcher, making the changes visible.
     * Defaults to <code>true</code>.
     */
    public void setWaitSearcher(Boolean waitSearcher) {
        this.waitSearcher = waitSearcher;
    }

    
}

