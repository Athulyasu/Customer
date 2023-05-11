package com.consumer.ConsumerProduction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.consumer.ConsumerProduction.Repository.ProductRepository;
import com.consumer.ConsumerProduction.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.consumer.ConsumerProduction.configuration.RedisConfig;

@Component
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${application.properties.topic}")
    private String topicName;
    @Autowired
    RedisConfig RedisConfig;

    private final Random random=new Random();
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Scheduled(fixedDelay = 60000)
    public void startSendingNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(100);
            numbers.add(number);
        }
        kafkaTemplate.send(topicName, numbers.toString());
        System.out.println("Sent numbers: " + numbers);
//        int number = random.nextInt(100);
//        kafkaTemplate.send(topicName, String.valueOf(number));
//        System.out.println("Sent number: " + number);
    }
}