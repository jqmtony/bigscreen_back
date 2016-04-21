package com.gochinatv.accelarator.api.proxy;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gochinatv.accelarator.api.util.Md5Util;
import com.gochinatv.accelarator.api.util.redis.WriteTmpData;

/**
 * Created by shhao.
 * Date: 15-1-28
 * Time:上午11:10
 */
@Aspect
@Component
public class CacheServiceProxy {
    Logger logger = LoggerFactory.getLogger(CacheServiceProxy.class);

    //Spring AOP 注入
    @Around("within(com.gochinatv.accelarator.api.service.impl.*)")
    public Object serviceProxy(ProceedingJoinPoint proceed) throws Throwable {
        Object object = null;
        String clazzName = proceed.getTarget().getClass().getSimpleName();
        String methodName = proceed.getSignature().getName();
        
        if(StringUtils.equals(clazzName, "DeviceServiceImpl") &&
        		(StringUtils.equals(methodName, "getDeviceInfo")||
        				StringUtils.equals(methodName, "getAdInfo"))
        		){
        	 Object[] args = proceed.getArgs();
             String key = Md5Util.makeKey(clazzName, methodName, args);
             logger.info("serverProxy key={}", key);
             if(WriteTmpData.notTTL(key)){
                 object = WriteTmpData.getObject(key);
                 logger.info("==get data from cache=="+key);
             }
             else {
                 object = proceed.proceed();
                 WriteTmpData.delete(key);
                 WriteTmpData.setObject(key, object);
             }
        }else{
        	   object = proceed.proceed();
        }
        //for (Object o : args) System.out.println("args:" + o);
        return object;
    }

}