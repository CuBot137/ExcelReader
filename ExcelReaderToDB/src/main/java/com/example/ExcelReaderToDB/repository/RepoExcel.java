package com.example.ExcelReaderToDB.repository;

import com.example.ExcelReaderToDB.model.ModelExcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoExcel extends JpaRepository<ModelExcel, Long> {

}
