/*package com.gochinatv.accelarator.api.controller;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gochina.mis.bean.LogInfo;
import com.gochina.mis.service.LogInfoService;
import com.gochina.mis.util.JsonUtil;
import com.gochina.mis.util.Md5Util;
import com.gochina.mis.util.StringUtil;
import com.gochina.mis.vo.ResponseCDNAddTaskVo;

@Aspect
@Component
public class LogAopProxy {
    Logger logger = LoggerFactory.getLogger(LogAopProxy.class);

    @Autowired
	private LogInfoService logInfoService;
    
    //Spring AOP 注入
    @Around("within(com.gochina.mis.api.*)")
    public Object serviceProxy(ProceedingJoinPoint proceed) throws Throwable {
    	String result = (String)proceed.proceed();
    	try {
    		String clazzName = proceed.getTarget().getClass().getSimpleName();
            String methodName = proceed.getSignature().getName();
            Object[] args = proceed.getArgs();
            String arg = "";
            for (Object o : args){
            	arg = arg +","+ o.toString();
            }
            //组织参数
            LogInfo log = new LogInfo();
            log.setParameter(arg);
            log.setUrl(clazzName+"."+methodName);
            log.setTime(new Date());
            
            //解析结果
            JSONObject jsonObj = JSONObject.fromObject(result);
            Iterator iterator = jsonObj.keys();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                value = jsonObj.getString(key);
                if ("success".equals(key)) {
                	if ("true".equals(value)||"True".equals(value)) {
                		log.setType("成功");
    				}else {
    					log.setType("失败");
    				}
    			}
                if ("msg".equals(key)) {
    				log.setDescription(value);
    			}
            }
            if (StringUtil.isEmpty(log.getType())) {
    			log.setType("未知");
    		}
            logInfoService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("请求日志保存失败，错误信息为：{}",e.getMessage());
		}
        return result;
    }

}*/