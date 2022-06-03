package com.spring.boot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.dto.UsersDTO;
import com.spring.boot.dto.UsersDataDTO;
import com.spring.boot.service.UsersDataService;
import com.spring.boot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLDecoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UsersController {
	
	//자동DI
	@Autowired
	private UsersService service;
	

	@Autowired
	private UsersDataService usersDataService;
	
	
	//로그인폼
	@RequestMapping(value = "/loginForm.action")
	public ModelAndView loginForm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signup/loginForm");
		
		return mav;
	
	}

	//회원가입폼
	@RequestMapping(value = "/signupForm.action")
	public ModelAndView writeForm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signup/signupForm");
		
		return mav;
	}

	//아이디찾기폼
	@RequestMapping("/findUserIdForm.action")
	public ModelAndView findUserIdForm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signup/findUserIdForm");
		
		return mav;
		
	}

	//비밀번호찾기폼
	@RequestMapping("/findUserPwdForm.action")
	public ModelAndView findUserPwdForm() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signup/findUserPwdForm");
		
		return mav;
	
	}

	//로그인 처리
	@RequestMapping("/login.action")
	@ResponseBody
	public boolean login(@RequestBody UsersDTO dto,HttpSession session, HttpServletResponse response) throws Exception{

		UsersDTO usersDTO = service.login(dto);
		if (ObjectUtils.isEmpty(usersDTO)) {
			return false;
		}
		session.setAttribute("login", usersDTO);
		session.setMaxInactiveInterval(60*60*24);
		
		return true;
	}

	//로그아웃 처리
	@RequestMapping(value = "/logout.action")
	public ModelAndView logout(HttpSession session, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("login");
		
		mav.setViewName("redirect:/");
		
		return mav;	
		
	}
	
	//회원가입처리
	@PostMapping("/signup.action")
	@ResponseBody
	public void write(@RequestBody UsersDTO dto) throws Exception {
		service.write(dto);
	}

	// 유저 아이디 중복 체크
	@RequestMapping(value = "/isUserId.action")
	@ResponseBody
	public boolean isUserId(@RequestBody UsersDTO dto) throws Exception{
		return service.isUserId(dto);
	}
	
	// 유저 아이디 찾기
	@RequestMapping("/findUserId.action")
	@ResponseBody
	public String findUserId(@RequestBody UsersDTO dto) throws Exception{
		return service.findUserId(dto);
	}

			// 유저 비밀번호 찾기
	@RequestMapping("/findUserPwd.action")
	@ResponseBody
	public String findUserPwd(@RequestBody UsersDTO dto) throws Exception{
		return service.findUserPwd(dto);
	}
		
	
	  //마이페이지
	  
	  @RequestMapping(value = "/mypage.action",
	  method = {RequestMethod.GET,RequestMethod.POST})
	  public ModelAndView mypage(UsersDTO
	  dto, HttpSession session,HttpServletRequest request)
			  throws Exception{
	  
	 UsersDTO login = (UsersDTO) session.getAttribute("login");
	 int usersDataNo = login.getUserNo();
	  
	 List<UsersDataDTO> heartLists = new ArrayList<UsersDataDTO>();
	  ModelAndView mav = new ModelAndView();
	 
	 // 마이페이지 정보 가져오기
	  service.getReadData(usersDataNo);
	  
	  // 내 제품 판매 내역
	  List<UsersDataDTO> saleLists =usersDataService.mySaleProduct(usersDataNo);
	  
	  // 내가 찜한 목록
	  List<Integer> heartProdNoLists = usersDataService.myUserHeart(usersDataNo);
	  
	  if(heartProdNoLists!=null) {
	  
		for(int i=0;i<heartProdNoLists.size();i++) {
			
			int heartProdNo = heartProdNoLists.get(i);
			
			UsersDataDTO heartDTO = usersDataService.myHeartProduct(heartProdNo);
			
			heartLists.add(heartDTO);
			
		}
	  }
		
	        // 내가 댓글 쓴 목록
			List<UsersDataDTO> reviewLists =usersDataService.myReview(usersDataNo);
			  
				for(int i=0;i<reviewLists.size();i++) {
					
					int reviewProdNo = reviewLists.get(i).getProdNo();
					
					UsersDataDTO reviewProductDTO =  usersDataService.myReviewProduct(reviewProdNo);
					
					reviewLists.get(i).setProdNo(reviewProductDTO.getProdNo());
					reviewLists.get(i).setProdName(reviewProductDTO.getProdName());
					reviewLists.get(i).setProdCreated(reviewProductDTO.getProdCreated());
					reviewLists.get(i).setProdPrice(reviewProductDTO.getProdPrice());
					reviewLists.get(i).setProdSubject(reviewProductDTO.getProdSubject());
					
				}
			
			List<UsersDataDTO> commuLists = usersDataService.myCommu(usersDataNo);
			
			mav.addObject("commuLists",commuLists);
		
			mav.addObject("reviewLists",reviewLists);
			mav.addObject("saleLists",saleLists);
			mav.addObject("heartLists",heartLists);
			mav.addObject("dto",login); 
	
			mav.setViewName("mypage/mypage");
		  
			return mav;
	  
	  }
	
	  //개인 정보 수정
	  @JsonProperty("dto")
	  @RequestMapping(value = "mypage_updated.action",
			  method = {RequestMethod.GET, RequestMethod.POST})
	  public String memberModifyGET(UsersDTO dto, HttpSession session,
			  HttpServletRequest request, Model model) throws Exception{
	        
		  UsersDTO login = (UsersDTO) session.getAttribute("login");
		  System.out.println(login.getUserId());
		  
		  UsersDTO modifyMember = service.membermodifyGET(login.getUserId());
		   System.out.println(modifyMember.getUserBirth());	   
		   
	      model.addAttribute("userName", modifyMember.getUserName());
	      model.addAttribute("userId", modifyMember.getUserId());
	      model.addAttribute("userEmail", modifyMember.getUserEmail());
	      model.addAttribute("userPwd", modifyMember.getUserPwd());
	      model.addAttribute("userTel", modifyMember.getUserTel());
	      model.addAttribute("userBirth", modifyMember.getUserBirth());
	      model.addAttribute("userAddr", modifyMember.getUserAddr());
	      model.addAttribute("userGender", modifyMember.getUserGender());
	        
	      return "mypage/updated";    
	  
	  }
	 
		
		@RequestMapping("/mypage_updated_ok.action")
		@ResponseBody
		public String memberModifyPOST(@RequestBody UsersDTO dto,HttpSession session)
				throws Exception{

			service.memberModifyPOST(dto);
			System.out.println(dto.getUserNo());
			System.out.println(dto.getUserName());
			System.out.println(dto.getUserId());
			System.out.println(dto.getUserGender());
			session.setAttribute("login", dto);
			
			return "mypage/updated";
		}
	
}