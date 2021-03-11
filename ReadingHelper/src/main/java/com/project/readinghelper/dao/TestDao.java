package com.project.readinghelper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

	@Autowired
	TestMapper mapper;
}
