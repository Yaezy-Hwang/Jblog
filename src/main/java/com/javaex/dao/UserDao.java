package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void insertUser(UserVo userVo) {
		System.out.println("userDao.insertUser");
		
		sqlSession.insert("user.insert", userVo);
	}

	public int selectSameId(String id) {
		System.out.println("userDao.insertUser");
		
		return sqlSession.selectOne("user.selectSameId", id);
	}

	public UserVo selectUser(UserVo userVo) {
		System.out.println("userDao.selectUser");
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}

	

}
