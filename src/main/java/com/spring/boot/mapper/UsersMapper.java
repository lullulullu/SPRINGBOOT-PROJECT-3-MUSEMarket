package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.UsersDTO;

@Mapper
public interface UsersMapper {
	
	//1.로그인
	public UsersDTO login(UsersDTO dto) throws Exception;
	
	//2.회원가입
	public int write(UsersDTO dto) throws Exception;

	//3.아이디 중복 체크
	int userIdChk(UsersDTO dto) throws Exception;

	//mypage
	public UsersDTO getReadData(int userNo) throws Exception;
	
	public int getDataCount() throws Exception;
	
	public List<UsersDTO> getList() throws Exception;

	public void updateData(UsersDTO dto) throws Exception;

	public UsersDTO update_mypage(UsersDTO dto) throws Exception;
	
	UsersDTO findUserId(UsersDTO dto) throws Exception;

	UsersDTO findUserPwd(UsersDTO dto) throws Exception;
	
	public UsersDTO memberModifyGET(String userId) throws Exception;

	public void memberModifyPOST(UsersDTO dto) throws Exception;

}
