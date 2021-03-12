package com.project.readinghelper.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.readinghelper.domain.SecurityUser;
import com.project.readinghelper.domain.UserInfoEntity;

@Controller
@RequestMapping("/login")
public class LoginController {

	@PostMapping("/loginRes")
	@ResponseBody
	public void loginRes(UserInfoEntity param) {
		System.out.println("param:" + param);
	}
	
	@GetMapping("/getAuth")
	@ResponseBody
	public Map<String, Object> getAuth(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		resultMap.put("userId", authentication.getPrincipal());
		resultMap.put("userAuth", authentication.getAuthorities());
		return resultMap;
		
	}
}
