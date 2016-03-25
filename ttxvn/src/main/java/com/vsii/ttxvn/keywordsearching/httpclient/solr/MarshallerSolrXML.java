/*
 * FILENAME
 *     MarshallerSolrXML.java
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

package com.vsii.ttxvn.keywordsearching.httpclient.solr;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;

/**
 * <p>
 * The class for marshalling Object to XML.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Component("marshallerSolrXML")
public class MarshallerSolrXML implements Marshaller {
	static final Logger logger = LoggerFactory.getLogger(MarshallerSolrXML.class);

	private static final String ADD_ELEMENT_NAME = "add";
	private static final String DOC_ELEMENT_NAME = "doc";
	private static final String FIELD_ELEMENT_NAME = "field";
	private static final String FIELD_ELEMENT_NAME_ATTRIBUTE = "name";

	/**
	 * {@inheritDoc}
	 *
	 * @see org.springframework.oxm.Marshaller#marshal(java.lang.Object,
	 *      javax.xml.transform.Result)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void marshal(Object bean, Result result) throws IOException,
			XmlMappingException {
		List<SolrDoc> infos = (List<SolrDoc>) bean;

		OutputStream out = null;
		XMLWriter writer = null;

		if (result instanceof StreamResult) {
			try {
				out = ((StreamResult) result).getOutputStream();

				Document document = DocumentHelper.createDocument();
				Element root = document.addElement(ADD_ELEMENT_NAME);

				for (SolrDoc item : infos) {
					Element doc = root.addElement(DOC_ELEMENT_NAME);

					doc.addElement(FIELD_ELEMENT_NAME)
							.addAttribute(FIELD_ELEMENT_NAME_ATTRIBUTE, "id")
							.addText(item.getUrl());
					doc.addElement(FIELD_ELEMENT_NAME)
							.addAttribute(FIELD_ELEMENT_NAME_ATTRIBUTE,
									"content").addText(item.getContent());
				}

				writer = new XMLWriter(out);

				writer.write(document);
			} finally {
				try {
					writer.close();
				} catch (Exception e) {
				}
				IOUtils.closeQuietly(out);
			}

		}

		logger.debug("Marshalled bean of size {}.", infos.size());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.springframework.oxm.Marshaller#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return (clazz.isAssignableFrom(List.class));
	}
}
