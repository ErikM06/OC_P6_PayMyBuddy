package com.PayMyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.PayMyBuddy.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userDetailsService.loadUserByUsername(username))
				// grant authorities here
				.passwordEncoder(passwordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/register").anonymous().antMatchers("/", "/register", "/css/**")
				.permitAll().antMatchers("/user/**").authenticated().antMatchers("/admin/**").hasRole("ADMIN").and()
				.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login")
				.defaultSuccessUrl("/user/home").and().logout().invalidateHttpSession(true).permitAll()
				.logoutSuccessUrl("/");
		http.csrf().disable();
	}

}
