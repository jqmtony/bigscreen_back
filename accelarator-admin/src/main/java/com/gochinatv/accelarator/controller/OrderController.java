package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Order;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.OrderService;


/**
 * 
 * @作者 zhuhh
 * @描述 订单控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    
	@Autowired
	private OrderService orderService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "order/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Order> queryList(int page,int rows,Order order) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Order> list = orderService.getListByEntity(order);
		PageInfo<Order> pageInfo = new PageInfo<Order>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
