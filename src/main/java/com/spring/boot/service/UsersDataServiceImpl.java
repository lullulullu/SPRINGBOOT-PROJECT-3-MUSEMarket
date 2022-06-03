package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.boot.dto.UsersDTO;
import com.spring.boot.dto.UsersDataDTO;
import com.spring.boot.mapper.UsersDataMapper;
import com.spring.boot.mapper.UsersMapper;

@Service
public class UsersDataServiceImpl implements UsersDataService {
	
	@Autowired
	private UsersDataMapper usersDataMapper;


	@Override
	public UsersDataDTO myHeartProduct(int prodNo) throws Exception {
		return usersDataMapper.myHeartProduct(prodNo);
	}

	@Override
	public List<UsersDataDTO> mySaleProduct(int userNo) throws Exception {
		return usersDataMapper.mySaleProduct(userNo);
	}

	@Override
	public List<UsersDataDTO> myReview(int userNo) throws Exception {
		return usersDataMapper.myReview(userNo);
	}

	@Override
	public UsersDataDTO myReviewProduct(int prodNo) throws Exception {
		return usersDataMapper.myReviewProduct(prodNo);
	}

	@Override
	public List<Integer> myUserHeart(int userNo) throws Exception {
		return usersDataMapper.myUserHeart(userNo);
	}

	@Override
	public List<UsersDataDTO> myCommu(int userNo) throws Exception {
		return usersDataMapper.myCommu(userNo);
	}


}
