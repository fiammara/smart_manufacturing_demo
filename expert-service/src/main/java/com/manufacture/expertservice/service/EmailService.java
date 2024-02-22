package com.manufacture.expertservice.service;

import java.util.Properties;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    Properties getMailProperties();
}
