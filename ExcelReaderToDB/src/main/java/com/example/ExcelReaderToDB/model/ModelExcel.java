package com.example.ExcelReaderToDB.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModelExcel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private String docketNumber;
    @Column
    private String date;

    @Column
    private String source;
    @Column
    private String size4000;
    @Column
    private String size3500;
    @Column
    private String size2600;
    @Column
    private String hours;
    @Column
    private String destination;
    @Column
    private String Hauliers;
}
