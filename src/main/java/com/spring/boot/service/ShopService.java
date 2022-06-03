package com.spring.boot.service;

import java.util.List;

import com.spring.boot.dto.ShopDTO;



public interface ShopService {
	
	//페이징 처리
	public int maxNum() throws Exception;
	
	public int maxImgNum() throws Exception;
	
	//데이터 삽입
	public void insertData(ShopDTO dto) throws Exception;
	
	//대표이미지 삽입
	public void insertTmFile(ShopDTO dto) throws Exception;
	
	//파일삽입
	public void insertFile(ShopDTO dto) throws Exception;
	
	//데이터 갯수 카운팅
	public int getDataCount (String searchKey,String searchValue) throws Exception;
	
	//리스트 풀기
	public List<ShopDTO> getList(int prodNo, ShopDTO dto ) throws Exception;
	
	//셀렉트 리스트
	public List<ShopDTO> getSelectLists(int prodNo, String prodSelect, ShopDTO dto) throws Exception;
	
	//하나의 데이터 일겅오기
	public ShopDTO getReadData(int listNo) throws Exception;
	
	//아티클 안에서 hitCount 만드는거
	public void updateHitCount(int listNo) throws Exception;
	
	//데이터 수정
	public void updateData(ShopDTO dto) throws Exception;
	
	//데이터 아티클에서 삭제
	public void deletData(int listNo) throws Exception;

}
