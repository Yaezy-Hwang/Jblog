package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class AdminService {
	
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
	
	public int updateBasic(String id, MultipartFile logoFile, String blogTitle) {
		System.out.println("AdminService.updateBasic");
		
		String saveDir = "C:\\javaStudy\\upload";
		String saveName = "";
		
		if(logoFile.getOriginalFilename() != "") {
			// 확장자
			String exName = logoFile.getOriginalFilename().substring(logoFile.getOriginalFilename().lastIndexOf("."));
			
			// 저장 파일 이름
			saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
			
			// 파일 경로
			String filePath = saveDir + "\\" + saveName;
			
			// 파일을 서버에 복사
			try {
				byte[] fileData = logoFile.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
				bout.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// basic정보 vo에 저장
		BlogVo blogVo = new BlogVo(id, blogTitle, saveName);
		
		return blogDao.updateBlogBasic(blogVo);
	}



	public CategoryVo addCategory(CategoryVo categoryVo) {
		System.out.println("AdminService.addCategory");
		
		int nextSeq = categoryDao.nextSeq();
		categoryVo.setCateNo(nextSeq);
		
		categoryDao.insertCategory(categoryVo);
		
		CategoryVo newCate = categoryDao.selectNewCate(nextSeq);
		
		return newCate;
	}


	public List<CategoryVo> adminCateList(String id) {
		System.out.println("AdminService.카테고리 불러오기");
		
		return categoryDao.selectAdminList(id);
	}



	public int delCategory(int cateNo) {
		System.out.println("AdminService.카테고리 삭제");
		
		return categoryDao.deleteCategory(cateNo);
	}

	public void writePost(PostVo postVo) {
		System.out.println("AdminService.포스트 쓰기");
		
		postDao.insertPost(postVo);
	}
	
	
}
