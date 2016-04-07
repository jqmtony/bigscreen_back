package com.gochinatv.accelarator.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.OrdersDetailDao;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.OrdersDetailService;


/**
 * 
 * @作者 zhuhh
 * @描述     订单详情业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrdersDetailServiceImpl extends BaseServiceImpl<OrdersDetail> implements OrdersDetailService {

	@Autowired
	private OrdersDetailDao ordersDetailDao;
	
	@Override
	protected BaseDao<OrdersDetail> getDao() {
		return ordersDetailDao;
	}
	
	public List<OrdersDetail> getOrdersDetailList(int ordersId) {
		return ordersDetailDao.getOrdersDetailList(ordersId);
	}
	
	
}
