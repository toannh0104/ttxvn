/*
 * FILENAME
 *     example.java
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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * The class for purpose of Cache.
 * </p>
 * 
 * @version 1.0
 * @author sonta
 **/
public class TtxvnCache {
	private static Map<String, CacheObject> cacheHashMap = new HashMap<String, CacheObject>();
	private final static String CACHE_KEY_FOR_SEARCH_ALL="c:0k:0u:0";
	private Log log = LogFactory.getLog(getClass());
	public void putCache(CacheObject object) {
		// Remember if the HashMap previously contains a mapping for the key,
		// the old value
		// will be replaced. This is valid functioning.
		cacheHashMap.put(object.getKey(), object);
	}

	// method to getCache value
	public CacheObject getCache(String key) {
		// synchronized (lock) // UNCOMMENT LINE XXX
		// { // UNCOMMENT LINE XXX
		CacheObject object = (CacheObject) cacheHashMap.get(key);
		// The code to create the object would be placed here.
		// } // UNCOMMENT LINE XXX
		if (object == null)
			return null;
		if (object.isExpired()) {
			log.debug("Remove cache have key: "+key);
			cacheHashMap.remove(key);
			return null;
		} else {
			return object;
		}
	}
	public Map<String, CacheObject> getAllCache(){
		return cacheHashMap;
	}
	public void removeCachebyKey(String key){
		// TODO Auto-generated method stub
		log.debug("Remove cache have key: "+key);
		cacheHashMap.remove(key);	
	}

	public void removeCacheWhenUpdateKeyWord() {
		// TODO Auto-generated method stub/
		Iterator<Map.Entry<String, CacheObject>> keys = cacheHashMap.entrySet().iterator();
		while (keys.hasNext()) {
			Map.Entry<String, CacheObject> key = keys.next();
			if(key.getKey().contains(CACHE_KEY_FOR_SEARCH_ALL)){	
				log.debug("Remove cache have key: "+key.getKey());
				keys.remove();
			}
		}
	}


	public static class CacheObject {
		private Date dateofExpiration = null;
		private Date dateofCache = null;
		private String key = null;
		/*
		 * This contains the real "value". This is the object which needs to be
		 * shared.
		 */
		public Object value = null;

		public CacheObject(Object obj, String id, int minutesToLive) {
			this.value = obj;
			this.key = id;
			// minutesToLive of 0 means it lives on indefinitely.
			if (minutesToLive != 0) {
				dateofExpiration = new java.util.Date();
				Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(dateofExpiration);
				cal.add(Calendar.MINUTE, minutesToLive);
				dateofExpiration = cal.getTime();
			}
			this.dateofCache = new java.util.Date();
		}

		public boolean isExpired() {
			// Remember if the minutes to live is zero then it lives forever!
			if (dateofExpiration != null) {
				// date of expiration is compared.
				if (dateofExpiration.before(new java.util.Date())) {
					return true;
				} else {
					return false;
				}
			} else
				// This means it lives forever!
				return false;
		}

		public String getKey() {
			return key;
		}

		public Object getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		public java.util.Date getDateofExpiration() {
			return dateofExpiration;
		}

		public java.util.Date getDateofCache() {
			return dateofCache;
		}

	}
}
