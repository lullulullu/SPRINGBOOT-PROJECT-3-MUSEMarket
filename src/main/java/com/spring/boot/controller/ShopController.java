package com.spring.boot.controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.ShopDTO;
import com.spring.boot.dto.UsersDTO;
import com.spring.boot.service.ShopService;
import com.spring.boot.util.MyUtil;

@RestController
public class ShopController {

	@Resource
	private ShopService shopService;

	@Autowired
	MyUtil myUtil;

	// 여기서 list에서 넘어오는 데이터를 받고,,

	@RequestMapping(value = "/upload.action", method = { RequestMethod.GET })
	public ModelAndView upload(HttpSession session) throws Exception {
		
		UsersDTO login = (UsersDTO) session.getAttribute("login");
		 System.out.println(login.getUserNo());
		
		ModelAndView mav = new ModelAndView();

		mav.setViewName("shop/write");

		return mav;
	}

	  @RequestMapping(value = "/upload_ok.action", 
			method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView upload_ok(@RequestParam("upfiles")MultipartFile[] mFile,HttpSession session, @RequestParam("tupload")MultipartFile tmFile,HttpServletRequest request, ShopDTO dto) 
			throws Exception{
		
			 UsersDTO login = (UsersDTO) session.getAttribute("login");
			 System.out.println(login.getUserNo());
		  
		String UPLOAD_PATH = "D:\\sts-bundle\\work\\MuseMarket\\src\\main\\resources\\static\\upload"; // 업로드 할 위치
		
		ModelAndView mav = new ModelAndView();
		
			
			// product 테이블 인서트 ---------------------------
			int maxNum = shopService.maxNum();

			dto.setUserNo(login.getUserNo());
			dto.setProdNo(maxNum + 1);
			
			shopService.insertData(dto);
			//--------------------------------------------------
			
			// productFile 테이블 ---------------------------------------------------------------------------
			for(int i=0;i<mFile.length;i++) {
				
				MultipartFile file = mFile[i];
				

				// 이름 설정용 :: 파일 시간으로 해서 이름 설정 : 
				String files = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt());
				
				// 오리진파일 :: 
				String originFile = file.getOriginalFilename();
				
				// 확장자 확인용 :: 
				String fileExtension = originFile.substring(originFile.lastIndexOf(".") + 1);
				
				// 오리진파일 :: 이름을 정할 필요가 있을까..??? 밑에는.jpg 가 빠진 상태 :: 테이블을 바꾸면 삭제해도 됌
				originFile = originFile.substring(0, originFile.lastIndexOf(".")); 
				
				// upload경로를
				 //String fileName = String.valueOf(i+ 1);
				// File fileSave = new File(UPLOAD_PATH + "\\" + dto.getProdNo(), fileName+ "." + fileExtension); 
				// 여기서 UPLOAD_PATH 는 어디일까용..????
				File fileSave = new File(UPLOAD_PATH, files + "." + fileExtension); // 저장될 폴더, 저장될 파일 이름
				
				// 이미지 저장할 경로 폴더 유무 확인 후 없을시 생성
				if(!fileSave.exists()) {
					fileSave.mkdirs();
				}
				
				// 이미지 실제 경로로 노트북에 저장			
				file.transferTo(fileSave);
						
				// productFile 에 데이터 insert : 여기는 오케이링
				int maxImgNo = shopService.maxImgNum();
				dto.setImgNo(maxImgNo+1);
				//dto.setSaveFileName(fileName);
				dto.setSaveFileName(files + "." + fileExtension);
				dto.setOriginalFileName(originFile);				
				shopService.insertFile(dto);				
							
			} 
			
			
			// productTm 테이블 설정
			MultipartFile tfile = tmFile;
			
			String tfiles = ("t" + new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt());

			String toriginFile= tfile.getOriginalFilename();
			
			String tfileExtension = toriginFile.substring(toriginFile.lastIndexOf(".") + 1);
			
			toriginFile = toriginFile.substring(0, toriginFile.lastIndexOf("."));
			
			File tfileSave = new File(UPLOAD_PATH, tfiles + "." + tfileExtension);

			if(!tfileSave.exists()) {
				tfileSave.mkdirs();
			}
			
			tfile.transferTo(tfileSave);
			
			dto.setOriginalFileName(toriginFile);
			dto.setTmImg(tfiles + "." + tfileExtension);
			shopService.insertTmFile(dto);
			
		mav.setViewName("redirect:/shop.action");	
		
		return mav;
		
	}
	

		
		@RequestMapping(value = "/shop.action",
				method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView shop(HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			ShopDTO dto = new ShopDTO();
			
			List<ShopDTO> lists = shopService.getList(dto.getProdNo(), dto);
			
			mav.addObject("lists", lists);
			
			mav.setViewName("shop/shop");
			
			return mav;
			
			
		}
			
		
		@RequestMapping(value = "/selecshop.action",
				method = {RequestMethod.GET})
		public ModelAndView selecshop( HttpServletRequest request) throws Exception{
			
			ModelAndView mav = new ModelAndView();
			
			
			String select = request.getParameter("prodSelect");
			
			ShopDTO dto = new ShopDTO();
			
			List<ShopDTO> alists = shopService.getSelectLists(dto.getProdNo(), select, dto);
			
			
			mav.addObject("prodSelect", select);
			mav.addObject("alists", alists);
			
			
			mav.setViewName("shop/artshop");
			
			return mav;
			
			
		}
	 

}
