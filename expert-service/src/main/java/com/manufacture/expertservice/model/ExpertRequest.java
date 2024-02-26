package com.manufacture.expertservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.manufacture.identityservice.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

/*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "request_expertid",
        joinColumns = @JoinColumn(name = "request_id"),
        inverseJoinColumns = @JoinColumn(name = "expert_id"))
    private Set<User> experts = new HashSet<>(); */


    @OneToMany(cascade = CascadeType.ALL)

    private Set<Evaluation> evaluations = new HashSet<>();

    private String submit_date;

    public String companyid;

    public long lastFormToEvaluateId;

}
