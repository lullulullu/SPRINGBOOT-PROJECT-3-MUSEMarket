package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.CommuDTO;
import com.spring.boot.mapper.CommuMapper;

@Service("CommuService")
public class CommuServiceImpl implements CommuService{

	@Autowired
	private CommuMapper commuMapper;//commuMapper DI 의존성 주입

	@Override
	public int maxNum() throws Exception {
		
		return commuMapper.maxNum();
	}

	@Override
	public void insertData(CommuDTO dto) throws Exception {
		
		commuMapper.insertData(dto);
		
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return commuMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<CommuDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception {
		
		return commuMapper.getList(start, end, searchKey, searchValue);
	}

	@Override
	public CommuDTO getReadData(int commuNo) throws Exception {
		
		return commuMapper.getReadData(commuNo);
	}

	@Override
	public void updateHitCount(int commuNo) throws Exception {
		commuMapper.updateHitCount(commuNo);
	}

	@Override
	public void updateData(CommuDTO dto) throws Exception {
		
		commuMapper.updateData(dto);
	}

	@Override
	public void deleteData(int commuNo) throws Exception {
		
		commuMapper.deleteData(commuNo);
	}

	@Override
	public String getUserId(int userNo) throws Exception {
		return commuMapper.getUserId(userNo);
	}

	

	
	//찾아가는 순서
	//BoardController -> BoardService(Interface) -> BoardServiceImpl(Class) -> BoardMapper(I) -> boardMapper.xml
	
	
	
}