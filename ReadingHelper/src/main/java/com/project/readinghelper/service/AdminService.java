package com.project.readinghelper.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.readinghelper.dao.AdminDao;
import com.project.readinghelper.domain.UserInfoEntity;
import com.project.readinghelper.domain.UserInfoParam;

@Service
public class AdminService {

	@Autowired
	AdminDao dao;
	
	public List<UserInfoEntity> getUserList() {
		return dao.getUserList();
	}
	
	public List<UserInfoEntity> getUserListFilter(UserInfoParam params) {
		return dao.getUserListFilter(params);
	}
}
