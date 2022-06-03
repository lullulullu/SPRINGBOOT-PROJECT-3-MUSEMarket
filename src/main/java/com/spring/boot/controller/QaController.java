package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.QaDTO;
import com.spring.boot.dto.UsersDTO;
import com.spring.boot.service.QaService;
import com.spring.boot.util.MyUtil;

@RestController
public class QaController {

	@Resource
	private QaService qaService;
	
	@Autowired
	MyUtil myUtil;

	
	@RequestMapping(value = "/qa_contact.action",method = {RequestMethod.GET})
	public ModelAndView qa_upload() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("bbs/qa_contact");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/qa_contact_ok.action",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView created_ok(QaDTO dto, 
			HttpServletRequest request, HttpSession session) throws Exception{

		UsersDTO login = (UsersDTO) session.getAttribute("login");
		
		ModelAndView mav = new ModelAndView();
		
		int maxNum = qaService.maxNum();
		
		dto.setQaNo(maxNum + 1);
		
		int userNo = login.getUserNo();
		
		dto.setUserNo(userNo);
		dto.setUserId(qaService.getUserId(userNo));
		dto.setUserEmail(qaService.getUserEmail(userNo));
		
		
		qaService.insertData(dto);
		
		mav.addObject("userNo",userNo);
		mav.addObject("dto",dto);
		
		System.out.println(dto.getUserId());
		
		mav.setViewName("redirect:/index.action");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/qa_list.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request,
			HttpSession session) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null)
			currentPage = Integer.parseInt(pageNum);
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue==null) {
			searchKey = "qaSubject";
			searchValue = "";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		int dataCount = qaService.getDataCount(searchKey, searchValue);
		
		int numPerPage = 10;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage>totalPage)
			currentPage = totalPage;
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		List<QaDTO> lists = 
				qaService.getList(start, end, searchKey, searchValue);
		
		for(int i=0;i<lists.size();i++) {
			
			int userNo = lists.get(i).getUserNo();
			
			String userId = qaService.getUserId(userNo);
			
			lists.get(i).setUserId(userId);
			
			System.out.println();
			
		}
		
		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		String listUrl = "/qa_list.action";
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		String pageIndexList = 
				myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/qa_article.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("lists", lists);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		
		mav.setViewName("bbs/qa_list");
		
		return mav;
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/qa_article.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView article(HttpServletRequest request,
			HttpSession session) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("qaNo"));
		
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		//qaService.updateHitCount(num);
		
		QaDTO dto = qaService.getReadData(num);
		int userNo = dto.getUserNo();
		
		if(dto==null) {			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/qa_list.action?pageNum=" + pageNum);
			return mav;
		}
		
		int lineSu = dto.getQaContent().split("\n").length;
		
		//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		dto.setUserId(qaService.getUserId(userNo));
		dto.setUserEmail(qaService.getUserEmail(userNo));
		
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
		
		mav.setViewName("bbs/qa_article");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/qa_updated.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated(HttpServletRequest request,
			HttpSession session) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("qaNo"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue =
					URLDecoder.decode(searchValue, "UTF-8");
		}
		
		QaDTO dto = qaService.getReadData(num);
		
		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/qa_list.action?pageNum=" + pageNum);
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
		
		mav.setViewName("bbs/qa_updated");
		
		return mav;		
		
	}
		
	@RequestMapping(value = "/qa_updated_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated_ok(QaDTO dto,HttpServletRequest request,
			HttpSession session) throws Exception{
	
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		dto.setQaContent(dto.getQaContent().replaceAll("<br/>", "\r\n"));
		
		qaService.updateData(dto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/qa_list.action?" + param);
				
		return mav;
		
	}
	
	@RequestMapping(value = "/qa_deleted_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleted_ok(HttpServletRequest request,
			HttpSession session) throws Exception{
	
		int num = Integer.parseInt(request.getParameter("qaNo"));
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		qaService.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/qa_list.action?" + param);		
				
		return mav;
		
	}
	
	
}






