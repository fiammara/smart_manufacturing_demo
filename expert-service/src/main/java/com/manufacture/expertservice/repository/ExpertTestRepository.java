package com.manufacture.expertservice.repository;


import com.manufacture.expertservice.model.ExpertTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpertTestRepository extends JpaRepository<ExpertTest, Long> {

}