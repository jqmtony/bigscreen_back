package com.gochinatv.accelarator.api.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisPoolUtils {
	
	private static JedisPool pool;
    static Logger logger= LoggerFactory.getLogger(JedisPoolUtils.class);

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     * 
     */
    private static void createJedisPool() {

        ResourceBundle bundle = ResourceBundle.getBundle("redis");   
        if (bundle == null) {   
            throw new IllegalArgumentException(   
                    "[redis.properties] is not found!");   
        } 
    	

        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();

        // 设置最大连接数
        config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));

        // 设置空间连接
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));

        // 创建连接池
        pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
        logger.info("redis.ip={} redis.port={},maxActive={}", new Object[]{bundle.getString("redis.ip"), bundle.getString("redis.port"),bundle.getString("redis.pool.maxWait")});
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     * 
     * @return
     */
    public static Jedis getJedis() {

        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 归还一个连接
     * 
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

}
