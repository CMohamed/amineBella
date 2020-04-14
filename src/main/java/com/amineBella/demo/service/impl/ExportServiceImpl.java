package com.amineBella.demo.service.impl;

import com.amineBella.demo.service.ExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

@Service
public class ExportServiceImpl implements ExportService {
    @Override
    public InputStreamResource export() {
        InputStreamResource inputStreamResource = new InputStreamResource(null);
        try(Workbook workbook = new XSSFWorkbook()) {

        } catch (Exception e) {

        }
        return inputStreamResource;
    }
}
