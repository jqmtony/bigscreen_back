package com.gochinatv.accelarator.dao;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.PlayListDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述    排播详情数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlayListDetailDao  extends BaseDao<PlayListDetail>{
   
	
	/**
	 * 根据排播id批量删除排播集合
	 * @param params
	 */
    public void deleteAll(List<Long> playListIds);

    /**
     * 排播组合列表
     * @param playListDetail 
     * @return
     */
	public List<PlayListDetail> getOnlineAdListByEntity(PlayListDetail playListDetail);
}
