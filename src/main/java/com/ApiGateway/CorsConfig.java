package com.ApiGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

// ── FILE LOCATION ─────────────────────────────────────────────────────────────
// src/main/java/com/ApiGateway/CorsConfig.java
// ─────────────────────────────────────────────────────────────────────────────
// This is the ONLY place CORS is configured for the entire backend.
// All microservices (Inquiry, Account, Add PG) have NO cors config of their own.
// The API Gateway handles all CORS for every service behind it.
// ─────────────────────────────────────────────────────────────────────────────

@Configuration
public class CorsConfig {

	@Bean
	public CorsWebFilter corsWebFilter() {

		CorsConfiguration config = new CorsConfiguration();

		// ── Exact origins ─────────────────────────────────────────────────────
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedOrigin("https://nookly-frontend-hslj.vercel.app");
		config.addAllowedOrigin("https://cribup.vercel.app");

		config.addAllowedOriginPattern("https://nookly-frontend-hslj-*.vercel.app");
		config.addAllowedOriginPattern("https://cribup-*.vercel.app");

		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		config.addAllowedMethod("OPTIONS");

		config.addAllowedHeader("*");

		config.setAllowCredentials(true);

		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}
}