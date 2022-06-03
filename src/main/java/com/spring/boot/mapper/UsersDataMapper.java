package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.UsersDataDTO;

@Mapper
public interface UsersDataMapper {

	public List<Integer> myUserHeart(int userNo) throws Exception;
	
	public UsersDataDTO myHeartProduct(int prodNo) throws Exception;
	
	public List<UsersDataDTO> mySaleProduct(int userNo) throws Exception;
	
	public List<UsersDataDTO> myReview(int userNo) throws Exception;
	
	public UsersDataDTO myReviewProduct(int prodNo) throws Exception;
	
	public List<UsersDataDTO> myCommu(int userNo) throws Exception;
}
