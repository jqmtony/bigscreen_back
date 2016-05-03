package com.gochinatv.accelarator.framework.web.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;



/**
 * 
 *  <bean id="dataSource1" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://127.0.0.1:3306/accelarator?useUnicode=true&amp;characterEncoding=UTF-8" /><pre>
		<property name="username" value="upenv" />
		<property name="password" value="upenv" />
	</bean>
	
	<bean id="dataSource2" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://210.14.151.100:6033/accelarator?useUnicode=true&amp;characterEncoding=UTF-8" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>
	
	<bean id="dataSource" class="com.gochinatv.accelarator.framework.web.base.datasource.RoutingDataSource">
		<property name="targetDataSources">
		   <map key-type="java.lang.String">
		      <entry key="master" value-ref="dataSource1"/>
		      <entry key="slave" value-ref="dataSource2"/>
		   </map>
		</property>
		<property name="defaultTargetDataSource" ref="dataSource1"/>
	</bean>
	
	
	<aop:config>
		<aop:aspect ref="dataSourceAspect" order="-1000">
	     <aop:pointcut id="routingDataSource" expression="execution(* com.gochinatv.accelarator.dao.*.*(..))" />
	     <aop:before method="before" pointcut-ref="routingDataSource"/>
	    </aop:aspect>
	</aop:config>
 * 
 * 
 * @作者 zhuhh
 * @描述   
 * @创建时间 2016年5月3日 下午4:35:08
 * @修改时间
 */
public class RoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		return ContextDataSource.getDataSource();
	}

}
