package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.TrainEntity;
import com.manufacture.expertservice.repository.TrainingEntityRepository;
import com.manufacture.expertservice.service.TrainEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainEntityServiceImpl implements TrainEntityService {

    @Autowired
    private TrainingEntityRepository trainEntityRepository;

    @Override
    public List<TrainEntity> findAllTrainEntities() {

        return trainEntityRepository.findAll();
    }

    @Override
    public void addTrainEntity(TrainEntity entity) {

        trainEntityRepository.save(entity);

    }

    @Override
    public TrainEntity findTrainEntity(String company) {

        TrainEntity ent = trainEntityRepository.findTop1ByCompanyOrderByIdDesc(company);
        return ent;

    }
}
