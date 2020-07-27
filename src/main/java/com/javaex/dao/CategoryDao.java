package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void insertDefaultCate(String id) {
		System.out.println("categoryDao.insertDefaultCate");
		
		sqlSession.insert("category.insertDefaultCate", id);
	}

	//블로그 메인에서 리스트 보기
	public List<CategoryVo> selectCateList(String id) {
		System.out.println("categoryDao.selectCateList");
		
		return sqlSession.selectList("category.selectCateList", id);
	}
	
	public int nextSeq() {
		int nextSeq = sqlSession.selectOne("category.selectNextVal");
		
		System.out.println(nextSeq);
		
		return nextSeq;
	}
	
	public void insertCategory(CategoryVo categoryVo) {
		System.out.println("categoryDao.insertCategory");
		
		int count = sqlSession.insert("category.insertCategory", categoryVo);
		System.out.println(count+"개 등록");
	}

	public CategoryVo selectNewCate(int nextSeq) {
		System.out.println("categoryDao.selectNewCate");
		
		CategoryVo vo = sqlSession.selectOne("category.selectNewCate", nextSeq);
		
		System.out.println(vo);
		
		return vo;
	}
	
	public List<CategoryVo> selectAdminList(String id) {
		System.out.println("categoryDao.selectCateList");
		
		return sqlSession.selectList("category.selectAdminList", id);
	}

	public int deleteCategory(int cateNo) {
		System.out.println("categoryDao.deleteCategory");
		
		return sqlSession.delete("category.deleteCategory", cateNo);
	}
}
