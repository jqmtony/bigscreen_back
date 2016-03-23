package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.OrderDetailDao;
import com.gochinatv.accelarator.dao.entity.OrderDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.OrderDetailService;


/**
 * 
 * @作者 zhuhh
 * @描述     订单详情业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {

	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Override
	protected BaseDao<OrderDetail> getDao() {
		return orderDetailDao;
	}
	
	
}
