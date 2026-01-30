package com.shirt.pod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF cho REST API
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép truy cập Product APIs mà không cần authentication
                        // Sử dụng nhiều pattern để đảm bảo match đúng
                        .requestMatchers("/api/v1/products", "/api/v1/products/**").permitAll()
                        // Cho phép truy cập Swagger UI
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/api-docs/**", "/v3/api-docs/**").permitAll()
                        // Cho phép truy cập các endpoints khác (có thể thêm sau)
                        .requestMatchers("/api/v1/users/**").permitAll() // Tạm thời permit all cho User APIs
                        // Tất cả các request khác cần authentication
                        .anyRequest().permitAll() // Tạm thời permit all để debug
                )
                // Disable form login (không cần login page cho REST API)
                .formLogin(AbstractHttpConfigurer::disable)
                // Disable HTTP Basic authentication
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Cho phép tất cả origins (có thể giới hạn sau)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false); // Không cần credentials cho public APIs
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
