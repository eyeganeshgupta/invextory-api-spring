package com.invextory.security;

import com.invextory.exceptions.CustomAccessDeniedHandler;
import com.invextory.exceptions.CustomAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        logger.info("SecurityConfig initialized with custom authentication filter and exception handlers.");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Starting configuration of SecurityFilterChain...");

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .exceptionHandling(exception -> {
                    logger.info("Applying custom access denied handler and authentication entry point.");
                    exception
                            .accessDeniedHandler(customAccessDeniedHandler)
                            .authenticationEntryPoint(customAuthenticationEntryPoint);
                })
                .authorizeHttpRequests(request -> {
                    logger.info("Setting authorization rules: permitting '/api/auth/**', securing all other endpoints.");
                    request
                            .requestMatchers("/api/auth/**").permitAll()
                            .anyRequest().authenticated();
                })
                .sessionManagement(manager -> {
                    logger.info("Applying stateless session management policy.");
                    manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        SecurityFilterChain filterChain = http.build();
        logger.info("SecurityFilterChain instance created: {}", filterChain.getClass().getName());

        return filterChain;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating BCryptPasswordEncoder bean.");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        logger.info("Retrieving AuthenticationManager bean from AuthenticationConfiguration.");
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        logger.info("AuthenticationManager instance created: {}", authenticationManager.getClass().getName());
        return authenticationManager;
    }

}
