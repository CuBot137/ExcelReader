package com.example.ExcelReaderToDB.service;
import com.example.ExcelReaderToDB.model.ModelExcel;
import com.example.ExcelReaderToDB.repository.RepoExcel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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



    public void saveExcelData(MultipartFile file) throws IOException {
        // Creating instance of workbook using the file parameter
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        // Creating instance of sheet. Sheet is the page you are on. Getting sheet 8 of Excel file.
        Sheet sheet = workbook.getSheetAt(8);
        // Loop through each row in the excel form
        for(int i = 2; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);

            if(row != null){
                excelModel.setSource(formatter.formatCellValue(row.getCell(5)));
                excelModel.setSize4000(formatter.formatCellValue(row.getCell(6)));
                excelModel.setSize3500(formatter.formatCellValue(row.getCell(7)));
                excelModel.setSize2600(formatter.formatCellValue(row.getCell(8)));
                repoExcel.save(excelModel);
            }

        }
    }

    public void generateExcel(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mammy's Sheet");
        List<ModelExcel> information = repoExcel.findAll();

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Location");
        row.createCell(1).setCellValue("4000");
        row.createCell(2).setCellValue("3500");
        row.createCell(3).setCellValue("2600");

        int dataRowIndex = 1;
        for (ModelExcel databaseInformation : information) {
            XSSFRow dataRow = (sheet).createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(databaseInformation.getSource());
            dataRow.createCell(1).setCellValue(databaseInformation.getSize4000());
            dataRow.createCell(2).setCellValue(databaseInformation.getSize3500());
            dataRow.createCell(3).setCellValue(databaseInformation.getSize2600());

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




