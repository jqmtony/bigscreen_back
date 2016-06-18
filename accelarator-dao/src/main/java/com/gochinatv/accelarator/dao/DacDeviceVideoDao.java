package com.gochinatv.accelarator.dao;


import java.util.List;
import com.gochinatv.accelarator.dao.entity.DacDeviceVideo;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;



/**
 * @作者 zhuhh
 * @描述     数据分析后的元数据Dao处理
 * @创建时间 2016年6月14日 下午4:10:22
 * @修改时间
 */

public interface DacDeviceVideoDao  extends BaseDao<DacDeviceVideo>{

	  /**
	   * 根据设备信息
	   */
	  public void updateDevice();
	  
	  
	  /**
	   * 根据广告主信息
	   */
	  public void updateAdvertiser();
	  
	  
	  /**
	   * 根据视频id取得视频播放次数的详情
	   * @param videoId
	   * @return
	   */
	  public List<DacDeviceVideo> getPlayCountDetail(DacDeviceVideo dacDeviceVideo);

}
