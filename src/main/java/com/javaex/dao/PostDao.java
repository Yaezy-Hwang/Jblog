package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void insertPost(PostVo postVo) {
		System.out.println("postDao.insertPost");
		
		sqlSession.insert("post.insertPost", postVo);
	}
	
	public List<PostVo> selectPostList(Map<String, Object> pMap) {
		System.out.println("postDao.selectPostList");
		
		return sqlSession.selectList("post.selectPostList", pMap);
	}

	public PostVo selectPost(Map<String, Object> pMap) {
		System.out.println("postDao.selectPost");
		
		return sqlSession.selectOne("post.selectPost", pMap);
	}
	
	//최신 카테고리 넘버 찾기
	public int recentCateNo() {
		
		return sqlSession.selectOne("post.recentCateNo");
	}
	
	//현 카테고리 최신 포스트 넘버 찾기
	public int recentPostNo() {
		int recentCateNo = recentCateNo();
		
		int recentPostNo = sqlSession.selectOne("post.recentPostNo", recentCateNo);
		
		System.out.println("최신 포스트 넘버는: "+recentPostNo);
		
		return recentPostNo;
	}


}
