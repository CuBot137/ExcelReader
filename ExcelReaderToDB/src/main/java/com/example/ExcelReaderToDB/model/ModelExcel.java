package com.example.ExcelReaderToDB.model;


import jakarta.persistence.*;
import lombok.*;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;



@Entity
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ModelExcel {

    @Id
    @Column
    private String source;
    @Column
    private String size4000;
    @Column
    private String size3500;
    @Column
    private String size2600;





}
