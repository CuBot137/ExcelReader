package com.example.ExcelReaderToDB.controller;


import com.example.ExcelReaderToDB.service.ServiceExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class ControllerExcel {

    @Autowired
    private ServiceExcel serviceExcel;

    @PostMapping("/get")
    //  Multipart file is an excel file
    public ResponseEntity<String>getExcelFile(@RequestParam("file")MultipartFile file){
        try{
            serviceExcel.saveExcelData(file);
            return ResponseEntity.ok("Excel file was uploaded");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
        }
    }

    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        serviceExcel.generateExcel(response);

    }




}
