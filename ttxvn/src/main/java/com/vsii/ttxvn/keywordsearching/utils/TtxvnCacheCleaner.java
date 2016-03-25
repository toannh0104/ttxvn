/*
 * FILENAME
 *     TtxvnCacheCleaner.java
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

import java.util.Iterator;
import java.util.Map;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vsii.ttxvn.keywordsearching.utils.TtxvnCache.CacheObject;

/**
 * <p>
 * The class for purpose of clear Cache.
 * </p>
 * 
 * @version 1.0
 * @author sonta
 **/
public final class TtxvnCacheCleaner implements Runnable{
	private static TtxvnCache cacheUtils=new TtxvnCache();
	private Log log = LogFactory.getLog(getClass());
	Map.Entry<String, CacheObject> key=null;
	Map<String, CacheObject> cacheHashMap=null;

	@Override
	public void run() {
		cacheHashMap=cacheUtils.getAllCache();
		Iterator<Map.Entry<String, CacheObject>> keys = cacheHashMap.entrySet().iterator();
		
		log.debug("Run Clear Cache");
		while (keys.hasNext()) {
			key = keys.next();
			if(key.getValue().isExpired()){	
				log.debug("Remove cache have key: "+key.getKey());
				keys.remove();
			}
		}
	}

}
