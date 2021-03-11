package com.project.readinghelper.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.readinghelper.domain.UserInfoEntity;

@Controller
@RequestMapping("/login")
public class LoginController {

	@PostMapping("/loginRes")
	@ResponseBody
	public void loginRes(UserInfoEntity param) {
		System.out.println("param:" + param);
	}
}
