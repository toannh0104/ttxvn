/*
 * FILENAME
 *     HomepageController.java
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
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vsii.ttxvn.keywordsearching.databinder.ReportCriteriaEditor;
import com.vsii.ttxvn.keywordsearching.domain.ReportCriteria;
import com.vsii.ttxvn.keywordsearching.domain.ReportItem;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageRequest;
import com.vsii.ttxvn.keywordsearching.domain.TtxvnPageWrapper;
import com.vsii.ttxvn.keywordsearching.entity.Category;
import com.vsii.ttxvn.keywordsearching.entity.CategoryKeyword;
import com.vsii.ttxvn.keywordsearching.entity.SourceUrl;
import com.vsii.ttxvn.keywordsearching.service.CategoryService;
import com.vsii.ttxvn.keywordsearching.service.KeywordService;
import com.vsii.ttxvn.keywordsearching.service.ReportSummaryService;
import com.vsii.ttxvn.keywordsearching.service.SourceUrlService;
import com.vsii.ttxvn.keywordsearching.utils.LanguageUtils;
import com.vsii.ttxvn.keywordsearching.utils.ServiceResolver;

/**
 * <p>
 * The Controller for Hompage requests.
 * </p>
 * 
 * @version 1.0
 * @author namdx
 **/
@Controller
@RequestMapping("/report")
public class ReportSummaryController extends BaseController {
    @RequestMapping(value = "summary", method = RequestMethod.GET)
    public ModelAndView reportSummary() {
        ModelAndView modelAndView = new ModelAndView(
                "report/reportSummary");
                
        List<Category> listCategory = getCategoryService().findAll(
                Category.class);
        List<String> listKeyword = getKeywordService().getDistinctKeyword();
        List<SourceUrl> listSourceUrl = getSourceUrlService().findAll(
                SourceUrl.class);
        List<CategoryKeyword> listCategoryKeyword = getCategoryService()
                .findAll(CategoryKeyword.class);
        
        Map mapKeywordNameByNumber = new HashMap<String, String>();
        for (int i=1; i<=listKeyword.size(); i++) {
            mapKeywordNameByNumber.put(String.valueOf(i), listKeyword.get(i-1));
        }
        getReportService().init(mapKeywordNameByNumber);

        modelAndView.addObject("categories", listCategory);
        modelAndView.addObject("keywords", mapKeywordNameByNumber);
        modelAndView.addObject("sourceUrls", listSourceUrl);

        return modelAndView;
    }

    @RequestMapping(value = "summary/getSummaryReport", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public TtxvnPageWrapper<ReportItem> getReport( @RequestParam("reportCriteria") ReportCriteria reportCriteria, HttpServletRequest request) throws JsonMappingException, JsonParseException, IOException {
        TtxvnPageWrapper<ReportItem> pageWrapper = null;
        if (reportCriteria != null) {
            /*
             * log.debug("--> search all paper has: {keyword: " +
             * searchCriteria.getKeyword() + ", url: " + searchCriteria.getUrl()
             * + ", page: " + searchCriteria.getPage() + "}");
             */
            int page = reportCriteria.getPage() - 1;

            if (page < 0) {
                page = 0;
            }
            reportCriteria.setLanguageCode(LanguageUtils
                    .getCurrentLanguageCode());
            log.debug("--> current LanguageCode: "
                    + reportCriteria.getLanguageCode());
            int pageSize = 7;
            final TtxvnPageRequest pageRequest = new TtxvnPageRequest(page, pageSize);
            final PageImpl<ReportItem> aReportPage = getReportService().queryReport(reportCriteria, pageRequest);
            // final PageImpl<ReportItem> reportPage = buildReport(solrPage);               
//            pageWrapper = new TtxvnPageWrapper<ReportItem>(aReportPage, "", getReportService().getListReportItem().size()/pageSize+1);
            pageWrapper = new TtxvnPageWrapper<ReportItem>(aReportPage, "", 5);
        }

        return pageWrapper;
    }

    // @RequestMapping(value = "/AnimalList", method = RequestMethod.GET)
    // public String getAnimals(Model model) {
    // List animalList = getReportService().getAnimalList();
    // model.addAttribute("animalList", animalList);
    // return "AnimalList";
    // }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void getExcel(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            buildExcelDocument(getReportService().getListReportItem(),
                    new HSSFWorkbook(), request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Autowired
    MessageSource messageResource;
    protected void buildExcelDocument(List<ReportItem> reportItemList,
            HSSFWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Locale locate = LocaleContextHolder.getLocale();
        HSSFSheet excelSheet = workbook.createSheet(messageResource.getMessage("ttxvn.report.sheetname", null, locate));
        setExcelHeader(excelSheet);
        setExcelRows(excelSheet, reportItemList);

        response.setContentType("application/x-ms-excel");
        response.setContentType("application/x-felix; charset=us-ascii");
        response.setHeader("Content-Transfer-Encoding", "7bit");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"Report" + getCurrentStringDate()
                        + ".xls\"");
        try {
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getCurrentStringDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        return (dateFormat.format(cal.getTime()));
    }
    
    public void setExcelHeader(HSSFSheet excelSheet) {
        Locale locate = LocaleContextHolder.getLocale();
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue(messageResource.getMessage("ttxvn.report.keyword", null, locate));
        excelHeader.createCell(1).setCellValue(messageResource.getMessage("ttxvn.report.category", null, locate));
        excelHeader.createCell(2).setCellValue(messageResource.getMessage("ttxvn.report.start", null, locate));
        excelHeader.createCell(3).setCellValue(messageResource.getMessage("ttxvn.report.end", null, locate));
        excelHeader.createCell(4).setCellValue(messageResource.getMessage("ttxvn.report.source", null, locate));
        excelHeader.createCell(5).setCellValue(messageResource.getMessage("ttxvn.report.numappearance", null, locate));
        excelHeader.createCell(6).setCellValue(messageResource.getMessage("ttxvn.report.numnews", null, locate));
    }

    public void setExcelRows(HSSFSheet excelSheet,
            List<ReportItem> reportItemList) {
        int record = 1;
        if (reportItemList != null && reportItemList.size() > 0) {
            for (ReportItem reportItem : reportItemList) {
                HSSFRow excelRow = excelSheet.createRow(record++);
                excelRow.createCell(0).setCellValue(reportItem.getKeyWord());
                excelRow.createCell(1).setCellValue(reportItem.getCategory());
                if (reportItem.getStartDate() != null)
                    excelRow.createCell(2).setCellValue(
                            reportItem.getStartDate());
                if (reportItem.getEndDate() != null)
                    excelRow.createCell(3)
                            .setCellValue(reportItem.getEndDate());
                excelRow.createCell(4).setCellValue(reportItem.getUrl());
                excelRow.createCell(5).setCellValue(
                        reportItem.getNumOfAppearance());
                excelRow.createCell(6).setCellValue(reportItem.getNumOfNews());
            }
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ReportCriteria.class,
                new ReportCriteriaEditor());
    }

    /**
     * <p>
     * Getter for searchNewsService.
     * </p>
     * 
     * @return the searchNewsService
     */
    public ReportSummaryService getReportService() {
        return ServiceResolver.findService(ReportSummaryService.class);
    }

    public CategoryService getCategoryService() {
        return ServiceResolver.findService(CategoryService.class);
    }

    public KeywordService getKeywordService() {
        return ServiceResolver.findService(KeywordService.class);
    }

    public SourceUrlService getSourceUrlService() {
        return ServiceResolver.findService(SourceUrlService.class);
    }

}