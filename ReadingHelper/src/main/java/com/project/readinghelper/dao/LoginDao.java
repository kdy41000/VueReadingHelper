package com.project.readinghelper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;

@Repository
public class LoginDao {
	
	@Autowired
	LoginMapper mapper;

	public UserInfoEntity selectMember(String userId) {
		return mapper.selectMember(userId);
	}
	
	public String selectEncryptPass(String password) {
		return mapper.selectEncryptPass(password);
	}
}
