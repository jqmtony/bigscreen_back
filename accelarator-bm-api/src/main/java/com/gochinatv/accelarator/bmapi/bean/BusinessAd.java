package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BusinessAd implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "Id")
	private long id;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "价格")
	private String price;
	@ApiModelProperty(value = "排序")
	private int sort;
	@ApiModelProperty(value = "描述")
	private String description;
	@ApiModelProperty(value = "图片地址")
	private String imagePath;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "英文名称")
	private String ename;
	@ApiModelProperty(value = "设备ID")
	private int deviceId;
	@ApiModelProperty(value = "商家ID")
	private int businessId;
	@ApiModelProperty(value = "商铺ID")
	private int placeId;
	@ApiModelProperty(value = "截屏时间")
	private int refreshTime;
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getPlaceId() {
		return placeId;
	}
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	public int getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}
