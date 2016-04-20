package com.gochinatv.accelarator.bmapi.bean;

import java.io.Serializable;
import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;


/**
 * 
 * @作者 zhuhh
 * @描述 商家账号表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Business implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private long id;
	@ApiModelProperty(value = "账号")
	private String userName;// 账号
	@ApiModelProperty(value = "密码")
	private String password;// 密码
	@ApiModelProperty(value = "真实姓名")
	private String realName;// 真实姓名
	@ApiModelProperty(value = "国家")
	private String countryCode;// 国家
	@ApiModelProperty(value = "地区")
	private String areaCode;// 地区
	@ApiModelProperty(value = "城市")
	private String cityCode;// 城市
	@ApiModelProperty(value = "手机")
	private String mobile;// 手机
	@ApiModelProperty(value = "电子邮箱")
	private String email;// 电子邮箱
	@ApiModelProperty(value = "FaceBook")
	private String facebook;// FaceBook
	@ApiModelProperty(value = "微信号")
	private String weixin;// 微信号
	@ApiModelProperty(value = "备注")
	private String remark;// 备注
	@ApiModelProperty(value = "生日")
	private String birthday;// 生日
	@ApiModelProperty(value = "创建日期")
	private Date createTime; // 创建日期
	@ApiModelProperty(value = "是否启用")
	private int status;// 是否启用   (1：启用 2：禁用)

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
