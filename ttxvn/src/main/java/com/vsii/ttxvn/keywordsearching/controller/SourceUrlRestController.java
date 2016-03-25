/*
 * FILENAME
 *     SourceUrlController.java
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

package com.vsii.ttxvn.keywordsearching.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * The class for purpose of handling all request about SourceUrl.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/source-url")
public class SourceUrlRestController extends BaseController {
	
    /**
	 * <p>
	 * Getter for sourceUrlService.
	 * </p>
	 * 
	 * @return the sourceUrlService
	 */
	public SourceUrlService getSourceUrlService() {
		return (SourceUrlService) ServiceResolver
				.findService(SourceUrlService.class);
	}
	
	@RequestMapping(value = "/loadSourceUrl", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> findSourceUrlByCategory(HttpServletRequest request, HttpServletResponse response)
	{
		List<SourceUrl> sourceUrls = getSourceUrlService().getListSourceUrlByLanguage(
				LanguageUtils.getCurrentLanguageCode());
		Map<String, String> values = new HashMap<String, String>();
		
		for (SourceUrl source : sourceUrls)
		{
			values.put(source.getId().toString(), source.getDomain());
		}
		return values;
	}
}
