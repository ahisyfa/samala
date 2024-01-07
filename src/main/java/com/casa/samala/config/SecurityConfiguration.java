package com.casa.samala.config;

import com.casa.samala.filter.JWTTokenGeneratorFilter;
import com.casa.samala.filter.JWTTokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: SecurityFilterChain.java, v 0.1 2023-12-29  22.44 Ahmad Isyfalana Amin Exp $
 */
@Configuration
public class SecurityConfiguration {

    @Autowired
    private SamalaConfigProperties samalaConfigProperties;

    private static final String[] AUTH_WHITE_LIST = {
            "/",
            "/index.html",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/csrf",
            "/webjars/springfox-swagger-ui/**",
            "/swagger-resources/**"
    };

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

            .addFilterBefore(new JWTTokenValidatorFilter(samalaConfigProperties.getJwtSecretKey()), BasicAuthenticationFilter.class)
            .addFilterAfter(new JWTTokenGeneratorFilter(samalaConfigProperties.getJwtSecretKey()), BasicAuthenticationFilter.class)

            // Authentication Config
            .authorizeHttpRequests(request -> {
                request.requestMatchers(AUTH_WHITE_LIST).permitAll();

                request.requestMatchers("/error").permitAll();
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
        return (web) -> web.ignoring().requestMatchers(AUTH_WHITE_LIST);
    }

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}