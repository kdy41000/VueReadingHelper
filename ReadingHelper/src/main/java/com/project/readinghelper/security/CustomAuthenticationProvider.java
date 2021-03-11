package com.project.readinghelper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.readinghelper.service.LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
  
    @Autowired
    private LoginService loginService;
      
	//form이 submit되면 .loginProcessingUrl("/loginprocess") 에서 받아서 해당 메서드(authenticate)로 전달된다.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
          
        String username = authentication.getName();   //입력한 아이디
        String password = (String) authentication.getCredentials();   //입력한 패스워드
          System.out.println("===userInfo===");
          System.out.println(username);
          System.out.println(password);
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication; 
        UserDetails user = loginService.loadUserByUsername(authToken.getName());   //입력한 아이디의 사용자가 존재하는지 확인
        String encryptPass = loginService.selectEncryptPass(password);      //패스워드 암호화(mariadb PASSWORD함수 사용)

        
        //입력 받은 비밀번호와 DB에 저장된 비밀번호를 비교하여 인증 처리를 한다.
        if(!encryptPass.equals(user.getPassword())) {
						//로그인 실패 (.failureUrl("/login"))
            throw new BadCredentialsException("패스워드 불일치");
        }
      
        //토큰발급하여 로그인 성공처리를 한다. (.defaultSuccessUrl("/home"))
				//파라미터(username, password, auth)가 전달되어야 한다.
        return new UsernamePasswordAuthenticationToken(username, encryptPass, user.getAuthorities());
    }
  
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
