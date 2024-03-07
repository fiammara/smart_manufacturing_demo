package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.message.ResponseMessage;
import com.manufacture.expertservice.model.ExcelData;
import com.manufacture.expertservice.model.FileInfo;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.service.FilesStorageService;
import com.manufacture.expertservice.service.impl.OrderFormServiceImpl;
import com.manufacture.expertservice.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Api(tags = {DescriptionVariables.FILES})
@Log4j2
@CrossOrigin("*")
@RestController
@RequestMapping("/api/uploads")
public class FilesController {
    @Autowired
    OrderFormServiceImpl orderFormService;
    @Autowired
    FilesStorageService filesStorageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {

            filesStorageService.save(file);
            message = "File uploaded successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> uploadOrderFile(@RequestParam("file") MultipartFile file, @RequestParam("userid") String userid) {

        String message = "";
        try {
            String tempOrderData = filesStorageService.saveOrder(file, userid);
            message = "Order data uploaded successfully: " + tempOrderData;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Error uploading data: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = filesStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/orders")
    public ResponseEntity<List<FileInfo>> getListFilesOrders() {
        List<FileInfo> fileInfos = filesStorageService.loadAllFilesOrders().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = filesStorageService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    @GetMapping()
    public ResponseEntity<List<ExcelData>> findAllUploads() {
        log.info("Getting all excel data uploads");
        List<ExcelData> uploadsList = filesStorageService.findAllUploads();
        if (uploadsList.isEmpty()) {
            log.warn("Uploads list is empty {}", uploadsList);
            return ResponseEntity.noContent().build();
        }
        log.debug("Uploads list size: {}", uploadsList::size);
        return ResponseEntity.ok(uploadsList);
    }


    @PostMapping(value = "/addId/{sampleId}")
    public ResponseEntity<ResponseMessage> learn(@PathVariable("sampleId") String sampleId, @RequestBody String body) {

        String message = "";
        try {
            filesStorageService.getPythonModel(sampleId, body);

            message = "Model training was successful, now you can upload an order.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Model training was not successful";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping(value = "/addIdPrice/{orderid}")
    public ResponseEntity<ResponseMessage> getPrice(@PathVariable("orderid") String orderid, @RequestBody String predict_data) {

        Optional<UzsakymoForma> order = orderFormService.getById(Long.valueOf(orderid));
        String whoPlacedOrder = order.get().getCompanyid();
        String message = "";
        try {

            String price = filesStorageService.getPrice(orderid, whoPlacedOrder, predict_data);
            message = "Order price is: " + price + " Eur";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not calculate an order price, error";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

}
