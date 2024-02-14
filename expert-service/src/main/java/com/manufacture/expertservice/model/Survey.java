package com.manufacture.expertservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    //   @JsonFormat(pattern="dd/MM/yyyy")
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
