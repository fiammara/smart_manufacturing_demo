package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.message.ResponseMessage;
import com.manufacture.expertservice.model.ExcelData;
import com.manufacture.expertservice.model.FileInfo;
import com.manufacture.expertservice.model.UzsakymoForma;
import com.manufacture.expertservice.service.FilesStorageService;
import com.manufacture.expertservice.service.UzsakymoFormaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin("*")
@RestController
public class FilesController {
    @Autowired
    UzsakymoFormaService uzsakymoFormaService;
    @Autowired
    FilesStorageService storageService;

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String tempData = storageService.getTempData(file);
            storageService.save(file);

            message = "Duomenys sėkmingai pateikti";
            return ResponseEntity.status(HttpStatus.OK).body(tempData);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/api/uploadOrder")
    public ResponseEntity<?> uploadOrderFile(@RequestParam("file") MultipartFile file, @RequestParam("userid") String userid) {

        String message = "";
        try {
            // String tempOrderData =storageService.getTempOrderData(file);
            String tempOrderData = storageService.saveOrder(file, userid);
            message = "Užsakymo duomenys sėkmingai pateikti";
            // System.out.println (tempOrderData);
            // message = "Įkelta: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(tempOrderData);
        } catch (Exception e) {
            message = "Nepavyko pateikti užsakymo: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    /*
     * @PostMapping("/api/uploadOrder") public ResponseEntity<ResponseMessage>
     * uploadOrderFile(@RequestParam("file") MultipartFile file) { String message =
     * ""; try { storageService.saveOrder(file); message =
     * "Užsakymo duomenys sėkmingai pateikti";
     *
     * return ResponseEntity.status(HttpStatus.OK).body(new
     * ResponseMessage(message)); } catch (Exception e) { message =
     * "Nepavyko pateikti užsakymo: " + file.getOriginalFilename() + "!"; return
     * ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
     * ResponseMessage(message)); } }
     */

    @GetMapping("/api/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/api/filesOrders")
    public ResponseEntity<List<FileInfo>> getListFilesOrders() {
        List<FileInfo> fileInfos = storageService.loadAllFilesOrders().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/api/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    @GetMapping("/api/uploads")
    public List<ExcelData> findAllUploads() {
        return storageService.findAllUploads();
    }

    /*
     * @PostMapping(value="/addId/{sampleId}") public void
     * learn(@PathVariable("sampleId") String sampleId) throws IOException{
     * storageService.usePython(sampleId); }
     */
    @PostMapping(value = "/api/addId/{sampleId}")
    public ResponseEntity<ResponseMessage> learn(@PathVariable("sampleId") String sampleId, @RequestBody String body) {


        String message = "";
        try {
            // storageService.usePython(sampleId);
            storageService.getPythonModel(sampleId, body);

            message = "Mokymas sėkmingas, galima pateikti užsakymą";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Mokymas nepavyko";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping(value = "/api/addIdPrice/{orderid}")
    public ResponseEntity<?> getPrice(@PathVariable("orderid") String orderid, @RequestBody String predict_data) {
        //System.out.println("orderid: " +orderid);
        UzsakymoForma uzs = uzsakymoFormaService.getById(Long.valueOf(orderid));
        String whoplacedorder = uzs.getCompanyid();
        String message = "";
        System.out.println("kaina: " + orderid);
        try {
            // storageService.usePython(sampleId);

            String price = storageService.getPrice(orderid, whoplacedorder, predict_data);

            message = "Užsakymo kaina: " + price + " Eur";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Kainos apskaičiuoti nepavyko";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

}
