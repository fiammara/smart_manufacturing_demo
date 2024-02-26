package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.TrainingEntity;
import com.manufacture.expertservice.repository.TrainingEntityRepository;
import com.manufacture.expertservice.service.TrainingEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainingEntityServiceImpl implements TrainingEntityService {

    @Autowired
    private TrainingEntityRepository trainEntityRepository;

    @Override
    public List<TrainingEntity> findAllTrainEntities() {

        return trainEntityRepository.findAll();
    }

    @Override
    public void addTrainEntity(TrainingEntity entity) {

        trainEntityRepository.save(entity);

    }

    @Override
    public TrainingEntity findTrainEntity(String company) {

        TrainingEntity ent = trainEntityRepository.findTop1ByCompanyOrderByIdDesc(company);
        return ent;

    }
}
