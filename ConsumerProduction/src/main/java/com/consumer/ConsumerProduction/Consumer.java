package com.consumer.ConsumerProduction;
import com.consumer.ConsumerProduction.Repository.ProductRepository;
import com.consumer.ConsumerProduction.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
@Component
public class Consumer {
    int i=0;
    @Autowired
    ProductRepository productrepo;
     @KafkaListener(topics = "${application.properties.topic}")
        public void consumeNumber(String value) throws JsonProcessingException {

         String[] parts = value.substring(1, value.length() - 1).split(",");
         List<Object> datalist=productrepo.getAll();

         // Convert Map to JSON
         ObjectMapper objectMapper = new ObjectMapper();
         String json = objectMapper.writeValueAsString(datalist);
         // Convert JSON to list of entities
         List<Product> dataobjectlist = objectMapper.readValue(json, new TypeReference<List<Product>>(){});
         for (String part : parts) {
             int flag=0;
             if(dataobjectlist.size()!=0) {
//                 Product data = dataobjectlist.stream()
//                         .filter(product -> part.equals(product.getNumber()))
//                         .findAny()
//                         .orElse(null);
//
//                 if(data!=null){
//                     flag=1;
//                 }
                 for (Product data : dataobjectlist) {
                     String no=data.getNumber();
                     if(data.getNumber().equals(part)) {
                         flag=1;
                         break;
                     }
                 }
             }
             if(flag==0){
                 System.out.println("Recieved NO::"+part);
                 Product product=new Product();
                 Date date = new Date();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                 String strDate = dateFormat.format(date);
                 product.setNumber(part);
                 product.setId(i);
                 product.setName("TEST");
                 product.setUpdatedOn(strDate);
                 productrepo.addProduct(product);
                 i=i+1;
             }
         }
     }
}
