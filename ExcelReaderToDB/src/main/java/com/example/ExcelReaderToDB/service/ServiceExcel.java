package com.example.ExcelReaderToDB.service;

import com.example.ExcelReaderToDB.model.ModelExcel;
import com.example.ExcelReaderToDB.repository.RepoExcel;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class ServiceExcel {

    @Autowired
    private RepoExcel repoExcel;

    // Formats the cell value into a string
    DataFormatter formatter = new DataFormatter();

    public void saveExcelData(MultipartFile file) throws IOException{
        // Workbook holds an instance of an excel file
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        // Sheet is the page number of the excel file
        // This excel file has 13 pages. The data I need is on page 7
        Sheet sheet = workbook.getSheetAt(7);


        for(int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {

            } else {
                ModelExcel modelExcel = new ModelExcel();
                modelExcel.setDocketNumber(formatter.formatCellValue(row.getCell(3)));
                modelExcel.setDate(formatter.formatCellValue(row.getCell(4)));
                modelExcel.setSource(formatter.formatCellValue(row.getCell(5)));
                modelExcel.setSize4000(formatter.formatCellValue(row.getCell(6)));
                modelExcel.setSize3500(formatter.formatCellValue(row.getCell(7)));
                modelExcel.setSize2600(formatter.formatCellValue(row.getCell(8)));
                modelExcel.setHours(formatter.formatCellValue(row.getCell(9)));
                modelExcel.setDestination(formatter.formatCellValue(row.getCell(11)));
                modelExcel.setHauliers(formatter.formatCellValue(row.getCell(12)));


                repoExcel.save(modelExcel);
                workbook.close();
            }


        }

//        for(Row row : sheet){
//            ModelExcel modelExcel = new ModelExcel();
//            for (Cell cell : row){
//                modelExcel.setDocketNumber(cell.getStringCellValue());
//            }
//            modelExcel.setDocketNumber(row.getCell(0) .getStringCellValue());
//            repoExcel.save(modelExcel);
//        }
//        workbook.close();

    }
}
