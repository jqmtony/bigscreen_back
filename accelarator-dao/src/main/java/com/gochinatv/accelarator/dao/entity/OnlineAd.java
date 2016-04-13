package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.List;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 zhuhh
 * @描述 排播详情表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class OnlineAd extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long playListId;// 排播列表id
	private String startTime;// 开始时间
	private List<PlayListDetail> adList;
	public long getPlayListId() {
		return playListId;
	}
	public void setPlayListId(long playListId) {
		this.playListId = playListId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public List<PlayListDetail> getAdList() {
		return adList;
	}
	public void setAdList(List<PlayListDetail> adList) {
		this.adList = adList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
