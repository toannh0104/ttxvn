/*
 * FILENAME
 *     UnmarshallerSolrJSON.java
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.SolrResponse;
import com.vsii.ttxvn.keywordsearching.enums.LanguageCode;

/**
 * <p>
 * The class for unmarshalling JSON reponse from Solr.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Component(value = "unmarshallerSolrJSON")
public class UnmarshallerSolrJSON implements Unmarshaller {
    static final Logger logger = LoggerFactory.getLogger(UnmarshallerSolrJSON.class);
    
    /**
     * {@inheritDoc}
     *
     * @see org.springframework.oxm.Unmarshaller#unmarshal(javax.xml.transform.Source)
     */
    @Override
    public Object unmarshal(Source source) throws IOException, MappingException {
        SolrResponse solrResponse = new SolrResponse();
        List<SolrDoc> solrDocs = new ArrayList<SolrDoc>();
        
        solrResponse.setSolrDocs(solrDocs);
        
        if (source instanceof StreamSource) {
            InputStream in = null;
            
            try {
                in = ((StreamSource) source).getInputStream();
                Reader reader = new InputStreamReader(in, "UTF-8");
                
                JSONParser parser = new JSONParser();
                JSONObject jsonDocument = (JSONObject) parser.parse(reader);
                JSONObject jsonResponse  = (JSONObject) jsonDocument.get("response");
                JSONArray jsonDocs = (JSONArray) jsonResponse.get("docs");
                
                logger.debug("--------> num found: " + jsonResponse.get("numFound"));
                logger.debug("--------> start: " + jsonResponse.get("start"));
                logger.debug("--------> page size: " + jsonDocs.size());
                
                solrResponse.setNumFound((Long) jsonResponse.get("numFound"));
                solrResponse.setStart((Long) jsonResponse.get("start"));                
                
                if (jsonDocs != null && jsonDocs.size() > 0) {
                    SolrDoc solrDoc = null;
                    JSONObject doc;
                    
                    for (int j = 0; j < jsonDocs.size(); j++)  {
                        doc = (JSONObject) jsonDocs.get(j);
                        solrDoc = new SolrDoc();
                        solrDoc.setId((String) doc.get("id")); 
                        solrDoc.setUrl((String) doc.get("url"));
                        solrDoc.setTitle((String) doc.get("title"));                         
                        if (doc.get("lang-code")!=null && String.valueOf(doc.get("lang-code")).equalsIgnoreCase((LanguageCode.EN.getCode())))
                        	solrDoc.setContent((String) doc.get("content-en"));
                        if (doc.get("lang-code")!=null && String.valueOf(doc.get("lang-code")).equalsIgnoreCase((LanguageCode.VI.getCode())))
                        	solrDoc.setContent((String) doc.get("content-vi"));                        
//                        solrDoc.setContentSolr((String) doc.get("content"));
                        solrDocs.add(solrDoc);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return solrResponse;
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