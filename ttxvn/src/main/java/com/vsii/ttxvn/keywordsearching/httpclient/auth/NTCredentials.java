/*
 * FILENAME
 *     NTCredentials.java
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

package com.vsii.ttxvn.keywordsearching.httpclient.auth;

import java.io.Serializable;

/**
 * <p>
 * NTLM Credentials bean used to create
 * <code>org.apache.commons.httpclient.NTCredentials</code> and
 * <code>org.apache.commons.httpclient.auth.AuthScope</code>.
 * </p>
 * 
 * <p>
 * <strong>Note</strong>: User name, password, host, and domain are required.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class NTCredentials extends Credentials implements Serializable {

	private static final long serialVersionUID = -6920896316432574273L;

	protected String host = null;
	protected String domain = null;

	/**
	 * Constructor
	 */
	public NTCredentials() {
	}

	/**
	 * Constructor
	 */
	public NTCredentials(String authScopeHost, int authScopePort,
			String userName, String password, String host, String domain) {
		super(authScopeHost, authScopePort, userName, password);

		this.host = host;
		this.domain = domain;
	}

	/**
	 * Gets host.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Sets host.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets domain.
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets domain.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Returns a string representation of the object.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getClass().getName());
		sb.append(" { ");
		sb.append("authScopeHost=" + authScopeHost);
		sb.append("  authScopePort=" + authScopePort);
		sb.append("  userName=" + userName);
		sb.append("  password=" + password);
		sb.append("  host=" + host);
		sb.append("  domain=" + domain);
		sb.append(" }");

		return sb.toString();
	}

}
