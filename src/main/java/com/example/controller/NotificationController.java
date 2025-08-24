package com.example.controller;

import com.example.events.NotificationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/notification")
    public void sendNotification(@RequestBody NotificationEvent notificationEvent) {
        kafkaTemplate.send("notifications.email", notificationEvent);
    }
}
