package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.CommuDTO;

public interface CommuService {

	public int maxNum() throws Exception;
	
	public void insertData(CommuDTO dto) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<CommuDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public CommuDTO getReadData(int commuNo) throws Exception;
	
	public void updateHitCount(int commuNo) throws Exception;
	
	public void updateData(CommuDTO dto) throws Exception;
	
	public void deleteData(int commuNo) throws Exception;

	public String getUserId(int userNo) throws Exception;
	
}
