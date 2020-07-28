package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void insertBlog(UserVo userVo) {
		System.out.println("blogDao.insertBlog");
		
		sqlSession.insert("blog.insertBlog", userVo);
	}

	public BlogVo selectBlogInfo(String id) {
		System.out.println("blogDao.selectBlogInfo");
		
		return sqlSession.selectOne("blog.selectBlogInfo", id);
	}

	public int updateBlogBasic(BlogVo blogVo) {
		System.out.println("blogDao.updateBlogBasic");
		
		return sqlSession.update("blog.updateBlogBasic", blogVo);
	}

	public List<BlogVo> selectByKeyword(Map<String, String> sMap) {
		System.out.println("blogDao.selectByKeyword");
		
		return sqlSession.selectList("blog.selectByKeyword", sMap);
	}

}
