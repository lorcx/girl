package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    /**
     * 测试
     */
    public void demo1() {
        // 1. 设置ip地址和端口
        Jedis jedis = new Jedis("192.168.1.109", 7200);
        // 2. 保存数据：
        jedis.set("name", "bbbb");
        jedis.set("age", "111");
        jedis.set("high", "100");
        // 3. 获取数据
        System.out.println(jedis.get("name"));
        // 4. 释放资源
        jedis.close();
    }

    /**
     * 使用连接池的方式
     */
    public void demo2() {
        // 获得连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大的空闲连接数
        config.setMaxIdle(10);
        // 获得连接池
        JedisPool pool = new JedisPool(config, "192.168.1.109", 7200);

        // 获得核心对象
        Jedis jedis = null;
        try {
            // 通过连接池获得连接
            jedis = pool.getResource();
            // 设置数据
            jedis.set("name", "data");
            // 获取数据
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (pool != null) {
                pool.close();
            }
        }

    }
}
