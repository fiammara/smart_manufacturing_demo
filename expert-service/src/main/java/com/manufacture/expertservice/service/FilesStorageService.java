package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.ExcelData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FilesStorageService {

    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

    public List<ExcelData> findAllUploads();

    // public String usePython(String submitId) throws IOException;

    public String saveOrder(MultipartFile file, String userid);

    public Stream<Path> loadAllFilesOrders();

    public String getTempData(MultipartFile file);

    public String getPythonModel(String sampleId, String body);

    public String getPrice(String orderid, String sampleId, String order);

    //public String getTempOrderData(MultipartFile file);

    // public String getPrice(String sampleId) throws IOException;
}
