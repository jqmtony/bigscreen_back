package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
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

	private String code;// 设备编码
	private String brand;// 设备品牌
	private String model;// 设备型号
	private String mac;// 设备mac
	private int placeId;// 区域id
	private int businessId;// 商家id
	private int screenNum;// 分屏数量
	private Date createTime; // 创建时间
	private int status;// 状态
	private String versionNum;// 版本号
	private String versionName;// 版本名
	private Date bootTime;//开机时间

	private String cname;// 临时，店铺中文名称
	private String userName;// 临时，店主用户名
	//供查询使用
	private String countryCode;// 临时，国家
	private String areaCode;// 临时，地区
	private String cityCode;// 临时，城市
	
	private int screenShotInterval;//截屏时间 单位 分钟
	public Device(){
		
	}
	public Device(String code, String brand, String mac, Date createTime) {
		this.code = code;
		this.brand = brand;
		this.mac = mac;
		this.createTime = createTime;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Date getBootTime() {
		return bootTime;
	}

	public void setBootTime(Date bootTime) {
		this.bootTime = bootTime;
	}

	public int getScreenShotInterval() {
		return screenShotInterval;
	}

	public void setScreenShotInterval(int screenShotInterval) {
		this.screenShotInterval = screenShotInterval;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
