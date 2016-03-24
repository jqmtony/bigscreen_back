package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.OrdersService;


/**
 * 
 * @作者 zhuhh
 * @描述 订单控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/order")
public class OrdersController extends BaseController{
    
	@Autowired
	private OrdersService ordersService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "order/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Orders> queryList(int page,int rows,Orders orders) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Orders> list = ordersService.getListByEntity(orders);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
