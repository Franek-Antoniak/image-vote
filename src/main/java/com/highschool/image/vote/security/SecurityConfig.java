package com.highschool.image.vote.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("admin")
		    .password(passwordEncoder().encode("pass"))
		    .roles("ADMIN");
	}


	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
		   .antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf()
		    .disable()
		    .requiresChannel()
		    .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
		    .requiresSecure()
		    .and()
		    .authorizeRequests()
		    .antMatchers("/admin/**")
		    .hasRole("ADMIN")
		    .antMatchers("/login")
		    .permitAll()
		    .antMatchers("/observer/**")
		    .hasAnyRole("ADMIN", "OBSERVER")
		    .antMatchers("/user/**")
		    .hasAnyRole("ADMIN", "USER")
		    .anyRequest()
		    .authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    .loginProcessingUrl("/perform_login")
		    .defaultSuccessUrl("/", true)
		    .failureUrl("/login")
		    .and()
		    .oauth2Login()
		    .loginPage("/login")
		    .defaultSuccessUrl("/", true)
		    .failureUrl("/login")
		    .and()
		    .logout()
		    .logoutUrl("/perform_logout")
		    .deleteCookies("JSESSIONID");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}


}
