package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.OrderFormRepository;
import com.manufacture.expertservice.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderFormServiceImpl implements OrderFormService {

    @Autowired
    private OrderFormRepository uzsakymoFormaRepository;

    @Override
    public List<UzsakymoForma> findAllForms() {

        return uzsakymoFormaRepository.findAll();
    }

    @Override
    public void addOrderForm(UzsakymoForma uzsakymoForma) {

        uzsakymoFormaRepository.save(uzsakymoForma);

    }

    @Override
    public Optional<UzsakymoForma> getById(long id) {

        return uzsakymoFormaRepository.findById(id);

    }
}