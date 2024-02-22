package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.TrainEntity;

import java.util.List;

public interface TrainEntityService {
    List<TrainEntity> findAllTrainEntities();

    void addTrainEntity(TrainEntity entity);

    TrainEntity findTrainEntity(String company);
}
