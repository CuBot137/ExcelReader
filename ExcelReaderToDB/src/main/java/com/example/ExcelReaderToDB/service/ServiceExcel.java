package com.example.ExcelReaderToDB.service;
import com.example.ExcelReaderToDB.model.ModelExcel;
import com.example.ExcelReaderToDB.repository.RepoExcel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ServiceExcel {

    @Autowired
    private RepoExcel repoExcel;
    // Formats the cell value into a string
    @Autowired
    private ModelExcel excelModel;


    DataFormatter formatter = new DataFormatter();

    @Autowired
    public ServiceExcel(RepoExcel repoExcel, ModelExcel excelModel) {
        this.repoExcel = repoExcel;
        this.excelModel = excelModel;
    }





public void saveExcelData(@NotNull MultipartFile file) throws IOException {
    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
        Sheet sheet = workbook.getSheetAt(8);
        List<ModelExcel> modelExcelList = new ArrayList<>();
        for(int i = 2; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if(row != null){
                ModelExcel modelExcel = new ModelExcel();
                modelExcel.setSource(formatter.formatCellValue(row.getCell(5)));
                modelExcel.setSize4000(row.getCell(6));
                modelExcel.setSize3500((row.getCell(7)));
                modelExcel.setSize2600(row.getCell(8));
                modelExcelList.add(modelExcel);
            }
        }
        repoExcel.saveAll(modelExcelList);
    }
}



//    public void saveExcelData(MultipartFile file) throws IOException {
//        // Workbook holds an instance of an excel file
//        Workbook workbook = new XSSFWorkbook(file.getInputStream());
//        // Sheet is the page number of the excel file
//        // This excel file has 13 pages. The data I need is on page 7
//        ModelExcel modelExcel = new ModelExcel();
//        Sheet sheet = workbook.getSheetAt(8);
//        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
//            Row row = sheet.getRow(i);
//
//            if (row == null) {
//            } else {
//
//                modelExcel.setDocketNumber(formatter.formatCellValue(row.getCell(3)));
//                modelExcel.setDate(formatter.formatCellValue(row.getCell(4)));
//                modelExcel.setSource(formatter.formatCellValue(row.getCell(5)));
//                modelExcel.setSize4000(formatter.formatCellValue(row.getCell(6)));
//                modelExcel.setSize3500(formatter.formatCellValue(row.getCell(7)));
//                modelExcel.setSize2600(formatter.formatCellValue(row.getCell(8)));
//                modelExcel.setHours(formatter.formatCellValue(row.getCell(9)));
//
//                // modelExcel.setDestination(formatter.formatCellValue(row.getCell(11)));
//                modelExcel.setHauliers(formatter.formatCellValue(row.getCell(12)));
//
//                repoExcel.save(modelExcel);
//
//            }
//        }
//
//        workbook.close();
//    }






    //Create a method that gets information from database and groups it together.
    //Loop through this grouped data in the method below
    public List<ModelExcel> getIntegerExcelDataFromDatabase(){
        return repoExcel.groupIntegerInformation();
    }

    public List<ModelExcel> modifyExcelDataFromDatabse(List<ModelExcel> excelData){
        excelData = getIntegerExcelDataFromDatabase();

        return null;
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<ModelExcel> information = getIntegerExcelDataFromDatabase();  // Gonna do some stuff with this guy!!
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mammy's Sheet");

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Location");
        row.createCell(1).setCellValue("4000");
        row.createCell(2).setCellValue("3500");
        row.createCell(3).setCellValue("2600");
        row.createCell(4).setCellValue("Hours");

        int dataRowIndex = 1;
        for (ModelExcel databaseInformation : information) {
            XSSFRow dataRow = (sheet).createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(databaseInformation.getSource());
            dataRow.createCell(1).setCellValue(databaseInformation.getSize4000());
            dataRow.createCell(2).setCellValue(databaseInformation.getSize3500());
            dataRow.createCell(3).setCellValue(databaseInformation.getSize2600());
            dataRow.createCell(4).setCellValue(databaseInformation.getHours());
            dataRowIndex++;
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=file.xlsx");

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}




