package com.project.readinghelper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${custom.mainloc}")
	private String mainloc;
	
	@Value("${custom.defaultloc}")
	private String defaultloc;
	
	
	@Autowired
	private CustomAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN") // Admin 권한이 있는 경우 접근 허용
				.antMatchers("/user/**").hasAuthority("USER") // User 권한이 있는 경우 접근 허용
				.antMatchers(defaultloc,"/", "/loginRes", "/login/loginRes", "/signup", "/loginprocess","/signupprocess","/resources/**").permitAll() // 해당 URL은 전체 접근 허용
				//.anyRequest().authenticated() // 이외의 URL은 인증 절차를 수행하기 위해 login 페이지로 이동
				.and()
				.csrf().disable()
				.formLogin()        //form submit시 로그인 수행
				.loginPage(defaultloc)     //로그인페이지 경로
				.loginProcessingUrl("/loginRes")     //로그인form의 action경로와 일치시켜야 한다! -> AuthenticationProvider를 상속받은 클래스의 오버라이딩 받은 authenticate메서드 호출
				.failureUrl(defaultloc)    //로그인 실패시 해당 어노테이션 호출
				.defaultSuccessUrl(mainloc)    //로그인 성공시 해당 어노테이션 호출
				.usernameParameter("userId")    //파라미터
				.passwordParameter("userPw") // 파라미터
				.and()
				.logout()         //로그아웃 수행
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))   //로그아웃 버튼 클릭시 해당 어노테이션 호출
				.logoutSuccessUrl(defaultloc)   //로그아웃 성공시 해당 어노테이션 호출
				.invalidateHttpSession(true)
				.permitAll();      //전체 접근 허용
	}

}
