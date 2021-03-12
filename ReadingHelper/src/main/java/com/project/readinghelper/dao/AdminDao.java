package com.project.readinghelper.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;

@Repository
public class AdminDao {

	@Autowired
	AdminMapper mapper;
	
	public List<UserInfoEntity> getUserList() {
		return mapper.getUserList();
	}
}
