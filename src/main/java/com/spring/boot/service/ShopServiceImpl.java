package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.ShopDTO;
import com.spring.boot.mapper.ShopMapper;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopMapper shopMapper;

	@Override
	public int maxNum() throws Exception {
		
		return shopMapper.maxNum();
	}

	@Override
	public void insertData(ShopDTO dto) throws Exception {
		
		shopMapper.insertData(dto);
		
	}
	
	@Override
	public void insertTmFile(ShopDTO dto) throws Exception {

		shopMapper.insertTmFile(dto);
		
	}
	
	@Override
	public void insertFile(ShopDTO dto) throws Exception {
		
		shopMapper.insertFile(dto);		
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		
		return shopMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<ShopDTO> getList(int prodNo, ShopDTO dto) throws Exception {
		
		return shopMapper.getLists(prodNo, dto);
	}

	
	@Override
	public List<ShopDTO> getSelectLists(int prodNo, String prodSelect, ShopDTO dto) throws Exception {
		
		return shopMapper.getSelectLists(prodNo, prodSelect ,dto);
	}
	
	
	@Override
	public ShopDTO getReadData(int prodNo) throws Exception {
		
		return shopMapper.getReadData(prodNo);
	}

	@Override
	public void updateHitCount(int prodNo) throws Exception {
		
		shopMapper.updateHitCount(prodNo);
		
	}

	@Override
	public void updateData(ShopDTO dto) throws Exception {
		
		shopMapper.updateData(dto);
		
	}

	@Override
	public void deletData(int prodNo) throws Exception {
		
		shopMapper.deleteData(prodNo);
		
	}

	@Override
	public int maxImgNum() throws Exception {
		return shopMapper.maxImgNum();
	}

}
