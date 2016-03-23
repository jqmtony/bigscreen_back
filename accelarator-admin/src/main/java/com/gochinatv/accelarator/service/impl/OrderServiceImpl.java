package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.OrderDao;
import com.gochinatv.accelarator.dao.entity.Order;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.OrderService;


/**
 * 
 * @作者 zhuhh
 * @描述    订单业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	protected BaseDao<Order> getDao() {
		return orderDao;
	}
	
	
}
