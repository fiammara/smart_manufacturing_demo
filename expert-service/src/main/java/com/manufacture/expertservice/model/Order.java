package com.manufacture.expertservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String order_id;
    private String invoice_ad;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date invoice_date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date pay_date;
    private Long id_seller;
    private Long id_client;
    private float price_net;
    private float price;
    private String currency;
    private String source;



}
