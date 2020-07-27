package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class MainService {
	
	@Autowired
	private BlogDao blogDao;

	public List<BlogVo> search(String keyword) {
		System.out.println("Mservice-검색");
		
		return blogDao.selectByKeyword(keyword);
	}

}
