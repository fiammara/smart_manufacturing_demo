package com.manufacture.expertservice.repository;


import com.manufacture.expertservice.model.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingEntityRepository extends JpaRepository<TrainingEntity, Long> {
    TrainingEntity findTop1ByCompanyOrderByIdDesc(String company);


}
