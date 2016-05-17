package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 administrator
 * @描述   2-4号位发放区域
 * @创建时间  2016-5-9
 * @修改时间
 */
public class TwoFourBmArea extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id; //ID
	private int twoFourBmId; //2,4号位id
	private String cityCode; //城市代码
	private Date createTime; //创建时间
	
	//查询使用
	private String startTime;
	private String endTime;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTwoFourBmId() {
		return twoFourBmId;
	}
	public void setTwoFourBmId(int twoFourBmId) {
		this.twoFourBmId = twoFourBmId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
