package com.manufacture.expertservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expertrequest")
public class ExpertRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @Transient
    private ArrayList<String> selectedOptions;
    private String text;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "request_expertid",
        joinColumns = @JoinColumn(name = "request_id"),
        inverseJoinColumns = @JoinColumn(name = "expert_id"))
    private Set<User> experts = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL
      //  fetch = FetchType.LAZY,
      //  mappedBy = "expertrequest"
    )

    private Set<Evaluation> evaluations = new HashSet<>();

    private String submit_date;

    public String companyid;

    public long lastFormToEvaluateId;



}
