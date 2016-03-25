/*
 * FILENAME
 *     Credentials.java
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
 * Credentials bean used to create
 * <code>org.apache.commons.httpclient.UsernamePasswordCredentials</code> and
 * <code>org.apache.commons.httpclient.auth.AuthScope</code>.
 * </p>
 * 
 * <p>
 * <strong>Note</strong>: User authorization scope's host, authorization scope's
 * port, name, password are both required.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
public class Credentials implements Serializable {

	private static final long serialVersionUID = 427351901207639483L;

	protected String authScopeHost = null;
	protected int authScopePort = 80;
	protected String userName = null;
	protected String password = null;

	/**
	 * Constructor
	 */
	public Credentials() {
	}

	/**
	 * Constructor
	 */
	public Credentials(String authScopeHost, int authScopePort,
			String userName, String password) {
		this.authScopeHost = authScopeHost;
		this.authScopePort = authScopePort;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Gets authorization scope's host.
	 */
	public String getAuthScopeHost() {
		return authScopeHost;
	}

	/**
	 * Sets authorization scope's host.
	 */
	public void setAuthScopeHost(String autoScopeHost) {
		this.authScopeHost = autoScopeHost;
	}

	/**
	 * Gets authorization scope's port.
	 */
	public int getAuthScopePort() {
		return authScopePort;
	}

	/**
	 * Sets authorization scope's port.
	 */
	public void setAuthScopePort(int authScopePort) {
		this.authScopePort = authScopePort;
	}

	/**
	 * Gets user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets user name.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 */
	public void setPassword(String password) {
		this.password = password;
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
		sb.append(" }");

		return sb.toString();
	}

}
