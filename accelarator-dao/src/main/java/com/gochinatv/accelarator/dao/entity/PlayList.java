package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 排播列表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class PlayList extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int areaId;// 区域id
	private int type;// 区域类型
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

}
