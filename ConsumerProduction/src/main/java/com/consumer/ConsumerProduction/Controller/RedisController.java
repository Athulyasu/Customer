package com.consumer.ConsumerProduction.Controller;

import com.consumer.ConsumerProduction.Repository.ProductRepository;
import com.consumer.ConsumerProduction.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/api/v1/redis")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RedisController {

    @Autowired
    ProductRepository priductrepo;
    @GetMapping("/redisData")
    public List<Object> redisData(){
        return priductrepo.getAll();
    }
}