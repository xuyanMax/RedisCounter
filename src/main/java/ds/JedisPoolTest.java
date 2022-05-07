package ds;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();//连接池配置对象
        poolConfig.setMaxIdle(10);//空闲连接数
        poolConfig.setMaxTotal(30);//最大连接数

        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();

            jedis.set("name", "KEY");

            System.out.println(jedis.get("name"));

            jedis.del("name");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
            if (jedisPool != null) jedisPool.close();
        }

    }
}
