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

import com.spring.boot.dto.ArticleDTO;
import com.spring.boot.dto.CommRipDTO;
import com.spring.boot.dto.CommuDTO;
import com.spring.boot.dto.LoginDTO;
import com.spring.boot.dto.UsersDTO;
import com.spring.boot.service.CommRipService;
import com.spring.boot.service.CommuService;
import com.spring.boot.util.MyUtil;

@RestController
public class CommuController {
	@Resource
	private CommuService commuService;
	
	@Resource
	private CommRipService commuRipService;
	
	@Autowired
	MyUtil myUtil;
	
	@RequestMapping(value = "/index.action")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/created.action",method = {RequestMethod.GET})
	public ModelAndView created(HttpSession session) throws Exception{
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("bbs/created");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/created_ok.action",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView created_ok(CommuDTO dto, LoginDTO dto2,
			HttpServletRequest request, HttpSession session) throws Exception{
		
		UsersDTO login = (UsersDTO) session.getAttribute("login");
		
		ModelAndView mav = new ModelAndView();
		
		int userNo = login.getUserNo();
		System.out.println(userNo);
		
		int maxNum = commuService.maxNum();
		
		dto.setUserNo(userNo);
		dto.setCommuNo(maxNum + 1);
		dto.setCommuIpAddr(request.getRemoteAddr());

		commuService.insertData(dto);		
		
		mav.setViewName("redirect:/list.action");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/list.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request, HttpSession session) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null)
			currentPage = Integer.parseInt(pageNum);
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue==null) {
			searchKey = "commuSubject";
			searchValue = "";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		int dataCount = commuService.getDataCount(searchKey, searchValue);
		
		int numPerPage = 10;
		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
		
		if(currentPage>totalPage)
			currentPage = totalPage;
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		List<CommuDTO> lists = 
				commuService.getList(start, end, searchKey, searchValue);
		
		for(int i=0;i<lists.size();i++) {
			
			int userNo = lists.get(i).getUserNo();
			
			String userId = commuService.getUserId(userNo);
			
			lists.get(i).setUserId(userId);
			
			System.out.println();
			
		}
		
		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		String listUrl = "/list.action";
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		String pageIndexList = 
				myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
				
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("lists", lists);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("dataCount", dataCount);
		
		mav.setViewName("bbs/list");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/article.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView article(HttpServletRequest request,
			HttpSession session) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("commuNo"));
		String pageNum = request.getParameter("pageNum");
		int commuNo = Integer.parseInt(request.getParameter("commuNo")); 
		
		commuService.updateHitCount(num);
		
		CommuDTO dto = commuService.getReadData(num);
		String userId = commuService.getUserId(dto.getUserNo());
		dto.setUserId(userId);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commuNo", commuNo);
	
		mav.addObject("dto", dto);

		mav.addObject("pageNum", pageNum);
		
		mav.setViewName("bbs/article");
		
		return mav;
		
	}
	
	@RequestMapping(value = "/commuRipList.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView commuRipList(CommuDTO dto,HttpServletRequest request) throws Exception{
		
		int commuNo = Integer.parseInt(request.getParameter("commuNo"));
		
		// commuRip 댓글 구현
		List<CommRipDTO> lists = commuRipService.getReplyList(commuNo);
		
		for(int i=0;i<lists.size();i++) {
			
			int commuRipUserNo = lists.get(i).getUserNo();
			
			String commuRipUserId = commuRipService.getCommuRipUserId(commuRipUserNo);
			
			lists.get(i).setUserId(commuRipUserId);
			
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("lists",lists);
		
		mav.setViewName("bbs/commuRipList");
		
		return mav;
	}
	
	@RequestMapping(value = "/commuRipCreate.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView commuRipCreate(CommRipDTO dto, HttpServletRequest request, HttpSession session) throws Exception{
			
		
		int commuNo = Integer.parseInt(request.getParameter("commuNo"));
		
		UsersDTO login = (UsersDTO) session.getAttribute("login");
		 
		int userNo = login.getUserNo();
		
		int numMax = commuRipService.maxNum();
		
		dto.setCommuRipNo(numMax+1);
		dto.setUserNo(userNo);
			
		commuRipService.insertReply(dto);

		ModelAndView mav = new ModelAndView();
		
		mav.addObject("commuNo",commuNo);
		mav.setViewName("redirect:/commuRipList.action");
		
		return mav;
		
		}
	
	@RequestMapping(value = "/updated.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated(HttpServletRequest request,
			HttpSession session) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("commuNo"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null) {
			searchValue =
					URLDecoder.decode(searchValue, "UTF-8");
		}
		
		CommuDTO dto = commuService.getReadData(num);
		
		if(dto==null) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
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
		
		mav.setViewName("bbs/updated");
		
		return mav;		
		
	}
		
	@RequestMapping(value = "/updated_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView updated_ok(CommuDTO dto, HttpServletRequest request,
			HttpSession session) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("commuNo"));
		
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		dto.setCommuContent(dto.getCommuContent().replaceAll("<br/>", "\r\n"));
		
		commuService.updateData(dto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list.action");			
				
		return mav;
		
	}
	
	@RequestMapping(value = "/deleted_ok.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleted_ok(HttpServletRequest request,
			HttpSession session) throws Exception{
	
		int num = Integer.parseInt(request.getParameter("commuNo"));
		String pageNum = request.getParameter("pageNum");		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
				
		commuService.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
			
		ModelAndView mav = new ModelAndView();
		 
		mav.setViewName("redirect:/list.action");		
				
		return mav;
		
	}
	
	
}

