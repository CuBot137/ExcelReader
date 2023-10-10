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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column
    private String docketNumber;
    @Column
    private String date;

    @Column
    private String source;
    @Column
    private double size4000;
    @Column
    private double size3500;
    @Column
    private double size2600;
    @Column
    private double hours;
    //@Column
   // private String destination;
    @Column
    private String hauliers;


    public void setSize4000(Cell cell) {
    }

    public void setSize3500(Cell cell){
    }
    public void setSize2600(Cell cell){
    }
}
