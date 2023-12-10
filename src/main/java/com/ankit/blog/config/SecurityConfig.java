package com.ankit.blog.config;


import java.util.Arrays;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ankit.blog.security.CustomUserDetailService;
import com.ankit.blog.security.JwtAuthenticationEntryPoint;
import com.ankit.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
		
	
	 public static final String[] PUBLIC_URLS = {"/api/v1/auth/**", "/v3/api-docs", "/v2/api-docs",
	            "/swagger-resources/**", "/swagger-ui/**", "/webjars/**"};
	
		@Autowired
		private CustomUserDetailService customUserDetailService;
		
		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
		@Autowired
		private JwtAuthenticationFilter jwtAuthenticationFilter;
		
		//@SuppressWarnings("removal")
		@Bean
		public SecurityFilterChain securityFiltrChain(HttpSecurity http) throws Exception{
			
			http
			.csrf((csrf)->csrf.disable())
			.authorizeHttpRequests((auth)->auth.requestMatchers(HttpMethod.GET).permitAll().requestMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated())
			.exceptionHandling(ex->ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
			http.authenticationProvider(daoAuthenticationProvider());
			
			DefaultSecurityFilterChain defatltSecurityFilterChain = http.build();			
			return defatltSecurityFilterChain;
			
			 
		}
		
		@Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
			return null;
	        
	    }
		
		
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
			
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		
		
		@Bean
		public AuthenticationManager authenticationManagerBean (AuthenticationConfiguration configuration) throws Exception {
			return configuration.getAuthenticationManager();
		}
		
		@Bean
		public DaoAuthenticationProvider daoAuthenticationProvider() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(this.customUserDetailService);
			provider.setPasswordEncoder(passwordEncoder());
			return provider;
			
			
		}
		
		
		 
//		@Bean
//	    public FilterRegistrationBean corsFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//	        CorsConfiguration corsConfiguration = new CorsConfiguration();
//	        corsConfiguration.setAllowCredentials(true);
//	        corsConfiguration.addAllowedOriginPattern("*");
//	        corsConfiguration.addAllowedHeader("Authorization");
//	        corsConfiguration.addAllowedHeader("Content-Type");
//	        corsConfiguration.addAllowedHeader("Accept");
//	        corsConfiguration.addAllowedMethod("POST");
//	        corsConfiguration.addAllowedMethod("GET");
//	        corsConfiguration.addAllowedMethod("DELETE");
//	        corsConfiguration.addAllowedMethod("PUT");
//	        corsConfiguration.addAllowedMethod("OPTIONS");
//	        corsConfiguration.setMaxAge(3600L);
//
//	        source.registerCorsConfiguration("/**", corsConfiguration);
//
//	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//
//	        bean.setOrder(-110);
//
//	        return bean;
//	    }

		
		
		
		
		
//		 @Bean
//		    public CorsConfigurationSource corsConfigurationSource() {
//			 
//			 CorsConfiguration corsConfiguration = new CorsConfiguration();
//		        corsConfiguration.setAllowCredentials(true);
//		        corsConfiguration.addAllowedOriginPattern("*");
//		        corsConfiguration.addAllowedHeader("Authorization");
//		        corsConfiguration.addAllowedHeader("Content-Type");
//		        corsConfiguration.addAllowedHeader("Accept");
//		        corsConfiguration.addAllowedMethod("POST");
//		        corsConfiguration.addAllowedMethod("GET");
//		        corsConfiguration.addAllowedMethod("DELETE");
//		        corsConfiguration.addAllowedMethod("PUT");
//		        corsConfiguration.addAllowedMethod("OPTIONS");
//		        corsConfiguration.setMaxAge(3600L);
//
//		        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		        source.registerCorsConfiguration("/**", corsConfiguration);
//
//		        return (CorsConfigurationSource) source;
//		    }
		
} 
	

