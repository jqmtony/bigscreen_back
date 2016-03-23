package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;
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

	private int playListId;// 排播列表id
	private int advertisementId;// 广告视频id
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private int duration;// 视频时长
	private int sort;// 排序

	public int getPlayListId() {
		return playListId;
	}

	public void setPlayListId(int playListId) {
		this.playListId = playListId;
	}

	public int getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
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
