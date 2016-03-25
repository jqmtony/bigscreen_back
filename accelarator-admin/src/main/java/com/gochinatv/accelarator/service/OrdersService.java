package com.gochinatv.accelarator.service;


import java.util.List;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

/**
 * 
 * @作者 zhuhh
 * @描述     订单业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface OrdersService  extends BaseService<Orders>{
   
	/**
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders);
	
	
}
