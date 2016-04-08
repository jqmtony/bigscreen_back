package com.gochinatv.accelarator.service;


import java.util.HashMap;
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
	 * 订单预览确认
	 * @param orders
	 * @return
	 */
	public List<Orders> getRetryOrdersList(Orders orders);
	
	
	/**
	 * 保存排播组合前查询需要排播组合的列表
	 * @param data {type,cityCode}
	 */
	public List<Orders>  getOrdersPlayList(HashMap<String,Object> data);
	
	
	/**
	 * 保存订单，保存订单详情
	 * @param place
	 * @throws Exception 
	 */
	public void save(Orders orders) throws Exception;


	/**
	 * 审核订单上线
	 * @param orders
	 * @throws Exception 
	 */
	public void checkOnline(Orders orders) throws Exception;


	/**
	 * 提前下线
	 * @param orders
	 * @return 
	 * @throws Exception 
	 */
	public Orders updateOfflineTime(Orders orders) throws Exception;
}
