package com.mrlii.consumer.controller;

import com.mrlii.consumer.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaConsumerController
{
    private final KafkaConsumerService kafkaConsumerService;

    @GetMapping("/message")
    public String getMessage(){
        return "Message from kafka server : " + kafkaConsumerService.getMessage();
    }

}
