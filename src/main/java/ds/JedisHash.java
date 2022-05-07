package ds;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class JedisHash {
    private static String MYHASH = "myhash";
    private static String MYHASH2 = "myhash2";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.hset(MYHASH, "username", "peter");
        jedis.hset(MYHASH, "age", "18");

        System.out.println(jedis.hgetAll(MYHASH));
        System.out.println();
        System.out.println(jedis.hkeys(MYHASH));
        System.out.println();
        System.out.println(jedis.hvals(MYHASH));

        jedis.hdel(MYHASH, "username");
        System.out.println(jedis.hgetAll(MYHASH));
        jedis.hset(MYHASH, "username", "max");

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jax");
        params.put("age", "26");
        params.put("sex", "boy");
        params.put("strength", "18cm");
        jedis.hmset(MYHASH2, params);

        System.out.println();
        System.out.println(jedis.hgetAll(MYHASH2));
        jedis.hdel(MYHASH2, "username");

        System.out.println();
        System.out.println(jedis.hgetAll(MYHASH2));

        System.out.println(jedis.hexists(MYHASH2, "username"));

    }

    public void sjs(String a, String... fields) {
        for (String s : fields)
            System.out.println(s);
    }
}
