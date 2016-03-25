package com.vsii.ttxvn.keywordsearching.databinder;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.vsii.ttxvn.keywordsearching.domain.ReportItem;

public class ReportItemListExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Báo cáo tổng hợp");
		setExcelHeader(excelSheet);
		
		List<ReportItem> reportItemList = (List<ReportItem>) model.get("reportItemList");
		setExcelRows(excelSheet,reportItemList);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Từ khóa");
		excelHeader.createCell(1).setCellValue("Chủ đề");
		excelHeader.createCell(2).setCellValue("Bắt đầu");
		excelHeader.createCell(3).setCellValue("Kết thúc");
		excelHeader.createCell(4).setCellValue("Nguồn");
		excelHeader.createCell(3).setCellValue("Số lần xuất hiện");
		excelHeader.createCell(4).setCellValue("Số tin tức");
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<ReportItem> reportItemList){
		int record = 1;
		for (ReportItem reportItem : reportItemList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(reportItem.getKeyWord());
			excelRow.createCell(1).setCellValue(reportItem.getCategory());
			excelRow.createCell(2).setCellValue(reportItem.getStartDate());
			excelRow.createCell(3).setCellValue(reportItem.getEndDate());
			excelRow.createCell(4).setCellValue(reportItem.getUrl());
			excelRow.createCell(4).setCellValue(reportItem.getNumOfAppearance());
			excelRow.createCell(4).setCellValue(reportItem.getNumOfNews());
		}
	}
}
