package com.manufacture.expertservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private String price;

    private String expertName;
    private String submit_date;
    @Transient
    private String expertNameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertrequest_id")
    @JsonIgnore
    private ExpertRequest expertrequest;

}
