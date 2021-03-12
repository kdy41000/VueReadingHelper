package com.project.readinghelper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.readinghelper.domain.UserInfoEntity;

@Mapper
@Repository
public interface AdminMapper {

	List<UserInfoEntity> getUserList();
	
}
