package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 广告套餐表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Packages extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;// 套餐名称
	private Date createTime; // 创建时间
	private Date validTime;// 生效时间
	private Date invalidTime; // 失效时间
	private int totalPlaytime;// 总播放时长
	private int totalAds; // 总广告数量
	private int validAds; // 有效广告数量
	private int invalidAds; // 无效广告数量
	private String remark; // 描述

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public int getTotalPlaytime() {
		return totalPlaytime;
	}

	public void setTotalPlaytime(int totalPlaytime) {
		this.totalPlaytime = totalPlaytime;
	}

	public int getTotalAds() {
		return totalAds;
	}

	public void setTotalAds(int totalAds) {
		this.totalAds = totalAds;
	}

	public int getValidAds() {
		return validAds;
	}

	public void setValidAds(int validAds) {
		this.validAds = validAds;
	}

	public int getInvalidAds() {
		return invalidAds;
	}

	public void setInvalidAds(int invalidAds) {
		this.invalidAds = invalidAds;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
