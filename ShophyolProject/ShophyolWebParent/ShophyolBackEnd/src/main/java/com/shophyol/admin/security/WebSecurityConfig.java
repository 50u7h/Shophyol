package com.shophyol.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new ShophyolUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/users/**", "/settings/**", "/countries/**", "/states/**")
				.hasAuthority("Admin").requestMatchers("/categories/**", "/brands/**")
				.hasAnyAuthority("Admin", "Editor").requestMatchers("/products/new", "/products/delete/**")
				.hasAnyAuthority("Admin", "Editor")
				.requestMatchers("/products/edit/**", "/products/save", "/products/check_unique")
				.hasAnyAuthority("Admin", "Editor", "Salesperson")
				.requestMatchers("/products", "/products/", "/products/detail/**", "/products/page/**")
				.hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper").requestMatchers("/products/**")
				.hasAnyAuthority("Admin", "Editor").requestMatchers("/customers/**")
				.hasAnyAuthority("Admin", "Salesperson").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").usernameParameter("email").permitAll().and().logout().permitAll().and()
				.rememberMe();

		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
	}
}