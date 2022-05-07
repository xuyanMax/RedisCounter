package ds;

import redis.clients.jedis.Jedis;

public class RedisString {
    private final static String KEY = "key";
    private final static String KEY2 = "key2";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set(KEY, "golloe");

        jedis.set(KEY2, "5");

        System.out.println(jedis.get(KEY));
        System.out.println(jedis.get(KEY2));

        jedis.del(KEY);
        System.out.println(jedis.get(KEY));

        jedis.set(KEY, "golloe");
        System.out.println(jedis.append(KEY, "5"));
        System.out.println(jedis.get(KEY));
        System.out.println(jedis.append("newStr", "12345"));

        jedis.close();
    }
}
