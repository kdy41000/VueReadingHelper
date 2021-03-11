package com.project.readinghelper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;

@Mapper
@Repository
public interface LoginMapper {

	UserInfoEntity selectMember(String userId);
	
	String selectEncryptPass(String password);
}
