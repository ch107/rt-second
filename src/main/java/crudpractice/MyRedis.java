package crudpractice;

import redis.clients.jedis.Jedis;

public class MyRedis {
    public static void main(String[] args) {
        // Connect to Redis running on localhost at port 6379
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            // Set a key-value pair
            jedis.set("greeting", "Hello, Redis!");

            // Retrieve the value
            String value = jedis.get("greeting");
            System.out.println("Stored value: " + value);

            // Delete the key
            //jedis.del("greeting");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
