package com.consumer.ConsumerProduction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class RedisDuplicateSaver {
    private Jedis jedis;
    private String setKey;

    public RedisDuplicateSaver(String redisHost, int redisPort, String setKey) {
        this.jedis = new Jedis(redisHost, redisPort);
        this.setKey = setKey;
    }

    public boolean saveDataWithoutDuplicates(String data) {
        try {
            // Try to add the data to the Redis set.
            // If the data already exists in the set, the add method will return 0.
            // Otherwise, it will return 1.
            Long result = jedis.sadd(setKey, data);
            return result == 1L;
        } catch (JedisDataException e) {
            // Handle any Redis-related exceptions here.
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection() {
        jedis.close();
    }
}