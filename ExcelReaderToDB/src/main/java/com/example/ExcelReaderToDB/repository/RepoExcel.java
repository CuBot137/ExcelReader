package com.example.ExcelReaderToDB.repository;

import com.example.ExcelReaderToDB.model.ModelExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface RepoExcel extends JpaRepository<ModelExcel, Long> {

    @Query(value = "SELECT source, size4000, size3500, size2600 FROM model_excel;", nativeQuery = true)
    List<ModelExcel> groupIntegerInformation();

    @Query(value = "SELECT source FROM model_excel GROUP BY source;", nativeQuery = true)
    List<ModelExcel> getSourceFromDatabase();

}
