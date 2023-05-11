package com.consumer.ConsumerProduction.Repository;

import com.consumer.ConsumerProduction.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepository   {

    public static final String HASH_KEY="Product";
    @Autowired
    private RedisTemplate<String, Product> redisTemplate;

    public void addProduct(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY,product.getId(),product);
    }
    public List<Object> getAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

}
