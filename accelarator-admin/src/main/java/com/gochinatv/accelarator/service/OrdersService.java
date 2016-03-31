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
	
	
	/**
	 * 查看可用广告位
	 * @param place
	 * @return
	 */
	public List<Orders> getAvailableList(Orders orders);
	
	
	/**
	 * 订单预览确认
	 * @param orders
	 * @return
	 */
	public List<Orders> getRetryOrdersList(Orders orders);
	
	
	/**
	 * 保存订单，保存订单详情
	 * @param place
	 * @throws Exception 
	 */
	public void save(Orders orders) throws Exception;
}
