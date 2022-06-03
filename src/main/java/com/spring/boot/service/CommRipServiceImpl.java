package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CommRipDTO;
import com.spring.boot.mapper.CommRipMapper;

@Service("CommRipService")
public class CommRipServiceImpl implements CommRipService{

	@Autowired
	private CommRipMapper commuRipMapper;//commripMapper DI 의존성 주입

	@Override
	public int maxNum() throws Exception {
		return commuRipMapper.maxNum();
	}

	@Override
	public void insertReply(CommRipDTO dto) throws Exception {
		commuRipMapper.insertReply(dto);
	}

	@Override
	public int getDataCount() throws Exception {
		return commuRipMapper.getDataCount();
	}

	@Override
	public List<CommRipDTO> getReplyList(int commuNo) throws Exception {
		return commuRipMapper.getReplyList(commuNo);
	}

	@Override
	public String getCommuRipUserId(int userNo) throws Exception {
		return commuRipMapper.getCommuRipUserId(userNo);
	}

	@Override
	public List<CommRipDTO> getCommuRipList(int commuNo) throws Exception {
		return commuRipMapper.getCommuRipList(commuNo);
	}

	@Override
	public String getUserId(int userNo) throws Exception {
		return commuRipMapper.getUserId(userNo);
	}

	
	
}
