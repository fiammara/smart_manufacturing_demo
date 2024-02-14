package com.manufacture.expertservice.repository;

import com.manufacture.expertservice.model.ExcelData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelData, Long> {

}
