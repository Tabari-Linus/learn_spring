package com.mrlii.excelreadingwithapachepoidemo.controller;

import com.mrlii.excelreadingwithapachepoidemo.model.Employee;
import com.mrlii.excelreadingwithapachepoidemo.service.ExcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/generateExcel")
    public ResponseEntity<byte[]> generateExcel(){
        List<Employee> employees = Arrays.asList(
                new Employee("Mr.Li", "li.IT@speedTec.com", "IT"),
                new Employee("Balak Mantain","Bala.IT@speedTec.com", "IT"),
                new Employee("Felia", "felia.IT@speedTec.com", "IT"));

        try {
            byte[] excelData = excelService.generateExcel(employees);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=employees.xlsx")
                    .body(excelData);
            } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


