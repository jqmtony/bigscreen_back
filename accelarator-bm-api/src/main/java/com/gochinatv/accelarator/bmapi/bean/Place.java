package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
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

	@ApiModelProperty(value = "id")
	private long id;
	@ApiModelProperty(value = "中文名称")
	private String cname;// 中文名称
	@ApiModelProperty(value = "英文名称")
	private String ename;// 英文名称
	@ApiModelProperty(value = "类型（1：餐厅  2：大使馆   3：商场   4：美甲区    5：其它）")
	private int type; // 类型       （1：餐厅  2：大使馆   3：商场   4：美甲区    5：其它）
	@ApiModelProperty(value = "规模大小")
	private String scale;// 规模大小
	@ApiModelProperty(value = "日均人流量")
	private String averageDailyFlow;// 日均人流量
	@ApiModelProperty(value = "国家")
	private String countryCode;// 国家
	@ApiModelProperty(value = "地区")
	private String areaCode;// 地区
	@ApiModelProperty(value = "城市")
	private String cityCode;// 城市
	@ApiModelProperty(value = "地址")
	private String address;// 地址
	@ApiModelProperty(value = "邮编")
	private String zipCode;// 邮编
	@ApiModelProperty(value = "商家id")
	private int businessId;// 商家id
	@ApiModelProperty(value = "创建日期")
	private Date createTime;// 创建日期
	
	//前端需要
	@ApiModelProperty(value = "国家")
	private String countryName;
	@ApiModelProperty(value = "地区")
	private String areaName;
	@ApiModelProperty(value = "城市")
	private String cityName;
	
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
