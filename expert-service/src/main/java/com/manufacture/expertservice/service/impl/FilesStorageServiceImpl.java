package com.manufacture.expertservice.service.impl;

import com.manufacture.expertservice.model.ExcelData;
import com.manufacture.expertservice.model.TrainEntity;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.repository.ExcelDataRepository;
import com.manufacture.expertservice.service.FilesStorageService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ExcelDataServiceImpl excelDataService;
    @Autowired
    private ExcelDataRepository excelDataRepository;

    @Autowired
    private TrainEntityServiceImpl trainEntityService;
    @Autowired
    OrderFormServiceImpl uzsakymoFormaService;
    private final Path root = Paths.get("uploads");

    // private List<ExcelDataDTO> postdata = new ArrayList<ExcelDataDTO>();

    public static JSONArray usersjson = new JSONArray();
    public static JSONObject usernested = new JSONObject();
    public static String jsonStr = "...";
    public static JSONObject orderTempJson = new JSONObject();
    public static JSONObject orderTempJson1 = new JSONObject();
    public static String foundTrainModelId = null;
    public static JSONArray predictdatafinalvalueArr = new JSONArray();
    public static String price = null;

    @Override
    public void init() {
        try {

            Files.createDirectory(root);
            // Files.createDirectory(root1);

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            // saveToDatabase(file);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public String saveOrder(MultipartFile file, String userid) {
        //System.out.println ("fserviceuserid "+userid);
        try {
            // saveOrderToDatabase(file);
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            InputStream inputStream2 = new BufferedInputStream(file.getInputStream());

            Workbook workbook2 = new XSSFWorkbook(inputStream2);

            DataFormatter df1 = new DataFormatter();

            Cell c0 = workbook2.getSheetAt(0).getRow(1).getCell(0);
            Cell c1 = workbook2.getSheetAt(0).getRow(1).getCell(1);
            Cell c2 = workbook2.getSheetAt(0).getRow(1).getCell(2);
            Cell c3 = workbook2.getSheetAt(0).getRow(1).getCell(3);
            Cell c4 = workbook2.getSheetAt(0).getRow(1).getCell(4);
            Cell c5 = workbook2.getSheetAt(0).getRow(1).getCell(5);
            Cell c6 = workbook2.getSheetAt(0).getRow(1).getCell(6);
            Cell c7 = workbook2.getSheetAt(0).getRow(1).getCell(7);
            Cell c8 = workbook2.getSheetAt(0).getRow(1).getCell(8);
            Cell c9 = workbook2.getSheetAt(0).getRow(1).getCell(9);
            Cell c10 = workbook2.getSheetAt(0).getRow(1).getCell(10);
            Cell c11 = workbook2.getSheetAt(0).getRow(1).getCell(11);
            Cell c12 = workbook2.getSheetAt(0).getRow(1).getCell(12);
            Cell c13 = workbook2.getSheetAt(0).getRow(1).getCell(13);
            Cell c14 = workbook2.getSheetAt(0).getRow(1).getCell(14);
            Cell c15 = workbook2.getSheetAt(0).getRow(1).getCell(15);
            Cell c16 = workbook2.getSheetAt(0).getRow(1).getCell(16);
            Cell c17 = workbook2.getSheetAt(0).getRow(1).getCell(17);
            Cell c18 = workbook2.getSheetAt(0).getRow(1).getCell(18);
            Cell c19 = workbook2.getSheetAt(0).getRow(1).getCell(19);
            Cell c20 = workbook2.getSheetAt(0).getRow(1).getCell(20);
            Cell c21 = workbook2.getSheetAt(0).getRow(1).getCell(21);
            Cell c22 = workbook2.getSheetAt(0).getRow(1).getCell(22);
            Cell c23 = workbook2.getSheetAt(0).getRow(1).getCell(23);
            Cell c24 = workbook2.getSheetAt(0).getRow(1).getCell(24);
            Cell c25 = workbook2.getSheetAt(0).getRow(1).getCell(25);
            Cell c26 = workbook2.getSheetAt(0).getRow(1).getCell(26);
            Cell c27 = workbook2.getSheetAt(0).getRow(1).getCell(27);
            Cell c28 = workbook2.getSheetAt(0).getRow(1).getCell(28);
            Cell c29 = workbook2.getSheetAt(0).getRow(1).getCell(29);
            Cell c30 = workbook2.getSheetAt(0).getRow(1).getCell(30);
            Cell c31 = workbook2.getSheetAt(0).getRow(1).getCell(31);
            Cell c32 = workbook2.getSheetAt(0).getRow(1).getCell(32);
            Cell c33 = workbook2.getSheetAt(0).getRow(1).getCell(33);
            Cell c34 = workbook2.getSheetAt(0).getRow(1).getCell(34);

            String value0 = df1.formatCellValue(c0);
            String value1 = df1.formatCellValue(c1);
            String value2 = df1.formatCellValue(c2);
            String value3 = df1.formatCellValue(c3);
            String value4 = df1.formatCellValue(c4);
            String value5 = df1.formatCellValue(c5);
            String value6 = df1.formatCellValue(c6);
            String value7 = df1.formatCellValue(c7);
            String value8 = df1.formatCellValue(c8);
            String value9 = df1.formatCellValue(c9);
            String value10 = df1.formatCellValue(c10);
            String value11 = df1.formatCellValue(c11);
            String value12 = df1.formatCellValue(c12);
            String value13 = df1.formatCellValue(c13);
            String value14 = df1.formatCellValue(c14);
            String value15 = df1.formatCellValue(c15);
            String value16 = df1.formatCellValue(c16);
            String value17 = df1.formatCellValue(c17);
            String value18 = df1.formatCellValue(c18);
            String value19 = df1.formatCellValue(c19);
            String value20 = df1.formatCellValue(c20);
            String value21 = df1.formatCellValue(c21);
            String value22 = df1.formatCellValue(c22);
            String value23 = df1.formatCellValue(c23);
            String value24 = df1.formatCellValue(c24);
            String value25 = df1.formatCellValue(c25);
            String value26 = df1.formatCellValue(c26);
            String value27 = df1.formatCellValue(c27);
            String value28 = df1.formatCellValue(c28);
            String value29 = df1.formatCellValue(c29);
            String value30 = df1.formatCellValue(c30);
            String value31 = df1.formatCellValue(c31);
            String value32 = df1.formatCellValue(c32);
            String value33 = df1.formatCellValue(c33);
            String value34 = df1.formatCellValue(c34);

            UzsakymoForma edata = new UzsakymoForma();
            edata.setCompanyid(userid);
            edata.setUzsakymas(value0);
            edata.setM10(Double.valueOf(value1));
            edata.setM20(Double.valueOf(value2));
            edata.setM30(Double.valueOf(value3));
            edata.setM40(Double.valueOf(value4));
            edata.setM50(Double.valueOf(value5));
            edata.setM60(Double.valueOf(value6));
            edata.setM70(Double.valueOf(value7));
            edata.setM80(Double.valueOf(value8));
            edata.setW10(Double.valueOf(value9));
            edata.setW20(Double.valueOf(value10));
            edata.setW30(Double.valueOf(value11));
            edata.setW40(Double.valueOf(value12));
            edata.setW45(Double.valueOf(value13));
            edata.setW50(Double.valueOf(value14));
            edata.setW55(Double.valueOf(value15));
            edata.setW60(Double.valueOf(value16));
            edata.setW70(Double.valueOf(value17));
            edata.setW80(Double.valueOf(value18));
            edata.setW90(Double.valueOf(value19));
            edata.setW100(Double.valueOf(value20));
            edata.setKiekis(Double.valueOf(value21));
            edata.setMedz_kaina(Double.valueOf(value22));
            edata.setSkirtingu_medz_kiekis(Double.valueOf(value23));
            edata.setDaliu_kiekis(Double.valueOf(value24));
            edata.setSkirtingu_daliu_kiekis(Double.valueOf(value25));
            edata.setVn(Double.valueOf(value26));
            edata.setM(Double.valueOf(value27));
            edata.setM2(Double.valueOf(value28));
            edata.setM3(Double.valueOf(value29));
            edata.setKg(Double.valueOf(value30));
            edata.setKp(Double.valueOf(value31));
            edata.setL(Double.valueOf(value32));
            edata.setFurn_type(Double.valueOf(value33));
            edata.setFixed_customer(value34);

            long idofobj = excelDataService.addExcelOrderData(edata);
            //System.out.println("idofobj" + idofobj);
            Field changeMap = orderTempJson.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);

            changeMap.set(orderTempJson, new LinkedHashMap<>());

            changeMap.setAccessible(false);

            orderTempJson.put("uzsakymas", value0);
            orderTempJson.put("m10", value1);
            orderTempJson.put("m20", value2);
            orderTempJson.put("m30", value3);
            orderTempJson.put("m40", value4);
            orderTempJson.put("m50", value5);
            orderTempJson.put("m60", value6);
            orderTempJson.put("m70", value7);
            orderTempJson.put("m80", value8);
            orderTempJson.put("w10", value9);
            orderTempJson.put("w20", value10);
            orderTempJson.put("w30", value11);
            orderTempJson.put("w40", value12);
            orderTempJson.put("w45", value13);
            orderTempJson.put("w50", value14);
            orderTempJson.put("w55", value15);
            orderTempJson.put("w60", value16);
            orderTempJson.put("w70", value17);
            orderTempJson.put("w80", value18);
            orderTempJson.put("w90", value19);
            orderTempJson.put("w100", value20);
            orderTempJson.put("kiekis", value21);
            orderTempJson.put("medz_kaina", value22);
            orderTempJson.put("skirtingu_medz_kiekis", value23);
            orderTempJson.put("daliu_kiekis", value24);
            orderTempJson.put("skirtingu_daliu_kiekis", value25);
            orderTempJson.put("vn", value26);
            orderTempJson.put("m", value27);
            orderTempJson.put("m2", value28);
            orderTempJson.put("m3", value29);
            orderTempJson.put("kg", value30);
            orderTempJson.put("kp", value31);
            orderTempJson.put("l", value32);
            orderTempJson.put("furn_type", value33);
            orderTempJson.put("fixed_customer", value34);
            orderTempJson1.put(String.valueOf(idofobj), orderTempJson);
            //System.out.println("nestedjson: " + orderTempJson1.toString());
            //	workbook2.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return orderTempJson1.toString();
    }

    /*
     * private void saveToDatabase(MultipartFile file) throws JSONException { try {
     * InputStream inputStream = new BufferedInputStream(file.getInputStream());
     *
     * Workbook workbook = new XSSFWorkbook(inputStream); Sheet spreadsheet =
     * workbook.getSheetAt(0);
     *
     * DataFormatter df1 = new DataFormatter(); for (int rowNumber = 1; rowNumber <
     * spreadsheet.getLastRowNum() + 1; rowNumber++) {
     *
     * Cell c0 = workbook.getSheetAt(0).getRow(rowNumber).getCell(0); Cell c1 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(1); Cell c2 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(2); Cell c3 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(3); Cell c4 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(4); Cell c5 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(5); Cell c6 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(6); Cell c7 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(7); Cell c8 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(8); Cell c9 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(9); Cell c10 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(10); Cell c11 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(11); Cell c12 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(12); Cell c13 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(13); Cell c14 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(14); Cell c15 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(15); Cell c16 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(16); Cell c17 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(17); Cell c18 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(18); Cell c19 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(19); Cell c20 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(20); Cell c21 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(21); Cell c22 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(22); Cell c23 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(23); Cell c24 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(24); Cell c25 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(25); Cell c26 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(26); Cell c27 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(27); Cell c28 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(28); Cell c29 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(29); Cell c30 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(30); Cell c31 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(31); Cell c32 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(32); Cell c33 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(33); Cell c34 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(34); Cell c35 =
     * workbook.getSheetAt(0).getRow(rowNumber).getCell(35);
     *
     * String value0 = df1.formatCellValue(c0); String value1 =
     * df1.formatCellValue(c1); String value2 = df1.formatCellValue(c2); String
     * value3 = df1.formatCellValue(c3); String value4 = df1.formatCellValue(c4);
     * String value5 = df1.formatCellValue(c5); String value6 =
     * df1.formatCellValue(c6); String value7 = df1.formatCellValue(c7); String
     * value8 = df1.formatCellValue(c8); String value9 = df1.formatCellValue(c9);
     * String value10 = df1.formatCellValue(c10); String value11 =
     * df1.formatCellValue(c11); String value12 = df1.formatCellValue(c12); String
     * value13 = df1.formatCellValue(c13); String value14 =
     * df1.formatCellValue(c14); String value15 = df1.formatCellValue(c15); String
     * value16 = df1.formatCellValue(c16); String value17 =
     * df1.formatCellValue(c17); String value18 = df1.formatCellValue(c18); String
     * value19 = df1.formatCellValue(c19); String value20 =
     * df1.formatCellValue(c20); String value21 = df1.formatCellValue(c21); String
     * value22 = df1.formatCellValue(c22); String value23 =
     * df1.formatCellValue(c23); String value24 = df1.formatCellValue(c24); String
     * value25 = df1.formatCellValue(c25); String value26 =
     * df1.formatCellValue(c26); String value27 = df1.formatCellValue(c27); String
     * value28 = df1.formatCellValue(c28); String value29 =
     * df1.formatCellValue(c29); String value30 = df1.formatCellValue(c30); String
     * value31 = df1.formatCellValue(c31); String value32 =
     * df1.formatCellValue(c32); String value33 = df1.formatCellValue(c33); String
     * value34 = df1.formatCellValue(c34); String value35 =
     * df1.formatCellValue(c35);
     *
     * ExcelData edata = new ExcelData(); edata.setUzsakymas(value0);
     * edata.setM10(Double.valueOf(value1)); edata.setM20(Double.valueOf(value2));
     * edata.setM30(Double.valueOf(value3)); edata.setM40(Double.valueOf(value4));
     * edata.setM50(Double.valueOf(value5)); edata.setM60(Double.valueOf(value6));
     * edata.setM70(Double.valueOf(value7)); edata.setM80(Double.valueOf(value8));
     * edata.setW10(Double.valueOf(value9)); edata.setW20(Double.valueOf(value10));
     * edata.setW30(Double.valueOf(value11)); edata.setW40(Double.valueOf(value12));
     * edata.setW45(Double.valueOf(value13)); edata.setW50(Double.valueOf(value14));
     * edata.setW55(Double.valueOf(value15)); edata.setW60(Double.valueOf(value16));
     * edata.setW70(Double.valueOf(value17)); edata.setW80(Double.valueOf(value18));
     * edata.setW90(Double.valueOf(value19));
     * edata.setW100(Double.valueOf(value20));
     * edata.setKiekis(Double.valueOf(value21));
     * edata.setKaina(Double.valueOf(value22));
     * edata.setMedz_kaina(Double.valueOf(value23));
     * edata.setSkirtingu_medz_kiekis(Double.valueOf(value24));
     * edata.setDaliu_kiekis(Double.valueOf(value25));
     * edata.setSkirtingu_daliu_kiekis(Double.valueOf(value26));
     * edata.setVn(Double.valueOf(value27)); edata.setM(Double.valueOf(value28));
     * edata.setM2(Double.valueOf(value29)); edata.setM3(Double.valueOf(value30));
     * edata.setKg(Double.valueOf(value31)); edata.setKp(Double.valueOf(value32));
     * edata.setL(Double.valueOf(value33));
     * edata.setFurn_type(Double.valueOf(value34));
     * edata.setFixed_customer(value35);
     *
     *
     *
     * JSONObject user = new JSONObject();
     *
     * try { Field changeMap = user.getClass().getDeclaredField("map");
     * changeMap.setAccessible(true); try { changeMap.set(user, new
     * LinkedHashMap<>()); } catch (IllegalArgumentException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } catch
     * (IllegalAccessException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } changeMap.setAccessible(false);
     *
     * } catch (NoSuchFieldException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); } catch (SecurityException e) { // TODO Auto-generated
     * catch block e.printStackTrace(); }
     *
     * user.put("uzsakymas", value0); user.put("m10", value1); user.put("m20",
     * value2); user.put("m30", value3); user.put("m40", value4); user.put("m50",
     * value5); user.put("m60", value6); user.put("m70", value7); user.put("m80",
     * value8); user.put("w10", value9); user.put("w20", value10); user.put("w30",
     * value11); user.put("w40", value12); user.put("w45", value13); user.put("w50",
     * value14); user.put("w55", value15); user.put("w60", value16); user.put("w70",
     * value17); user.put("w80", value18); user.put("w90", value19);
     * user.put("w100", value20); user.put("kiekis", value21); user.put("kaina",
     * value22); user.put("medz_kaina", value23); user.put("skirtingu_medz_kiekis",
     * value24); user.put("daliu_kiekis", value25);
     * user.put("skirtingu_daliu_kiekis", value26); user.put("vn", value27);
     * user.put("m", value28); user.put("m2", value29); user.put("m3", value30);
     * user.put("kg", value31); user.put("kp", value32); user.put("l", value33);
     * user.put("furn_type", value34); user.put("fixed_customer", value35);
     *
     * usersjson.put(user);
     *
     * excelDataService.addExcelData(edata); }
     *
     * usernested.put("train_data", usersjson);
     *
     * excelDataService.tryLearning(usernested);
     *
     * workbook.close();
     *
     * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
     * e) { e.printStackTrace(); } }
     */

    private void saveOrderToDatabase(MultipartFile file) {

        try {
            InputStream inputStream2 = new BufferedInputStream(file.getInputStream());

            Workbook workbook2 = new XSSFWorkbook(inputStream2);

            DataFormatter df1 = new DataFormatter();

            Cell c0 = workbook2.getSheetAt(0).getRow(1).getCell(0);
            Cell c1 = workbook2.getSheetAt(0).getRow(1).getCell(1);
            Cell c2 = workbook2.getSheetAt(0).getRow(1).getCell(2);
            Cell c3 = workbook2.getSheetAt(0).getRow(1).getCell(3);
            Cell c4 = workbook2.getSheetAt(0).getRow(1).getCell(4);
            Cell c5 = workbook2.getSheetAt(0).getRow(1).getCell(5);
            Cell c6 = workbook2.getSheetAt(0).getRow(1).getCell(6);
            Cell c7 = workbook2.getSheetAt(0).getRow(1).getCell(7);
            Cell c8 = workbook2.getSheetAt(0).getRow(1).getCell(8);
            Cell c9 = workbook2.getSheetAt(0).getRow(1).getCell(9);
            Cell c10 = workbook2.getSheetAt(0).getRow(1).getCell(10);
            Cell c11 = workbook2.getSheetAt(0).getRow(1).getCell(11);
            Cell c12 = workbook2.getSheetAt(0).getRow(1).getCell(12);
            Cell c13 = workbook2.getSheetAt(0).getRow(1).getCell(13);
            Cell c14 = workbook2.getSheetAt(0).getRow(1).getCell(14);
            Cell c15 = workbook2.getSheetAt(0).getRow(1).getCell(15);
            Cell c16 = workbook2.getSheetAt(0).getRow(1).getCell(16);
            Cell c17 = workbook2.getSheetAt(0).getRow(1).getCell(17);
            Cell c18 = workbook2.getSheetAt(0).getRow(1).getCell(18);
            Cell c19 = workbook2.getSheetAt(0).getRow(1).getCell(19);
            Cell c20 = workbook2.getSheetAt(0).getRow(1).getCell(20);
            Cell c21 = workbook2.getSheetAt(0).getRow(1).getCell(21);
            Cell c22 = workbook2.getSheetAt(0).getRow(1).getCell(22);
            Cell c23 = workbook2.getSheetAt(0).getRow(1).getCell(23);
            Cell c24 = workbook2.getSheetAt(0).getRow(1).getCell(24);
            Cell c25 = workbook2.getSheetAt(0).getRow(1).getCell(25);
            Cell c26 = workbook2.getSheetAt(0).getRow(1).getCell(26);
            Cell c27 = workbook2.getSheetAt(0).getRow(1).getCell(27);
            Cell c28 = workbook2.getSheetAt(0).getRow(1).getCell(28);
            Cell c29 = workbook2.getSheetAt(0).getRow(1).getCell(29);
            Cell c30 = workbook2.getSheetAt(0).getRow(1).getCell(30);
            Cell c31 = workbook2.getSheetAt(0).getRow(1).getCell(31);
            Cell c32 = workbook2.getSheetAt(0).getRow(1).getCell(32);
            Cell c33 = workbook2.getSheetAt(0).getRow(1).getCell(33);
            Cell c34 = workbook2.getSheetAt(0).getRow(1).getCell(34);

            String value0 = df1.formatCellValue(c0);
            String value1 = df1.formatCellValue(c1);
            String value2 = df1.formatCellValue(c2);
            String value3 = df1.formatCellValue(c3);
            String value4 = df1.formatCellValue(c4);
            String value5 = df1.formatCellValue(c5);
            String value6 = df1.formatCellValue(c6);
            String value7 = df1.formatCellValue(c7);
            String value8 = df1.formatCellValue(c8);
            String value9 = df1.formatCellValue(c9);
            String value10 = df1.formatCellValue(c10);
            String value11 = df1.formatCellValue(c11);
            String value12 = df1.formatCellValue(c12);
            String value13 = df1.formatCellValue(c13);
            String value14 = df1.formatCellValue(c14);
            String value15 = df1.formatCellValue(c15);
            String value16 = df1.formatCellValue(c16);
            String value17 = df1.formatCellValue(c17);
            String value18 = df1.formatCellValue(c18);
            String value19 = df1.formatCellValue(c19);
            String value20 = df1.formatCellValue(c20);
            String value21 = df1.formatCellValue(c21);
            String value22 = df1.formatCellValue(c22);
            String value23 = df1.formatCellValue(c23);
            String value24 = df1.formatCellValue(c24);
            String value25 = df1.formatCellValue(c25);
            String value26 = df1.formatCellValue(c26);
            String value27 = df1.formatCellValue(c27);
            String value28 = df1.formatCellValue(c28);
            String value29 = df1.formatCellValue(c29);
            String value30 = df1.formatCellValue(c30);
            String value31 = df1.formatCellValue(c31);
            String value32 = df1.formatCellValue(c32);
            String value33 = df1.formatCellValue(c33);
            String value34 = df1.formatCellValue(c34);

            UzsakymoForma edata = new UzsakymoForma();
            edata.setUzsakymas(value0);
            edata.setM10(Double.valueOf(value1));
            edata.setM20(Double.valueOf(value2));
            edata.setM30(Double.valueOf(value3));
            edata.setM40(Double.valueOf(value4));
            edata.setM50(Double.valueOf(value5));
            edata.setM60(Double.valueOf(value6));
            edata.setM70(Double.valueOf(value7));
            edata.setM80(Double.valueOf(value8));
            edata.setW10(Double.valueOf(value9));
            edata.setW20(Double.valueOf(value10));
            edata.setW30(Double.valueOf(value11));
            edata.setW40(Double.valueOf(value12));
            edata.setW45(Double.valueOf(value13));
            edata.setW50(Double.valueOf(value14));
            edata.setW55(Double.valueOf(value15));
            edata.setW60(Double.valueOf(value16));
            edata.setW70(Double.valueOf(value17));
            edata.setW80(Double.valueOf(value18));
            edata.setW90(Double.valueOf(value19));
            edata.setW100(Double.valueOf(value20));
            edata.setKiekis(Double.valueOf(value21));
            edata.setMedz_kaina(Double.valueOf(value22));
            edata.setSkirtingu_medz_kiekis(Double.valueOf(value23));
            edata.setDaliu_kiekis(Double.valueOf(value24));
            edata.setSkirtingu_daliu_kiekis(Double.valueOf(value25));
            edata.setVn(Double.valueOf(value26));
            edata.setM(Double.valueOf(value27));
            edata.setM2(Double.valueOf(value28));
            edata.setM3(Double.valueOf(value29));
            edata.setKg(Double.valueOf(value30));
            edata.setKp(Double.valueOf(value31));
            edata.setL(Double.valueOf(value32));
            edata.setFurn_type(Double.valueOf(value33));
            edata.setFixed_customer(value34);

            excelDataService.addExcelOrderData(edata);

            //	workbook2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public Stream<Path> loadAllFilesOrders() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    public List<ExcelData> findAllUploads() {

        return excelDataRepository.findAll();
    }

    /*
     * @Override public String usePython(String submitId) throws IOException { //
     * System.out.println (submitId); String arg1 = submitId;
     *
     * String pythonScriptPath =
     * "/home/rasa/Documents/SG2/gamyba/polling-app-server/src/main/resources/script1.py";
     * String[] cmd = new String[3]; cmd[0] = "python3"; cmd[1] = pythonScriptPath;
     * cmd[2] = arg1; // create runtime to execute external command Runtime rt =
     * Runtime.getRuntime(); Process pr = rt.exec(cmd);
     *
     * // retrieve output from python script BufferedReader bfr = new
     * BufferedReader(new InputStreamReader(pr.getInputStream())); String line = "";
     * while ((line = bfr.readLine()) != null) { // display each output line form
     * python script System.out.println(line); } return submitId;
     *
     * }
     */

    @Override
    public String getPrice(String orderid, String companyId, String predict_data) {
        foundTrainModelId = trainEntityService.findTrainEntity(companyId).getModel_id();

        try {
            JSONObject predictdataarrvalue = new JSONObject(predict_data).getJSONObject("predict_data");

		/*	JSONObject objOrder = new JSONObject();
			objOrder.put("uzsakymas", value.getString("uzsakymas"));
			objOrder.put("m10", value.getString("m10"));
			objOrder.put("m20", value.getString("m20"));
			objOrder.put("m30", value.getString("m30"));
			objOrder.put("m40", value.getString("m40"));
			objOrder.put("m50", value.getString("m50"));
			objOrder.put("m60", value.getString("m60"));
			objOrder.put("m70", value.getString("m70"));
			objOrder.put("m80", value.getString("m80"));
			objOrder.put("w10", value.getString("w10"));
			objOrder.put("w20", value.getString("w20"));
			objOrder.put("w30", value.getString("w30"));
			objOrder.put("w40", value.getString("w40"));
			objOrder.put("w45", value.getString("w45"));
			objOrder.put("w50", value.getString("w50"));
			objOrder.put("w55", value.getString("w55"));
			objOrder.put("w60", value.getString("w60"));
			objOrder.put("w70", value.getString("w70"));
			objOrder.put("w80", value.getString("w80"));
			objOrder.put("w90", value.getString("w90"));
			objOrder.put("w100", value.getString("w100"));
			objOrder.put("kiekis", value.getString("kiekis"));
			objOrder.put("medz_kaina", value.getString("medz_kaina"));
			objOrder.put("skirtingu_medz_kiekis", value.getString("skirtingu_medz_kiekis"));
			objOrder.put("daliu_kiekis", value.getString("daliu_kiekis"));
			objOrder.put("skirtingu_daliu_kiekis", value.getString("skirtingu_daliu_kiekis"));
			objOrder.put("vn", value.getString("vn"));
			objOrder.put("m", value.getString("m"));
			objOrder.put("m2", value.getString("m2"));
			objOrder.put("m3", value.getString("m3"));
			objOrder.put("kg", value.getString("kg"));
			objOrder.put("kp", value.getString("kp"));
			objOrder.put("l", value.getString("l"));
			objOrder.put("furn_type", value.getString("furn_type"));
			objOrder.put("fixed_customer", value.getString("fixed_customer")); */

            predictdatafinalvalueArr.put(predictdataarrvalue);

        } catch (JSONException e3) {
            // TODO Auto-generated catch block
            e3.printStackTrace();
        }

        JSONObject test = new JSONObject();

        try {
            Field changeMap;
            changeMap = test.getClass().getDeclaredField("map");
            changeMap.setAccessible(true);

            try {
                changeMap.set(test, new LinkedHashMap<>());
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            changeMap.setAccessible(false);
        } catch (NoSuchFieldException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } catch (SecurityException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {
            test.put("model_id", foundTrainModelId);
            test.put("predict_data", predictdatafinalvalueArr);
        } catch (JSONException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(test.toString(), headers);

        String restt = restTemplate.postForObject("http://158.129.140.156:5000/predict", entity, String.class);

        try {
            String priceJson = new JSONObject(restt).getString("ML_kaina");

            price = new JSONObject(priceJson).getString("0");
            System.out.println(price);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        UzsakymoForma uzs = uzsakymoFormaService.getById(Long.valueOf(orderid));
        uzs.setPythonprice(price);
        uzsakymoFormaService.addOrderForm(uzs);
        return price;
    }

    @Override
    public String getTempData(MultipartFile file) {

        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet spreadsheet = workbook.getSheetAt(0);

            DataFormatter df1 = new DataFormatter();
            for (int rowNumber = 1; rowNumber < spreadsheet.getLastRowNum() + 1; rowNumber++) {

                Cell c0 = workbook.getSheetAt(0).getRow(rowNumber).getCell(0);
                Cell c1 = workbook.getSheetAt(0).getRow(rowNumber).getCell(1);
                Cell c2 = workbook.getSheetAt(0).getRow(rowNumber).getCell(2);
                Cell c3 = workbook.getSheetAt(0).getRow(rowNumber).getCell(3);
                Cell c4 = workbook.getSheetAt(0).getRow(rowNumber).getCell(4);
                Cell c5 = workbook.getSheetAt(0).getRow(rowNumber).getCell(5);
                Cell c6 = workbook.getSheetAt(0).getRow(rowNumber).getCell(6);
                Cell c7 = workbook.getSheetAt(0).getRow(rowNumber).getCell(7);
                Cell c8 = workbook.getSheetAt(0).getRow(rowNumber).getCell(8);
                Cell c9 = workbook.getSheetAt(0).getRow(rowNumber).getCell(9);
                Cell c10 = workbook.getSheetAt(0).getRow(rowNumber).getCell(10);
                Cell c11 = workbook.getSheetAt(0).getRow(rowNumber).getCell(11);
                Cell c12 = workbook.getSheetAt(0).getRow(rowNumber).getCell(12);
                Cell c13 = workbook.getSheetAt(0).getRow(rowNumber).getCell(13);
                Cell c14 = workbook.getSheetAt(0).getRow(rowNumber).getCell(14);
                Cell c15 = workbook.getSheetAt(0).getRow(rowNumber).getCell(15);
                Cell c16 = workbook.getSheetAt(0).getRow(rowNumber).getCell(16);
                Cell c17 = workbook.getSheetAt(0).getRow(rowNumber).getCell(17);
                Cell c18 = workbook.getSheetAt(0).getRow(rowNumber).getCell(18);
                Cell c19 = workbook.getSheetAt(0).getRow(rowNumber).getCell(19);
                Cell c20 = workbook.getSheetAt(0).getRow(rowNumber).getCell(20);
                Cell c21 = workbook.getSheetAt(0).getRow(rowNumber).getCell(21);
                Cell c22 = workbook.getSheetAt(0).getRow(rowNumber).getCell(22);
                Cell c23 = workbook.getSheetAt(0).getRow(rowNumber).getCell(23);
                Cell c24 = workbook.getSheetAt(0).getRow(rowNumber).getCell(24);
                Cell c25 = workbook.getSheetAt(0).getRow(rowNumber).getCell(25);
                Cell c26 = workbook.getSheetAt(0).getRow(rowNumber).getCell(26);
                Cell c27 = workbook.getSheetAt(0).getRow(rowNumber).getCell(27);
                Cell c28 = workbook.getSheetAt(0).getRow(rowNumber).getCell(28);
                Cell c29 = workbook.getSheetAt(0).getRow(rowNumber).getCell(29);
                Cell c30 = workbook.getSheetAt(0).getRow(rowNumber).getCell(30);
                Cell c31 = workbook.getSheetAt(0).getRow(rowNumber).getCell(31);
                Cell c32 = workbook.getSheetAt(0).getRow(rowNumber).getCell(32);
                Cell c33 = workbook.getSheetAt(0).getRow(rowNumber).getCell(33);
                Cell c34 = workbook.getSheetAt(0).getRow(rowNumber).getCell(34);
                Cell c35 = workbook.getSheetAt(0).getRow(rowNumber).getCell(35);

                String value0 = df1.formatCellValue(c0);
                String value1 = df1.formatCellValue(c1);
                String value2 = df1.formatCellValue(c2);
                String value3 = df1.formatCellValue(c3);
                String value4 = df1.formatCellValue(c4);
                String value5 = df1.formatCellValue(c5);
                String value6 = df1.formatCellValue(c6);
                String value7 = df1.formatCellValue(c7);
                String value8 = df1.formatCellValue(c8);
                String value9 = df1.formatCellValue(c9);
                String value10 = df1.formatCellValue(c10);
                String value11 = df1.formatCellValue(c11);
                String value12 = df1.formatCellValue(c12);
                String value13 = df1.formatCellValue(c13);
                String value14 = df1.formatCellValue(c14);
                String value15 = df1.formatCellValue(c15);
                String value16 = df1.formatCellValue(c16);
                String value17 = df1.formatCellValue(c17);
                String value18 = df1.formatCellValue(c18);
                String value19 = df1.formatCellValue(c19);
                String value20 = df1.formatCellValue(c20);
                String value21 = df1.formatCellValue(c21);
                String value22 = df1.formatCellValue(c22);
                String value23 = df1.formatCellValue(c23);
                String value24 = df1.formatCellValue(c24);
                String value25 = df1.formatCellValue(c25);
                String value26 = df1.formatCellValue(c26);
                String value27 = df1.formatCellValue(c27);
                String value28 = df1.formatCellValue(c28);
                String value29 = df1.formatCellValue(c29);
                String value30 = df1.formatCellValue(c30);
                String value31 = df1.formatCellValue(c31);
                String value32 = df1.formatCellValue(c32);
                String value33 = df1.formatCellValue(c33);
                String value34 = df1.formatCellValue(c34);
                String value35 = df1.formatCellValue(c35);

                JSONObject user = new JSONObject();

                try {
                    Field changeMap = user.getClass().getDeclaredField("map");
                    changeMap.setAccessible(true);
                    try {
                        changeMap.set(user, new LinkedHashMap<>());
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    changeMap.setAccessible(false);

                } catch (NoSuchFieldException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    user.put("uzsakymas", value0);
                    user.put("m10", value1);
                    user.put("m20", value2);
                    user.put("m30", value3);
                    user.put("m40", value4);
                    user.put("m50", value5);
                    user.put("m60", value6);
                    user.put("m70", value7);
                    user.put("m80", value8);
                    user.put("w10", value9);
                    user.put("w20", value10);
                    user.put("w30", value11);
                    user.put("w40", value12);
                    user.put("w45", value13);
                    user.put("w50", value14);
                    user.put("w55", value15);
                    user.put("w60", value16);
                    user.put("w70", value17);
                    user.put("w80", value18);
                    user.put("w90", value19);
                    user.put("w100", value20);
                    user.put("kiekis", value21);
                    user.put("kaina", value22);
                    user.put("medz_kaina", value23);
                    user.put("skirtingu_medz_kiekis", value24);
                    user.put("daliu_kiekis", value25);
                    user.put("skirtingu_daliu_kiekis", value26);
                    user.put("vn", value27);
                    user.put("m", value28);
                    user.put("m2", value29);
                    user.put("m3", value30);
                    user.put("kg", value31);
                    user.put("kp", value32);
                    user.put("l", value33);
                    user.put("furn_type", value34);
                    user.put("fixed_customer", value35);
                    usersjson.put(user);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            //workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersjson.toString();
    }

    @Override
    public String getPythonModel(String sampleId, String jsonForTraining) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonForTraining, headers);

        // System.out.println(restTemplate.postForObject(
        // "http://158.129.140.156:5000/train", entity, String.class ));
        String jsnstr = restTemplate.postForObject("http://158.129.140.156:5000/train", entity, String.class);
        JSONObject obj;
        try {
            obj = new JSONObject(jsnstr);
            String modelId = obj.getString("model_id");
            String modelMse = obj.getString("model_mse");
            // System.out.println(modelId);
            // System.out.println(modelMse);
            TrainEntity ent = new TrainEntity();
            ent.setCompany(sampleId);
            ent.setModel_id(modelId);
            ent.setModel_mse(modelMse);
            trainEntityService.addTrainEntity(ent);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return restTemplate.postForObject("http://158.129.140.156:5000/train", entity, String.class);

    }

}
