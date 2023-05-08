package com.consumer.ConsumerProduction;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${application.properties.topic}")
    private String topicName;

    private final Random random=new Random();
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Scheduled(fixedDelay = 60000)
    public void startSendingNumbers() {
        int number = random.nextInt(100);
        kafkaTemplate.send(topicName, String.valueOf(number));
        System.out.println("Sent number: " + number);
    }
}