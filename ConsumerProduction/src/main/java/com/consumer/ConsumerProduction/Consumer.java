package com.consumer.ConsumerProduction;
import com.consumer.ConsumerProduction.Repository.ProductRepository;
import com.consumer.ConsumerProduction.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;
/*
 *****Kafka Consumer
 * Consume the random number batch of size 5
 * and store it into redis if not present in redis
 */
@Component
public class Consumer {
    int i=0;
    @Autowired
    ProductRepository productrepo;
     @KafkaListener(topics = "${application.properties.topic}")
        public void consumeNumber(final List<String> producedValue) throws JsonProcessingException {

         List<Product> list = (List<Product>) productrepo.findAll();
         List<String> numberList = list.stream()
                 .map(Product::getNumber)
                 .collect(Collectors.toList());
         List<String> filteredList=producedValue.stream().filter(
                 u -> ! numberList.contains(u)
         ).collect(Collectors.toList());
         System.out.println("filteredList>>"+filteredList);
         for(String number:filteredList)
         {
             i=i+1;
             Product product=new Product();
             product.setNumber(number);
             product.setId(i);
             productrepo.save(product);

         }
     }
}
