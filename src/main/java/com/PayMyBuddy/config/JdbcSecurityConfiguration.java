package com.PayMyBuddy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(JdbcSecurityConfiguration.class);

	// @Autowired
//	private DataSource dataSource; 

	
	/* @Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password FROM User WHERE username = ?")
			.authoritiesByUsernameQuery("SELECT u.username, a.authorities"
				+ " FROM User u, user_authorities a "
				+ "WHERE u.username = ?"
				+ "AND u.id = a.user_id");
		logger.info("authinf is" + auth);
	} */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("PayMyBuddyUser").password(passwordEncoder().encode("PMB"))
		.roles("USER")
		.and()
		.withUser("root").password(passwordEncoder().encode("rootroot"))
		.roles("ADMIN", "USER");
}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/login*", "/", "/users/register").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
		/*	.loginPage("/login")
			.loginProcessingUrl("/perform_login")
		      .defaultSuccessUrl("/homepage.html", true)
		      .failureUrl("/login.html?error=true")
		      .failureHandler(authenticationFailureHandler())
		      .and()
		      .logout()
		      .logoutUrl("/perform_logout")
		      .deleteCookies("JSESSIONID")
		      .logoutSuccessHandler(logoutSuccessHandler()) */
		    .and()
		    .oauth2Login();
		//disabled csrf to permit post operation
		http.csrf().disable();

	
	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
