package com.PayMyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.PayMyBuddy.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	// setter to avoid circulare references error


	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	// this set userdetailsService to match user's info and set a password with encoder to match with the token from db
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
				.antMatchers( "/","/register").anonymous()
				//.antMatchers("/secure/*").hasAnyAuthority("ADMIN").anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/user/home")
				.and()
				.logout().invalidateHttpSession(true)
				.permitAll()
				.logoutSuccessUrl("/index");
		// .oauth2Login();
		// disabled csrf to permit post operation
		http.csrf().disable();
	}

	
}
