package com.mrlii.consumer.service;

import com.mrlii.consumer.model.Course;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Getter
@Service
@Slf4j
public class KafkaConsumerService {

    private String message;

    @KafkaListener(topics = "course-topic", groupId = "deepdev_group")
    public void consume(Course course) {
        message = course + " Got message from kafka serve";
        log.info(message);
    }

}
