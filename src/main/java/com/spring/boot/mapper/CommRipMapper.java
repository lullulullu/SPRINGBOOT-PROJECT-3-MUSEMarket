package com.spring.boot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.spring.boot.dto.CommRipDTO;

@Mapper
public interface CommRipMapper {

	public int maxNum() throws Exception;
	
	public void insertReply(CommRipDTO dto) throws Exception;

	public int getDataCount() throws Exception;
	
	public List<CommRipDTO> getReplyList(int commuNo) throws Exception;
	
	public String getCommuRipUserId(int userNo) throws Exception;
	
	public List<CommRipDTO> getCommuRipList(int commuNo) throws Exception;
	
	public String getUserId(int userNo) throws Exception;

	
	/*
	 * public void updateReply(CommRipDTO dto) throws Exception;
	 * 
	 * public void deleteReply(int crNo) throws Exception;
	 */


}
