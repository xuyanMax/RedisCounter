package ds;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class JedisZSort {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        Map<String, Double> params = new HashMap<String, Double>();
        params.put("a", Double.valueOf(70));
        params.put("b", Double.valueOf(80));
        params.put("c", Double.valueOf(90));
        params.put("d", Double.valueOf(99));
        params.put("e", Double.valueOf(99.5));
        params.put("f", Double.valueOf(99.6));
        params.put("g", Double.valueOf(99.7));
        jedis.zadd("mysort", params);
        jedis.zadd("mysort", 100, "Peter");
        System.out.println(jedis.zcard("mysort"));
        System.out.println(jedis.zscore("mysort", "Peter"));
        System.out.println(jedis.zrange("mysort", 0, -1));
        System.out.println(jedis.zrangeWithScores("mysort", 0, -1));
        System.out.println(jedis.zrevrange("mysort", 0, -1));
        System.out.println(jedis.zrevrangeWithScores("mysort", 0, -1));


        System.out.println(jedis.zremrangeByRank("mysort", 3, -1));
        System.out.println(jedis.zrange("mysort", 0, -1));
        System.out.println(jedis.zremrangeByScore("mysort", 0, 90));
        System.out.println(jedis.zrange("mysort", 0, -1));

        System.out.println(jedis.zincrby("mysort", 0.03, "e"));
        System.out.println(jedis.zrangeWithScores("mysort", 0, 1));


        System.out.println(jedis.zcount("mysort", 0, 99));
        System.out.println(jedis.zcount("mysort", 99, 99.999));

        jedis.close();

    }
}
