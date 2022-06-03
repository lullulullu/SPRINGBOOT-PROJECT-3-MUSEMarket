package com.spring.boot.dto;

public class UsersDataDTO {
	
	private int userNo;
	
	private int prodNo;
	private String prodName;
	private String prodSubject;
	private String prodPrice;
	private String prodCreated;
	private String tmImg;
	
	private int reviewNo;
	private String reviewContent;
	private String reviewCreated;
	
	private int commuNo;
	private String commuSubject;
	private String commuCreated;
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdSubject() {
		return prodSubject;
	}
	public void setProdSubject(String prodSubject) {
		this.prodSubject = prodSubject;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdCreated() {
		return prodCreated;
	}
	public void setProdCreated(String prodCreated) {
		this.prodCreated = prodCreated;
	}
	public String getTmImg() {
		return tmImg;
	}
	public void setTmImg(String tmImg) {
		this.tmImg = tmImg;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}	
	public String getReviewCreated() {
		return reviewCreated;
	}
	public void setReviewCreated(String reviewCreated) {
		this.reviewCreated = reviewCreated;
	}
	public int getCommuNo() {
		return commuNo;
	}
	public void setCommuNo(int commuNo) {
		this.commuNo = commuNo;
	}
	public String getCommuSubject() {
		return commuSubject;
	}
	public void setCommuSubject(String commuSubject) {
		this.commuSubject = commuSubject;
	}
	public String getCommuCreated() {
		return commuCreated;
	}
	public void setCommuCreated(String commuCreated) {
		this.commuCreated = commuCreated;
	}
		
}
