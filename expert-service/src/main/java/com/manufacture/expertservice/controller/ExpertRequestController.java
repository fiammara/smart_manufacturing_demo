package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.service.impl.ExpertRequestServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api/experts/requests")
public class ExpertRequestController {
    @Autowired
    private ExpertRequestServiceImpl expertRequestService;

    @GetMapping()
    public ResponseEntity<List<ExpertRequest>> findAllRequests() {

        log.info("Getting all expert requests");
        List<ExpertRequest> expertRequestList = expertRequestService.findAllRequests();
        if (expertRequestList.isEmpty()) {
            log.warn("List of expert requests is empty {}", expertRequestList);
            return ResponseEntity.notFound().build();
        }
        log.debug("List of expert requests, size: {}", expertRequestList::size);
        return ResponseEntity.ok(expertRequestList);

    }

    @GetMapping("/all/{usercompany_id}")
    public ResponseEntity<List<ExpertRequest>> findCompanyRequests(@PathVariable(value = "usercompany_id") String usercompany_id) {

        log.info("Find company`s expert request by passing company id, where id is :{} ", usercompany_id);
        List<ExpertRequest> expertRequestList = expertRequestService.findCompanyRequests(usercompany_id);
        if (expertRequestList.isEmpty()) {
            log.warn("Expert request list was found empty.");
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Expert requests were found by company id.");
        }
        return ResponseEntity.ok(expertRequestList);

    }

    @GetMapping("/all/expert/{expert_id}")
    public ResponseEntity<List<ExpertRequest>> findSentReq(@PathVariable(value = "expert_id") String expert_id) {

        log.info("Find company`s expert request by passing company id, where id is :{} ", expert_id);
        List<ExpertRequest> expertRequestList = expertRequestService.findSentRequests(Long.valueOf(expert_id));
        if (expertRequestList.isEmpty()) {
            log.warn("Expert request list was found empty.");
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Expert requests were found by company id.");
        }
        return ResponseEntity.ok(expertRequestList);

    }
}
