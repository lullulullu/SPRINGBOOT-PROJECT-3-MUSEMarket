package com.spring.boot.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.UsersDataDTO;

public interface UsersDataService {
	

	public List<Integer> myUserHeart(int userNo) throws Exception;
	
	public UsersDataDTO myHeartProduct(int prodNo) throws Exception;
	
	public List<UsersDataDTO> mySaleProduct(int userNo) throws Exception;
	
	public List<UsersDataDTO> myReview(int userNo) throws Exception;
	
	public UsersDataDTO myReviewProduct(int prodNo) throws Exception;
	
	public List<UsersDataDTO> myCommu(int userNo) throws Exception;
		
		
}
