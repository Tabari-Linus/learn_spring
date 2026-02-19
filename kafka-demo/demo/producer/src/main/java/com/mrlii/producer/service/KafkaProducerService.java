package com.mrlii.producer.service;

import com.mrlii.producer.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Course> kafkaTemplate;

    public String sendMessage( Course course) {
        kafkaTemplate.send("course-topic", "course", course);
        return "Course message sent to kafka serve";
    }
}