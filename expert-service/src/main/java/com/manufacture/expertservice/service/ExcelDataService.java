package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExcelData;
import com.manufacture.expertservice.model.UzsakymoForma;
import org.json.JSONObject;

import java.util.List;

public interface ExcelDataService {
    List<ExcelData> findAllExcelData();
    long addExcelData(ExcelData excelData);
    long addExcelOrderData(UzsakymoForma uzsakymoForma);
    String tryLearning(JSONObject usernested);
}
