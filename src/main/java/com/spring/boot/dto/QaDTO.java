package com.spring.boot.dto;

public class QaDTO {

	private int qaNo;
	private int userNo;
	private String userId;
	private String userEmail;
	private String qaSubject;
	private String qaContent;
	private String qaCreated;
	
	public int getQaNo() {
		return qaNo;
	}
	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getQaSubject() {
		return qaSubject;
	}
	public void setQaSubject(String qaSubject) {
		this.qaSubject = qaSubject;
	}
	public String getQaContent() {
		return qaContent;
	}
	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}
	public String getQaCreated() {
		return qaCreated;
	}
	public void setQaCreated(String qaCreated) {
		this.qaCreated = qaCreated;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
	
}
