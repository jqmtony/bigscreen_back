package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 广告内容表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Advertisement extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;// 广告类型    (1：视频类型    2：图片类型    3：文字类型)
	private String videoPath;// 视频地址
	private String picPath;// 图片地址
	private String content;// 文字内容
	private int advertiserId;// 广告商id
	private Date startTime;// 生效时间
	private Date endTime;// 失效时间
	private int duration;// 广告时长
	private String remark;// 描述
	private int status;// 状态   (1：上线      2：下线)
	private int source;// 广告来源   (1：自有   2：商家)
	
	
	private String userName;//临时变量，广告商用户名
	private String realName;//临时变量，广告商昵称

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

	public int getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(int advertiserId) {
		this.advertiserId = advertiserId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
