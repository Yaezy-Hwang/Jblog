package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class MainService {
	
	@Autowired
	private BlogDao blogDao;

	public List<BlogVo> search(String keyword, String kwdOpt) {
		System.out.println("Mservice-검색");
		
		Map<String, String> sMap = new HashMap<>();
		sMap.put("keyword", keyword);
		sMap.put("kwdOpt", kwdOpt);
		
		return blogDao.selectByKeyword(sMap);
	}

}
