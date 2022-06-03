package com.spring.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.ShopDTO;

@Mapper
public interface ShopMapper {
	
	public int maxNum() throws Exception;
	
	public int maxImgNum() throws Exception;
	
	public void insertData(ShopDTO dto) throws Exception;
	
	public void insertTmFile(ShopDTO dto) throws Exception;
	
	public void insertFile(ShopDTO dto) throws Exception;
	
	
	public int getDataCount(String searchKey,String searchValue) throws Exception;	
	
	public List<ShopDTO> getLists(int prodNo, ShopDTO dto) throws Exception;
	
	//셀렉트 리스트
		public List<ShopDTO> getSelectLists(int prodNo, String prodSelect, ShopDTO dto) throws Exception;
	
	
	//article하나의 데이터 읽어오는것
	
	public ShopDTO getReadData(int prodNo) throws Exception;
	
	//article 조회수 증가
	public void updateHitCount(int prodNo) throws Exception;
	
	//create 수정
	public void updateData(ShopDTO dto) throws Exception;
	
	//article 데이터 삭제 
	public void deleteData(int prodNo) throws Exception;


	
	

}
