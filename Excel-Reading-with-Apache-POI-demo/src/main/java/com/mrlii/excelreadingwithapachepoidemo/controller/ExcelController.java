package com.mrlii.excelreadingwithapachepoidemo.controller;

import com.mrlii.excelreadingwithapachepoidemo.model.Employee;
import com.mrlii.excelreadingwithapachepoidemo.service.ExcelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/generateExcel")
    public ResponseEntity<byte[]> generateExcel() throws IOException {

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=employees.xlsx")
                    .body(excelService.generateExcel());

    }

    // Upload + Read
    @PostMapping(value = "/uploadEmployeesExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Employee>> uploadEmployeesExcel(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(excelService.readEmployees(file));
    }
}


