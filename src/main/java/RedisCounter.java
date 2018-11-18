import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicInteger;

public class RedisCounter {
    private static String KEY = "myCounter";
    final static int UPPERLIMIT = 100;


    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.del(KEY);
        jedis.close();

        CounterThread thread1 = new CounterThread();
        CounterThread thread2 = new CounterThread();
        thread1.start();
        thread2.start();
    }

    public static class CounterThread extends Thread {
        static AtomicInteger ai = new AtomicInteger();
        /**
         * 总库存量
         */

        /**
         * redis key
         */
        String key = "myCounter";

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                v2();
            }
        }


        /**
         * 版本一
         */
        public static void v1() {
            int incAmount = 1;

            Jedis jedis = new Jedis("localhost", 6379);
            if (!jedis.exists(KEY)) {
                jedis.set(KEY, "95");
            }
            String currAmount = jedis.get(KEY);

            if (Integer.valueOf(currAmount) + incAmount > UPPERLIMIT) {
                System.out.println(ai.getAndAdd(1) + "BAD LUCK: " + Thread.currentThread().getName());
            } else {
                jedis.decrBy(KEY, incAmount);
                System.out.println(ai.getAndAdd(1) + "Good Luck: " + Thread.currentThread().getName());
            }
            jedis.close();

        }

        /**
         * 版本2
         */

        public void v2() {
            int incrAmount = 1;
            Jedis jedis = new Jedis("localhost", 6379);
            if (!jedis.exists(KEY)) {
                jedis.setnx(KEY, String.valueOf(95));
            }

            if (jedis.incrBy(KEY, incrAmount) > UPPERLIMIT) {
                System.out.println(ai.getAndAdd(1) + "BAD LUCK: " + Thread.currentThread().getName());
            } else {
                System.out.println(ai.getAndAdd(1) + "Good Luck: " + Thread.currentThread().getName());
            }
            jedis.close();
        }
    }
}
