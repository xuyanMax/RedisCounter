import redis.clients.jedis.Jedis;

public class JedisSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.sadd("myset1", "1", "2", "3", "a", "b", "c", "e");
        jedis.sadd("myset2", "1", "3", "a", "c");
        System.out.println(jedis.smembers("myset1"));
        System.out.println(jedis.smembers("myset2"));
        System.out.println();

        System.out.println(jedis.sinter("myset1", "myset2"));
        System.out.println(jedis.sinterstore("mysetinter", "myset1", "myset2"));
        System.out.println(jedis.smembers("mysetinter"));
        System.out.println();

        System.out.println(jedis.sdiff("myset1", "myset2"));//>两个也可以
        System.out.println(jedis.sdiffstore("mysetdiff", "myset1", "myset2"));
        System.out.println(jedis.smembers("mysetdiff"));
        System.out.println();


        System.out.println(jedis.sunion("myset1", "myset2"));//>两个也可以
        System.out.println(jedis.sunionstore("mysetunion", "myset1", "myset2"));
        System.out.println(jedis.smembers("mysetunion"));
        System.out.println();

        System.out.println(jedis.srandmember("myset1"));
        System.out.println(jedis.srandmember("myset2"));

        System.out.println(jedis.scard("myset1"));

        jedis.close();
    }
}
