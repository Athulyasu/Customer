package com.consumer.ConsumerProduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableKafka
@EnableScheduling
@SpringBootApplication
public class ConsumerProductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerProductionApplication.class, args);
	}

}
