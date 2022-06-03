package com.spring.boot.dto;


public class LoginDTO {
	
	private String userId;
	private String userPwd;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", getUserId()="
				+ getUserId() + ", getUserPwd()=" + getUserPwd() + ", getUserName()=" + getUserName() + "]";
	}
	

}
