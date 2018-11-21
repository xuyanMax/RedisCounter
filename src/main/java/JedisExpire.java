import redis.clients.jedis.Jedis;

public class JedisExpire {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set("company", "ericsson");
        System.out.println(jedis.get("company"));
        jedis.rename("company", "corporation");
        jedis.get("company");
        jedis.get("corporation");

        jedis.expire("corporation", 10);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(jedis.ttl("corporation"));
        jedis.close();
    }
}
