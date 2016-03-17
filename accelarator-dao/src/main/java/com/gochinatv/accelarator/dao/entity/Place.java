package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述    投放区域表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Place extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cname;// 中文名称
	private String ename;// 英文名称
	private int type; // 类型
	private String scale;// 规模大小
	private String averageDailyFlow;// 日均人流量
	private int country;// 国家
	private int area;// 地区
	private String address;// 地址
	private String zipCode;// 邮编
	private int businessId;// 商家id
	private Date createTime;// 创建日期

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getAverageDailyFlow() {
		return averageDailyFlow;
	}

	public void setAverageDailyFlow(String averageDailyFlow) {
		this.averageDailyFlow = averageDailyFlow;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
