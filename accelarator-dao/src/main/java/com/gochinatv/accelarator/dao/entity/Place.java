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
	private int type; // 类型       （1：餐厅  2：大使馆   3：商场   4：美甲区    5：其它）
	private String scale;// 规模大小
	private String averageDailyFlow;// 日均人流量
	private String countryCode;// 国家
	private String areaCode;// 地区
	private String cityCode;// 城市
	private String address;// 地址
	private String zipCode;// 邮编
	private int businessId;// 商家id
	private Date createTime;// 创建日期
	
	
	private String userName;//临时，商家用户名称
	private String startTime;//临时，查看可用广告的开始时间
	private String endTime;//临时，查看可用广告的结束时间
	private String ids;//id集合，查看可用广告逗号分隔
	private String types;//临时，店铺类型的多选逗号分隔

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
}
