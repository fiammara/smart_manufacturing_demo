package com.manufacture.emailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RequestConsumer {

    private static final String requestTopic = "${spring.kafka.topic.name}";
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;


    @Autowired
    public RequestConsumer(ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;

    }

    @KafkaListener(topics = requestTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        log.info("message consumed {}", message);

        ExpertRequestDto expertRequestDto = objectMapper.readValue(message, ExpertRequestDto.class);

        log.info("Expert request event received in email service " + expertRequestDto.toString());

        // send an email
    }

}
