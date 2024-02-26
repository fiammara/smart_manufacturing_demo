package com.manufacture.expertservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experttest")
public class ExpertTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String bendrosop1_min;
    private String bendrosop1_true;
    private String bendrosop1_max;
    private String bendrosop2_min;
    private String bendrosop2_true;
    private String bendrosop2_max;
    private String bendrosop3_min;
    private String bendrosop3_true;
    private String bendrosop3_max;
    private String bendrosop4_min;
    private String bendrosop4_true;
    private String bendrosop4_max;

    private String opkoef1_min;
    private String opkoef1_true;
    private String opkoef1_max;
    private String opkoef2_min;
    private String opkoef2_true;
    private String opkoef2_max;
    private String opkoef3_min;
    private String opkoef3_true;
    private String opkoef3_max;
    private String opkoef4_min;
    private String opkoef4_true;
    private String opkoef4_max;


    private String kainos1_min;
    private String kainos1_true;
    private String kainos1_max;
    private String kainos2_min;
    private String kainos2_true;
    private String kainos2_max;
    private String kainos3_min;
    private String kainos3_true;
    private String kainos3_max;
    private String kainos4_min;
    private String kainos4_true;
    private String kainos4_max;

    private String darbai1_min;
    private String darbai1_true;
    private String darbai1_max;
    private String darbai2_min;
    private String darbai2_true;
    private String darbai2_max;
    private String darbai3_min;
    private String darbai3_true;
    private String darbai3_max;
    private String darbai4_min;
    private String darbai4_true;
    private String darbai4_max;


}
