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
    private int size4000;
    @Column
    private int size3500;
    @Column
    private int size2600;
    @Column
    private int hours;
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
