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

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	// this set userdetailsService to match user's info and set a password with
	// encoder to match with the token from db

	// https://github.com/Yoh0xFF/java-spring-security-example/blob/master/src/main/java/io/example/configuration/security/SecurityConfig.java
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> userDetailsService.loadUserByUsername(username))
				// grant authorities here
				.passwordEncoder(passwordEncoder);
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("rootroot")).roles("ADMIN");

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/**").authenticated()
				.antMatchers("/", "/register").anonymous().and()
				.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login")
				.defaultSuccessUrl("/user/home").and().logout().invalidateHttpSession(true).permitAll()
				.logoutSuccessUrl("/");
		// .oauth2Login();
		// disabled csrf to permit post operation
		http.csrf().disable();
	}

}
