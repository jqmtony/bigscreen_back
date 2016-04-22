package com.gochinatv.accelarator.bmapi.util.redis;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.gochinatv.accelarator.bmapi.util.SerializeUtil;
import com.gochinatv.accelarator.bmapi.util.StringUtil;


/**
 * RedisUtil 工具类
 * @Modified: 2015年1月30日  LBQ-PC 添加方法：getArrayKeys和removeByKeys
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * ====================String======start============
     *
     * @throws Exception
     */

    public static void set(String key, Object value, int seconds) {
        try {
            if (!StringUtil.isEmpty(key) && value != null) {
                if (value != null) {
                    Jedis jedis = null;
                    try {
                        jedis = JedisPoolUtils.getJedis();
                        jedis.set(key, value.toString());
                        jedis.expire(key, seconds);
                        logger.debug("set key[" + key + "],value[" + value + "]");
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        if (jedis != null) {
                            JedisPoolUtils.returnRes(jedis);
                        }
                    }
                }
            } else {
                logger.warn("set key[" + key + "],value[" + value + "] is invalid");
            }
        } catch (Exception e) {
            logger.error("set key[" + key + "],value[" + value + "] is error", e);
        }
    }

    //获取一个object
    public static Object get(String key) {
        Object result = null;
        Jedis jedis = null;
        try {
            if (!StringUtil.isEmpty(key)) {
                jedis = JedisPoolUtils.getJedis();
                byte[] value = jedis.get(key.getBytes());
//                //剩余时间
//                long remainingTime = jedis.ttl(key);
                result = SerializeUtil.unserialize(value);
                //logger.debug("get  key[" + key + "] ,value:" + result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
        return result;
    }

    public static boolean notTTL(String key) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = JedisPoolUtils.getJedis();
            flag = jedis.ttl(key.getBytes()) > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
            	JedisPoolUtils.returnRes(jedis);
            }
            return flag;
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
                jedis = JedisPoolUtils.getJedis();
                byte[] value = jedis.get(key.getBytes());
                result = SerializeUtil.unserialize(value);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
            	JedisPoolUtils.returnRes(jedis);
            }
        }
        return result;
    }
    //get keySet

    public static Set<String> getKeys(String key) {
        Set<String> objectSet = new HashSet<String>();
        Jedis jedis = null;
        try {
            if (!StringUtil.isEmpty(key)) {
                jedis = JedisPoolUtils.getJedis();
                Set<byte[]> set = jedis.keys(key.getBytes());
                for (byte[] value : set) {
                    String k = new String(value);
                    logger.info("key:" + k);
                    //objectSet.add(String.valueOf(SerializeUtil.unserialize(value)));
                    objectSet.add(k);
                }
                logger.debug("get  key[" + key + "] ,value:" + objectSet.size());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
        return objectSet;
    }
    
    /**
	 * 根据传入的格式获取key
	 * @param key
	 * @return 数组
	 * @author LBQ-PC
	 * @date:2015年1月30日
	 */
	public static String[] getArrayKeys(String key) {
		String[] arrayKeys = null;
		Jedis jedis = null;
		try {
			if (!StringUtil.isEmpty(key)) {
				jedis = JedisPoolUtils.getJedis();
				Set<byte[]> set = jedis.keys(key.getBytes());
				arrayKeys = new String[set.size()];
				int i=0;
				for (byte[] value : set) {
					String k = new String(value);
					logger.info("key:" + k);
					arrayKeys[i]=k;
					i++;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				JedisPoolUtils.returnRes(jedis);
			}
		}
		return arrayKeys;
	}
    
	/**
	 * 根据传入的数组批量删除key
	 * @date:2015年1月30日
	 */
	public static void removeByKeys(String[] keys) {
		try {
			Jedis jedis = null;
			try {
				jedis = JedisPoolUtils.getJedis();
				if(keys!=null && keys.length>0) {
					jedis.del(keys);
					logger.info("del key[" + keys + "]");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				if (jedis != null) {
					JedisPoolUtils.returnRes(jedis);
				}
			}
		} catch (Exception e) {
			logger.error("remove element error! key[" + keys + "]", e);
		}
	}
	
    public static void remove(String key) {
        try {
            Jedis jedis = null;
            try {
                jedis = JedisPoolUtils.getJedis();
                jedis.del(key.getBytes());
                logger.info("del key[" + key + "]");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (jedis != null) {
                    JedisPoolUtils.returnRes(jedis);
                }
            }
        } catch (Exception e) {
            logger.error("remove element error! key[" + key + "]", e);
        }
    }

    /**
     * ====================String======end============
     */


    /**
     * ====================List=====start=============
     */


    public static void setList(String key, List value) {
        try {
            if (!StringUtil.isEmpty(key) && value != null) {
                byte[] bytes = SerializeUtil.serializeList(value);
                if (bytes != null) {
                    Jedis jedis = null;
                    try {
                        jedis = JedisPoolUtils.getJedis();
                        jedis.set(key.getBytes(), bytes);
                        logger.debug("set key[" + key + "],value[" + value + "]");
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    } finally {
                        if (jedis != null) {
                            JedisPoolUtils.returnRes(jedis);
                        }
                    }
                }
            } else {
                logger.warn("set key[" + key + "],value[" + value + "] is invalid");
            }
        } catch (Exception e) {
            logger.error("set key[" + key + "],value[" + value + "] is error", e);
        }
    }

    /**
     * =======================getList
     *
     * @param key
     * @return
     */
    public static List getList(String key) {
        List result = null;
        Jedis jedis = null;
        try {
            if (!StringUtil.isEmpty(key)) {
                jedis = JedisPoolUtils.getJedis();
                byte[] value = jedis.get(key.getBytes());
                result = SerializeUtil.unserializeList(value);
                // logger.debug("get  key[" + key + "] ,value:" + result);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
        return result == null ? new ArrayList() : result;
    }


    /**
     * List中删除某个元素
     *
     * @param key
     * @param value
     */
    public static void ldel(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            jedis.lrem(key, 1, value);
            logger.info("lrem key[" + key + "],value[" + value + "]");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
    }

    //push a value to List
    public static void rpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            jedis.rpush(key, value);
            logger.debug("rpush key[" + key + "],value[" + value + "]");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
    }


    public static boolean exists(String key) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = JedisPoolUtils.getJedis();
            flag = jedis.exists(key.getBytes());
            logger.debug("exsist key[" + key + "]");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
            return flag;
        }
    }

    /**
     * ====================reset=====Cache=============
     */
    public static void resetDB() {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            jedis.flushDB();
            logger.debug("resetDB =========");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
        }
    }

    //get redis DB size
    public static long getDBSize() {
        Jedis jedis = null;
        long size = 0;
        try {
            jedis = JedisPoolUtils.getJedis();
            size = jedis.dbSize();
            logger.debug("getDBSize =========");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                JedisPoolUtils.returnRes(jedis);
            }
            return size;
        }
    }
}
