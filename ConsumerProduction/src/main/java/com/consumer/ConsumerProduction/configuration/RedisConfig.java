package com.consumer.ConsumerProduction.configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.consumer.ConsumerProduction.entity.Product;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<String, Product> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

}
