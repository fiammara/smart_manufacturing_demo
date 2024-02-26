package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.TrainingEntity;

import java.util.List;

public interface TrainingEntityService {
    List<TrainingEntity> findAllTrainEntities();

    void addTrainEntity(TrainingEntity entity);

    TrainingEntity findTrainEntity(String company);
}
