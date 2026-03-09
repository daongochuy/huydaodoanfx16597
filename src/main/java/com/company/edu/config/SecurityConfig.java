package com.company.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth ->
	 * auth.anyRequest().permitAll()); return http.build(); }
	 */

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getUserDetailsService());
		auth.setPasswordEncoder(getPasswordEncoder());
		return auth;
	}

	private final CustomAccessDeniedHandler accessDeniedHandler;

	public SecurityConfig(CustomAccessDeniedHandler accessDeniedHandler) {
		this.accessDeniedHandler = accessDeniedHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/signin", "/admin/user/login", "/admin/user/register",
								"/admin/user/createUser", "/admin/user/forgot-password",
								"/admin/user/reset-password/**", "/error", "/css/**", "/js/**", "/images/**")
						.permitAll()

						.requestMatchers("/admin/admin/**").hasRole("ADMIN")

						.requestMatchers("/admin/**").hasAnyRole("USER", "ADMIN")

						.anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/admin/signin").loginProcessingUrl("/admin/user/signin")
						.usernameParameter("email").passwordParameter("password")
						.defaultSuccessUrl("/admin/programs/", true).permitAll())
				.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
				.logout(logout -> logout.permitAll());

		return http.build();
	}

}
