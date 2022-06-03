package com.spring.boot.service;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.boot.dto.UsersDTO;
import com.spring.boot.mapper.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersmapper;

	@Override
	public UsersDTO login(UsersDTO dto) throws Exception {
		return usersmapper.login(dto);
	}

	@Override
	public void write(UsersDTO dto) throws Exception {
		usersmapper.write(dto);
	}

	@Override
	public boolean isUserId(UsersDTO dto) throws Exception {
		int userIdCount = usersmapper.userIdChk(dto);
		return 0 < userIdCount;
	}

	@Override
	public UsersDTO getReadData(int userNo) throws Exception {
		return usersmapper.getReadData(userNo);
	}

	@Override
	public int getDataCount() throws Exception {
		return usersmapper.getDataCount();
	}

	@Override
	public List<UsersDTO> getList() throws Exception {
		return usersmapper.getList();
	}

	@Override
	public void updateData(UsersDTO dto) throws Exception {
		usersmapper.updateData(dto);
	}

	@Override
	public UsersDTO update_mypage(UsersDTO dto) throws Exception {
		usersmapper.update_mypage(dto);
		
		return usersmapper.login(dto);
	}

	@Override
	public String findUserId(UsersDTO dto) throws Exception {
		UsersDTO usersDTO = usersmapper.findUserId(dto);
		if (ObjectUtils.isEmpty(usersDTO)) {
			return "";
		}
		return usersDTO.getUserId();
	}

	@Override
	public String findUserPwd(UsersDTO dto) throws Exception {
		UsersDTO usersDTO = usersmapper.findUserPwd(dto);
		if (ObjectUtils.isEmpty(usersDTO)) {
			return "";
		}
		return usersDTO.getUserPwd();
	}

	@Override
	public UsersDTO membermodifyGET(String userId) throws Exception {
		return usersmapper.memberModifyGET(userId);
	}

	@Override
	public void memberModifyPOST(UsersDTO dto) throws Exception {
	
		usersmapper.memberModifyPOST(dto);
	}

	



}
