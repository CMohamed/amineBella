package com.amineBella.demo.service.impl;

import com.amineBella.demo.entities.User;
import com.amineBella.demo.service.ExportService;
import com.amineBella.demo.utils.exportUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.amineBella.demo.DemoApplication.users;

@Service
public class ExportServiceImpl implements ExportService {
    private final String[] userColumns = {"firstName", "lastName", "tel"};
    @Override
    public InputStreamResource export() {
        InputStreamResource inputStreamResource = null;
        try(Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = exportUtils.getSheet(workbook, userColumns);

            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontName("Arial");
            style.setFillForegroundColor((short) 11);
            //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setColor(HSSFColor.WHITE.index);
            style.setFont(font);
            // create header row
            Row header = sheet.createRow(1);
            header.createCell(1).setCellValue("Title");
            header.getCell(1).setCellStyle(style);
            int rowNum = 1;
            /*
            Stream<String> col = Arrays.stream(User.class.getDeclaredFields()).map(Field::getName);
            for(Field field : User.class.getDeclaredFields()) {
                String l = field.getName();
                System.out.println(l);
            }*/
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0)
                        .setCellValue(user.getFirstName());
                row.createCell(1).
                        setCellValue(user.getLastName());
                row.createCell(2)
                        .setCellValue(user.getTel());
            }
            inputStreamResource = exportUtils.writeWorkbook(workbook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputStreamResource;
    }
}
