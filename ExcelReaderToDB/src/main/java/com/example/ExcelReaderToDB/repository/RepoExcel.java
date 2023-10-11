package com.example.ExcelReaderToDB.repository;

import com.example.ExcelReaderToDB.model.ModelExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface RepoExcel extends JpaRepository<ModelExcel, Long> {



}
