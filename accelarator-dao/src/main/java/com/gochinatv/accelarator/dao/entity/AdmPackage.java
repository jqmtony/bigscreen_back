package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * @作者 zhuhh
 * @描述 广告套餐关联表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class AdmPackage extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int admId;// 广告内容id
	private Date playStarttime;// 播放开始时间
	private Date playEndtime;// 播放结束时间
	private int duration;// 广告时长
	private int packageId;// 套餐id

	public int getAdmId() {
		return admId;
	}

	public void setAdmId(int admId) {
		this.admId = admId;
	}

	public Date getPlayStarttime() {
		return playStarttime;
	}

	public void setPlayStarttime(Date playStarttime) {
		this.playStarttime = playStarttime;
	}

	public Date getPlayEndtime() {
		return playEndtime;
	}

	public void setPlayEndtime(Date playEndtime) {
		this.playEndtime = playEndtime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
}
