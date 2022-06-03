package com.spring.boot.dto;

public class ArticleDTO {

	
	private int prodNo;
	private int userNo;
	private String prodSelect;
	private String prodSubject;
	private String prodSale;
	private String prodName;
	private String prodPrice;
	private int prodHitCount;
	private int prodHeart;
	private String prodQuality;
	private String prodCreated;
	private String prodAddr;
	private String prodTrade;
	private String prodImg;
	private String prodContent;
	
	///////////////////////////
	
	private String userId;
	private String userTel;
	
	///////////////////////////
//	private int listNum;
//
//	private int num;
//	private String name;
//	private String email;
//	private String content;
//	private String ipAddr;
//	private String created;
//
//	private String pageNum;
	//////////////////////////
	
	private String reviewUserId;
	private int reviewNo;
	private String reviewContent;
	private String reviewIpAddr;
	private int reviewGroupNo;
	private int reviewDepth;
	private int reviewOrderNo;
	private int reviewParent;
	private String reviewCreated;
	

	private int heartNo;
	private int heartCheck;
	
	private String tmImg;
	
	private int imgNo;
	private String originalFileName;
	private String saveFileName;	
	
	
	
	public String getTmImg() {
		return tmImg;
	}
	public void setTmImg(String tmImg) {
		this.tmImg = tmImg;
	}
	public int getImgNo() {
		return imgNo;
	}
	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public int getHeartNo() {
		return heartNo;
	}
	public void setHeartNo(int heartNo) {
		this.heartNo = heartNo;
	}
	public int getHeartCheck() {
		return heartCheck;
	}
	public void setHeartCheck(int heartCheck) {
		this.heartCheck = heartCheck;
	}
	public String getReviewUserId() {
		return reviewUserId;
	}
	public void setReviewUserId(String reviewUserId) {
		this.reviewUserId = reviewUserId;
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
	public String getReviewIpAddr() {
		return reviewIpAddr;
	}
	public void setReviewIpAddr(String reviewIpAddr) {
		this.reviewIpAddr = reviewIpAddr;
	}
	public int getReviewGroupNo() {
		return reviewGroupNo;
	}
	public void setReviewGroupNo(int reviewGroupNo) {
		this.reviewGroupNo = reviewGroupNo;
	}
	public int getReviewDepth() {
		return reviewDepth;
	}
	public void setReviewDepth(int reviewDepth) {
		this.reviewDepth = reviewDepth;
	}
	public int getReviewOrderNo() {
		return reviewOrderNo;
	}
	public void setReviewOrderNo(int reviewOrderNo) {
		this.reviewOrderNo = reviewOrderNo;
	}
	public int getReviewParent() {
		return reviewParent;
	}
	public void setReviewParent(int reviewParent) {
		this.reviewParent = reviewParent;
	}
	public String getReviewCreated() {
		return reviewCreated;
	}
	public void setReviewCreated(String reviewCreated) {
		this.reviewCreated = reviewCreated;
	}
//	public int getListNum() {
//		return listNum;
//	}
//	public void setListNum(int listNum) {
//		this.listNum = listNum;
//	}
//	public int getNum() {
//		return num;
//	}
//	public void setNum(int num) {
//		this.num = num;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public String getIpAddr() {
//		return ipAddr;
//	}
//	public void setIpAddr(String ipAddr) {
//		this.ipAddr = ipAddr;
//	}
//	public String getCreated() {
//		return created;
//	}
//	public void setCreated(String created) {
//		this.created = created;
//	}
//	public String getPageNum() {
//		return pageNum;
//	}
//	public void setPageNum(String pageNum) {
//		this.pageNum = pageNum;
//	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getProdSelect() {
		return prodSelect;
	}
	public void setProdSelect(String prodSelect) {
		this.prodSelect = prodSelect;
	}
	public String getProdSubject() {
		return prodSubject;
	}
	public void setProdSubject(String prodSubject) {
		this.prodSubject = prodSubject;
	}
	public String getProdSale() {
		return prodSale;
	}
	public void setProdSale(String prodSale) {
		this.prodSale = prodSale;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdHitCount() {
		return prodHitCount;
	}
	public void setProdHitCount(int prodHitCount) {
		this.prodHitCount = prodHitCount;
	}
	public int getProdHeart() {
		return prodHeart;
	}
	public void setProdHeart(int prodHeart) {
		this.prodHeart = prodHeart;
	}
	public String getProdQuality() {
		return prodQuality;
	}
	public void setProdQuality(String prodQuality) {
		this.prodQuality = prodQuality;
	}
	public String getProdCreated() {
		return prodCreated;
	}
	public void setProdCreated(String prodCreated) {
		this.prodCreated = prodCreated;
	}
	public String getProdAddr() {
		return prodAddr;
	}
	public void setProdAddr(String prodAddr) {
		this.prodAddr = prodAddr;
	}
	public String getProdTrade() {
		return prodTrade;
	}
	public void setProdTrade(String prodTrade) {
		this.prodTrade = prodTrade;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	public String getProdContent() {
		return prodContent;
	}
	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}
	
	
	
}
