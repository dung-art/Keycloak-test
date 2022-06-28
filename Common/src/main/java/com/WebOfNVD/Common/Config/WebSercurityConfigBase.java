package com.WebOfNVD.Common.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebSecurity
@Configuration
@EnableSwagger2
public class WebSercurityConfigBase extends WebSecurityConfigurerAdapter{

	public WebSercurityConfigBase() {
		super();
	}
	
//	cau hinh de co the vao duoc swagger voi moi user
//	@Override
//	public void configure(WebSecurity web) {
//
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "home").permitAll().anyRequest().authenticated().and().formLogin()
		.loginPage("/logins").permitAll().and().logout().permitAll();
}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
}
