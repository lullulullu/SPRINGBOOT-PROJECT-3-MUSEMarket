package com.spring.boot.dto;

public class MasterDTO {

	private int masterNo;
	private String masterSubject;

	private String masterContent;
	private int masterHitCount;
	private String masterCreated;
	
	public int getMasterNo() {
		return masterNo;
	}
	public void setMasterNo(int masterNo) {
		this.masterNo = masterNo;
	}
	public String getMasterSubject() {
		return masterSubject;
	}
	public void setMasterSubject(String masterSubject) {
		this.masterSubject = masterSubject;
	}
	public String getMasterContent() {
		return masterContent;
	}
	public void setMasterContent(String masterContent) {
		this.masterContent = masterContent;
	}
	public int getMasterHitCount() {
		return masterHitCount;
	}
	public void setMasterHitCount(int masterHitCount) {
		this.masterHitCount = masterHitCount;
	}
	public String getMasterCreated() {
		return masterCreated;
	}
	public void setMasterCreated(String masterCreated) {
		this.masterCreated = masterCreated;
	}
	
}
