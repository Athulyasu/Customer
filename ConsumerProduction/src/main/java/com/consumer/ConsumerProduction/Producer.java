package com.consumer.ConsumerProduction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.consumer.ConsumerProduction.configuration.RedisConfig;
/*
 *****Kafka Producer
 * Produce random number batch of size 5
 * and send to kafka consumer as list
 */
@Component
public class Producer {

    private final KafkaTemplate<String, List<String>> kafkaTemplate;
    @Value("${application.properties.topic}")
    private String topicName;
    @Autowired
    RedisConfig RedisConfig;

    private final Random random=new Random();
    public Producer(KafkaTemplate<String, List<String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 60000)
    public void startSendingNumbers() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String number = String.valueOf(random.nextInt(100));
            numbers.add(number);
        }
        kafkaTemplate.send(topicName, numbers);
        System.out.println("Sent numbers: " + numbers);

    }
}