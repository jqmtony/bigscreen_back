package com.gochinatv.accelarator.dao;

import java.util.HashMap;
import java.util.List;

import com.gochinatv.accelarator.dao.entity.PlayList;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述     排播数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlayListDao  extends BaseDao<PlayList>{
   
	/**
	 * 排播列表中的cityCode、type、startTime、endTime删除
	 * @param params
	 */
    public void deleteByMap(HashMap<String,Object> params);

    /**
	 * 得到设备的在播广告
	 * @param playList
	 * @return
	 * @throws Exception
	 */
	public List<PlayList> getOnlineAdForDevice(PlayList playList);
}
