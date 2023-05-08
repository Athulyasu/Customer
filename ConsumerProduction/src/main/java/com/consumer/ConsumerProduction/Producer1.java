package com.consumer.ConsumerProduction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//import org.apache.commons.lang.RandomStringUtils;
import java.util.Random;
@Component
public class Producer1 {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${application.properties.topic}")
    private String topicName;

    private final Random random=new Random();
    public Producer1(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 60000)
    public void startSendingStrings() {
        int number = random.nextInt(100);
//        RandomStringUtils.randomAlphanumeric();
        kafkaTemplate.send(topicName, String.valueOf(number));
        System.out.println("Sent number111: " + number);
    }
}
