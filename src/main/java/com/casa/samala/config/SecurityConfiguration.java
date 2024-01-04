package com.casa.samala.config;

import com.casa.samala.filter.JWTTokenGeneratorFilter;
import com.casa.samala.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: SecurityFilterChain.java, v 0.1 2023-12-29  22.44 Ahmad Isyfalana Amin Exp $
 */
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // CORS Config
            .cors(Customizer.withDefaults()) // Enable CORS

            // CSRF Config
            .csrf(csrf -> csrf.disable())    // Disable CSRF

            // Session Config
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })

            .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)

            // Authentication Config
            .authorizeHttpRequests(request -> {
                request.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults())
            .headers(headers -> {
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
            });


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}