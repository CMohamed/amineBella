package com.amineBella.demo.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class exportUtils {

    public static Sheet getSheet(Workbook workbook, String[] columns) {
        Sheet sheet = workbook.createSheet();

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        headerCellStyle.setFillForegroundColor((short) 11);
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(font);
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        return sheet;
    }

    public static InputStreamResource writeWorkbook(Workbook workbook) throws IOException {
        InputStreamResource inputStreamResource = null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            workbook.write(bos);
            byte[] barray = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(barray);
            inputStreamResource = new InputStreamResource(is);
            return inputStreamResource;
        }
    }
}
