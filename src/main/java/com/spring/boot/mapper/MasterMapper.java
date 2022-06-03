package com.spring.boot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.spring.boot.dto.MasterDTO;

@Mapper
public interface MasterMapper {

	public int maxNum() throws Exception;
		
	public void insertData(MasterDTO dto) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<MasterDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception;
	
	public MasterDTO getReadData(int masterNo) throws Exception;
	
	public void updateHitCount(int masterNo) throws Exception;
	
	public void updateData(MasterDTO dto) throws Exception;
	
	public void deleteData(int masterNo) throws Exception;
	
}
