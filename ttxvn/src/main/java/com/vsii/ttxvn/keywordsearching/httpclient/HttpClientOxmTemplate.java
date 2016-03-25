/*
 * FILENAME
 *     HttpClientOxmTemplate.java
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.MarshallingFailureException;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * <p>
 * Template for easier use of <code>HttpClient</code> and also uses Spring Web
 * Services OXM framework to marshall/unmarshall XML from requests and
 * responses.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Component
public class HttpClientOxmTemplate<T> extends AbstractHttpClientTemplate<T>
		implements InitializingBean {
	final Logger logger = LoggerFactory.getLogger(HttpClientOxmTemplate.class);

	@Autowired
	protected Marshaller marshaller;
	@Autowired
	protected Unmarshaller unmarshaller;

	/**
	 * Constructor.
	 */
	public HttpClientOxmTemplate() {
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 */
	public HttpClientOxmTemplate(String defaultUri) {
		super(defaultUri, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 * @param marshaller
	 *            Marshaller to use for marshalling requests.
	 * @param unmarshaller
	 *            Unmarshaller to use for unmarshalling requests.
	 */
	public HttpClientOxmTemplate(String defaultUri, Marshaller marshaller,
			Unmarshaller unmarshaller) {
		this(defaultUri);

		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 * @param marshaller
	 *            Marshaller to use for marshalling requests.
	 * @param unmarshaller
	 *            Unmarshaller to use for unmarshalling requests.
	 * @param init
	 *            Whether or not to initialize the bean.
	 */
	public HttpClientOxmTemplate(String defaultUri, Marshaller marshaller,
			Unmarshaller unmarshaller, boolean init) {
		this(defaultUri, marshaller, unmarshaller);

		if (init) {
			try {
				afterPropertiesSet();
			} catch (Exception e) {
				throw new HttpAccessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Gets marshaller.
	 */
	public Marshaller getMarshaller() {
		return marshaller;
	}

	/**
	 * Sets marshaller.
	 */
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * Gets unmarshaller.
	 */
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	/**
	 * Sets unmarshaller.
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	/**
	 * Implementation of <code>InitializingBean</code> that initializes the
	 * <code>HttpClient</code> if it is <code>null</code> and also sets the
	 * connection manager to <code>MultiThreadedHttpConnectionManager</code> if
	 * it is <code>null</code> while initializing the <code>HttpClient</code>.
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(marshaller);
		Assert.notNull(unmarshaller);

		super.afterPropertiesSet();
	}

	/**
	 * Execute post method.
	 * 
	 * @param requestPayload
	 *            Request data to post after marshalling. The
	 *            <code>Marshaller</code> should be able to process this
	 *            instance.
	 */
	public void executePostMethod(T requestPayload) {
		executePostMethod(defaultUri, requestPayload, null, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param requestPayload
	 *            Request data to post after marshalling. The
	 *            <code>Marshaller</code> should be able to process this
	 *            instance.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(T requestPayload, ResponseCallback<?> callback) {
		executePostMethod(defaultUri, requestPayload, null, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param requestPayload
	 *            Request data to post after marshalling. The
	 *            <code>Marshaller</code> should be able to process this
	 *            instance.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 */
	public void executePostMethod(T requestPayload, Map<String, String> hParams) {
		executePostMethod(requestPayload, hParams, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param requestPayload
	 *            Request data to post after marshalling. The
	 *            <code>Marshaller</code> should be able to process this
	 *            instance.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(T requestPayload,
			Map<String, String> hParams, ResponseCallback<?> callback) {
		executePostMethod(defaultUri, requestPayload, hParams, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param requestPayload
	 *            Request data to post after marshalling. The
	 *            <code>Marshaller</code> should be able to process this
	 *            instance.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public void executePostMethod(String uri, T requestPayload,
			Map<String, String> hParams, ResponseCallback<?> callback) {
		PostMethod post = new PostMethod(uri);

		if (requestPayload != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			try {
				marshaller.marshal(requestPayload, new StreamResult(out));
			} catch (IOException e) {
				throw new MarshallingFailureException(e.getMessage(), e);
			}

			post.setRequestEntity(new ByteArrayRequestEntity(out.toByteArray()));
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
	@SuppressWarnings("unchecked")
	protected void processHttpMethod(HttpMethod httpMethod,
			@SuppressWarnings("rawtypes") ResponseCallback callback) {
		try {
			client.executeMethod(httpMethod);

			validateResponse(httpMethod);

			if (callback != null) {
				Object value = unmarshaller.unmarshal(new StreamSource(
						httpMethod.getResponseBodyAsStream()));

				callback.doWithResponse((T) value);
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
