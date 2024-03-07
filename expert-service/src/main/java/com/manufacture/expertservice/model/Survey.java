package com.manufacture.expertservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customer_id;

    private String submit_date;

    private String customer;

    private String technology;
    @Transient
    private ArrayList<String> technologyF;

    private String size;
    private String wood;
    private String metal;
    private String glass;
    private String wiring;
    @Transient
    private ArrayList<String> woodF;
    @Transient
    private ArrayList<String> metalF;
    @Transient
    private ArrayList<String> glassF;
    @Transient
    private ArrayList<String> wiringF;

}
