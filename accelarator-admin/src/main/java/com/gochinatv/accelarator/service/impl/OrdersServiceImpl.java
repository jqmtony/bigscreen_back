package com.gochinatv.accelarator.service.impl;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.OrdersDao;
import com.gochinatv.accelarator.dao.OrdersDetailDao;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.dao.entity.Place;
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
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders){
		return ordersDao.queryPlayList(orders);
	}
	
	
	/**
	 * 保存订单，保存订单详情
	 * @param place
	 * @throws Exception 
	 */
	public void save(Place place) throws Exception{
		
		Orders orders = new Orders();
		orders.setOrderNo("");
		orders.setCreater(SessionUtils.getLoginUser().getId());
		orders.setCreateTime(new Date());
		orders.setAdvertiserId(advertiserId);
		orders.setStartTime(startTime);
		orders.setEndTime(endTime);
		orders.setStatus(1);
		ordersDao.save(orders);
		
		OrdersDetail detail = new OrdersDetail();
		detail.setType(type);
		detail.setOrdersId(orders.getId());
		detail.setCountryCode(countryCode);
		detail.setAreaCode(areaCode);
		detail.setCityCode(cityCode);
		ordersDetailDao.save(detail);
	}
}
