package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 订单表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Orders extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNo;// 订单号
	private int creater;// 创建人
	private Date createTime;// 创建时间
	private int auditor;// 审核人
	private Date auditTime;// 审核时间
	private int advertiserId;// 广告商id
	private int advertisementId;// 视频广告id
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private int status;//订单状态   （0:取消订单, 1:待审核, 2：审核通过, 3：审核不通过, 4：已过期）
	//补充字段
	private Date aheadModifyTime;// 提前下线修改时间
	private String originalEndTime;// 记录最初的下线时间时间
	private String remark;// 下线备注描述
	
	
	private String title;//临时，广告标题
	
	private String countryCode;//临时，国家code
	private String areaCode;//临时，地区code
	private String cityCode;//临时，城市code
	private String createrName;//临时，创建人名称
	private String auditorName;//临时，审核人名称
	private int duration;//临时，广告时长(秒)
	
	private int type;//临时，店铺类型
	private String ids;//id集合，查看可用广告逗号分隔
	private String types;//临时，店铺类型的多选逗号分隔

	private String advertiserName;//临时，广告主
	private String realName;//临时，真实姓名
	

	public Date getAheadModifyTime() {
		return aheadModifyTime;
	}

	public void setAheadModifyTime(Date aheadModifyTime) {
		this.aheadModifyTime = aheadModifyTime;
	}

	public String getOriginalEndTime() {
		return originalEndTime;
	}

	public void setOriginalEndTime(String originalEndTime) {
		this.originalEndTime = originalEndTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdvertiserName() {
		return advertiserName;
	}

	public void setAdvertiserName(String advertiserName) {
		this.advertiserName = advertiserName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getCreater() {
		return creater;
	}

	public void setCreater(int creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAuditor() {
		return auditor;
	}

	public void setAuditor(int auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public int getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(int advertiserId) {
		this.advertiserId = advertiserId;
	}

	public int getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
