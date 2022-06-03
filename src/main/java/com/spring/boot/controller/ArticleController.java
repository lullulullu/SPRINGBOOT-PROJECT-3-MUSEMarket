package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.dto.ArticleDTO;
import com.spring.boot.dto.UsersDTO;
import com.spring.boot.service.ArticleService;
import com.spring.boot.util.MyUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	
	@Autowired
	MyUtil myUtil;
	
	@RequestMapping(value = "/sale_article.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView article(HttpServletRequest request,RedirectAttributes redirectAttributes,HttpSession session) throws Exception{
		
		// 세션세션
		UsersDTO login = (UsersDTO) session.getAttribute("login");
		 System.out.println(login.getUserNo());
		
		 int prodNo = Integer.parseInt(request.getParameter("prodNo")); 
		 System.out.println(prodNo+"prodNo");
		 redirectAttributes.addAttribute("prodNo",prodNo); 
		  
		articleService.updateHitCount(prodNo);
		
		ArticleDTO dto = articleService.getReadData(prodNo);
		String userId  = articleService.getUserId(dto.getUserNo()); // 판매자 아이디
		
		int reviewCount = articleService.getReviewCount(prodNo);
		
		int heartCount = articleService.heartCount(prodNo);
		System.out.println(heartCount+"bbbb");
		// 세션으로 세션유저 가져오기
		ArticleDTO userDto = new ArticleDTO();
		userDto.setProdNo(prodNo);
		userDto.setUserId(login.getUserId());
		userDto.setUserNo(login.getUserNo());
		
		// 하트 테이블에 해당 유저 상품 정보 아무것도 없을 때  : myheartCheck = 0, myHeartCount = 2
		int myHeartCheck = articleService.myHeartCheck(userDto);
		int myHeartCount = 2;
		
		// 정보 있을때 : myHeartCheck = 1, myHeartCount = 0(♡), myHeartCount = 1(♥)
		if(myHeartCheck==1) {
			myHeartCount = articleService.myHeartCount(userDto);
			
		}
		
		List<ArticleDTO> imgDtos = articleService.findProductFile(prodNo);
		
	
		// String userId = session.getAttribute("userId");
		//String reviewUserId = "hyemin"; // 리뷰작성자 아이디 (세션)
		
		
		dto.setProdContent(dto.getProdContent().replaceAll("<br/>", "\n"));
	
		  
		System.out.println(heartCount+"aaaaaaaaaaaaaaaaa");
		System.out.println(myHeartCheck);
		System.out.println(myHeartCount);
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("prodNo",prodNo);
		mav.addObject("imgDtos",imgDtos);
		mav.addObject("heartCount",heartCount);
		mav.addObject("myHeartCheck",myHeartCheck);
		mav.addObject("myHeartCount",myHeartCount);
		mav.addObject("reviewCount",reviewCount);
		mav.addObject("userId",userId);
		mav.addObject("dto", dto);
		mav.addObject("userDto", userDto);
		
		mav.setViewName("article/article");
		
		return mav;
	}

	@RequestMapping(value = "/sale_list.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request) throws Exception{
		
		 int prodNo = Integer.parseInt(request.getParameter("prodNo")); 

		List<ArticleDTO> lists = articleService.listData(prodNo);
		
		for(int i=0;i<lists.size();i++) {
			
			int reviewUserNo = lists.get(i).getUserNo();
			
			String reviewUserId = articleService.getUserId(reviewUserNo);
			
			lists.get(i).setReviewUserId(reviewUserId);
			
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("lists",lists);
		
		mav.setViewName("article/list");
		
		return mav;
	}
	
	@RequestMapping(value = "/sale_create.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView create(ArticleDTO dto, HttpServletRequest request, RedirectAttributes redirectAttributes,HttpSession session) throws Exception{
		
		// 세션세션
		UsersDTO login = (UsersDTO) session.getAttribute("login");
		 System.out.println(login.getUserNo());
		
		int prodNo = dto.getProdNo();
		
		redirectAttributes.addAttribute("prodNo",prodNo);
		
		String aa = dto.getReviewContent();
		
		String userId = articleService.getUserId(login.getUserNo());
		int numMax = articleService.numMax();
		
		dto.setReviewNo(numMax + 1);
		dto.setProdNo(prodNo);
		dto.setUserNo(login.getUserNo());
		dto.setReviewIpAddr(request.getRemoteAddr());
			
		articleService.insertData(dto);
			
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("prodNo",prodNo);
		mav.addObject("userNo",login.getUserNo());
		
		mav.setViewName("redirect:/sale_list.action");
		
		return mav;
	}
	
	
	/*
	 * @RequestMapping(value = "/sale_delete.action", method = {RequestMethod.GET,
	 * RequestMethod.POST}) public ModelAndView delete(ArticleDTO dto,
	 * HttpServletRequest request) throws Exception{
	 * 
	 * //int prodNo = Integer.parseInt(request.getParameter("prodNo")); //int userNo
	 * = Integer.parseInt(request.getParameter("userNo")); int reviewNo =
	 * Integer.parseInt(request.getParameter("reviewNo"));
	 * 
	 * System.out.println("여기까지"); System.out.println(reviewNo);
	 * 
	 * articleService.deleteData(reviewNo);
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * mav.setViewName("redirect:/sale_list.action");
	 * 
	 * return mav; }
	 */
	
	@RequestMapping(value = "/heartData.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView heartIn(ArticleDTO dto,HttpServletRequest request,HttpSession session) throws Exception{
	
		// 세션세션
		UsersDTO login = (UsersDTO) session.getAttribute("login");

		dto.setUserNo(login.getUserNo());

		int myHeartCheck = articleService.myHeartCheck(dto);
		
		System.out.println("myheartcheck "+myHeartCheck);
		
		if(myHeartCheck == 0) {
			
			int heartNo = articleService.heartNumMax();
			
			dto.setHeartNo(heartNo + 1);
			
			articleService.heartIn(dto);
			
		}

		if(myHeartCheck == 1) {
			System.out.println("bbbb");
			int myHeartCount = articleService.myHeartCount(dto);
			System.out.println("cccc");
			if(myHeartCount==1) {
				System.out.println("ddd");
				dto.setHeartCheck(0);
				
			}else if(myHeartCount==0) {
				System.out.println("eee");
				dto.setHeartCheck(1);
				
			}
			System.out.println("ffff");
			articleService.heartUpdate(dto); 
		}
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("article/heartData");
		
		return mav;
		
	}
	
}
