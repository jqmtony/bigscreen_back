package com.gochinatv.accelarator.service;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.DacDeviceVideo;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


public interface DacDeviceVideoService extends BaseService<DacDeviceVideo>{

    
	  
	  /**
	   * 根据视频id取得视频播放次数的详情
	   * @param videoId
	   * @return
	   */
	  public List<DacDeviceVideo> getPlayCountDetail(int videoId);
}
