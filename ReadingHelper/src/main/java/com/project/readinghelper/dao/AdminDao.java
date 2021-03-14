package com.project.readinghelper.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;
import com.project.readinghelper.domain.UserInfoParam;

@Repository
public class AdminDao {

	@Autowired
	AdminMapper mapper;
	
	public List<UserInfoEntity> getUserList() {
		return mapper.getUserList();
	}
	
	public List<UserInfoEntity> getUserListFilter(UserInfoParam params) {
		return mapper.getUserListFilter(params);
	}
	
	public int updateUserInfo(UserInfoEntity params) {
		return mapper.updateUserInfo(params);
	}
	
}
