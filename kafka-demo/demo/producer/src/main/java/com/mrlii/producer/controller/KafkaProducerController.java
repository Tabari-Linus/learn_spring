package com.mrlii.producer.controller;

import com.mrlii.producer.model.Course;
import com.mrlii.producer.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/course")
    public ResponseEntity<String> addCource(@RequestBody Course course){
        return ResponseEntity.ok(kafkaProducerService.sendMessage(course));
    }
}
