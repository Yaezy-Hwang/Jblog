package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join");
		
		userService.join(userVo);
		
		return "user/joinSuccess";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		
		UserVo authUser = userService.login(userVo);
		
		if(authUser != null) { //로그인 성공 시
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
			
		} else{ //로그인 실패 시
			System.out.println("실패");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	//API
	@ResponseBody
	@RequestMapping("/idcheck")
	public boolean idcheck(String newId) {
		System.out.println("/user/idcheck");
		System.out.println("idcheck: "+ newId);
		
		int sameId = userService.idCheck(newId);
		boolean result = false;
		
		if(sameId == 0) {
			result = true;
		}
		
		return result;
	}
	



}
