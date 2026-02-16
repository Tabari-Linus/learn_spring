package com.mrlii.excelreadingwithapachepoidemo.service;

import com.mrlii.excelreadingwithapachepoidemo.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ExcelService {

    List<Employee> employees = Arrays.asList(
            new Employee("Mr.Li", "li.IT@speedTec.com", "IT"),
            new Employee("Balak Mantain","Bala.IT@speedTec.com", "IT"),
            new Employee("Felia", "felia.IT@speedTec.com", "IT"));


    public  byte[] generateExcel() throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Email");
        headerRow.createCell(2).setCellValue("Department");

        int rowNum = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getName());
            row.createCell(1).setCellValue(employee.getEmail());
            row.createCell(2).setCellValue(employee.getDepartment());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (Exception e) {
            Logger.getLogger(ExcelService.class.getName()).log(Level.SEVERE, null, e);
        }
        workbook.close();

        byte[] excelData = outputStream.toByteArray();
        outputStream.close();

        return excelData;
    }

    public List<Employee> readEmployees(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        String originalName = file.getOriginalFilename();
        if (originalName == null || !(originalName.endsWith(".xlsx") || originalName.endsWith(".xls"))) {
            throw new IllegalArgumentException("Unsupported file type. Upload .xlsx or .xls");
        }

        DataFormatter formatter = new DataFormatter();
        List<Employee> result = new ArrayList<>();

        try (InputStream in = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(in)) {

            Sheet sheet = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : null;
            if (sheet == null) {
                return result;
            }

            boolean headerSkipped = false;

            for (Row row : sheet) {
                // Skip header row (row 0)
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                // Read columns: 0=name, 1=email, 2=department
                String name = formatter.formatCellValue(row.getCell(0)).trim();
                String email = formatter.formatCellValue(row.getCell(1)).trim();
                String department = formatter.formatCellValue(row.getCell(2)).trim();

                // Skip completely blank rows
                if (name.isBlank() && email.isBlank() && department.isBlank()) {
                    continue;
                }

                result.add(new Employee(name, email, department));
            }
        }

        return result;
    }
}
