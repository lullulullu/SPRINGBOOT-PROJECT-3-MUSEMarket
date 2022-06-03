package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.MasterDTO;
import com.spring.boot.service.MasterService;
import com.spring.boot.util.MyUtil;

@RestController
public class MasterController {

	@Resource
	private MasterService masterService;
	
	@Autowired
	MyUtil myUtil;

	@RequestMapping(value = "/master_created.action",method = {RequestMethod.GET})
	public ModelAndView qa_upload() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		String userId = "Master";
		
		mav.addObject("userId",userId);
		
		mav.setViewName("bbs/master_created");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/master_created_ok.action",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView created_ok(MasterDTO dto, 
			HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		int maxNum = masterService.maxNum();
		
		dto.setMasterNo(maxNum + 1);
		// dto.setUserId("Master");
			
		
		masterService.insertData(dto);
		

		mav.setViewName("redirect:/master_list.action");
		//mav.setViewName("bbs/contact_list");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/master_list.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null)
			currentPage = Integer.parseInt(pageNum);
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue==null) {
			searchKey = "masterSubject";
			searchValue = "";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		int dataCount = masterService.getDataCount(searchKey, searchValue);
		
		int numPerPage = 10;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage>totalPage)
			currentPage = totalPage;
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		List<MasterDTO> lists = 
				masterService.getList(start, end, searchKey, searchValue);
		
		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		String listUrl = "/master_list.action";
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		String pageIndexList = 
				myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/master_article.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
				
		ModelAndView mav = new ModelAndView();
		
		String userId = "Master";
		
		mav.addObject("userId",userId);
		
		mav.addObject("lists", lists);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		
		mav.setViewName("bbs/master_list");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/master_article.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView article(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("masterNo"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		masterService.updateHitCount(num);
		
		MasterDTO dto = masterService.getReadData(num);
		
		if(dto==null) {			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/master_list.action?pageNum=" + pageNum);
			return mav;
		}
		
		int lineSu = dto.getMasterContent().split("\n").length;
		
		//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		String param = "pageNum=" + pageNum;
		if(searchValue!=null&&!searchValue.equals("")) {
			
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" + 
					URLEncoder.encode(searchValue, "UTF-8");
			
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("params", param);
		mav.addObject("lineSu", lineSu);
		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("bbs/master_article");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/master_updated.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("masterNo"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue =
					URLDecoder.decode(searchValue, "UTF-8");
		}
		
		MasterDTO dto = masterService.getReadData(num);
		
		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/master_list.action?pageNum=" + pageNum);
			return mav;
		}
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);
		
		mav.setViewName("bbs/master_updated");
		
		return mav;		
		
	}
		
	@RequestMapping(value = "/master_updated_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated_ok(MasterDTO dto,HttpServletRequest request) throws Exception{
	
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		dto.setMasterContent(dto.getMasterContent().replaceAll("<br/>", "\r\n"));
		
		masterService.updateData(dto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/master_list.action?" + param);
				
		return mav;
		
	}
	
	@RequestMapping(value = "/master_deleted_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleted_ok(HttpServletRequest request) throws Exception{
	
		int num = Integer.parseInt(request.getParameter("masterNo"));
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		masterService.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/master_list.action?" + param);		
				
		return mav;
		
	}
	
	
}






