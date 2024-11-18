package com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig;

import com.jbohorquez.microservices_users.infrastructure.adapters.securityconfig.jwtconfiguration.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.jbohorquez.microservices_users.constants.ValidationConstants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ConfigFilter {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of(HTTP));
                    config.setAllowedMethods(List.of(ALLOWED_METHODS));
                    config.setAllowedHeaders(List.of(ALL));
                    config.setAllowCredentials(true);
                    return config;
                })
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(V3_API).permitAll()
                .antMatchers(AUTH).permitAll()
                .antMatchers(SWAGGER_UI).permitAll()
                .antMatchers(SWAGGER_UI_RESOURCES).permitAll()
                .antMatchers(ALL_API).authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}