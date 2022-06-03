package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.MasterDTO;
import com.spring.boot.mapper.MasterMapper;


@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	private MasterMapper mastertMapper;//BoardMapper DI 의존성 주입
	
	@Override
	public int maxNum() throws Exception {
		return mastertMapper.maxNum();
	}

	@Override
	public void insertData(MasterDTO dto) throws Exception {
		mastertMapper.insertData(dto);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return mastertMapper.getDataCount(searchKey, searchValue);
	}

	@Override
	public List<MasterDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception {
		return mastertMapper.getList(start, end, searchKey, searchValue);
	}

	@Override
	public MasterDTO getReadData(int masterNo) throws Exception {
		return mastertMapper.getReadData(masterNo);
	}

	@Override
	public void updateData(MasterDTO dto) throws Exception {
		mastertMapper.updateData(dto);

	}

	@Override
	public void deleteData(int masterNo) throws Exception {
		mastertMapper.deleteData(masterNo);

	}

	@Override
	public void updateHitCount(int masterNo) throws Exception {
		mastertMapper.updateHitCount(masterNo);
	}
	
	//찾아가는 순서
	//BoardController -> BoardService(Interface) -> BoardServiceImpl(Class) -> BoardMapper(I) -> boardMapper.xml
	
	
	
}
