package com.company.edu.service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExcelServiceImpl implements ExcelService{

	@Override
	public void exportToExcel(List<?> dataList, String[] headers, String[] fields, String sheetName,
			HttpServletResponse response) throws IOException{
		Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        // ===== HEADER =====
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // ===== DATA =====
        int rowIdx = 1;

        for (Object obj : dataList) {
            Row row = sheet.createRow(rowIdx++);

            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = obj.getClass().getDeclaredField(fields[i]);
                    field.setAccessible(true);

                    Object value = field.get(obj);

                    if (value != null) {
                        row.createCell(i).setCellValue(value.toString());
                    }

                } catch (Exception e) {
                    row.createCell(i).setCellValue("ERROR");
                }
            }
        }

        // ===== AUTO SIZE =====
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // ===== RESPONSE =====
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + sheetName + ".xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
		
		
	}

}
