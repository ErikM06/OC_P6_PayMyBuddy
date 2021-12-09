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

	/* @Autowired
	private DataSource dataSource; */

	
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
		.withUser("springuser").password(passwordEncoder().encode("spring123"))
		.roles("USER")
		.and()
		.withUser("springadmin").password(passwordEncoder().encode("admin123"))
		.roles("ADMIN", "USER");
}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasRole("USER")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
		    .and()
		    .oauth2Login();

		//http.csrf().ignoringAntMatchers("/admin");
		//http.headers().frameOptions().sameOrigin();
	}
	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
