package com.gochinatv.accelarator.service.impl;


import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
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
	
}
