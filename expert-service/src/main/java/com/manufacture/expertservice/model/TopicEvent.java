package com.manufacture.expertservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicEvent {
    private String message;
    private ExpertRequest expertRequest;
}
