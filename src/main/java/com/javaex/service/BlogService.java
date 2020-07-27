package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public BlogVo getBlogInfo(String id) {
		System.out.println("blogService.getBlogInfo");
		
		return blogDao.selectBlogInfo(id);
	}
	
	public List<CategoryVo> getCategoryList(String id) {
		System.out.println("blogService.getBlogInfo");
		
		return categoryDao.selectCateList(id);
	}

	public PostVo getPost(String id, int crtCateNo, int postNo) {
		System.out.println("blogService.getPost");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("id", id);
		pMap.put("postNo", postNo);
		
		if(crtCateNo == 0) {
			pMap.put("cateNo", postDao.recentCateNo());
		}else {	
			pMap.put("cateNo", crtCateNo);
		}
		
		return postDao.selectPost(pMap);
	}

	public List<PostVo> getPostList(String id, int crtCateNo) {
		System.out.println("blogService.getPost");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("id", id);
		
		if(crtCateNo == 0) {
			pMap.put("cateNo", postDao.recentCateNo());
			
		}else {
			pMap.put("cateNo", crtCateNo);
			
		}
		
		return postDao.selectPostList(pMap);
	}

}
