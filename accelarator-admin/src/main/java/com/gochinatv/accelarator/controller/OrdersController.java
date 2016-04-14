package com.gochinatv.accelarator.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.OrdersDetailService;
import com.gochinatv.accelarator.service.OrdersService;
import com.gochinatv.accelarator.util.SessionUtils;


/**
 * 
 * @作者 zhuhh
 * @描述 订单控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController{
    
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrdersDetailService ordersDetailService;

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "orders/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Orders> queryList(int page,int rows,Orders orders) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Orders> list = ordersService.getOrdersList(orders);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
	/**
	 * 到在播广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoPlayList")
	public String gotoPlayList(Model model) throws Exception{
		return "orders/play_list";
	}
	
	/**
	 * 查询在播广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPlayList")
	@ResponseBody
	public PageInfo<Orders> queryPlayList(int page,int rows,Orders orders) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Orders> list = ordersService.queryPlayList(orders);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}
	
	
	/**
	 * 到可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoAvailableList")
	public String gotoAvailableList() throws Exception{
		return "orders/available_list";
	}
	
	/**
	 * 查询可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAvailableList")
	@ResponseBody
	public List<Orders> queryAvailableList(Orders orders) throws Exception{
		List<Orders> list = ordersService.getAvailableList(orders);
		return list;
	}
	
	
	/**
	 * 生成订单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createOrder")
	public String createOrder(Orders orders,Model model) throws Exception{
		orders.setOrderNo(DateFormatUtils.format(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
		model.addAttribute("orders",orders);
		return "orders/order_preview";
	}
	
	
	/**
	 * 订单详情预览功能
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public List<Orders> orderDetail(Orders orders) throws Exception{
		List<Orders> list = ordersService.getRetryOrdersList(orders);
		return list;
	}
	
	/**
	 * 保存订单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveOrder")
	@ResponseBody
	public Map<String,Object> saveOrder(Orders orders){
		Map<String,Object> result = this.success(null);
		try{
			orders.setCreater(SessionUtils.getLoginUser(getRequest()).getId());
			ordersService.save(orders);
		}catch(Exception e){
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 审核订单上线
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkOnline")
	@ResponseBody
	public Map<String,Object> checkOnline(Orders orders){
		Map<String,Object> result = this.success(null);
		try{
			orders.setAuditor(SessionUtils.getLoginUser(getRequest()).getId());
			ordersService.auditOrders(orders);
		}catch(Exception e){
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 退回
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendBack")
	@ResponseBody
	public Map<String,Object> sendBack(int id){
		Map<String,Object> result = this.success(null);
		try{
			Orders orders = ordersService.getEntityById(id);
			//审核不通过
			orders.setStatus(3);
			ordersService.update(orders);
		}catch(Exception e){
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 审核订单
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkOrder")
	public String checkOrder(int ordersId, Model model) throws Exception{
		Orders orders = ordersService.getEntityById(ordersId);
		if(orders.getTitle() == null){
			orders.setTitle("暂无");
		}
		model.addAttribute("orders",orders);
		return "orders/order_check";
	}
	
	/**
	 * 审核订单详情
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkOrderDetail")
	@ResponseBody
	public List<OrdersDetail> checkOrderDetail(@RequestParam(value="id",defaultValue="0") int id) throws Exception{
		List<OrdersDetail> list = ordersDetailService.getOrdersDetailList(id);
		return list;
	}
	/**
	 * 查看订单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewOrder")
	public String viewOrder(int ordersId,Model model) throws Exception{
		Orders orders = ordersService.getEntityById(ordersId);
		if(orders.getTitle() == null){
			orders.setTitle("暂无");
		}
		model.addAttribute("orders",orders);
		return "orders/order_view";
	}

	/**
	 * 取消订单
	 * @param id
	 * @return
	 */
	@RequestMapping("/cancleOrder")
	@ResponseBody
	public Map<String,Object> cancleOrder(int id){
		Map<String,Object> result = this.success(null);
		try{
			Orders orders = ordersService.getEntityById(id);
			orders.setStatus(0);
			ordersService.update(orders);
		}catch(Exception e){
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 提前下线
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aheadOffline")
	public String aheadOffline(int ordersId,Model model) throws Exception{
		Orders orders = ordersService.getEntityById(ordersId);
		if(orders.getTitle() == null){
			orders.setTitle("暂无");
		}
		model.addAttribute("orders",orders);
		return "orders/order_ahead_offline";
	}
	
	/**
	 * 修改提前下线时间
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateOfflineTime")
	@ResponseBody
	public Map<String,Object> updateOfflineTime(Orders orders){
		Map<String,Object> result = this.success(null);
		try{
			orders.setAuditor(SessionUtils.getLoginUser(getRequest()).getId());
			ordersService.offlineOrders(orders);
		}catch(Exception e){
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}
}
