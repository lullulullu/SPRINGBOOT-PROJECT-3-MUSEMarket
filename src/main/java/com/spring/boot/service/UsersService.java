package com.spring.boot.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.dto.UsersDTO;

public interface UsersService {
	
		//1.로그인
		public UsersDTO login(UsersDTO dto) throws Exception;
		
		//2.회원가입
		public void write(UsersDTO dto) throws Exception;

		//3.유저아이디 중복 체크
		boolean isUserId(UsersDTO dto) throws Exception;
	
		//mypage
		public UsersDTO getReadData(int userNo) throws Exception;
		
		public int getDataCount() throws Exception;

		public List<UsersDTO> getList() throws Exception;

		public void updateData(UsersDTO dto) throws Exception;
		
		public UsersDTO update_mypage(UsersDTO dto) throws Exception;
		
		String findUserId(UsersDTO dto) throws Exception;

		String findUserPwd(UsersDTO dto) throws Exception;
		
		public UsersDTO membermodifyGET(String userId) throws Exception;
		
		public void memberModifyPOST(UsersDTO dto) throws Exception;
		
		
		
}
