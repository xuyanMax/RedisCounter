package ds;

import redis.clients.jedis.Jedis;

public class JedisSelectDB {
    /**
     * 最多提供16个数据库, 下标从0到15
     *
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.select(0);

        jedis.set("name", "tom");

        System.out.println(jedis.get("name"));

        jedis.select(1);

        System.out.println(jedis.get("name"));

        jedis.select(0);

        jedis.move("name", 1);

        jedis.select(1);

        System.out.println(jedis.get("name"));

        jedis.close();
    }
}
