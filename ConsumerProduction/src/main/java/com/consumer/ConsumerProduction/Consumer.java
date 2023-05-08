package com.consumer.ConsumerProduction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
     @KafkaListener(topics = "${application.properties.topic}")
        public void consumeNumber(String value) {
                     System.out.println("Received number: " + value);
        }
}
