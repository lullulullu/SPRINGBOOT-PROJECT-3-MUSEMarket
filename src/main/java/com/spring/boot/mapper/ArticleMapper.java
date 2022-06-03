package com.spring.boot.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.boot.dto.ArticleDTO;

@Mapper
public interface ArticleMapper {

	public ArticleDTO getReadData(int prodNo) throws Exception;
	
	public void updateHitCount(int prodNo) throws Exception;
	
	public String getUserId(int userNo) throws Exception;

	public int numMax() throws Exception;
	
	public void insertData(ArticleDTO articleDTO) throws Exception;
	
	public int dataCount() throws Exception;
	
	public List<ArticleDTO> listData(int prodNo) throws Exception;
	
	public void deleteData(int num) throws Exception;

	public int getReviewCount(int prodNo) throws Exception;
	
	public void heartIn(ArticleDTO articleDTO) throws Exception;

	public int heartCount(int prodNo) throws Exception;
	
	public int heartNumMax() throws Exception;
	
	public void heartUpdate(ArticleDTO articleDTO) throws Exception;

	public int myHeartCount(ArticleDTO articleDTO) throws Exception;
	
	public int myHeartCheck(ArticleDTO articleDTO) throws Exception;
	
	public List<ArticleDTO> findProductFile(int prodNo) throws Exception;
}
