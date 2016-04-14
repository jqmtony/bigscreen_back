package com.gochinatv.accelarator.service;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.PlayList;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * 
 * @作者 zhuhh
 * @描述     排播业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlayListService  extends BaseService<PlayList>{

	/**
	 * 得到设备的在播广告
	 * @param pl
	 * @return
	 * @throws Exception
	 */
	List<PlayList> getOnlineAdForDevice(PlayList pl) throws Exception;
   

}
