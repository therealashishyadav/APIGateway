package com.ApiGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Exact origins
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("https://cribup.vercel.app");
        config.addAllowedOrigin("https://nookly-frontend-hslj.vercel.app");

        // Wildcard — covers ALL Vercel preview URLs automatically forever
        config.addAllowedOriginPattern("https://nookly-*.vercel.app");
        config.addAllowedOriginPattern("https://cribup-*.vercel.app");
        config.addAllowedOriginPattern("https://*-therealashishyadav1.vercel.app");
        config.addAllowedOriginPattern("nookly-git-main-therealashishyadav1.vercel.app");
        config.addAllowedOriginPattern("nookly-4kojk0tjf-therealashishyadav1.vercel.app");
        
//        
//        

        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}