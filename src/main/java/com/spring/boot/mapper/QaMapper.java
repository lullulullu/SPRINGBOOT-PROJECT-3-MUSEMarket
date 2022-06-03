package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.spring.boot.dto.QaDTO;


@Mapper
public interface QaMapper {

	public int maxNum() throws Exception;
	
	public void insertData(QaDTO dto) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<QaDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public QaDTO getReadData(int qaNo) throws Exception;
	
	public String getUserId(int userNo) throws Exception;
	
	public String getUserEmail(int userNo) throws Exception;
	
	public void updateData(QaDTO dto) throws Exception;
	
	public void deleteData(int qaNo) throws Exception;
	
}
