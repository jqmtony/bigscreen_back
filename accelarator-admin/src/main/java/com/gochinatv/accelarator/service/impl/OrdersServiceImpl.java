package com.gochinatv.accelarator.service.impl;


import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.OrdersDao;
import com.gochinatv.accelarator.dao.OrdersDetailDao;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.OrdersService;
import com.gochinatv.accelarator.util.SessionUtils;

/**
 * 
 * @作者 zhuhh
 * @描述    订单业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private OrdersDetailDao ordersDetailDao;
	
	@Override
	protected BaseDao<Orders> getDao() {
		return ordersDao;
	}
	
	
	/**
	 * 订单列表
	 * @param orders
	 * @return
	 */
	public List<Orders> getOrdersList(Orders orders){
		return ordersDao.getOrdersList(orders);
	}
	
	/**
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders){
		return ordersDao.queryPlayList(orders);
	}
	
	/**
	 * 查看可用广告位
	 * @param order
	 * @return
	 */
	public List<Orders> getAvailableList(Orders orders){
		String cityCode = orders.getCityCode();
		StringBuffer buffer = new StringBuffer("'");
		if(!StringUtils.isEmpty(cityCode)){
		   cityCode = cityCode.replaceAll(",","','");
		   buffer.append(cityCode);
		}
		buffer.append("'");
		orders.setCityCode(buffer.toString());
		return ordersDao.getAvailableList(orders);
	}
	
	/**
	 * 订单预览确认
	 * @param orders
	 * @return
	 */
	public List<Orders> getRetryOrdersList(Orders orders){
		return ordersDao.getRetryOrdersList(orders);
	}
	
	/**
	 * 保存订单，保存订单详情
	 * @param place
	 * @throws Exception 
	 */
	public void save(Orders orders) throws Exception{
		orders.setCreater(SessionUtils.getLoginUser().getId());
		orders.setCreateTime(new Date());
		orders.setStatus(1);
		ordersDao.save(orders);
		
		List<Orders> ordersList = this.getRetryOrdersList(orders);
		for (Orders o : ordersList) {
			OrdersDetail detail = new OrdersDetail();
			detail.setType(o.getType());
			detail.setOrdersId(orders.getId());
			detail.setCountryCode(o.getCountryCode());
			detail.setAreaCode(o.getAreaCode());
			detail.setCityCode(o.getCityCode());
			ordersDetailDao.save(detail);
		}
	}
	
	
	  /**
	   * 生成排播组合
	   * 1：保存订单完成之后，首先判断订单的有效日期是否已经过期，如果过期那么不用重新排列 
	   *     SELECT * FROM orders WHERE id=5 
	   * 2：查出orders_detail的涉及影响到哪些city_code和type 
	   *     SELECT type,city_code from orders_detail where orders_id=5 
	   * 3：根据city_code,type循环查询影响到这些的记录行数
	   *     for(){
	   *        SELECT od.type,od.city_code,o.advertisement_id,o.start_time,o.end_time FROM orders_detail od 
	   *        LEFT JOIN orders o  ON od.orders_id=o.id WHERE od.type=xxx AND city_code='xxxx' AND o.status IN(2,3,4) 
	   *        and o.endTime>当前时间，否则认为是过期的
	   *     }
	   * 4： 根据每次查询循环的结果开始执行排播？按时间段进行排播还是每天进行排播？
	   */
	public void createPlayList(Orders orders){
		  String endTime = orders.getEndTime();
		  Date end = DateUtils.parseDate(endTime,new String[]{"yyyy-MM-dd"});
		  Date now = new Date();
		  if(now.before(end)){//表示结束日期大于今天
			 List<OrdersDetail> ordersDetails = ordersDetailDao.getOrdersDetailByOrdersId(orders.getId());
			 for (OrdersDetail detail : ordersDetails) {
				  
			 } 
		  }else{
			  //结束时间必须大于当天
		  }
	}
	
	
}
