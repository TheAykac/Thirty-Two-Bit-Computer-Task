package com.example.thirtyTwoBit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.thirtyTwoBit.core.utilities.messages.BusinessMessages;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SecurityConfing {
	
	
	@Configuration
	public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception { // user types configured
			auth.inMemoryAuthentication().withUser("user").password("12345").roles("USER").and().withUser("admin") // usernames and passwords
					.password("12345").roles("USER", "ADMIN");

		}

		@Override
		protected void configure(HttpSecurity http) throws Exception { //  users access is configured
			
				http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/customer/**").hasRole("ADMIN")
						.antMatchers(HttpMethod.POST, "/api/customer/**").hasRole("USER")
						.antMatchers(HttpMethod.DELETE, "/api/customer/**").hasRole("ADMIN").and().csrf().disable()
						.formLogin();
				log.warn(BusinessMessages.LogMessages.SYSTEM_STARTED); // Logging
			

		}

	}
}
