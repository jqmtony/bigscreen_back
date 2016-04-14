package com.gochinatv.accelarator.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.dao.entity.OnlineAdResponse;
import com.gochinatv.accelarator.dao.entity.PlayList;
import com.gochinatv.accelarator.dao.entity.PlayListDetail;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.DeviceService;
import com.gochinatv.accelarator.service.PlayListDetailService;
import com.gochinatv.accelarator.service.PlayListService;


/**
 * 
 * @作者 zhuhh
 * @描述   排播列表控制层  
 * @创建时间 2016年4月1日 下午5:28:25
 * @修改时间
 */
@Controller
@RequestMapping("/play_list")
public class PlayListController extends BaseController{
    
	@Autowired
	private PlayListService playListService;
	@Autowired
	private PlayListDetailService playListDetailService;
	@Autowired
	private DeviceService deviceService;

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		playListService.getList();
		return "orders/list";
	}
	/**
	 * 跳转到在播广告组合列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoOnlineAdList")
	public String gotoOnlineAdList() throws Exception{
		//playListService.getList();
		return "orders/onlineAdvertisement";
	}
	/**
	 * 在播广告组合列表
	 * @param playListDetail
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/onlineAdList")
	@ResponseBody
	public List<OnlineAdResponse> onlineAdList(PlayListDetail playListDetail) throws Exception{
		List<OnlineAdResponse> list = playListDetailService.getOnlineAdListByEntity(playListDetail);
		return list;
	}
	/**
	 * 跳转到在播设备列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoDeviceList")
	public String gotoDeviceList() throws Exception{
		//playListService.getList();
		return "orders/device_list";
	}
	/**
	 * 查看在播设备详情
	 * @param deviceId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewDevice")
	public String viewDevice(int deviceId, Model model) throws Exception{
		Device device = deviceService.getEntityById(deviceId);
		model.addAttribute("device",device);
		return "orders/device_view";
	}
	/**
	 * 得到设备的在播广告
	 * @param device
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/onlineAdForDevice")
	@ResponseBody
	public PageInfo<PlayList> onlineAdForDevice(int page, int rows,PlayList playList) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<PlayList> list = playListService.getOnlineAdForDevice(playList);
		PageInfo<PlayList> pageInfo = new PageInfo<PlayList>(list);
		return pageInfo;
	}
	
}
