/*
 * FILENAME
 *     ExportNewsRestController.java
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.vsii.ttxvn.keywordsearching.domain.SearchCriteria;
import com.vsii.ttxvn.keywordsearching.domain.SolrDoc;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.entity.Keyword;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.service.SearchNewsService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.Constants;
import com.vsii.ttxvn.keywordsearching.utils.DateUtils;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;
import com.vsii.ttxvn.keywordsearching.utils.TtxvnUserContext;

/**
 * <p>
 * The class for exporting news.
 * </p>
 * 
 * @version 1.0
 * @author phunv
 **/
@Controller
@RequestMapping("/restful/exportnews")
public class ExportNewsRestController extends BaseController
{
	@RequestMapping(value = "/generatePDF", method = RequestMethod.POST)
	private void downloadNewsPDF(@ModelAttribute SearchCriteria searchCriteria, HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		final int page = (searchCriteria.getPage() > 0) ? (searchCriteria.getPage() - 1) : 0;
		final TtxvnPageRequest pageRequest = new TtxvnPageRequest(page, TtxvnPageWrapper.ITEM_PER_PAGE);
		long userId = TtxvnUserContext.getCurrentUser().getId();
		
		searchCriteria.setLanguageCode(LanguageUtils.getCurrentLanguageCode());
		if(searchCriteria.isFindByAllUserKeywordUnderMonitoring()){
			List<Keyword> keywordbyUser=getKeywordService().getKeywordUnderMonitoring(userId,searchCriteria.getLanguageCode());
			List<String> keywordNames = new ArrayList<String>();
			for(Keyword keyword : keywordbyUser){
				keywordNames.add(keyword.getName());
			}
			searchCriteria.setKeywordByUser(keywordNames);
		}
		if((!searchCriteria.isSearchInTitleOnly()&&StringUtils.isBlank(searchCriteria.getKeyword()))){
			List<SourceUrl> sourceUrls = getSourceUrlService().getListSourceUrlByLanguage(searchCriteria.getLanguageCode());
			List<String> urlNames = new ArrayList<String>();
			for(SourceUrl url : sourceUrls) {
				urlNames.add(url.getUrl());
			}
			searchCriteria.setUrlByKeyword(urlNames);
		}
		
		if(searchCriteria.isSearchInTitleOnly()&& !StringUtils.isNoneBlank(searchCriteria.getKeyword())){
			List<Keyword> keywords=getKeywordService().getKeywordUnderTitleNull(searchCriteria.getLanguageCode());
			List<String> keywordNames2 = new ArrayList<String>();
			for(Keyword keyword : keywords){
				keywordNames2.add(keyword.getName());
			}
			searchCriteria.setKeywords(keywordNames2);
		}
		
		final PageImpl<SolrDoc> solrPage = getSearchNewsService().findNewspaper(searchCriteria, pageRequest);
		TtxvnPageWrapper<SolrDoc> pageWrapper = new TtxvnPageWrapper<SolrDoc>(solrPage, "", 0);

		try
		{
			// filter news those had not selected by user
			if (CollectionUtils.isNotEmpty(pageWrapper.getContent()))
			{
				if ((searchCriteria.getSelectNewsIds() == null) || (searchCriteria.getSelectNewsIds().length == 0))
				{
					pageWrapper.setContent(null);
				}
				else if (searchCriteria.getSelectNewsIds().length < 7)
				{
					List<SolrDoc> solrDocs = pageWrapper.getContent();
					List<SolrDoc> solrDocsTemp = new ArrayList<SolrDoc>();

					for (int i = 0; i < solrDocs.size(); i++)
					{
						/*
					    for (String selectNewsId : searchCriteria.getSelectNewsIds())
						{
							if (selectNewsId.equalsIgnoreCase(solrDocs.get(i).getId()))
							{
								solrDocsTemp.add(solrDocs.get(i));
								break;
							}
						}
						*/
					    for (int selectNewsId : searchCriteria.getSelectNewsIds())
                        {
                            if (selectNewsId == i)
                            {
                                solrDocsTemp.add(solrDocs.get(i));
                                break;
                            }
                        }
					}
					log.debug("-----> solrDocsTemp.size(): " + solrDocsTemp.size());
					pageWrapper.setContent(solrDocsTemp);
				}
			}

			Document document = new Document();
			response.setHeader(Constants.CONTENT_DISPOSITION, Constants.OUTLINE_FILENAME + getPdfFileName() + "\"");
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			addMetaData(document);
			addContent(document, pageWrapper);
			document.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/*
	 * add metadata to the PDF which can be viewed in your Adobe Reader under
	 * File -> Properties
	 */
	private static void addMetaData(Document document)
	{
		document.addTitle("TTXVN Documents");
		document.addSubject("Crawls News");
		document.addKeywords("News, iText");
		document.addAuthor(System.getProperty("user.name"));
		document.addCreator(System.getProperty("user.name"));
	}

	private static void addContent(Document document, TtxvnPageWrapper<SolrDoc> pageWrapper) throws DocumentException,
			IOException
	{
		Paragraph paragraph;
		BaseFont baseFont = FontFactory.getFont(Constants.ARIAL_FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
				.getBaseFont();
		Font FONT13_NORMAL = new Font(baseFont, 13, Font.NORMAL, BaseColor.BLACK);
		Font FONT13_BOLD = new Font(baseFont, 13, Font.BOLD);

		if ((document != null) && (pageWrapper != null) && (CollectionUtils.isNotEmpty(pageWrapper.getContent())))
		{
			for (SolrDoc solrDoc : pageWrapper.getContent())
			{
				paragraph = new Paragraph();
				paragraph.setAlignment(Element.ALIGN_LEFT);
				paragraph.setIndentationLeft(5);
				paragraph.setIndentationRight(5);
				paragraph.add(new Paragraph(solrDoc.getTitle(), FONT13_BOLD));
				addEmptyLine(paragraph, 1);
				paragraph.add(new Paragraph(solrDoc.getUrl(), FONT13_NORMAL));
				addEmptyLine(paragraph, 1);
				paragraph.add(new Paragraph(solrDoc.getContent(), FONT13_NORMAL));
				addEmptyLine(paragraph, 3);
				document.add(paragraph);
			}
		}
		else
		{
			paragraph = new Paragraph();
			addEmptyLine(paragraph, 3);
			document.add(paragraph);
		}
	}

	private static void addEmptyLine(Paragraph paragraph, int number)
	{
		for (int i = 0; i < number; i++)
		{
			paragraph.add(new Paragraph(Constants.SPACE));
		}
	}

	private static String getPdfFileName()
	{
		return Constants.TTXVN_NEWS_PREFIX + DateUtils.getCurrentDate() + Constants.PDF_FILE_EXTENSION;
	}

	/**
	 * <p>
	 * Getter for searchNewsService.
	 * </p>
	 * 
	 * @return the searchNewsService
	 */
	public SearchNewsService getSearchNewsService()
	{
		return ServiceResolver.findService(SearchNewsService.class);
	}
	public KeywordService getKeywordService()
	{
		return ServiceResolver.findService(KeywordService.class);
	}
	    
	public SourceUrlService getSourceUrlService() {
	    return ServiceResolver.findService(SourceUrlService.class);
	 }
}
