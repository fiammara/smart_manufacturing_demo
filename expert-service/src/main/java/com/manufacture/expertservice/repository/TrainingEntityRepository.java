package com.manufacture.expertservice.repository;


import com.manufacture.expertservice.model.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingEntityRepository extends JpaRepository<TrainEntity, Long> {
    TrainEntity findTop1ByCompanyOrderByIdDesc(String company);


}
