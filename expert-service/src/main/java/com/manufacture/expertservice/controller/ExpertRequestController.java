package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.model.Company;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.service.impl.ExpertRequestServiceImpl;
import com.manufacture.expertservice.swagger.DescriptionVariables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api(tags = {DescriptionVariables.ExpertRequest})
@Log4j2
@RestController
@RequestMapping("/api/experts/requests")
public class ExpertRequestController {
    @Autowired
    private ExpertRequestServiceImpl expertRequestService;

    @GetMapping()
    @ApiOperation(value = "Finds all expert requests",
            notes = "Returns the entire list of expert requests",
            response = ExpertRequest.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = ExpertRequest.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
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

    @ApiOperation(value = "Finds all expert requests by company id",
            notes = "Returns the entire list of expert requests provided by company",
            response = ExpertRequest.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has succeeded", response = ExpertRequest.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
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

 /*   @GetMapping("/all/expert/{expert_id}")
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

    } */
}
