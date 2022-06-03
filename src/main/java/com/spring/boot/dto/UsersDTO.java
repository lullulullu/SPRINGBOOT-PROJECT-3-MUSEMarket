package com.spring.boot.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UsersDTO {
		
		private int userNo;
		private String userId;
		private String userPwd;
		private String userName;
		private String userEmail;
		private String userAddr;
		private String userTel;
		private String userGender;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private String userBirth;
		
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
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getUserAddr() {
			return userAddr;
		}
		public void setUserAddr(String userAddr) {
			this.userAddr = userAddr;
		}
		public String getUserTel() {
			return userTel;
		}
		public void setUserTel(String userTel) {
			this.userTel = userTel;
		}
		public String getUserGender() {
			return userGender;
		}
		public void setUserGender(String userGender) {
			this.userGender = userGender;
		}		
		
		public String getUserBirth() {
			return userBirth;
		}
		public void setUserBirth(String userBirth) {
			this.userBirth = userBirth;
		}
		@Override
		public String toString() {
			return "UsersDTO [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName="
					+ userName + ", userEmail=" + userEmail + ", userAddr=" + userAddr + ", userTel=" + userTel
					+ ", userGender=" + userGender + ", userBirth=" + userBirth + ", getUserNo()=" + getUserNo()
					+ ", getUserId()=" + getUserId() + ", getUserPwd()=" + getUserPwd() + ", getUserName()="
					+ getUserName() + ", getUserEmail()=" + getUserEmail() + ", getUserAddr()=" + getUserAddr()
					+ ", getUserTel()=" + getUserTel() + ", getUserGender()=" + getUserGender() + ", getUserBirth()="
					+ getUserBirth() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
					+ super.toString() + "]";
		}
		
		
		


}

