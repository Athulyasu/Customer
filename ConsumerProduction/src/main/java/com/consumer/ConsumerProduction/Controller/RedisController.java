package com.consumer.ConsumerProduction.Controller;

import com.consumer.ConsumerProduction.Repository.ProductRepository;
import com.consumer.ConsumerProduction.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
 *****Redis Controller for view all redis data
 */
@RequestMapping(path="/api/v1/redis")
@RestController
public class RedisController {

    @Autowired
    ProductRepository priductrepo;
    @GetMapping("/redisData")
    public List<Product> redisData(){
        return (List<Product>) priductrepo.findAll();
    }
}