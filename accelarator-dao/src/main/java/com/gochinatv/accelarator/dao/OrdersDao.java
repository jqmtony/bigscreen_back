package com.gochinatv.accelarator.dao;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * @作者 zhuhh
 * @描述     订单数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface OrdersDao  extends BaseDao<Orders>{
   
    
	/**
	 * 订单列表
	 * @param orders
	 * @return
	 */
	public List<Orders> getOrdersList(Orders orders);
	
	
	/**
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders);
	
	
	/**
	 * 查看可用广告位
	 * @param place
	 * @return
	 */
	public List<Orders> getAvailableList(Orders orders);
	
	/**
	 * 保存订单之前的确认订单
	 * @param orders
	 * @return
	 */
	public List<Orders>  getRetryOrdersList(Orders orders);
}
