package com.gochinatv.accelarator.api.util.redis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.gochinatv.accelarator.api.util.SerializeUtil;
import com.gochinatv.accelarator.api.util.StringUtil;

/**
 * Created by shhao.
 * Date: 15-1-27
 * Time:下午6:35
 */
public class WriteTmpData {
    private static JedisPool pool;
    static Logger logger = LoggerFactory.getLogger(WriteTmpData.class);
    static int writeDB = 0;
    static int expireTime = 60 * 60;//60分钟

    private static void createJedisPool() {

        Properties pro = new Properties();
        try {
            pro.load(WriteTmpData.class.getResourceAsStream("/redis.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
        // 设置空间连接
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
        String redis_ip = bundle.getString("redis.ip");
        int redis_port = Integer.valueOf(bundle.getString("redis.port"));
        // 创建连接池
        pool = new JedisPool(config, redis_ip, redis_port);
        logger.info("redis.ip={} redis.port={},expireTime={},writeDB={}", new Object[]{redis_ip, redis_port, expireTime, writeDB});
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
     */
    public static Jedis getJedis() {
        if (pool == null) {
            poolInit();
        }
        Jedis jedis = pool.getResource();
        jedis.select(writeDB);
        return jedis;
    }

    /**
     * 归还一个连接
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

    /**
     * 保存List 结果集
     */
    public static void setList(String key, List value) {
        try {
            if (!StringUtil.isEmpty(key) && value != null) {
                byte[] bytes = SerializeUtil.serializeList(value);
                if (bytes != null) {
                    Jedis jedis = null;
                    try {
                        jedis = getJedis();

                        jedis.set(key.getBytes(), bytes);
                        logger.debug("set key[" + key + "]");
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        if (jedis != null) {
                            returnRes(jedis);
                        }
                    }
                }
            } else {
               logger.warn("set key[" + key + "] is invalid");
            }
        } catch (Exception e) {
            logger.error("set key[" + key + "]", e);
        }
    }

    /**
     * 获取list 结果集
     */
    public static List getList(String key) {
        List result = null;
        Jedis jedis = null;
        try {
            if (!StringUtil.isEmpty(key)) {
                jedis = getJedis();
                byte[] value = jedis.get(key.getBytes());
                result = SerializeUtil.unserializeList(value);
                 logger.debug("get  key[" + key );
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                returnRes(jedis);
            }
        }
        return result;
    }

    /**
     * 保存 Object 结果集
     */
    public static void setObject(String key, Object value) {
        try {
            if (!StringUtil.isEmpty(key) && value != null) {
                byte[] bytes = SerializeUtil.serialize(value);
                if (bytes != null) {
                    Jedis jedis = null;
                    try {
                        jedis = getJedis();
                        jedis.set(key.getBytes(), bytes);
                        jedis.expire(key.getBytes(), expireTime);
                        logger.debug("set key[" + key );
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        if (jedis != null) {
                            returnRes(jedis);
                        }
                    }
                }
            } else {
               logger.warn("set key[" + key + "]");
            }
        } catch (Exception e) {
            logger.error("set key[" + key + "]", e);
        }
    }

    /**
     * 获取 Object 结果集
     */
    public static Object getObject(String key) {
        Object result = null;
        Jedis jedis = null;
        try {
            if (!StringUtil.isEmpty(key)) {
                jedis = getJedis();
                byte[] value = jedis.get(key.getBytes());
                result = SerializeUtil.unserialize(value);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                returnRes(jedis);
            }
        }
        return result;
    }

    public static boolean notTTL(String key) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = getJedis();
            flag = jedis.ttl(key.getBytes()) > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                returnRes(jedis);
            }
            return flag;
        }
    }

    public static void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
             jedis.del(key.getBytes()) ;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                returnRes(jedis);
            }
        }
    }
}
