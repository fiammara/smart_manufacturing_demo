package com.manufacture.expertservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manufacture.expertservice.model.ExpertRequest;
import com.manufacture.expertservice.model.TopicEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RequestProducer {

    @Value("${spring.kafka.topic.name}")
    private String requestTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public RequestProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public String sendMessage(ExpertRequest expertRequest) throws JsonProcessingException {
        String requestAsMessage = objectMapper.writeValueAsString(expertRequest);
        kafkaTemplate.send(requestTopic, requestAsMessage);

        log.info("request produced {}", requestAsMessage);

        return "request sent sucessfully";
    }
}
