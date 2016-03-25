/*
 * FILENAME
 *     HttpClientTemplate.java
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * The Template for easier use of <code>HttpClient</code>.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Component
public class HttpClientTemplate extends
		AbstractHttpClientTemplate<RequestEntity> {

	final Logger logger = LoggerFactory.getLogger(HttpClientTemplate.class);

	/**
	 * Constructor.
	 */
	public HttpClientTemplate() {
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 */
	public HttpClientTemplate(String defaultUri) {
		super(defaultUri, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 * @param init
	 *            Whether or not to initialize the bean.
	 */
	public HttpClientTemplate(String defaultUri, boolean init) {
		super(defaultUri, init);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            Byte array <code>RequestEntity</code> to post for the
	 *            request's data.
	 */
	public void executePostMethod(byte[] input) {
		executePostMethod(input, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            Byte array <code>RequestEntity</code> to post for the
	 *            request's data.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(byte[] input, ResponseCallback<?> callback) {
		executePostMethod(defaultUri,
				(input != null ? new ByteArrayRequestEntity(input) : null),
				null, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            <code>String</code> to post for the request's data.
	 */
	public void executePostMethod(String input) {
		executePostMethod(input, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            <code>String</code> to post for the request's data.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(String input, ResponseCallback<?> callback) {
		executePostMethod(
				(input != null ? new ByteArrayInputStream(input.getBytes())
						: null), callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            <code>InputStream</code> to post for the request's data.
	 */
	public void executePostMethod(InputStream input) {
		executePostMethod(input, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param input
	 *            <code>InputStream</code> to post for the request's data.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(InputStream input,
			ResponseCallback<?> callback) {
		executePostMethod(defaultUri, new InputStreamRequestEntity(input),
				null, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param requestPayload
	 *            <code>RequestEntity</code> data to post.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(String uri, RequestEntity requestPayload,
			Map<String, String> hParams, ResponseCallback<?> callback) {
		PostMethod post = new PostMethod(uri);

		if (requestPayload != null) {
			post.setRequestEntity(requestPayload);
		}

		processHttpMethodParams(post, hParams);

		processHttpMethod(post, callback);
	}

	/**
	 * Processes <code>HttpMethod</code> by executing the method, validating the
	 * response, and calling the callback.
	 * 
	 * @param httpMethod
	 *            <code>HttpMethod</code> to process.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	protected void processHttpMethod(HttpMethod httpMethod,
			ResponseCallback<?> callback) {
		try {
			client.executeMethod(httpMethod);

			validateResponse(httpMethod);

			if (callback instanceof ResponseByteCallback) {
				((ResponseByteCallback) callback).doWithResponse(httpMethod
						.getResponseBody());
			} else if (callback instanceof ResponseStreamCallback) {
				((ResponseStreamCallback) callback).doWithResponse(httpMethod
						.getResponseBodyAsStream());
			} else if (callback instanceof ResponseStringCallback) {
				((ResponseStringCallback) callback).doWithResponse(httpMethod
						.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			throw new HttpAccessException(e.getMessage(), e,
					httpMethod.getStatusCode());
		} catch (IOException e) {
			throw new HttpAccessException(e.getMessage(), e);
		} finally {
			httpMethod.releaseConnection();
		}
	}

}
