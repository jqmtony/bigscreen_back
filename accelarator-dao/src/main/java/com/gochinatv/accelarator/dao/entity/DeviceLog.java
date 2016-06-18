package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 设备日志记录
 * @创建时间 2016年6月14日 下午4:36:11
 * @修改时间
 */
public class DeviceLog extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String mac;
	private String msg;
	private int type; // 101:开机时间 、102:文件下载时长 、103:视频播放次数 、 104:视频删除反馈
	private Date createTime;
	private int isSync;//是否同步   （0:未同步      1：同步）

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsSync() {
		return isSync;
	}

	public void setIsSync(int isSync) {
		this.isSync = isSync;
	}

}
