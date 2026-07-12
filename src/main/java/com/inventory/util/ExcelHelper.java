package com.inventory.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class ExcelHelper {

    public ByteArrayInputStream productsToExcel(
            List<String[]> data) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Products");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("SKU");
            header.createCell(3).setCellValue("Selling Price");
            header.createCell(4).setCellValue("Cost Price");
            header.createCell(5).setCellValue("Category");

            int rowIndex = 1;

            for (String[] rowData : data) {

                Row row = sheet.createRow(rowIndex++);

                for (int i = 0; i < rowData.length; i++) {
                    row.createCell(i).setCellValue(rowData[i]);
                }
            }

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            workbook.write(out);

            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            throw new RuntimeException("Failed to export excel");
        }
    }
}