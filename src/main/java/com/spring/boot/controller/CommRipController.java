//package com.spring.boot.controller;
//
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.spring.boot.dto.CommRipDTO;
//import com.spring.boot.dto.CommuDTO;
//import com.spring.boot.service.CommRipService;
//import com.spring.boot.util.MyUtil;
//
//@RestController
//public class CommRipController {
//	
//
//	@Resource
//	private CommRipService commripService;
//	
//	@Autowired
//	MyUtil myUtil;
//	
//	@RequestMapping(value = "/reply_list.action",
//			method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView list(HttpServletRequest request, CommRipDTO dto) throws Exception{
//		
//		String pageNum = request.getParameter("pageNum");
//		
//		int currentPage = 1;
//		
//		if(pageNum!=null)
//			currentPage = Integer.parseInt(pageNum);
//		
//		String searchKey = request.getParameter("searchKey");
//		String searchValue = request.getParameter("searchValue");
//		
//		if(searchValue==null) {
//			searchKey = "userId";
//			searchValue = "";
//		}else {
//			if(request.getMethod().equalsIgnoreCase("GET")) {
//				searchValue = URLDecoder.decode(searchValue, "UTF-8");
//			}
//		}
//		
//		int dataCount = commripService.getDataCount(searchKey, searchValue);
//		
//		int numPerPage = 5;
//		int totalPage = myUtil.getPageCount(numPerPage, dataCount);
//		
//		if(currentPage>totalPage)
//			currentPage = totalPage;
//		
//		int start = (currentPage-1)*numPerPage+1;
//		int end = currentPage*numPerPage;
//		
//		//int commuNo = Integer.parseInt(request.getParameter("commuNo"));
//		int commuNo = 0;
//		List<CommRipDTO> lists = 
//				commripService.getReplyList(commuNo, start, end, searchKey, searchValue);
//		
//		String param = "";
//		if(searchValue!=null&&!searchValue.equals("")) {
//			param = "searchKey=" + searchKey;
//			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
//		}
//		
//		String listUrl = "/reply_list.action";
//		
//		if(!param.equals("")) {
//			listUrl += "?" + param;
//		}
//		
//		String pageIndexList = 
//				myUtil.pageIndexList(currentPage, totalPage, listUrl);
//		
//		String articleUrl = "/reply_article.action?pageNum=" + currentPage;
//		
//		if(!param.equals("")) {
//			articleUrl += "&" + param;
//		}
//				
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("lists", lists);
//		mav.addObject("articleUrl", articleUrl);
//		mav.addObject("pageIndexList", pageIndexList);
//		mav.addObject("dataCount", dataCount);
//		
//		mav.setViewName("comment/reply_list");
//		
//		return mav;
//		
//	}
//	
//	@RequestMapping(value = "/reply_article.action",
//			method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView article(HttpServletRequest request) throws Exception{
//		
//		int num = Integer.parseInt(request.getParameter("crNo").trim());
//		String pageNum = request.getParameter("pageNum");
//		
//		String searchKey = request.getParameter("searchKey");
//		String searchValue = request.getParameter("searchValue");
//		
//		if(searchValue!=null) {
//			searchValue = URLDecoder.decode(searchValue, "UTF-8");
//		}
//		
//		
//		CommRipDTO dto = commripService.getReadData(num);
//		
//		if(dto==null) {			
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("redirect:/reply_list.action?pageNum=" + pageNum);
//			return mav;
//		}
//		
//		int lineSu = dto.getCrContent().split("\n").length;
//		
//		//dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
//		
//		String param = "pageNum=" + pageNum;
//		if(searchValue!=null&&!searchValue.equals("")) {
//			
//			param += "&searchKey=" + searchKey;
//			param += "&searchValue=" + 
//					URLEncoder.encode(searchValue, "UTF-8");
//			
//		}
//		
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("dto", dto);
//		mav.addObject("params", param);
//		mav.addObject("lineSu", lineSu);
//		mav.addObject("pageNum", pageNum);
//		
//		mav.setViewName("comment/reply_view");
//		
//		return mav;
//		
//	}
//	
//	/*
//	 * @RequestMapping("/reply_list_json.action") public
//	 * List<CommRipDTO>list_json(int commuNo, int start, int end, String searchKey,
//	 * String searchValue) throws Exception{
//	 * 
//	 * return commripService.getReplyList(commuNo, start, end, searchKey,
//	 * searchValue);
//	 * 
//	 * }
//	 */
//	
//	@RequestMapping(value = "/insert.action",
//			method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView insert(CommRipDTO dto1, HttpSession session,HttpServletRequest request,
//			 @RequestParam(value="crContent") String crContent,
//			 @RequestParam(value="crNo") int crNo) throws Exception{
//		
//		 if (session.getAttribute("userId") != null) {
//	            
//		        String userId = (String)session.getAttribute("userId");
//		        dto1.setUserId(userId);
//		        
//		 }
//		 
//		int num = Integer.parseInt(request.getParameter("crNo"));
//		String pageNum = request.getParameter("pageNum");
//		
//		String searchKey = request.getParameter("searchKey");
//		String searchValue = request.getParameter("searchValue");
//		
//		if(searchValue!=null) {
//			searchValue =
//					URLDecoder.decode(searchValue, "UTF-8");
//		}
//		
//		CommRipDTO dto2 = commripService.getReadData(num);
//		
//		if(dto2==null) {
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("redirect:/reply_list.action?pageNum=" + pageNum);
//			return mav;
//		}
//		
//		String param = "pageNum=" + pageNum;
//		
//		if(searchValue!=null&&!searchValue.equals("")) {
//			param += "&searchKey=" + searchKey;
//			param += "&searchValue=" +
//					URLEncoder.encode(searchValue, "UTF-8");
//		}
//		
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("dto", dto2);
//		mav.addObject("pageNum", pageNum);
//		mav.addObject("params", param);
//		mav.addObject("searchKey", searchKey);
//		mav.addObject("searchValue", searchValue);
//		
//		//mav.setViewName("comment/insert");
//		
//		dto1.setCrContent(crContent);
//	    dto1.setCrNo(crNo);	      
//	        
//	    //댓글이 테이블에 저장된다
//	    commripService.insertReply(dto1);
//	        
//	    return mav;
//		
//	}
//	
//	@RequestMapping(value = "/reply_updated.action",
//			method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView updated(HttpServletRequest request,
//			@RequestParam(value="commuNo") int commuNo,
//			@RequestParam(value="crContent") String crContent,
//			@RequestParam(value="userId") String userId) throws Exception{
//		
//		int num = Integer.parseInt(request.getParameter("crNo"));
//		String pageNum = request.getParameter("pageNum");
//		
//		String searchKey = request.getParameter("searchKey");
//		String searchValue = request.getParameter("searchValue");
//		
//		if(searchValue!=null) {
//			searchValue =
//					URLDecoder.decode(searchValue, "UTF-8");
//		}
//		
//		CommRipDTO dto = commripService.getReadData(num);
//		
//		if(dto==null) {
//			ModelAndView mav = new ModelAndView();
//			mav.setViewName("redirect:/reply_list.action?pageNum=" + pageNum);
//			return mav;
//		}
//		
//		String param = "pageNum=" + pageNum;
//		
//		if(searchValue!=null&&!searchValue.equals("")) {
//			param += "&searchKey=" + searchKey;
//			param += "&searchValue=" +
//					URLEncoder.encode(searchValue, "UTF-8");
//		}
//		
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("dto", dto);
//		mav.addObject("pageNum", pageNum);
//		mav.addObject("params", param);
//		mav.addObject("searchKey", searchKey);
//		mav.addObject("searchValue", searchValue);
//		
//		//mav.setViewName("comment/reply_updated");
//		
//		dto.setCommuNo(commuNo);
//		dto.setCrContent(crContent);
//	    dto.setUserId(userId);	 
//        
//        System.out.println("dto에 있는값들 출력함"+dto);
// 
//        commripService.updateReply(dto);
//        
//		return mav;		
//		
//	}
//		
//	@RequestMapping(value = "/reply_deleted.action",
//			method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView deleted(HttpServletRequest request) throws Exception{
//	
//		int num = Integer.parseInt(request.getParameter("crNo"));
//		String pageNum = request.getParameter("pageNum");		
//		String searchKey = request.getParameter("searchKey");
//		String searchValue = request.getParameter("searchValue");
//				
//		commripService.deleteReply(num);
//		
//		String param = "pageNum=" + pageNum;
//		
//		if(searchValue!=null&&!searchValue.equals("")) {
//			param += "&searchKey=" + searchKey;
//			param += "&searchValue=" +
//					URLEncoder.encode(searchValue, "UTF-8");
//		}
//			
//		ModelAndView mav = new ModelAndView();
//		
//		mav.setViewName("redirect:/reply_list.action?" + param);		
//				
//		return mav;
//		
//	}
//	
//	
//}
//
//
//
//
//
//
