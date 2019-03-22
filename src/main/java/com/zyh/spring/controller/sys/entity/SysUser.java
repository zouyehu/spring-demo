package com.zyh.spring.controller.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 系统用户模型
 * @author HU
 *
 */
@Entity
@Table(name = "SYS_USER_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysUser implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7363512288744924050L;
	
	/*
	 * Oracle定义自增序列 语句如下:
	 * create sequence SEQ_SYS_USER_INFO
	 * minvalue 1
	 * maxvalue 9999999999999999999999
	 * start with 1
	 * increment by 1
	 * cache 30;
	 * 使用如下三个注解
	 * @Column(length = 19, name = "ID")
	 * @SequenceGenerator(name = "SEQ_SYS_USER_INFO", sequenceName =
	 * "SEQ_SYS_USER_INFO")
	 * @GeneratedValue(generator = "SEQ_SYS_USER_INFO")
	 */
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	/**
	 * 登录用户名
	 */
	@Column(length = 30, name = "LOGIN_NAME")
	private String loginName;
	
	/**
	 * 登录密码(加密后)
	 */
	@Column(length = 200, name = "LOGIN_PASSWORD")
	private String loginPassword;
	
	/**
	 * 修改密码时间
	 */
	@Column(name = "MODIFY_PASSWORD_TIME")
	private Date modifyPasswordTime;
	
	/**
	 * 登陆令签token
	 */
	@Column(length = 200, name = "ACCESS_TOKEN")
	private String accessToken;
	
	/**
	 * 最后登陆系统时间
	 */
	@Column(name = "LAST_LOGINTIME")
	private Date lastLoginTime;
	
	/**
	 * 性别
	 */
	@Column(length=20,name = "GENDER")
	private String gender;

	/**
	 * 客户的真实姓名
	 */
	@Column(length=40,name = "CUSTORMER_NAME")
	private String custerName;
	
	/**
	 * 详细住址
	 */
	@Column(length=100,name = "ADDRESS")
	private String address;
	
	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "UPDATETIME")
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Date getModifyPasswordTime() {
		return modifyPasswordTime;
	}

	public void setModifyPasswordTime(Date modifyPasswordTime) {
		this.modifyPasswordTime = modifyPasswordTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCusterName() {
		return custerName;
	}

	public void setCusterName(String custerName) {
		this.custerName = custerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

	
}
