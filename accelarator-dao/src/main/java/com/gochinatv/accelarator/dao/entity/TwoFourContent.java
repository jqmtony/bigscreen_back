package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 administrator
 * @描述   2-4号位内容实体
 * @创建时间  2016-5-9
 * @修改时间
 */
public class TwoFourContent extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id; //ID
	private int twoFourBmId; //2-4号位ID
	private String name; //图片名称
	private String content; //内容
	private int status; //状态        1：上线    2：下线
	private int playTime; //播放时间
	private int sort; //排序
	private Date createTime; //创建时间

    
	private String twoFourBmName;//图集名称
	private String twoFourBmType;//图集类型

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTwoFourBmName() {
		return twoFourBmName;
	}

	public void setTwoFourBmName(String twoFourBmName) {
		this.twoFourBmName = twoFourBmName;
	}

	public String getTwoFourBmType() {
		return twoFourBmType;
	}

	public void setTwoFourBmType(String twoFourBmType) {
		this.twoFourBmType = twoFourBmType;
	}

}
