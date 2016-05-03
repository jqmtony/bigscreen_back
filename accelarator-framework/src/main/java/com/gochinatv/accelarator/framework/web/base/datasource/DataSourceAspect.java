package com.gochinatv.accelarator.framework.web.base.datasource;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;


/**
 * 
 * @作者 zhuhh
 * @描述  拦截所有的事务方法
 * @创建时间 2016年5月3日 下午2:39:43
 * @修改时间
 */
@Component
public class DataSourceAspect {

	
	public void before(JoinPoint point) {
		String name = point.getSignature().getName();
		if (name.startsWith("query") || name.startsWith("get")) {
			ContextDataSource.setSlave();
		} else {
			ContextDataSource.setMaster();
		}
	}

}
