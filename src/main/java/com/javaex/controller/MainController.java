package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.MainService;
import com.javaex.vo.BlogVo;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;

	@RequestMapping("/main")
	public String main(@RequestParam(value="keyword", required = false) String keyword, Model model) {
		System.out.println("/main");
		
		if(keyword != null) {
			List<BlogVo> resultList = mainService.search(keyword);
			model.addAttribute("rList", resultList);
			
			return "main/index";
		}
		
		return "main/index";
	}
	
	
	
	

}
