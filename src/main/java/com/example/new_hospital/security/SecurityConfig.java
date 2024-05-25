package com.example.new_hospital.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .formLogin() // Active le formulaire de login
                .and()
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()); // Exige une authentification pour toutes les requêtes

        return httpSecurity.build();
    }
}
