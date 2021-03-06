package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 zhuhh
 * @描述 排播详情表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class PlayListDetail extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long playListId;// 排播列表id
	private int advertisementId;// 广告视频id
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private int duration;// 视频时长
	private int sort;// 排序

	private String title;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getPlayListId() {
		return playListId;
	}

	public void setPlayListId(long playListId) {
		this.playListId = playListId;
	}

	public int getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
