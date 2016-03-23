package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 系统广告表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class SystemAdvertisement extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type; // 广告类型   (1:视频   2：图片   3：文字内容)
	private String videoPath;// 视频地址
	private String picPath;// 图片地址
	private String content;// 文字内容
	private int placeId;// 区域id
	private int deviceId;// 设备id
	private int sort;// 排序
	private String remark;// 描述
	private int status;// 状态  (1：上线    2：下线)
	private int source;// 广告来源   (1：自有   2：商家)
	private int adIndex;// 广告位置
	private Date createTime;// 创建日期

	private String cname;
	private String code;
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getAdIndex() {
		return adIndex;
	}

	public void setAdIndex(int adIndex) {
		this.adIndex = adIndex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
