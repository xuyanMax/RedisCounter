package ds;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisList {
    private static String LIST = "mylist";
    private static String LIST2 = "mylist2";
    private static String LIST3 = "mylist3";

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.lpush(LIST, "1", "2", "3", "A", "1", "2", "3");
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();

        jedis.rpush(LIST2, "1", "2", "3", "A", "1", "2", "3");
        System.out.println(jedis.lrange(LIST2, 0, -1));
        System.out.println();


        jedis.lpop(LIST);
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();

        jedis.rpop(LIST);
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();
//        jedis.del(LIST);
//        jedis.del(LIST2);

        jedis.lset(LIST, 0, "A");
        System.out.println(jedis.lrange(LIST2, 0, -1));

        jedis.linsert(LIST, BinaryClient.LIST_POSITION.BEFORE, "A", "B");
        System.out.println(jedis.lrange(LIST2, 0, -1));
        System.out.println();

        jedis.linsert(LIST, BinaryClient.LIST_POSITION.AFTER, "A", "B");
        System.out.println(jedis.lrange(LIST2, 0, -1));
        System.out.println();

        //消息队列
        jedis.rpoplpush(LIST, LIST2);
        System.out.println(jedis.lrange(LIST2, 0, -1));
        System.out.println();

        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();

        System.out.println(jedis.llen(LIST));
        System.out.println();
        System.out.println(jedis.llen(LIST2));
        System.out.println();

        System.out.println(jedis.lrem(LIST, 2, "3"));//从前向后删除两个3
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();


        System.out.println(jedis.lrem(LIST, -2, "1"));//从后向前删除两个1
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();

        System.out.println(jedis.lrem(LIST, 0, "2"));//删除所有2...
        System.out.println(jedis.lrange(LIST, 0, -1));
        System.out.println();


        jedis.lpushx(LIST3, "1", "2", "9");
        System.out.println(jedis.lrange(LIST, 0, -1));

        jedis.close();

    }
}
