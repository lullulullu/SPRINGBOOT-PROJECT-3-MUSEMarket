package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.QaDTO;
import com.spring.boot.mapper.QaMapper;

@Service
public class QaServiceImpl implements QaService{
	
	@Autowired
	private QaMapper qaMapper;//BoardMapper DI 의존성 주입

	@Override
	public int maxNum() throws Exception {
		
		return qaMapper.maxNum();
		
	}

	@Override
	public void insertData(QaDTO dto) throws Exception {
		qaMapper.insertData(dto);
	}

	@Override
	public int getDataCount(String searchKey, String searchValue) throws Exception {
		return qaMapper.getDataCount(searchKey, searchValue);

	}

	@Override
	public List<QaDTO> getList(int start, int end, String searchKey, String searchValue) throws Exception {
		return qaMapper.getList(start, end, searchKey, searchValue);

	}

	@Override
	public QaDTO getReadData(int qaNo) throws Exception {
		return qaMapper.getReadData(qaNo);
	}

	@Override
	public void updateData(QaDTO dto) throws Exception {
		qaMapper.updateData(dto);
	}

	@Override
	public void deleteData(int qaNo) throws Exception {
		qaMapper.deleteData(qaNo);

	}

	@Override
	public String getUserId(int userNo) throws Exception {
		return qaMapper.getUserId(userNo);
	}

	@Override
	public String getUserEmail(int userNo) throws Exception {
		return qaMapper.getUserEmail(userNo);
	}

	

	
	//찾아가는 순서
	//BoardController -> BoardService(Interface) -> BoardServiceImpl(Class) -> BoardMapper(I) -> boardMapper.xml
	
	
	
}
