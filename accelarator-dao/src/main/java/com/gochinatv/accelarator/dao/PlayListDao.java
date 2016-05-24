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
	 * 根据排播id批量删除排播集合
	 * @param params
	 */
    public void deleteAll(List<Long> playListIds);
    
    
    /**
	 * 根据cityCode、type、startTime、endTime 查询PlayList的id
	 */
    public Long getIdByMap(HashMap<String,Object> params);
    
    
    /**
	 * 得到设备的在播广告
	 * @param playList
	 * @return
	 * @throws Exception
	 */
	public List<PlayList> getOnlineAdForDevice(PlayList playList);
}
