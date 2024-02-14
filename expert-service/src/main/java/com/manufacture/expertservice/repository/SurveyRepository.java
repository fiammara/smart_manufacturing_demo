package com.manufacture.expertservice.repository;

import com.manufacture.expertservice.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
