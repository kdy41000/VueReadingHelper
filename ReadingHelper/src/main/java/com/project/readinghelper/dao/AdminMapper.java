package com.project.readinghelper.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;
import com.project.readinghelper.domain.UserInfoParam;

@Mapper
@Repository
public interface AdminMapper {

	List<UserInfoEntity> getUserList();
	
	List<UserInfoEntity> getUserListFilter(UserInfoParam params);
	
}
