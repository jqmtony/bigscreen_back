package com.gochinatv.accelarator.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * @作者 zhuhh
 * @描述     订单详情数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface OrdersDetailDao  extends BaseDao<OrdersDetail>{
     
   
   /**
    * 根据订单id查询订单详情的集合
    * @param ordersId
    * @return
    */
   public List<OrdersDetail> getOrdersDetailByOrdersId(@Param(value="ordersId") int ordersId);

	/**
	 * 根据订单id得到订单详情列表
	 * @param ordersId
	 * @return
	 */
	List<OrdersDetail> getOrdersDetailList(int ordersId);
 
}
