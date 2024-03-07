package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.ExcelData;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.ExcelDataRepository;
import com.manufacture.expertservice.repository.OrderFormRepository;
import com.manufacture.expertservice.service.ExcelDataService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ExcelDataServiceImpl implements ExcelDataService {

    @Autowired
    private ExcelDataRepository excelDataRepository;
    @Autowired
    private OrderFormRepository orderFormRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<ExcelData> findAllExcelData() {

        return excelDataRepository.findAll();
    }

    @Override
    public long addExcelData(ExcelData excelData) {

        return excelDataRepository.saveAndFlush(excelData).getId();

    }
    @Override
    public long addExcelOrderData(UzsakymoForma uzsakymoForma) {

        return orderFormRepository.saveAndFlush(uzsakymoForma).getId();

    }
    @Override
    public String tryLearning(JSONObject usernested) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(usernested.toString(), headers);        //HttpEntity<JSONArray> entity = new HttpEntity<JSONArray>(usernested, headers);

        return restTemplate.postForObject("http://158.129.140.156:5000/train", entity, String.class);
    }
}
