package com.spring.boot.dto;

public class CommuDTO {

	private int commuNo;
	private int userNo;

	private String userId;
	private String commuSubject;
	private String commuContent;
	private String commuIpAddr;
	private String commuCreated;
	private int commuHitCount;
	
	public int getCommuNo() {
		return commuNo;
	}
	public void setCommuNo(int commuNo) {
		this.commuNo = commuNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommuSubject() {
		return commuSubject;
	}
	public void setCommuSubject(String commuSubject) {
		this.commuSubject = commuSubject;
	}
	public String getCommuContent() {
		return commuContent;
	}
	public void setCommuContent(String commuContent) {
		this.commuContent = commuContent;
	}
	public String getCommuIpAddr() {
		return commuIpAddr;
	}
	public void setCommuIpAddr(String commuIpAddr) {
		this.commuIpAddr = commuIpAddr;
	}
	public String getCommuCreated() {
		return commuCreated;
	}
	public void setCommuCreated(String commuCreated) {
		this.commuCreated = commuCreated;
	}
	public int getCommuHitCount() {
		return commuHitCount;
	}
	public void setCommuHitCount(int commuHitCount) {
		this.commuHitCount = commuHitCount;
	}
	
	
}
