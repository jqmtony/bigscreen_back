package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @作者 zhuhh
 * @描述 设备信息表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Device extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private long id;
	@ApiModelProperty(value = "设备编码")
	private String code;// 设备编码
	@ApiModelProperty(value = "设备品牌")
	private String brand;// 设备品牌
	@ApiModelProperty(value = "设备型号")
	private String model;// 设备型号
	@ApiModelProperty(value = "设备mac")
	private String mac;// 设备mac
	@ApiModelProperty(value = "商铺id")
	private int placeId;// 区域id
	@ApiModelProperty(value = "商家id")
	private int businessId;// 商家id
	@ApiModelProperty(value = "分屏数量")
	private int screenNum;// 分屏数量
	@ApiModelProperty(value = "创建时间")
	private Date createTime; // 创建时间
	@ApiModelProperty(value = "状态")
	private int status;// 状态
	@ApiModelProperty(value = "城市")
	private String cityCode;// 
	@ApiModelProperty(value = "截屏时间 单位 分钟")
	private int screenShotInterval;//截屏时间 单位 分钟
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getScreenShotInterval() {
		return screenShotInterval;
	}

	public void setScreenShotInterval(int screenShotInterval) {
		this.screenShotInterval = screenShotInterval;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
