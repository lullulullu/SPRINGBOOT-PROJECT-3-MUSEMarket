package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.CommuDTO;


@Mapper
public interface CommuMapper {

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
