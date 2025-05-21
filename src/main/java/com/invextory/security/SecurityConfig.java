package com.invextory.security;

import com.invextory.exceptions.CustomAccessDeniedHandler;
import com.invextory.exceptions.CustomAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final AuthFilter authFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(AuthFilter authFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint, CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.authFilter = authFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        logger.info("SecurityConfig initialized with custom auth filter and exception handlers.");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring SecurityFilterChain...");

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .exceptionHandling(exception -> {
                    logger.info("Setting custom authentication entry point and access denied handler.");
                    exception
                            .accessDeniedHandler(customAccessDeniedHandler)
                            .authenticationEntryPoint(customAuthenticationEntryPoint);
                })
                .authorizeHttpRequests(request -> {
                    logger.info("Configuring request authorization: permitting /api/auth/** and securing all other endpoints.");
                    request
                            .requestMatchers("/api/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                .sessionManagement(manager -> {
                    logger.info("Setting session management policy to STATELESS.");
                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("SecurityFilterChain configuration complete.");
        return http.build();
    }

}
