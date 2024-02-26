package com.manufacture.expertservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "excel")
public class ExcelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uzsakymas;
    private double m10;
    private double m20;
    private double m30;
    private double m40;
    private double m50;
    private double m60;
    private double m70;
    private double m80;
    private double w10;
    private double w20;
    private double w30;
    private double w40;
    private double w45;
    private double w50;
    private double w55;
    private double w60;
    private double w70;
    private double w80;
    private double w90;
    private double w100;
    private double kiekis;
    private double kaina;
    private double medz_kaina;
    private double skirtingu_medz_kiekis;
    private double daliu_kiekis;
    private double skirtingu_daliu_kiekis;
    private double vn;
    private double m;
    private double m2;
    private double m3;
    private double kg;
    private double kp;
    private double l;
    private double furn_type;
    private String fixed_customer;

}
