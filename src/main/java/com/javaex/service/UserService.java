package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;

	
	public void join(UserVo userVo) {
		System.out.println("userService.join");
		
		System.out.println(userVo);
		
		//users 추가
		userDao.insertUser(userVo);
		
		//블로그 개설
		
		blogDao.insertBlog(userVo);
		
		//카테고리 개설
		
		categoryDao.insertDefaultCate(userVo.getId());
		
	}

	public UserVo login(UserVo userVo) {
		System.out.println("userService:login");
		
		return userDao.selectUser(userVo);
	}
	
	
	
	
	public int idCheck(String id) {
		System.out.println("userService.idCheck");
		
		return userDao.selectSameId(id);
	}

}
