package com.company.edu.service.impl;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelService {
	 public void exportToExcel(
	            List<?> dataList,
	            String[] headers,
	            String[] fields,
	            String sheetName,
	            HttpServletResponse response
	    ) throws IOException;

}
