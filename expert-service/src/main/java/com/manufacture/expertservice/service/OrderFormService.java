package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.UzsakymoForma;

import java.util.List;
import java.util.Optional;

public interface OrderFormService {
    List<UzsakymoForma> findAllForms();

    void addOrderForm(UzsakymoForma uzsakymoForma);

    Optional<UzsakymoForma> getById(long id);
}
