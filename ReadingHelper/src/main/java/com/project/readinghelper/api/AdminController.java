package com.project.readinghelper.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.readinghelper.domain.TestDomain;
import com.project.readinghelper.domain.UserInfoEntity;
import com.project.readinghelper.domain.UserInfoParam;
import com.project.readinghelper.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;

	@GetMapping("/getUserList")
	@ResponseBody
	public Map<String,Object> getUserList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserInfoEntity> list = service.getUserList();
		resultMap.put("result", list);
		return resultMap;
	}
	
	@GetMapping("/getUserListFilter")
	@ResponseBody
	public Map<String,Object> getUserListFilter(UserInfoParam params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("param:"+params);
		List<UserInfoEntity> list = service.getUserListFilter(params);
		System.out.println("list:"+list);
		resultMap.put("result", list);
		return resultMap;
	}
}
