package com.project.readinghelper.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.readinghelper.dao.LoginDao;
import com.project.readinghelper.domain.SecurityUser;
import com.project.readinghelper.domain.UserInfoEntity;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private LoginDao dao;

	private Logger LOG = LoggerFactory.getLogger(LoginService.class);

	@Override // UserDetailsService를 implement받은 클래스의 오버라이드 메서드(사용자가 존재하는지 확인하는 역할)
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("userId:" + userId);
		// 회원 아이디로 회원이 존재하는지 조회 한다.
		UserInfoEntity dto = dao.selectMember(userId);
		System.out.println("member:" + dto);

		SecurityUser securityUser = new SecurityUser();

		if (dto == null) {
			LOG.info("## 계정정보가 존재하지 않습니다. ##");
			throw new UsernameNotFoundException(userId);
		} else {
			securityUser.setName(dto.getUserName());
			securityUser.setUsername(dto.getUserId());
			securityUser.setPassword(dto.getUserPw());

			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(dto.getUserAuth()));

			securityUser.setAuthorities(authorities);
		}

		return securityUser;
	}
	
	public String selectEncryptPass(String password) {
		return dao.selectEncryptPass(password);
	} 
}
